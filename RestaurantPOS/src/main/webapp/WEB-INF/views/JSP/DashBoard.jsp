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
<title>後台首頁</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<style>
#rowOne{
  margin-left: 300px;
  background: url('/RestaurantPOS/images/DashBoardLogo.jpg')no-repeat;
  background-size:cover;
  -webkit-background-size:cover;
  -moz-background-size:cover;
  -o-background-size:cover;
  -ms-background-size:cover;
  min-height:700px;
  filter:Alpha(opacity=20);
}

</style>

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
</head>

<body >
		<div  class="clearfix">
			<jsp:include page="headerTime.jsp" flush="true" />		
			<jsp:include page="sideBar.jsp" flush="true" />
		</div>
		
		<div class="row col-lg-9" id="rowOne" >
		 <div class="col-lg-8 col-xlg-8 col-md-8">
		  <div class="card">
		   <div class="card-block">
		   	<div class="row">
			<div class="col-8">
			<!-- 內容放這裡開始 -->	
				<div id="curve_chart" style="width: 800px; height: 400px;margin-top: 20px;"></div>
				

			<!-- 內容放這裡結束1 -->
			</div>
			</div>
		   </div>
		  </div>
		 </div>
		</div>
		<jsp:include page="footer.jsp" flush="true"/>
	
</body>
</html>
