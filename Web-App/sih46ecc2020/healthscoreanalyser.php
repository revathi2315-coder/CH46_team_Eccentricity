<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/social.css">
<script type="text/javascript" src="script/canvasjs.min.js"></script>
<script type="text/javascript">

  function generatepiechart(agegroup){
	  var datapoints = csvLines = points = [];
	  var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200)
			{
					if(this.responseText != null)
					{
					 	datainput = this.responseText;
						datainput =  datainput.substring(0, datainput.length - 1);
						csvLines = datainput.split(":");
							
						for (var i = 0; i < csvLines.length; i++)
							if (csvLines[i].length > 0) {
								points = csvLines[i].split(",");
								datapoints.push({ y: (points[0]),name:(points[1])});
							}
						var chart = new CanvasJS.Chart("chartContainer", {
						exportEnabled: true,
						animationEnabled: true,
						title:{
							text: "Health Score based on Individuals of Selected Age Group"
						},
						legend:{
							cursor: "pointer",
							itemclick: explodePie
						},
						data: [{
							type: "pie",
							showInLegend: true,
							toolTipContent: "{name}",
							indexLabel: "{name}",
							dataPoints:  datapoints
									
							}]
						});
					chart.render();	
							
					}
					else
					{
						document.getElementById("chartContainer").innerHTML = this.responseText;
					}
		   }
		};
    xmlhttp.open("GET", "piechartdata.php?useragegroup="+agegroup, true);
    xmlhttp.send();
	  
  }

function explodePie (e) {
	if(typeof (e.dataSeries.dataPoints[e.dataPointIndex].exploded) === "undefined" || !e.dataSeries.dataPoints[e.dataPointIndex].exploded) {
		e.dataSeries.dataPoints[e.dataPointIndex].exploded = true;
	} else {
		e.dataSeries.dataPoints[e.dataPointIndex].exploded = false;
	}
	e.chart.render();

}
</script>
 </head>
    <body class="image">
	<div class="containerpie">
	<div id="heading" class="h2"> HEALTH SCORE ANALYSER </div>
	<label for="cars">Choose an Age Group:</label>

<select name="AgeGroup" id="AgeGroup" onchange="generatepiechart(this.value);">
  <option value="0">Please Select Age Group</option>
  <option value="0-25">0 - 25 Years</option>
  <option value="25-45">25 - 45 Years</option>
  <option value="45-100">45 - 100 Years</option>
  </select>
	<div id="chartContainer" style="height: 370px; max-width: 920px; margin: 0px auto;"></div>
     <div class="footer">
    <p>SIH CH46 ECCENTRICITY © 2020. All rights reserved.</p>
      </div>
        </div>
     
</body>
</html>