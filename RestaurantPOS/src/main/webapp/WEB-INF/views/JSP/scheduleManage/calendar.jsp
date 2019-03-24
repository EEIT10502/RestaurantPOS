<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" />
<!--引用jQuery-->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>
<!--引用dataTables.js-->
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<title>CLASS</title>
</head>
<body>
	<div  class="clearfix">
	<jsp:include page="../headerTime.jsp" flush="true" />	
	<jsp:include page="../sideBar.jsp" flush="true" />
	<br>
	<jsp:include page="../scheduleManage/statusSearchHead.jsp" flush="true" />
	</div>
	<section class="">
		<fieldset class="w3-container" style="margin-left: 260px">
			<div id="allList" class="row">
				<table class="table table-hover">
					<tr>
						<th scope="col">#</th>
						<th scope="col">員工名稱</th>
						<th scope="col">03/31 日</th>
						<th scope="col">04/01 一</th>
						<th scope="col">04/02 二</th>
						<th scope="col">04/03 三</th>
						<th scope="col">04/04 四</th>
						<th scope="col">04/05 五</th>
						<th scope="col">04/06 六</th>
						<th scope="col">操作</th>
					</tr>

					<c:forEach var='calendar' items='${calendar}' varStatus="status">
						<tr class="">
							<th scope="row">${status.index + 1+currentBeginOfItemNo}</th>
							<td>${calendar.employee.empName}</td>
							<td style="color:${calendar.schedule.color}">${calendar.day1}</td>
							<td style="color:${calendar.schedule}">${calendar.day2}</td>
							<td style="color:${calendar.schedule.color}">${calendar.day3}</td>
							<td style="color:${calendar.schedule}">${calendar.day4}</td>
							<td style="color:${calendar.schedule.color}">${calendar.day5}</td>
							<td style="color:${calendar.schedule}">${calendar.day6}</td>
							<td style="color:${calendar.schedule.color}">${calendar.day7}</td>
							<td><a
								href="<c:url value='/calendar/update?id=${calendar.calendarId}'/>"
								title="編輯" class="btn btn-primary" id="edit" type="button">編輯</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</fieldset>
		<jsp:include page="../footer.jsp" flush="true" />
</body>