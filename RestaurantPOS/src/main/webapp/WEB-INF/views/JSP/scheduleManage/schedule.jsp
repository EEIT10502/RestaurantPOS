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
	<jsp:include page="../sideBar.jsp" flush="true" />
	<%-- <jsp:include page="../productManage/productSearchHead.jsp" flush="true" /> --%>
	<fieldset class="w3-container" style="margin-left: 260px">
		<div class='fieldset span-22'>
			<h3 class="span-2">班別表</h3>
			<div class="last span-2">
				<a href="schedule/add" title="新增組員" class="worker span-2 button"
					id="add_worker">+班表</a>
			</div>
			<table class="myDataTalbe">
				<thead>
					<tr>
						<th class="span-2">名稱</th>
						<th class="span-2">識別色</th>
						<th class="span-2">開始時間</th>
						<th class="span-2">結束時間</th>
						<th class="span-2">休息時間</th>
<!-- 						<th class="span-2">總時間</th> -->
						<th class="span-3 last">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="schedule" items="${schedule}">
						<tr>
							<td>${schedule.schedule}</td>
							<td><div width="20" height="20"
									style="width:20px;height:20px;border:1px solid #eee;background-color:${schedule.color}">&nbsp;</div></td>
							<td><fmt:formatDate value="${schedule.startTime}"
 									pattern="hh:mm" /></td> 
							<td><fmt:formatDate value="${schedule.endTime}"
 									pattern="hh:mm" /></td> 
							<td>${schedule.restTime}</td>
<%-- 							<td>${schedule.totalTime}</td> --%>
							<td><a
								href="<c:url value='/schedule/update?id=${schedule.scheduleId}'/>"
								title="編輯" class="image edit span-1" id="edit">編輯</a> <a
								href="https://shift.ekko.com.tw/group/delete_worker/14143.html"
								title="刪除" class="image delete span-1" id="delete">刪除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<script type="text/javascript">
			$(function() {

				$(".myDataTalbe").DataTable({
					searching : false, //關閉filter功能
					columnDefs : [ {
						targets : [ 3 ],
						orderable : false,
					} ]
				});
			});
		</script>
	</fieldset>
</body>