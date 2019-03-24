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
	<div class="clearfix">
		<jsp:include page="../headerTime.jsp" flush="true" />
		<jsp:include page="../sideBar.jsp" flush="true" />
	</div>
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

				<!-- 				<div class="form-group"> -->
				<!-- 					<label class="control-label col-lg-2 col-lg-2 hide" -->
				<!-- 						for='schedule.scheduleId'> scheduleId</label> -->
				<%-- 						<form:input id="calendar${calendar.schedule.scheduleId}" --%>
				<%-- 							path="schedule.scheduleId" type='text' --%>
				<%-- 							class='form:input-large hide' readonly="true" /> --%>
				<!-- 				</div> -->
				<!-- 				<div class="form-group"> -->
				<!-- 					<label class="control-label col-lg-2 col-lg-2 hide" for='date'>date</label> -->
				<%-- 						<form:input id="calendar${calendar.date}" path="date" type='text' --%>
				<%-- 							class='form:input-large hide' readonly="true" /> --%>
				<!-- 				</div> -->


				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='day1'>
						03/31 日</label>
					<form:select path="day1">
						<form:option value="休息" label="請選擇" />
						<c:forEach var="ss" items="${ss}">
							<form:option value="${ss.schedule}">${ss.schedule}</form:option>
						</c:forEach>
					</form:select>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='day2'>
						04/01 一</label>
					<form:select path="day2">
						<form:option value="休息" label="請選擇" />
						<c:forEach var="ss" items="${ss}">
							<form:option value="${ss.schedule}">${ss.schedule}</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='day3'>
						04/02 二</label>
					<form:select path="day3">
						<form:option value="休息" label="請選擇" />
						<c:forEach var="ss" items="${ss}">
							<form:option value="${ss.schedule}">${ss.schedule}</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='day4'>
						04/03 三</label>
					<form:select path="day4">
						<form:option value="休息" label="請選擇" />
						<c:forEach var="ss" items="${ss}">
							<form:option value="${ss.schedule}">${ss.schedule}</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='day5'>
						04/04 四</label>
					<form:select path="day5">
						<form:option value="休息" label="請選擇" />
						<c:forEach var="ss" items="${ss}">
							<form:option value="${ss.schedule}">${ss.schedule}</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='day6'>
						04/05 五</label>
					<form:select path="day6">
						<form:option value="休息" label="請選擇" />
						<c:forEach var="ss" items="${ss}">
							<form:option value="${ss.schedule}">${ss.schedule}</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='day7'>
						04/06 六</label>
					<form:select path="day7">
						<form:option value="休息" label="請選擇" />
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
			</fieldset>
		</form:form>
	</section>
</body>
<jsp:include page="../footer.jsp" flush="true" />
</html>
