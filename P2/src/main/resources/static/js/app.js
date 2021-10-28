function Load(){
    const settings = {
	"async": true,
	"crossDomain": true,
	"url": "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=Canada",
	"method": "GET",
	"headers": {
		"x-rapidapi-host": "covid-19-coronavirus-statistics.p.rapidapi.com",
		"x-rapidapi-key": "4e9d34e50fmshe7e9fd5d34de6fap19b8c9jsnba923529e479"
	}
    };

    $.ajax(settings).done(function (response) {
            console.log(response);
    });
}
