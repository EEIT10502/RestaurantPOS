<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
</head>
<title>員工查詢</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<style>
td,th{

border:1px solid black
}
</style>


<body>
<jsp:include page="../sideBar.jsp" flush="true" /> 
<div class="w3-container" style="margin-left:160px">
<h1>員工資料查詢</h1>
<table>
<tr>
<td rowspan="10"><img width="100px" height="100px" src="<c:url value='/getPicture/${empQueryFor1.empId}'/>"></td>
<td>序號</td><td>${empQueryFor1.empId}</td></tr>
<tr><td>狀態</td><td>${empQueryFor1.status}</td></tr>
<tr><td>編號</td><td>${empQueryFor1.empNo}</td></tr>
<tr><td>姓名</td><td>${empQueryFor1.empName}</td></tr>
<tr><td>性別</td><td>${empQueryFor1.gender}</td></tr>
<tr><td>職位</td><td>${empQueryFor1.position}</td></tr>
<tr><td>電話</td><td>${empQueryFor1.tel}</td></tr>
<tr><td>電話</td><td>${empQueryFor1.tel}</td></tr>
<tr><td>地址</td><td>${empQueryFor1.addr}</td></tr>
<tr><td>備註</td><td>${empQueryFor1.remark}</td></tr>

</table>
<p><a href="<spring:url value='empEdit?empId=${empQueryFor1.empId}'/>">修改資料</a>
<p>

<a href="<spring:url value='empQuery'/>">返回</a>
</p>
</div>

</body>
</html>
