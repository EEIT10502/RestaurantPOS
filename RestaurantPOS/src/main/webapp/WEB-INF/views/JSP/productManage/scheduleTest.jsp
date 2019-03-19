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
<script
  src="http://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#submitButton").click(function() {		
		var Tbdata = {}; 
		  var tr_length = $('.table tr').length; //tr 長度
		  for(var i=0; i < tr_length; i++){
		    var td_length = $('.table tr')[i].childElementCount; //當下td長度
		    if(i>=1){ //從第二列開始取值
		    Tbdata[i] = {};
		      for(var v=0; v < td_length; v++){
		        if(v>=1){ //從第二欄開始取值
		            Tbdata[i][v] = {};
		            Tbdata[i][v]['value'] = $('.table tr:eq('+i+') td:eq('+v+') select option:selected').text();
		            Tbdata[i][v]['date'] = $('.table tr:eq('+i+') td:eq('+v+') span').data('date');
		            Tbdata[i][v]['name'] = $('.table tr:eq('+i+') td:eq('+v+') span').data('name');
		        }
		      }
		     }
		  }
		  console.log(Tbdata);
    });
});
</script>

<body>
	<jsp:include page="../sideBar.jsp" flush="true" />
	<section class="">
	<fieldset class="w3-container" style="margin-left: 160px">
			<div id="allList" class="">
<%-- 			<form class='center' name="" action="" method="post"> --%>
				<table class="table" border="1">
					<tr>
						<th>姓名</th>
						<c:forEach var='dateList' items='${scheduleDateList}' varStatus="status">
							<th><c:out value="${dateList}" /></th>

						</c:forEach>
					</tr>
					<c:forEach var='empList' items='${scheduleEmpList}'
						varStatus="status">
						<tr>
							<td><c:out value="${empList.empName}" /></td>
							<c:forEach var='dateList' items='${scheduleDateList}' varStatus="status">
								<td>
									<span data-date="${dateList}" data-name="${empList.empName}">
										<select name="" id="" class="">
											<c:forEach var='workType' items='${workTypeList}'>
												<option selected="selected"><c:out value="${workType}" /></option>
											</c:forEach>
										</select>
									</span>
								</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
				<input id="submitButton" type='submit' class='' value="確認" />
<%-- 				</form> --%>
			</div>
	
	
	
	
	

<%-- 		<form:form method='POST' modelAttribute="MenuBean" --%>
<%-- 			class='form-horizontal'> --%>
<!-- 			<fieldset class="w3-container" style="margin-left: 160px"> -->
<!-- 				<div class="container"> -->
<!-- 					<h1> -->
<%-- 						<spring:message --%>
<%-- 							code='spring.productInsert.form.productInsertData.label' /> --%>
<!-- 					</h1> -->
<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label class="control-label col-lg-2 col-lg-2" for='productNameBtn'> -->
<%-- 						<spring:message code='spring.productInsert.form.productName.label' /> --%>
<!-- 					</label> -->
<!-- 					<div class="col-lg-10"> -->
<%-- 						<form:input id="productNameBtn" path="productName" type='text' --%>
<%-- 							name="productName" class='form:input-large' /> --%>
<%-- 						<p class="errorMessage" type="redError">${modelErrors.errorOfProductName} --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label class="control-label col-lg-2 col-lg-2" for='priceBtn'> -->
<%-- 						<spring:message code='spring.productInsert.form.price.label' /> --%>
<!-- 					</label> -->
<!-- 					<div class="col-lg-10"> -->
<%-- 						<form:input id="priceBtn" path="price" type='text' --%>
<%-- 							class='form:input-large' name="iPrice"/> --%>
<%-- 						<p class="errorMessage" type="redError">${modelErrors.errorOfPrice}${modelErrors.typeErrorOfPrice} --%>
<!-- 					</div> -->

<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label class='control-label col-lg-2 col-lg-2' for="cateBtn"> -->
<%-- 						<spring:message code='spring.productInsert.form.cate.label' /> --%>
<!-- 					</label> -->
<!-- 					<div class='col-lg-10'> -->
<%-- 						<form:select path="cate" id="cateBtn"> --%>
<%-- 							<form:option value="-1"> --%>
<%-- 								<spring:message code='spring.productInsert.form.select.label' /> --%>
<%-- 							</form:option> --%>
<%-- 							<form:options items="${cateList}" /> --%>
<%-- 						</form:select> --%>
<%-- 						<p class="errorMessage" type="redError">${modelErrors.errorOfCate} --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label class='control-label col-lg-2 col-lg-2' -->
<%-- 						for="productStatusBtn"> <spring:message --%>
<%-- 							code='spring.productInsert.form.productStatus.label' /> --%>
<!-- 					</label> -->
<!-- 					<div class='col-lg-10'> -->
<%-- 						<form:select path="productStatus" id="productStatusBtn"> --%>
<%-- 							<form:option value="-1"> --%>
<%-- 								<spring:message code='spring.productInsert.form.select.label' /> --%>
<%-- 							</form:option> --%>
<%-- 							<form:options items="${productStatusList}" /> --%>
<%-- 						</form:select> --%>
<%-- 						<p class="errorMessage" type="redError">${modelErrors.errorOfProductStatus} --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<div class='col-lg-offset-2 col-lg-10'> -->
<!-- 						<input id="inProduct" type='submit' class='btn btn-primary' -->
<%-- 							value="<spring:message code='spring.productInsert.form.submit.label' />" /> --%>
<!-- 						<input id="resetProduct" type='reset' class='btn btn-primary' -->
<%-- 							value="<spring:message code='spring.productInsert.form.reset.label' />" /> --%>
<!-- 					</div> -->
<!-- 				</div> -->

<!-- Button trigger modal -->
<!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter"> -->
<!--   Launch demo modal -->
<!-- </button> -->

<!-- Modal -->
<!-- <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true"> -->
<!--   <div class="modal-dialog modal-dialog-centered" role="document"> -->
<!--     <div class="modal-content"> -->
<!--       <div class="modal-header"> -->
<!--         <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5> -->
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!--           <span aria-hidden="true">&times;</span> -->
<!--         </button> -->
<!--       </div> -->
<!--       <div class="modal-body"> -->
<!--         ... -->
<!--       </div> -->
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!--         <button type="button" class="btn btn-primary">Save changes</button> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->
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
<%-- 		</form:form> --%>
	</section>
</body>
</html>
