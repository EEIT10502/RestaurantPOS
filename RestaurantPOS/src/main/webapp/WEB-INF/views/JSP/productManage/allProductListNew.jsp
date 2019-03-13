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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>sideBar</title>
<style type="text/css">
.hiddenList {
	display: none;
}

td.errorMessage[type="redError"] {
	color: red;
}
</style>

</head>
<!-- <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script> -->
<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" -->
<!-- 	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" -->
<!-- 	crossorigin="anonymous"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>
<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" -->
<!-- 	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" -->
<!-- 	crossorigin="anonymous"></script> -->
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
					<c:forEach var='product' items='${productsListGetByPage}'
						varStatus="status">
						<tr class="">
							<th scope="row">${status.index + 1+currentBeginOfItemNo}</th>
							<td>${product.cate}</td>
							<td>${product.productNo}</td>
							<td>${product.productName}</td>
							<td>${product.price}</td>
							<td>${product.productStatus}</td>
							<td>
								<button type="button" class="btn btn-primary"
									data-toggle="modal"
									data-target="#exampleModalCenter${product.pId}">修改</button>
							</td>
						</tr>
						<!-- Modal 開始-->
						<div class="modal fade" id="exampleModalCenter${product.pId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLongTitle">商品修改</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>

									<form:form class='center' action="${pageContext.request.contextPath}/productManage/allProductListEdit.action/${product.pId}" modelAttribute="MenuBean" method="put">
										<div class="modal-body">
											<%-- 												<form> --%>
											<input type="hidden" name="_method" id='put' value="">
											    <input type="" name="pId" value="${product.pId}" >
											    <input type="" name="productNo" value="${product.productNo}" >
<%-- 											    <input type="hidden" name="productName" value="${product.productName}" > --%>
											<!--     <input type="hidden" name="finalDecision" value="" >  -->

											<div class="form-group row">
												<label for="cateEdit${product.pId}"
													class="col-sm-2 col-form-label">類別</label>
												<div class="col-sm-10">
													<form:select path="cate" id="cateEdit${product.pId}"
														class="form-control">
														<form:option value="${product.cate}">${product.cate}</form:option>
														<form:options items="${cateList}" />
													</form:select>
													<%--       <form:input path="name" type="text" class="form-control" id="cateEdit${product.pId}" value="${product.cate}"> --%>
												</div>
											</div>
											<div class="form-group row">
												<label for="productNameEdit${product.pId}"
													class="col-sm-2 col-form-label">商品名稱</label>
												<div class="col-sm-10">
<%-- 													<form:input id="productNameEdit${product.pId}" path="productName" type='text' name="productName" class='form-control' /> --%>
													    <input type="text" class="form-control" id="productNameEdit${product.pId}" value="${product.productName}"/> 
												</div>
											</div>
											<div class="form-group row">
												<label for="priceEdit${product.pId}"
													class="col-sm-2 col-form-label">價格</label>
												<div class="col-sm-10">
<%-- 													<form:input id="priceEdit${product.pId}" path="price" type='text' class='form-control' name="iPrice" /> --%>
													    <input type="text" class="form-control" id="priceEdit${product.pId}" value="${product.price}">
												</div>
											</div>
											<div class="form-group row">
												<label for="productStatusEdit${product.pId}"
													class="col-sm-2 col-form-label">狀態</label>
												<div class="col-sm-10">
													<form:select path="productStatus"
														id="productStatusEdit${product.pId}" class="form-control">
														<form:option value="${product.productStatus}">${product.productStatus}</form:option>
														<form:options items="${productStatusList}" />
													</form:select>
													<%-- 	     <select class="form-control" id="inlineFormCustomSelectPref"> --%>
													<%-- 		    <option selected>${product.productStatus}</option> --%>
													<%-- 		    <option items="${productStatusList}" /> --%>
													<%-- 	  	</select> --%>
												</div>
												<!--     <div class="col-sm-10"> -->
												<%--       <input type="text" class="form-control" id="priceEdit${productStatus.pId}" value="${product.productStatus}"> --%>
												<!--     </div> -->
											</div>

											<!--   <div class="form-group row"> -->
											<!--     <div class="col-sm-10"> -->
											<!--       <button type="submit" class="btn btn-primary">Sign in</button> -->
											<!--     </div> -->
											<!--   </div> -->
											<%-- </form> --%>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">關閉</button>
											<button type="submit" class="btn btn-primary">儲存更新</button>
										</div>
									</form:form>
								</div>

							</div>
						</div>

						<!-- Modal 結束-->
					</c:forEach>
					<tr>
						<td colspan="6" class="errorMessage" type="redError">${noItemString}</td>
					</tr>
				</table>
				<nav aria-label="...">
					<ul class="pagination">
						<c:if test="${currentPageNo <= 1}">
							<li class="page-item disabled"><a class="page-link"
								href="<c:url value='allProductList.action?currentPageNoBtn=1' />"
								tabindex="-1" aria-disabled="true">First Page</a></li>
						</c:if>
						<c:if test="${currentPageNo > 1}">
							<li class="page-item"><a class="page-link"
								href="<c:url value='allProductList.action?currentPageNoBtn=1' />"
								tabindex="-1" aria-disabled="true">First Page</a></li>
						</c:if>
						<c:if test="${currentPageNo <= 1}">
							<li class="page-item disabled"><a class="page-link"
								href="<c:url value='allProductList.action?currentPageNoBtn=${currentPageNo-1}' />"
								tabindex="-1" aria-disabled="true">Previous</a></li>
						</c:if>
						<c:if test="${currentPageNo > 1}">
							<li class="page-item"><a class="page-link"
								href="<c:url value='allProductList.action?currentPageNoBtn=${currentPageNo-1}' />"
								tabindex="-1" aria-disabled="true">Previous</a></li>
						</c:if>

						<c:if test="${currentPageNo != totalPages}">
							<li class="page-item"><a class="page-link"
								href="<c:url value='allProductList.action?currentPageNoBtn=${currentPageNo+1}' />">Next</a>
							</li>
						</c:if>
						<c:if test="${currentPageNo == totalPages}">
							<li class="page-item disabled"><a class="page-link"
								href="<c:url value='allProductList.action?currentPageNoBtn=${currentPageNo+1}' />">Next</a>
							</li>
						</c:if>
						<c:if test="${currentPageNo == totalPages}">
							<li class="page-item disabled"><a class="page-link"
								href="<c:url value='allProductList.action?currentPageNoBtn=${totalPages}' />"
								tabindex="-1" aria-disabled="true">Last Page</a></li>
						</c:if>
						<c:if test="${currentPageNo != totalPages}">
							<li class="page-item"><a class="page-link"
								href="<c:url value='allProductList.action?currentPageNoBtn=${totalPages}' />"
								tabindex="-1" aria-disabled="true">Last Page</a></li>
						</c:if>
						<li class="page-item">第${currentPageNo}頁 /共${totalPages}頁</li>
					</ul>
				</nav>
			</div>


		</fieldset>
	</section>
	<section></section>

</body>
</html>
