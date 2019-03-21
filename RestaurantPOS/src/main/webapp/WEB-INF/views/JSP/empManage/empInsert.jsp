<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title>商品新增</title>
<style type="text/css">
p.errorMessage[type="redError"] {
	color: red;
}
</style>
</head>
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
	crossorigin="anonymous">
	
</script>
<style type="text/css">
p.errorMessage[type="redError"] {
	color: red;
}
</style>
<script type="text/javascript">
function autoInsert(){
    document.getElementById("empName").value='柳彥如';
    document.getElementById("tel").value='0932277888';
    document.getElementById("addr").value='台北市內湖區';
    document.getElementById("gender").value='女性';
    document.getElementById("position").value='服務生';
    document.getElementById("status").value='在職';
    document.getElementById("remark").value='無';
}
</script>
<body>
	<div  class="clearfix">
	<jsp:include page="../headerTime.jsp" flush="true" />	
	<jsp:include page="../sideBar.jsp" flush="true" />
	</div>
	<section class="">

		<form:form method='POST' modelAttribute="employeeBean"
			class='form-horizontal' enctype='multipart/form-data'>
			<fieldset class="w3-container" style="margin-left: 260px">
				<div class="container">
					<h1>員工資料填寫</h1>
				</div>
				<input type="button" value="一鍵輸入" onclick="autoInsert()">

				<div class="form-group">
					<label for='empName'>姓名</label>
					<form:input id="empName" path="empName" type='text' name="empName" />
					<p class="errorMessage" type="redError">${modelErrors.errorOfEmployeeName}
				</div>
				<div>
					<label for='tel'>電話</label>
					<form:input id="tel" path="tel" type='text' name="tel" />
					<p class="errorMessage" type="redError">${modelErrors.errorOfEmployeeTel}
				</div>
				<div>
					<label for='Tel'>地址</label>
					<form:input id="addr" path="addr" type='text' name="addr" />
					<p class="errorMessage" type="redError">${modelErrors.errorOfEmployeeAddr}
				</div>
				<div>
					<label for='Tel'>性別</label>
					<form:select id="gender" path="gender" name="gender">
						<form:option value="-1" label="請選擇" />
						<form:options items="${empGender}" />
					</form:select>
					<p class="errorMessage" type="redError">${modelErrors.errorOfGender}
				</div>
				<div>
					<label for='position'>職位</label>
					<form:select id="position" path="position" name="position">
						<form:option value="-1" label="請選擇" />
						<form:options items="${empPositionList}" />
					</form:select>
					<p class="errorMessage" type="redError">${modelErrors.errorOfPosition}
				</div>

				<div>
					<label for='status'>狀態</label>
					<form:select id="status" path="status" name="status">
						<form:option value="-1" label="請選擇" />
						<form:options items="${empStatusList}" />
					</form:select>
					<p class="errorMessage" type="redError">${modelErrors.errorOfEmpStatus}
				</div>
				<div>
					<label for='empImg'>圖片</label>
					<form:input id="empImg" path="empImg" type='file' name="empImg" />
					<p class="errorMessage" type="redError">${modelErrors.errorOfEmpPicture}
				</div>

				<div>
					<label for='remark'>備註</label>
					<form:input id="remark" path="remark" type='text' name="remark" />
				</div>

				<div>
					<input id="addbtn" type="submit" value="送出" /> <input
						id="resetbtn" type="reset" value="清除" />

				</div>
			</fieldset>
		</form:form>
	</section>
	<jsp:include page="../footer.jsp" flush="true" />
</body>
</html>
