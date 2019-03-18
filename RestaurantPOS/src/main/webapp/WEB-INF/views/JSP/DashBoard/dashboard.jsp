<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>DashBoard</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Bootstrap Core CSS -->
<link rel="stylesheet"	href="<c:url value="/css/bootstrap.min.css"/>">
<!-- Custom CSS -->
<link rel="stylesheet"	href="<c:url value="/css/style.css"/>">
<!-- Graph CSS -->
<link rel="stylesheet"	href="<c:url value="/css/font-awesome.css"/>">
<!-- jQuery -->
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- lined-icons -->
<link rel="stylesheet"	href="<c:url value="/css/icon-font.min.css"/>">
<!-- //lined-icons -->
<script src="<c:url value="/js/amcharts.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/serial.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/scripts.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/jquery.nicescroll.js"/>" type="text/javascript"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/js/bootstrap.min.js"/>" type="text/javascript"></script>
<!-- /Bootstrap Core JavaScript -->
<script src="<c:url value="/js/menu_jquery.js"/>" type="text/javascript"></script>
<!--pie-chart--->
<script src="<c:url value="/js/pie-chart.js"/>" type="text/javascript"></script>


<script type="text/javascript">
	$(document).ready(
			function() {
				var toggle = true;
				$(".sidebar-icon").click(
						function() {
							if (toggle) {
								$(".page-container").addClass(
										"sidebar-collapsed").removeClass(
										"sidebar-collapsed-back");
								$("#menu span").css({
									"position" : "absolute"
								});
							} else {
								$(".page-container").removeClass(
										"sidebar-collapsed").addClass(
										"sidebar-collapsed-back");
								setTimeout(function() {
									$("#menu span").css({
										"position" : "relative"
									});
								}, 400);
							}

							toggle = !toggle;
						});

			});
	function ShowTime() {
		var NowDate = new Date();
		var d = NowDate.getDay();
		var dayNames = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
				"星期六");
		document.getElementById('showbox').innerHTML = '目前時間：'
				+ NowDate.toLocaleString() + '（' + dayNames[d] + '）';
		setTimeout('ShowTime()', 1000);
	}
</script>
</head>
<body onload="ShowTime()">
	<div class="page-container">
		<!--/content-inner-->
		<div class="left-content">
			<div class="inner-content">
			
				<!-- header-starts -->
				<c:import url="dashHeader.jsp"/>
				<!-- //header-ends -->

				<!--content-->
				<div class="content">
					<!--//area 百分比視圖開始-->
					<div class="col-md-5 skil">
						<div class="content-top-1">
							<div class="col-md-6 top-content">
								<h5>目標</h5>
								<label>8761</label>
								
							</div>
							<div class="col-md-6 top-content1">
								<div id="demo-pie-1" class="pie-title-center" data-percent="30">
									<span class="pie-value">25%</span>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
					<!--//area 百分比視圖結束-->
					<div class="clearfix"></div>
					
					<!-- footer -->
						<c:import url="dashFooter.jsp"/>
					<!-- footer -->
				</div>
				<!--content-->
			</div>
		</div>
		<!--//content-inner-->
		
				<!--sidebar-->
					<c:import url="dashSideBar.jsp"/>
				<!--sidebar-->
		<div class="clearfix"></div>
	</div>



</body>
</html>