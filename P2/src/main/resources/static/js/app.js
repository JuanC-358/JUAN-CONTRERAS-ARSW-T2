function Load(){
    window.alert("Se demora en cargar la tabla completa, al hacer click en una fila busca por pais");
    const settings = {
	"async": true,
	"crossDomain": true,
	"url": "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats",
	"method": "GET",
	"headers": {
		"x-rapidapi-host": "covid-19-coronavirus-statistics.p.rapidapi.com",
		"x-rapidapi-key": "4e9d34e50fmshe7e9fd5d34de6fap19b8c9jsnba923529e479"
	}
    };

    $.ajax(settings).done(function (response) {
        for(var i = 0;i<response.data.covid19Stats.length;i++)
        {
            var pais=response.data.covid19Stats[i].country ;
            var tblRow = "<tr onclick='myFunction("+pais+")'>"+ "<td>" + response.data.covid19Stats[i].country + "</td>" +
                 "<td>" + response.data.covid19Stats[i].deaths + "</td>" + "<td>" + response.data.covid19Stats[i].confirmed+ "</td>" + "<td>" + response.data.covid19Stats[i].recovered + "</td>" + "</tr>"
                 $(tblRow).appendTo("#userdata tbody");
        }
            console.log(response);
    });
}

function myFunction(x) {
    window.alert(x);
  const settings = {
	"async": true,
	"crossDomain": true,
	"url": "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country="+x,
	"method": "GET",
	"headers": {
		"x-rapidapi-host": "covid-19-coronavirus-statistics.p.rapidapi.com",
		"x-rapidapi-key": "4e9d34e50fmshe7e9fd5d34de6fap19b8c9jsnba923529e479"
	}
    };

    $.ajax(settings).done(function (response) {
        for(var i = 0;i<response.data.covid19Stats.length;i++)
        {
            //console.log(str.person[i].id);
            var tblRow = "<tr>" + "<td>" + response.data.covid19Stats[i].city + "</td>" +
                 "<td>" + response.data.covid19Stats[i].deaths + "</td>" + "<td>" + response.data.covid19Stats[i].confirmed+ "</td>" + "<td>" + response.data.covid19Stats[i].recovered + "</td>" + "</tr>"
                 $(tblRow).appendTo("#dataByCountry tbody");
        }
            console.log(response);
    });
}