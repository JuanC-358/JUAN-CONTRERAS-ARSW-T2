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
 * 
 */
public class MyThread extends Thread{

    private int ini;
    private int fin;
    private List<File> resultFiles;
    private ResultAnalyzer resultAnalyzer;
    private AtomicInteger amountOfFilesProcessed;
    private TestReader testReader;
    private boolean pause;
    

    public MyThread(List<File> rFiles, ResultAnalyzer rAnalyzer, AtomicInteger amountOfFilesProcessed, TestReader testReader, int x, int y){
        this.resultFiles = rFiles;
        this.resultAnalyzer = rAnalyzer;
        this.amountOfFilesProcessed = amountOfFilesProcessed;
        this.testReader = testReader;
        this.pause = false;
        this.ini = x;
        this.fin = y;
    }

    /**
     *  en pausa los threads esperaran, si no, se empezaran a añadir  resultados
     */
    public void run(){
        for (int i=ini;i<=fin;i++){
            synchronized (this) {
                while (pause){
                    try{
                        wait();
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }

            List<Result> results = testReader.readResultsFromFile(file);

            for (Result result : results){
                resultAnalyzer.addResult(result);
            }
            amountOfFilesProcessed.incrementAndGet();

        }
    }

    /**
     * Si se pausan los thread, 
     */
    public void pauseThread(){
        pause = true;
    }

    /**
     * Se reanudaran todos los threads
     */
    public void resmueThread(){
        pause = false;
        synchronized (this){
            notifyAll();
        }
    }

}