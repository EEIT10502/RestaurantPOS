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
<title>員工查詢2</title>
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
	  var result = confirm("確定送出此筆記錄(帳號:" + (document.getElementById("empId").value.trim()) + ")?");
	  if (result) {
// 		  document.forms[0].putOrDelete.value = "PUT";
	      return true;
	  }
	  return false;
}

</script>
<body>
<jsp:include page="../sideBar.jsp" flush="true" /> 
<form:form method='POST' modelAttribute="empEdit"
			class='form-horizontal' enctype='multipart/form-data'>
<!-- 			 <input type="hidden" name="_method"  id='putOrDelete'   value="put" /> -->

<div class="w3-container" style="margin-left:160px">
<h1>員工資料查詢</h1>
<table>
<tr>
<td rowspan="10"><img width="100px" height="100px" src="<c:url value='/getPicture/${empEdit.empId}'/>">
<P><form:input path="empImg" type='file'  />empImg</td>

<td>編號</td><td><form:input path="empId" id="empId" name= "empId" type="number" value="${empEdit.empId}"/></td>


</tr>

<tr><td>狀態</td><td><form:input path="status" value="${empEdit.status}"/></td></tr>
<tr><td>編號</td><td><form:input path="empNo" value="${empEdit.empNo}"/></td></tr>
<tr><td>姓名</td><td><form:input path="empName" value="${empEdit.empName}"/></td></tr>
<tr><td>性別</td><td><form:input path="gender" value="${empEdit.gender}"/></td></tr>
<tr><td>職位</td><td><form:input path="position" value="${empEdit.position}"/></td></tr>
<tr><td>電話</td><td><form:input path="tel" value="${empEdit.tel}"/></td></tr>
<tr><td>地址</td><td><form:input path="addr" value="${empEdit.addr}"/></td></tr>
<tr><td>檔案名稱</td><td><form:input path="fileName" value="${empEdit.fileName}"/></td></tr>
<tr><td>備註</td><td><form:input path="remark" value="${empEdit.remark}"/></td></tr>
</table>

<p>
<input type="submit" name='updateBtn' onclick="return confirmUpdate('${empEdit.empId}');">
<a href="<spring:url value='empQuery'/>">返回</a>
</p>
</div>
</form:form>
</body>
</html>
