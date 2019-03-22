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
<script type="text/javascript">
	function autoInsert() {
		document.getElementById("productNameBtn").value='宮保雞丁飯';
		document.getElementById("priceBtn").value='100';
		document.getElementById("cateBtn").value='飯類';
		document.getElementById("productStatusBtn").value='販售';
	}	
</script>
<body>
	<div  class="clearfix">
	<jsp:include page="../headerTime.jsp" flush="true" />	
	<jsp:include page="../sideBar.jsp" flush="true" />
	</div>	
	<section class="">

		<form:form method='POST' modelAttribute="MenuBean"
			class='form-horizontal'>
			<fieldset class="w3-container" style="margin-left: 260px">
				<div class="container">
					<h1>
						<spring:message
							code='spring.productInsert.form.productInsertData.label' />
					</h1>
				</div>
				<div>
				<input type="button" value="一鍵輸入" onclick="autoInsert()">
					<p class="errorMessage" type="redError">${insertComplete}
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='productNameBtn'>
						<spring:message code='spring.productInsert.form.productName.label' />
					</label>
					<div class="col-lg-10">
						<form:input id="productNameBtn" path="productName" type='text'
							name="productName" class='form:input-large' />
						<p class="errorMessage" type="redError">${modelErrors.errorOfProductName}
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='priceBtn'>
						<spring:message code='spring.productInsert.form.price.label' />
					</label>
					<div class="col-lg-10">
						<form:input id="priceBtn" path="price" type='text'
							class='form:input-large' name="iPrice"/>
						<p class="errorMessage" type="redError">${modelErrors.errorOfPrice}${modelErrors.typeErrorOfPrice}
					</div>

				</div>
				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="cateBtn">
						<spring:message code='spring.productInsert.form.cate.label' />
					</label>
					<div class='col-lg-10'>
						<form:select path="cate" id="cateBtn">
							<form:option value="-1">
								<spring:message code='spring.productInsert.form.select.label' />
							</form:option>
							<form:options items="${cateList}" />
						</form:select>
						<p class="errorMessage" type="redError">${modelErrors.errorOfCate}
					</div>
				</div>
				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2'
						for="productStatusBtn"> <spring:message
							code='spring.productInsert.form.productStatus.label' />
					</label>
					<div class='col-lg-10'>
						<form:select path="productStatus" id="productStatusBtn">
							<form:option value="-1">
								<spring:message code='spring.productInsert.form.select.label' />
							</form:option>
							<form:options items="${productStatusList}" />
						</form:select>
						<p class="errorMessage" type="redError">${modelErrors.errorOfProductStatus}
					</div>
				</div>
				<div class="form-group">
					<div class='col-lg-offset-2 col-lg-10'>
						<input id="inProduct" type='submit' class='btn btn-primary'
							value="<spring:message code='spring.productInsert.form.submit.label' />" />
						<input id="resetProduct" type='reset' class='btn btn-primary'
							value="<spring:message code='spring.productInsert.form.reset.label' />" />
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
	<jsp:include page="../footer.jsp" flush="true" />
</body>
</html>
