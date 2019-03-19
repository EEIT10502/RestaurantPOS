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
<!-- <script type="text/javascript" -->
<!-- 	src="https://shift.ekko.com.tw/asset/js/jquery-1.7.1.min.js"></script> -->
<script type="text/javascript"
	src="https://shift.ekko.com.tw/asset/js/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript"
	src="https://shift.ekko.com.tw/asset/js/jquery.simple-color-picker.js"></script>
	
<title>CLASS</title>
</head>
<body>

	<div class="container prepend-top append-bottom">
		<div class="span-24 header"></div>
		<div id="menu" class="span-24 last">
		</div>
		<div id="main" class="span-24">
			<div id="content" class="span-22 prepend-1 append-1">
				<div class='mainInfo'>
					<ul class='tabs'>
						<li><a href="calendar">排班表</a></li>
						<li><a href="schedule">班別表</a></li>
						<!-- 						<li><a href="https://shift.ekko.com.tw/group/embed.html">內崁程式碼</a></li> -->
					</ul>
					<div class='fieldset span-22'>
						<h3 class="span-2">班別表</h3>
						<div class="last span-2">
							<a href="schedule/add" title="新增組員" class="worker span-2 button"
								id="add_worker">+班表</a>
						</div>
						<table class="group append-bottom">
							<thead>
								<tr>
									<th class="span-2">名稱</th>
									<th class="span-2">識別色</th>
									<th class="span-2">開始時間</th>
									<th class="span-2">結束時間</th>
									<th class="span-2">休息時間</th>
									<th class="span-2">總時間</th>
									<th class="span-3 last">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="schedule" items="${schedule}">
									<tr>
										<td>${schedule.schedule}</td>
										<td><div width="20" height="20" style="width:20px;height:20px;border:1px solid #eee;background-color:${schedule.color}">&nbsp;</div></td>
										<!-- 										<td><div width="20" height="20" -->
										<!-- 												style="width: 20px; height: 20px; border: 1px solid #eee; background-color: color=JColorChooser.showDialog(this, ”?色”, color);">&nbsp;</div></td> -->
										<td><fmt:formatDate value="${schedule.startTime}"
												pattern="hh:mm" /></td>
										<td><fmt:formatDate value="${schedule.endTime}"
												pattern="hh:mm" /></td>
										<td><fmt:formatDate value="${schedule.restTime}"
												pattern="hh:mm" /></td>
										<td>${schedule.totalTime}"</td>
										<td>
										<a href="<c:url value='/schedule/update?id=${schedule.scheduleId}'/>"
											title="編輯" class="image edit span-1" id="edit">編輯</a>
											
										<a href="https://shift.ekko.com.tw/group/delete_worker/14143.html"
											title="刪除" class="image delete span-1" id="delete">刪除</a></td>
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

		<div class="span-24" id="footer">
			<p>請使用Internet Explorer 90, Firefox 3x, Chrome 10x
				以上版本之瀏覽器，螢幕解析度1024x768以上，以達使用完整功能及最佳顯示效果。</p>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			if ($("div#status_message").length > 0) {
				$("div#status_message").delay(4000).slideUp(1000);
			}
		});
	</script>
</body>