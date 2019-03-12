<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
</head>
<title>員工查詢</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>

<style>
td,th{

border:1px solid black
}


</style>
<body>
<jsp:include page="../sideBar.jsp" flush="true" /> 
<div class="w3-container" style="margin-left:160px">
<h1>員工資料查詢</h1>
<table style="border:1px solid black">
<tr><th>序號</th><th>狀態</th><th>員工編號</th><th>員工姓名</th><th>性別</th>
<th>職位</th><th>電話</th><th>地址</th><th>照片</th><th>備註</th></tr>
<c:forEach var='employee' items='${empQuery}'>
<tr><td><a href="<spring:url value='empQueryFor1?empId=${employee.empId}'/>">${employee.empId}</a></td>
<td>${employee.status}</td><td>${employee.empNo}</td><td>${employee.empName}</td>
<td>${employee.gender}</td><td>${employee.position}</td><td>${employee.tel}</td>
<td>${employee.addr}</td><td>${employee.fileName}</td><td>${employee.remark}</td></tr>
<%-- <td>${employee.addr}</td><td><img src="<c:url value='/getPicture/${employee.empId}'/>">${employee.fileName}</td><td>${employee.remark}</td></tr> --%>
</c:forEach>

</table>



	<input type="button" value="上一頁" id="dBPage" name="dBPage">
	<input type="button" value="下一頁" id="dNPage" name="dNPage">
</div>

</body>
</html>
