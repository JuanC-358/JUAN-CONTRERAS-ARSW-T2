package eci.arsw.covidanalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Camel Application
 */
public class CovidAnalyzerTool implements Runnable{{

    private ResultAnalyzer resultAnalyzer;
    private TestReader testReader;
    private int amountOfFilesTotal;
    private AtomicInteger amountOfFilesProcessed;
    private int hilos = 5;
    private ConcurrentLinkedDeque <ProcessThread> hilosDeque;

    public CovidAnalyzerTool() {
        resultAnalyzer = new ResultAnalyzer();
        testReader = new TestReader();
        amountOfFilesProcessed = new AtomicInteger();
        hilosDeque = new ConcurrentLinkedDeque<>();
    }

    public void processResultData() {
        amountOfFilesProcessed.set(0);
        List<File> resultFiles = getResultFileList();
        amountOfFilesTotal = resultFiles.size();
        int intervalo =amountOfFilesTotal/hilos;
        for (int i = 0; i <= hilos-1; i++){
            if (i == hilos-1){
                hilosDeque.add(new MyThread(resultFiles, resultAnalyzer, amountOfFilesProcessed, testReader, i*intervalo, amountOfFilesTotal-1));
            } else{
                hilosDeque.add(new MyThread(resultFiles, resultAnalyzer, amountOfFilesProcessed, testReader, i*intervalo, (i*intervalo)+intervalo-1));
            }
            hilosDeque.getLast().start();
        }
        for (File resultFile : resultFiles) {
            List<Result> results = testReader.readResultsFromFile(resultFile);
            for (Result result : results) {
                resultAnalyzer.addResult(result);
            }
            amountOfFilesProcessed.incrementAndGet();
        }
    }

    private List<File> getResultFileList() {
        List<File> csvFiles = new ArrayList<>();
        try (Stream<Path> csvFilePaths = Files.walk(Paths.get("src/main/resources/")).filter(path -> path.getFileName().toString().endsWith(".csv"))) {
            csvFiles = csvFilePaths.map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFiles;
    }


    public Set<Result> getPositivePeople() {
        return resultAnalyzer.listOfPositivePeople();
    }
    public void answer(){
        String message = "Processed %d out of %d files.\nFound %d positive people:\n%s";
        Set<Result> positivePeople = getPositivePeople();
        String affectedPeople = positivePeople.stream().map(Result::toString).reduce("", (s1, s2) -> s1 + "\n" + s2);
        message = String.format(message, amountOfFilesProcessed.get(), amountOfFilesTotal, positivePeople.size(), affectedPeople);
        System.out.println(message);
    }
    public void resumeThread(){
        for (ProcessThread thread : hilosDeque){
            thread.resume();
        }
    }

    /**
     * Pausa los threads iniciados cambiando la variable pause a true
     */
    public void pauseThread(){
        pause = true;
        for (ProcessThread thread : hilosDeque){
            thread.pauseThread();
        }
        try{
            Thread.sleep(200);
        } catch (InterrumpedException e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo el cual pausa o continua los threads si se oprime una tecla
     */
    public void run(){
        Scanner scanner;
        Thread thread = new Thread(() -> MyThread());
        thread.start();

        while (amountOfFilesTotal == -1 || amountOfFilesProcessed.get()<amountOfFilesTotal){
            scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.contains("exit")) {
                break;
            } else if (line.isEmpty()){
                if (pause){
                    resumeThread();
                }else {
                    pauseThread();
                }
            }else if (!pause && !line.isEmpty()){
                showMessage();
            }
        }
    }

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Thread thread = new Thread(new CovidAnalyzerTool());
        thread.start();
    }

}

