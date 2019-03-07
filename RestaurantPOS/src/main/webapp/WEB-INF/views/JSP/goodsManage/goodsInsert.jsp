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


<body>
	<jsp:include page="../sideBar.jsp" flush="true" />
	<section>
		<div class="container">
			<h1>
				<spring:message code='spring.goodsInsert.form.goodsInsertData.label' />
			</h1>
		</div>
	</section>
	<!-- 	<section class="container"> -->
	<section class="">
		<form:form method='POST' modelAttribute="MenuBean"
			class='form-horizontal'>
			<fieldset class="w3-container" style="margin-left: 160px">
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='productNo'>
						<spring:message code='spring.goodsInsert.form.productNo.label' />
					</label>
					<div class="col-lg-10">
						<form:input id="productNo" path="productNo" type='text'
							class='form:input-large' />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='productName'>
						<spring:message code='spring.goodsInsert.form.productName.label' />
					</label>
					<div class="col-lg-10">
						<form:input id="productName" path="productName" type='text'
							class='form:input-large' />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='price'>
						<spring:message code='spring.goodsInsert.form.price.label' />
					</label>
					<div class="col-lg-10">
						<form:input id="price" path="price" type='text'
							class='form:input-large' />
					</div>
				</div>
				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="cate">
						<spring:message code='spring.goodsInsert.form.cate.label' />
					</label>
					<div class='col-lg-10'>
						<form:select path="cate">
							<form:option value="-1">
								<spring:message code='spring.goodsInsert.form.select.label' />
							</form:option>
							<form:options items="${cateList}" />
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="productStatus">
						<spring:message code='spring.goodsInsert.form.productStatus.label' />
					</label>
					<div class='col-lg-10'>
						<form:select path="productStatus">
							<form:option value="-1">
								<spring:message code='spring.goodsInsert.form.select.label' />
							</form:option>
							<form:options items="${productStatusList}" />
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<div class='col-lg-offset-2 col-lg-10'>
						<input id="inGoods" type='submit' class='btn btn-primary'
							value="<spring:message code='spring.goodsInsert.form.submit.label' />" />
						<input id="resetGoods" type='reset' class='btn btn-primary'
							value="<spring:message code='spring.goodsInsert.form.reset.label' />" />
					</div>
				</div>


				<!-- 	========================================================================== -->
				<!-- 				<div class="w3-container" style="margin-left: 160px"> -->
				<!-- 					<div> -->
				<!-- 						<label for="gIdInsert">商品編號</label> <input type="text" -->
				<!-- 							id="gIdInsert" name="gIdInsert"> -->
				<!-- 						<p> -->
				<!-- 							<label for="gNInsert">商品名稱</label> <input type="text" -->
				<!-- 								id="gNInsert" name="gNInsert"> -->
				<!-- 						<p> -->
				<!-- 							<label for="gPInsert">商品價格</label> <input type="text" -->
				<!-- 								id="gPInsert" name="gPInsert"> -->
				<!-- 						<p> -->

				<!-- 							<label for="cateInsert">商品類別</label> <select id="cateInsert"> -->
				<!-- 								<option>飯類</option> -->
				<!-- 								<option>麵類</option> -->
				<!-- 								<option>飲料</option> -->
				<!-- 								<option>甜點</option> -->
				<!-- 							</select> -->
				<!-- 						<p> -->
				<!-- 							<label for="gPicInsert">商品圖片</label> <input type="file" -->
				<!-- 								id="gPicInsert" name="gPicInsert"> -->
				<!-- 						<p> -->
				<!-- 							<input type="submit" value="新增" id="inGoods" name="inGoods"> -->
				<!-- 							<input type="reset" value="清除" id="resetGoods" name="resetGoods"> -->
				<!-- 						<p> -->
				<!-- 					</div> -->



				<!-- 				</div> -->
			</fieldset>
		</form:form>
	</section>
</body>
</html>
