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
<title>sideBar</title>
<style type="text/css">

span.errorMessage[type="redError"] {
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
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
<body>
	<section class="">
		<fieldset class="w3-container" style="margin-left: 160px">
			<h1>商品查詢/修改</h1>
			<form action="productListBySearch.action">
				<div class="">
					<label class="" for='searchBarBtn'>
						依商品名稱搜尋：
					</label>
					<c:if test="${searchBarString == null}">
						<input id="searchBar" type='text' name="searchBar" class=''
						placeholder="請輸入搜尋條件" /> 
					</c:if>
					<c:if test="${searchBarString != null}">
						<input id="searchBar" type='text' name="searchBar" class=''
						value="${searchBarString}" /> 
					</c:if>
					<input id="inProduct" type='submit' class='btn btn-primary' value="搜尋" /> 
<!-- 					<input id="resetProduct" type='reset' class='btn btn-primary' value="清除" /> -->
					<span class="errorMessage" type="redError">${noSearchBarString}</span>
				</div>
			</form>


			<div class="btn-group" role="group"
				aria-label="Button group with nested dropdown">
				<button type="button" class="btn btn-secondary"
					onclick="location.href='${pageContext.request.contextPath}/productManage/allProductList.action'">所有商品${contextPath}</button>

				<div class="btn-group" role="group">
					<button id="btnGroupDrop1" type="button"
						class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">依商品種類檢視</button>
					<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
						<c:forEach var='cates' items='${cateList}'>
							<a class="dropdown-item" href="<c:url value='ProductListByCate.action?whichCate=${cates}' />"><c:out value="${cates}" /></a>
						</c:forEach>
					</div>
				</div>
				
				<div class="btn-group" role="group">
					<button id="btnGroupDrop1" type="button"
						class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">依商品狀態檢視</button>
					<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
						<c:forEach var='productStatus' items='${productStatusList}'>
							<a class="dropdown-item" href="<c:url value='ProductListByProductStatus.action?whichStatus=${productStatus}' />"><c:out value="${productStatus}" /></a>
						</c:forEach>
					</div>
				</div>
			</div>
		</fieldset>
		</section>
</body>
</html>
