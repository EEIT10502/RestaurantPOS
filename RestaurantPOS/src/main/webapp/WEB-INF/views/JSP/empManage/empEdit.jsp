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

<script type="text/javascript">
function confirmUpdate(userId){
	  var result = confirm("確定送出此筆記錄(帳號:" + userId.trim() + ")?");
	  if (result) {
		  document.forms[0].putOrDelete.value = "PUT";
	      return true;
	  }
	  return false;
}

</script>
<body>
<jsp:include page="../sideBar.jsp" flush="true" /> 
<form:form class='center' action="${pageContext.request.contextPath}/_02/mscv/members/${member.pk}" 
     modelAttribute="member" method="POST" >
    <input type="hidden" name="_method"  id='putOrDelete'   value="" >
    <input type="hidden" name="pk"     value="${member.pk}" >
    <input type="hidden" name="id"     value="${member.id}${param.id}" >
    <input type="hidden" name="finalDecision" value="" >
<div class="w3-container" style="margin-left:160px">
<h1>員工資料查詢</h1>

<table>
<tr>
<td rowspan="10"><img width="100px" height="100px" src="<c:url value='/getPicture/${empQueryFor1.empId}'/>"></td><td>序號</td><td>${empQueryFor1.empId}</td></tr>
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

<p>
<input type="submit" value="更新" name='updateBtn' onclick="return confirmUpdate('${empQueryFor1.empId}');"> 
<a href="<spring:url value='empQuery'/>">返回</a>
</p>
</div>
</form:form>
</body>
</html>
