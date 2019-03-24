<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel='stylesheet' href="<c:url value="/css/DashBoard.css"/>">
<title>後台首頁</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>


    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
       function drawChart() {
          var Sdata = [['Year', '營業額(元)']];

    	<c:forEach var="CTB"  varStatus="loop" items="${ctbList}">
    	 Sdata.push(['${CTB.date}',${CTB.turnover}]);		
		</c:forEach>	
		
        var data = google.visualization.arrayToDataTable(Sdata);

        var options = {
          title: '日營業額',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
       }
    </script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      
      var cumulativeTurnover = 0;
      var target = ${TTBean.targetTurnover};
      
      <c:forEach var="CTB"  varStatus="loop" items="${ctbList}">
      	<c:if test="${loop.last}">
      	   cumulativeTurnover = ${CTB.cumulativeTurnover};      	   
      	</c:if>
	  </c:forEach>
	 	 
	  
	  
      function drawChart() {
				
		var Remaining = target-cumulativeTurnover;
		var per = ((cumulativeTurnover/target*100).toFixed())+"%";		
        var data = google.visualization.arrayToDataTable([
          ['Pac Man', 'Percentage'],
          ['', cumulativeTurnover],
          ['', Remaining]
        ]);

        var options = {
         titleTextStyle:{
        	 fontSize: 16,
        	 bold: true
        		},
          title: '目標達成率',
		  height: 360,
          width: 360,
		  pieHole: 0.9,
          legend: 'none',
          pieSliceText: 'none',
          pieStartAngle: 360,
          tooltip: { trigger: 'none' },
          slices: {
            0: { color: '#00BBFF' },
            1: { color: 'transparent' }
          }
        };

        var chart = new google.visualization.PieChart(document.getElementById('pacman'));
        chart.draw(data, options);
		$('#cnt').text(per);
      }
	</script>
	

</head>

<body >
		<div  class="clearfix">
			<jsp:include page="headerTime.jsp" flush="true" />		
			<jsp:include page="sideBar.jsp" flush="true" />
		</div>
		
		<div class="row col-lg-9" id="rowOne" >
		 <div class="col-lg-12 col-xlg-12 col-md-12">
		  <div class="card">
		   <div class="card-block">
		   	<div class="row ">
		   	<div class="col-md-4">
		   	<div id="pacman"></div>
		   	<div id="cnt" class="overlay"></div>
		   	</div>		

			</div>
			</div>
			<div class="row ">
				<div class="col-md-12" id="curve_chart">
				</div>
			</div>
		   </div>
		  </div>
		 </div>
		
		<jsp:include page="footer.jsp" flush="true"/>
	
</body>
</html>
