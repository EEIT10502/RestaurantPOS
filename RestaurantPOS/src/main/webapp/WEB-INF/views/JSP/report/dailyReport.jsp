<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>DashBoard</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- Bootstrap Core CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
<!-- Custom CSS -->
<link rel="stylesheet" href="<c:url value="/css/style.css"/>">
<!-- Graph CSS -->
<link rel="stylesheet" href="<c:url value="/css/font-awesome.css"/>">
<!-- jQuery -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script> -->
<!-- lined-icons -->
<link rel="stylesheet" href="<c:url value="/css/icon-font.min.css"/>">
<!-- //lined-icons -->
<script src="<c:url value="/js/amcharts.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/serial.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/scripts.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/jquery.nicescroll.js"/>"
	type="text/javascript"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/js/bootstrap.min.js"/>"
	type="text/javascript"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>
<!-- /Bootstrap Core JavaScript -->
<script src="<c:url value="/js/menu_jquery.js"/>" type="text/javascript"></script>
<!--pie-chart--->
<script src="<c:url value="/js/pie-chart.js"/>" type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script>
	// 結束日大於起始日判斷
	jQuery(document).ready(function($) {
		$("#dDate2").datepicker({
			maxDate : new Date,
			dateFormat : "yy-mm-dd",
			changeYear : true,
			changeMonth : true
		});
		$("#dDate1").datepicker({
			maxDate : new Date,
			dateFormat : "yy-mm-dd",
			changeYear : true,
			changeMonth : true
		}).bind("change", function() {
			var minValue = $(this).val();
			minValue = $.datepicker.parseDate("yy-mm-dd", minValue);
			minValue.setDate(minValue.getDate());
			$("#dDate2").datepicker("option", "minDate", minValue);
		})
	});
</script>

<script type="text/javascript">
	$(document).ready(
			function() {
				$('#demo-pie-1').pieChart(
						{
							barColor : '#3bb2d0',
							trackColor : '#eee',
							lineCap : 'round',
							lineWidth : 8,
							onStep : function(from, to, percent) {
								$(this.element).find('.pie-value').text(
										Math.round(percent) + '%');
							}
						});
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
				<c:import url="../DashBoard/dashHeader.jsp" />
				<!-- //header-ends -->

				<!--content-->
				<div class="content">
					<div class="women_main">
						<!--//內文<div>從這裡開始-->
						<div class="w_content">
							<!-- 報表版面 -->
							<form action="dailyReportGet" method="post">
								<div class="w3-container" style="margin-left: 160px">
									<div>
										<h2>營運銷售分析(日報表)</h2>
									</div>
									<div>
										<h3>選擇欲查詢日期</h3>
										<input type="text" id="dDate1" name="dDate1" readonly>- <input
											type="text" id="dDate2" name="dDate2" readonly>
										<p>

											<input type="submit" value="查詢" id="dSel" name="dSel">
									</div>

									<div>
										<h5>選擇日期：${dDate1}至${dDate2}</h5>
										<!-- 顯示查詢年月日 -->
										<input type="button" value="匯出報表" id="dExport" name="dExport">

										<table border="1">
											<tr>
												<th>日期</th>
												<th>總單數</th>
												<th>來客數</th>
												<th>銷售金額</th>
												<th>短溢收</th>
												<th>實收金額</th>
											</tr>
											<c:forEach var="dTableO" items="${listDailyOrder}"
												varStatus="loop">
												<c:set var="totalList" value="${totalList + dTableO[1]}" />
												<c:set var="totalCusFlow"
													value="${totalCusFlow + dTableO[2]}" />
												<c:set var="totalMoney" value="${totalMoney + dTableO[3]}" />
												<c:set var="totalShort"
													value="${totalShort + listDailyCumu[loop.count-1].shortoverAmount}" />
												<c:set var="totalReceived"
													value="${totalReceived + listDailyCumu[loop.count-1].moneyReceived}" />
												<tr>
													<td>${dTableO[0]}</td>
													<td>${dTableO[1]}</td>
													<td>${dTableO[2]}</td>
													<td>${dTableO[3]}</td>
													<td>${listDailyCumu[loop.count-1].shortoverAmount}</td>
													<td>${listDailyCumu[loop.count-1].moneyReceived}</td>
												</tr>
											</c:forEach>
											<tr>
												<th>總計</th>
												<td>${totalList}</td>
												<td>${totalCusFlow}</td>
												<td>${totalMoney}</td>
												<td>${totalShort}</td>
												<td>${totalReceived}</td>
											</tr>
										</table>
									</div>
								</div>
							</form>
						</div>
						<!--//內文</div>從這裡結束-->
						<div class="clearfix"></div>

						<!-- footer -->
						<c:import url="../DashBoard/dashFooter.jsp" />
						<!-- footer -->
					</div>
					<!--content-->
				</div>
			</div>
		</div>
		<!--//content-inner-->

		<!--sidebar-->
		<c:import url="../DashBoard/dashSideBar.jsp" />
		<!--sidebar-->
		<div class="clearfix"></div>
	</div>



</body>
</html>