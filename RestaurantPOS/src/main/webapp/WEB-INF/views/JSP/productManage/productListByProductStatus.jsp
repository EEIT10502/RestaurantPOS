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

td.errorMessage[type="redError"] {
	color: red;
}
.hiddenList {
	display: none;
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
	<jsp:include page="../sideBar.jsp" flush="true" />
	<jsp:include page="../productManage/productSearchHead.jsp" flush="true" />
	<section class="">
		<fieldset class="w3-container" style="margin-left: 160px">
			<div id="allList" class="">
				<table class="table table-hover">
					<tr>
						<th scope="col">#</th>
						<th scope="col">類別</th>
						<th scope="col">商品編號</th>
						<th scope="col">商品名稱</th>
						<th scope="col">價格</th>
						<th scope="col">狀態</th>
					</tr>
					<c:forEach var='product' items='${productsListGetByProductStatus}'
						varStatus="status">
						<tr class="">
							<th scope="row">${status.index + 1+currentBeginOfItemNo}</th>
							<td>${product.cate}</td>
							<td>${product.productNo}</td>
							<td>${product.productName}</td>
							<td>${product.price}</td>
							<td>${product.productStatus}</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="6" class="errorMessage" type="redError">${noItemString}</td>
					</tr>
				</table>
				<nav aria-label="...">
					<ul class="pagination">
						<c:if test="${currentPageNo <= 1}">
							<li class="page-item disabled"><a class="page-link"
								href="<c:url value='ProductListByProductStatus.action?currentPageNoBtnProductStatus=1&whichStatus=${whichStatus}' />"
								tabindex="-1" aria-disabled="true">First Page</a></li>
						</c:if>
						<c:if test="${currentPageNo > 1}">
							<li class="page-item"><a class="page-link"
								href="<c:url value='ProductListByProductStatus.action?currentPageNoBtnProductStatus=1&whichStatus=${whichStatus}' />"
								tabindex="-1" aria-disabled="true">First Page</a></li>
						</c:if>
						<c:if test="${currentPageNo <= 1}">
							<li class="page-item disabled"><a class="page-link"
								href="<c:url value='ProductListByProductStatus.action?currentPageNoBtnProductStatus=${currentPageNo-1}&whichStatus=${whichStatus}' />"
								tabindex="-1" aria-disabled="true">Previous</a></li>
						</c:if>
						<c:if test="${currentPageNo > 1}">
							<li class="page-item"><a class="page-link"
								href="<c:url value='ProductListByProductStatus.action?currentPageNoBtnProductStatus=${currentPageNo-1}&whichStatus=${whichStatus}' />"
								tabindex="-1" aria-disabled="true">Previous</a></li>
						</c:if>

						<c:if test="${currentPageNo != totalPages}">
							<li class="page-item"><a class="page-link"
								href="<c:url value='ProductListByProductStatus.action?currentPageNoBtnProductStatus=${currentPageNo+1}&whichStatus=${whichStatus}' />">Next</a>
							</li>
						</c:if>
						<c:if test="${currentPageNo == totalPages}">
							<li class="page-item disabled"><a class="page-link"
								href="<c:url value='ProductListByProductStatus.action?currentPageNoBtnProductStatus=${currentPageNo+1}&whichStatus=${whichStatus}' />">Next</a>
							</li>
						</c:if>
						<c:if test="${currentPageNo == totalPages}">
							<li class="page-item disabled"><a class="page-link"
								href="<c:url value='ProductListByProductStatus.action?currentPageNoBtnProductStatus=${totalPages}&whichStatus=${whichStatus}' />"
								tabindex="-1" aria-disabled="true">Last Page</a></li>
						</c:if>
						<c:if test="${currentPageNo != totalPages}">
							<li class="page-item"><a class="page-link"
								href="<c:url value='ProductListByProductStatus.action?currentPageNoBtnProductStatus=${totalPages}&whichStatus=${whichStatus}' />"
								tabindex="-1" aria-disabled="true">Last Page</a></li>
						</c:if>
						<li class="page-item">第${currentPageNo}頁 /共${totalPages}頁</li>
					</ul>
				</nav>
			</div>
		</fieldset>
	</section>


	<!-- ================================================================= -->
	<!-- 	<form action="" method='POST'> -->
	<!-- 		<div class="w3-container" style="margin-left: 160px"> -->
	<!-- 			<h2>商品查詢/修改</h2> -->
	<!-- 			<div> -->
	<!-- 				<label for="searchType"></label> <select id="searchType"> -->
	<!-- 					<option>商品名稱</option> -->
	<!-- 					<option>商品編號</option> -->
	<!-- 					<option>類別</option> -->
	<!-- 				</select> <label for=""></label> <select id=""> -->
	<!-- 					<option>已上架</option> -->
	<!-- 					<option>未上架</option> -->
	<!-- 				</select> <input type="text" id="goodsSearch" name="goodsSearch"> <input -->
	<!-- 					type="submit" value="查詢" id="dSel" name="dSel"> -->
	<!-- 			</div> -->
	<!-- 			<div> -->
	<!-- 				<input type="button" value="匯出檔案" id="dExport" name="dExport"> -->
	<!-- 			</div> -->
	<!-- 			<table border="1"> -->
	<!-- 				<tr> -->
	<!-- 					<th>序號</th> -->
	<!-- 					<th>商品編號</th> -->
	<!-- 					<th>商品名稱</th> -->
	<!-- 					<th>類別</th> -->
	<!-- 					<th>價格</th> -->
	<!-- 					<th>狀態</th> -->

	<!-- 				</tr> -->
	<%-- 						<c:forEach var="gTable" items="">
<%-- 				<tr> --%>
	<%-- 					<td></td> --%>
	<%-- 					<td></td> --%>
	<%-- 					<td></td> --%>
	<%-- 					<td></td> --%>
	<%-- 					<td></td> --%>
	<%-- 				</tr> --%>
	<%-- 		</c:forEach> --%>
	--%>
	<!-- 			</table> -->

	<!-- 			<input type="button" value="上一頁" id="dBPage" name="dBPage"> <input -->
	<!-- 				type="button" value="下一頁" id="dNPage" name="dNPage"> -->
	<!-- 		</div> -->
	<!-- 	</form> -->
	<!-- 	</div> -->
	<!-- 	</form> -->


</body>
</html>
