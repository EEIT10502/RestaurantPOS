<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<title>修改班別</title>
<script type="text/javascript">
	function confirmDelete(scheduleId) {
		var result = confirm("確定刪除此筆記錄(帳號:" + scheduleId.trim() + ")?");
		if (result) {
			document.forms[0].putOrDelete.value = "DELETE";
			return true;
		}
		return false;
	}
	function confirmUpdate(scheduleId) {
		var result = confirm("確定送出此筆記錄(帳號:" + scheduleId.trim() + ")?");
		if (result) {
			document.forms[0].putOrDelete.value = "POST";
			return true;
		}
		return false;
	}
</script>
<link rel='stylesheet' href='css/styles.css' type="text/css" />

</head>
<body>
<jsp:include page="../sideBar.jsp" flush="true" />
	<section class="container">
		<!--       三個地方要完全一樣 -->
		<form:form method='POST' modelAttribute="calendarBean"
			class='form-horizontal' enctype="multipart/form-data">
			<input type="hidden" name="_method" id='putOrDelete' value="">
			<fieldset class="w3-container" style="margin-left: 260px">
				<legend>修改排班資料</legend>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2 hide"
						for='calendarId'> calendarId</label>
						<form:input id="calendar${calendar.calendarId}" path="calendarId"
							type='text' class='form:input-large hide' readonly="true" />
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2 hide"
						for='employee.empId'> empId</label>
						<form:input id="calendar${calendar.employee.empId}"
							path="employee.empId" type='text' class='form:input-large hide'
							readonly="true" />
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2 hide"
						for='schedule.scheduleId'> scheduleId</label>
						<form:input id="calendar${calendar.schedule.scheduleId}"
							path="schedule.scheduleId" type='text'
							class='form:input-large hide' readonly="true" />
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2 hide" for='date'>date</label>
						<form:input id="calendar${calendar.date}" path="date" type='text'
							class='form:input-large hide' readonly="true" />
				</div>
				
				
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='day1'>
						day1</label>
						<form:select path="day1">
						 	<form:option value="-1" label="請選擇"/>
						 	<c:forEach var="ss" items="${ss}">
						 	<form:option value="${ss.schedule}">${ss.schedule}</form:option>
						 	</c:forEach>
						</form:select>
				</div>
				
				
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='day2'>
						day2</label>
						<form:select path="day2">
						 	<form:option value="-1" label="請選擇"/>
						 	<c:forEach var="ss" items="${ss}">
						 	<form:option value="${ss.schedule}">${ss.schedule}</form:option>
						 	</c:forEach>
						</form:select>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='day3'>
						day3</label>
						<form:select path="day3">
						 	<form:option value="-1" label="請選擇"/>
						 	<c:forEach var="ss" items="${ss}">
						 	<form:option value="${ss.schedule}">${ss.schedule}</form:option>
						 	</c:forEach>
						</form:select>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='day4'>
						day4</label>
						<form:select path="day4">
						 	<form:option value="-1" label="請選擇"/>
						 	<c:forEach var="ss" items="${ss}">
						 	<form:option value="${ss.schedule}">${ss.schedule}</form:option>
						 	</c:forEach>
						</form:select>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='day5'>
						day5</label>
						<form:select path="day5">
						 	<form:option value="-1" label="請選擇"/>
						 	<c:forEach var="ss" items="${ss}">
						 	<form:option value="${ss.schedule}">${ss.schedule}</form:option>
						 	</c:forEach>
						</form:select>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='day6'>
						day6</label>
						<form:select path="day6">
						 	<form:option value="-1" label="請選擇"/>
						 	<c:forEach var="ss" items="${ss}">
						 	<form:option value="${ss.schedule}">${ss.schedule}</form:option>
						 	</c:forEach>
						</form:select>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='day7'>
						day7</label>
						<form:select path="day7">
						 	<form:option value="-1" label="請選擇"/>
						 	<c:forEach var="ss" items="${ss}">
						 	<form:option value="${ss.schedule}">${ss.schedule}</form:option>
						 	</c:forEach>
						</form:select>
				</div>


				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" value="送出" name='updateBtn'
							onclick="return confirmUpdate('${calendar.calendarId}');" />
					</div>
				</div>
				<c:if test="${not empty requestScope.modify}">
					<c:remove var="member" scope="request" />
				</c:if>
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
				<script type="text/javascript">
					$(document)
							.ready(
									function() {
										$("input#name").focus();
										$('input#color').simpleColorPicker(
												{
													onChangeColor : function(
															color) {
														$('input#color').val(
																color);
													},
													showEffect : 'fade',
													hideEffect : 'slide',
													enableEscapeButton : true,
													colors : [ '#660000',
															'#783f04',
															'#7f6000',
															'#274e13',
															'#0c343d',
															'#073763',
															'#20124d',
															'#4C1130',
															'#990000',
															'#b45f06',
															'#bf9000',
															'#38761d',
															'#134f5c',
															'#0b5394',
															'#351c75',
															'#741b47',
															'#ff0000',
															'#ff9900',
															'#ffff00',
															'#00ff00',
															'#00ffff',
															'#0000ff',
															'#9900ff',
															'#ff00ff',
															'#cc0000',
															'#e69138',
															'#f1c232',
															'#6aa84f',
															'#45818e',
															'#3d85c6',
															'#674ea7',
															'#a64d79',
															'#e06666',
															'#f6b26b',
															'#ffd966',
															'#93c47d',
															'#76a5af',
															'#6fa8dc',
															'#8e7cc3',
															'#c27ba0' ]
												});
										$("form#work_form #message").hide();
										$("form#work_form #name").focus();
										$("form#work_form input[name='submit']")
												.bind(
														'click',
														function(e) {
															e.preventDefault();
															$
																	.ajax({
																		url : 'https://shift.ekko.com.tw/group/ajax_add_worker.html',
																		type : 'POST',
																		dataType : 'json',
																		data : {
																			name : $(
																					"input[name='name']")
																					.val(),
																			color : $(
																					"input[name='color']")
																					.val(),
																			rate : $(
																					"input[name='rate']")
																					.val(),
																			ci_csrf_token : $(
																					"input[name='ci_csrf_token']")
																					.val()
																		},
																		success : function(
																				data) {
																			if (data.status == 'error') {
																				$(
																						"div#message")
																						.html(
																								data.msg)
																						.show();
																			}
																			if (data.status == 'success') {
																				location
																						.replace('https://shift.ekko.com.tw/group/worker.html');

																				$
																						.ajax({
																							url : 'https://shift.ekko.com.tw/shift/ajax_get_workers.html',
																							type : 'GET',
																							dataType : 'json',
																							success : function(
																									data) {
																								$(
																										'div#workers')
																										.html(
																												data);
																								$(
																										'div#workers a')
																										.each(
																												function() {

																													// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
																													// it doesn't need to have a start or end
																													var eventObject = {
																														id : $(
																																this)
																																.attr(
																																		'id'),
																														title : $
																																.trim($(
																																		this)
																																		.text()), // use the element's text as the event title
																														backgroundColor : $(
																																this)
																																.css(
																																		'backgroundColor')
																													};

																													// store the Event Object in the DOM element so we can get to it later
																													$(
																															this)
																															.data(
																																	'eventObject',
																																	eventObject);

																													// make the event draggable using jQuery UI
																													$(
																															this)
																															.draggable(
																																	{
																																		helper : 'clone',
																																		opacity : 0.5,
																																		zIndex : 999,
																																		revert : true, // will cause the event to go back to its
																																		revertDuration : 0
																																	//  original position after the drag
																																	});

																												});
																							}
																						})

																				$.fancybox
																						.close();

																			}
																		}
																	});
															return false;
														});
										$("input[name='cancel']").bind('click',
												function(e) {
													e.preventDefault();
													$.fancybox.close();
												});
									});
				</script>
			</fieldset>
		</form:form>
	</section>
</body>
</html>
