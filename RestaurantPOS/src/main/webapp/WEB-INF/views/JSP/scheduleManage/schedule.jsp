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
	<div class="clearfix">
		<jsp:include page="../headerTime.jsp" flush="true" />
		<jsp:include page="../sideBar.jsp" flush="true" />
	</div>
	<section class="">
		<fieldset class="w3-container" style="margin-left: 260px">

			<div>
				<h3>班別管理</h3>
				<br>
			</div>

			<div id="allList" class="row">
				<table class="table table-hover">
					<tr>
						<th scope="col">#</th>
						<th scope="col">名稱</th>
						<!-- 						<th scope="col">識別色</th> -->
						<th scope="col">開始時間</th>
						<th scope="col">結束時間</th>
						<th scope="col">休息時數</th>
						<th scope="col">操作</th>
					</tr>

					<c:forEach var='schedule' items='${schedule}' varStatus="status">
						<tr class="">
							<th scope="row">${status.index + 1+currentBeginOfItemNo}</th>
							<td>${schedule.schedule}</td>
							<!-- 							<td><div width="20" height="20" -->
							<%-- 									style="width:20px;height:20px;border:1px solid #eee;background-color:${schedule.color}">&nbsp;</div></td> --%>
							<td>${schedule.startTime}</td>
							<td>${schedule.endTime}</td>
							<td>${schedule.restTime}</td>
							<%--  							<td>${schedule.totalTime}</td> --%>
							<td><a
								<%-- 								href="<c:url value='/schedule/update?id=${schedule.scheduleId}'/>" --%>
								href="<c:url value='/schedule/${schedule.scheduleId}'/>"
								title="編輯" class="btn btn-primary" id="edit" type="button">編輯</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<div class="last span-2">
					<a href="schedule/add" class="btn btn-primary" id="add_worker">新增班表</a>
				</div>
			</div>
		</fieldset>
	</section>
	<jsp:include page="../footer.jsp" flush="true" />
</body>
</html>