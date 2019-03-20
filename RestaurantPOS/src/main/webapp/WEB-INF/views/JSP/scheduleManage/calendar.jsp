<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" type="image/png" href="/favicon.ico" />
<meta name="keywords" content="線上排班、排班表、派班表" />
<meta name="description" content="簡單易用線上排班系統，提供滑鼠拖拉設定排班、列印及網頁內崁碼功能。" />
<!--[if lt IE 8]><link type="text/css" rel="stylesheet" href="/asset/css/ie.css" media="screen, projection" /><![endif]-->
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/screen.css"
	media="screen, projection" />
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/print.css" media="print" />
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/plugin/tabs.css"
	media="screen, projection" />
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/style.css" />
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/jquery.fancybox-1.3.4.css"
	media="screen, projection" />
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/jquery.simple-color-picker.css"
	media="screen, projection" />
<script type="text/javascript"
	src="https://shift.ekko.com.tw/asset/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="https://shift.ekko.com.tw/asset/js/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript"
	src="https://shift.ekko.com.tw/asset/js/jquery.simple-color-picker.js"></script>
<title>CLASS</title>
</head>
<body>
	<jsp:include page="../sideBar.jsp" flush="true" />
	<fieldset class="w3-container" style="margin-left: 260px">
		<!-- 	<div class="container prepend-top append-bottom"> -->
		<!-- 		<div class="span-24 header"></div> -->
		<!-- 		<div id="menu" class="span-24 last"> -->
		<!-- 			<div class="append-1 last" style="margin-top: 1em;"></div> -->
		<!-- 		</div> -->
		<div id="main" class="span-24">
			<div id="content" class="span-22 prepend-1 append-1">
				<div class='mainInfo'>
<!-- 					<ul class='tabs'> -->

<!-- 						<li><a href="calendar">排班表</a></li> -->
<!-- 						<li><a href="schedule" class="selected">班別表</a></li> -->
<!-- 												<li><a href="https://shift.ekko.com.tw/group/embed.html">內崁程式碼</a></li> -->
<!-- 					</ul> -->
					<div class='fieldset span-22'>
						<h4 class="span-3">排班表</h4>
						<table class="group append-bottom">
							<thead>
								<tr>
									<th class="span-2">員工名稱</th>
									<th class="span-1">一</th>
									<th class="span-1">二</th>
									<th class="span-1">三</th>
									<th class="span-1">四</th>
									<th class="span-1">五</th>
									<th class="span-1">六</th>
									<th class="span-1">日</th>
									<th class="span-2 last">修改</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="calendar" items="${calendar}">
									<tr>
										<td>${calendar.employee.empName}</td>
										<td><input type="button" value="${calendar.day1}"></td>
										<td><input type="button" value="${calendar.day2}"></td>
										<td><input type="button" value="${calendar.day3}"></td>
										<td><input type="button" value="${calendar.day4}"></td>
										<td><input type="button" value="${calendar.day5}"></td>
										<td><input type="button" value="${calendar.day6}"></td>
										<td><input type="button" value="${calendar.day7}"></td>
										<td><a
											href="<c:url value='/calendar/update?id=${calendar.calendarId}'/>"
											title="編輯" class="image edit span-1" id="edit">編輯</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<script type="text/javascript">
					$(document).ready(function() {
						$("a#add_worker, a#edit").live('click', function(e) {
							e.preventDefault();
							$.fancybox(this, {
								'scrolling' : 'no',
								'titleShow' : false,
								'centerOnScroll' : true,
								'autoScale' : false,
								'enableEscapeButton' : true,
								'type' : 'inline'
							});
						});
					})
				</script>
			</div>
		</div>

		<!-- 		<div class="span-24" id="footer"> -->
		<!-- 			<p>請使用Internet Explorer 90, Firefox 3x, Chrome 10x -->
		<!-- 				以上版本之瀏覽器，螢幕解析度1024x768以上，以達使用完整功能及最佳顯示效果。</p> -->
		<!-- 		</div> -->
		<!-- 	</div> -->
	</fieldset>
	<script type="text/javascript">
		$(document).ready(function() {
			if ($("div#status_message").length > 0) {
				$("div#status_message").delay(4000).slideUp(1000);
			}
		});
	</script>
</body>