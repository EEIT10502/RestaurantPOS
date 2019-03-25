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

<body>
	<div  class="clearfix">
	<div style="display: none;"><jsp:include page="../headerTime.jsp" flush="true" /></div>	
	<jsp:include page="../sideBar.jsp" flush="true" />
	</div>
	<jsp:include page="../productManage/productSearchHead.jsp" flush="true" />
	<section class="">
		<fieldset class="w3-container" style="margin-left: 260px">
			<div id="allList" class="row">
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
									<form class='center' name="editForm${product.pId}" action="${pageContext.request.contextPath}/productManage/allProductListEdit.action/${product.pId}?currentPageNoBtn=${currentPageNo}" method="post">
										<div class="modal-body">
											<input type="hidden" name="pIdEdit" value="${product.pId}" >
											<input type="hidden" name="productNoEdit" value="${product.productNo}" >
											<div class="form-group row">
												<label for="cateEdit${product.pId}" class="col-sm-2 col-form-label">類別</label>
												<div class="col-sm-10">
													<select name="cateEdit" id="cateEdit${product.pId}" class="form-control">
														<c:forEach var='cates' items='${cateList}'>
															<c:if test="${product.cate == cates}">
																<option selected="selected"><c:out value="${cates}" /></option>
															</c:if>
															<c:if test="${product.cate!=cates}" >
																<option><c:out value="${cates}" /></option>
															</c:if>
														</c:forEach>
													</select>
 												</div>
											</div>
											<div class="form-group row">
												<label for="productNameEdit${product.pId}"
													class="col-sm-2 col-form-label">商品名稱</label>
												<div class="col-sm-10">
													<input id="productNameEdit${product.pId}" type="text" class="form-control" name="productNameEdit" value="${product.productName}"/> 
													<span id="productNameEditError${product.pId}"></span>
												</div>
											</div>
											<div class="form-group row">
												<label for="priceEdit${product.pId}"
													class="col-sm-2 col-form-label">價格</label>
												<div class="col-sm-10">
													<input id="priceEdit${product.pId}" type="text" class="form-control" name="priceEdit" value="${product.price}" onkeyup="value=value.replace(/[^\d.]/g,'')">
													<span id="priceEditError${product.pId}"></span>
												</div>
											</div>
											<div class="form-group row">
												<label for="productStatusEdit${product.pId}"
													class="col-sm-2 col-form-label">狀態</label>
												<div class="col-sm-10">
													<select name="productStatusEdit" id="productStatusEdit${product.pId}" class="form-control">
														<c:forEach var='productStatus' items='${productStatusList}'>
															<c:if test="${product.productStatus == productStatus}">
																<option selected="selected"><c:out value="${productStatus}" /></option>
															</c:if>
															<c:if test="${product.productStatus!=productStatus}" >
																<option><c:out value="${productStatus}" /></option>
															</c:if>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">關閉</button>
											<button type="submit" id="submit${product.pId}" class="btn btn-primary">儲存更新</button>
										</div>
									</form>
								</div>

							</div>
						</div>
						<!-- Modal 結束-->
						<script>
							var flag="";
							document.addEventListener("DOMContentLoaded", function() {
								document.getElementById("productNameEdit${product.pId}").addEventListener("blur", function () {
									if (document.getElementById("productNameEdit${product.pId}").value == "") {
										document.getElementById("productNameEditError${product.pId}").innerHTML = "<span style='color:red'><i>商品名稱不可空白";
										if (flag.indexOf("${product.pId}A") == -1) {
											flag = flag.concat("${product.pId}A");
										}													
										document.getElementById("submit${product.pId}").disabled=true;
									} else{
										if (flag.indexOf("${product.pId}A") != -1) {
											flag = flag.replace("${product.pId}A","");
											document.getElementById("productNameEditError${product.pId}").innerHTML = "";
										}
										if (flag.indexOf("${product.pId}A") == -1 && flag.indexOf("${product.pId}B") == -1) {
											document.getElementById("productNameEditError${product.pId}").innerHTML = "";
											document.getElementById("submit${product.pId}").disabled=false;
										}
										
									}
								});
							})

							document.addEventListener("DOMContentLoaded", function() {
								document.getElementById("priceEdit${product.pId}").addEventListener("blur", function () {
									var reg = /[^\d.]/g;
									if (document.getElementById("priceEdit${product.pId}").value == "") {
										document.getElementById("priceEditError${product.pId}").innerHTML = "<span style='color:red'><i>價格不可空白";
										if (flag.indexOf("${product.pId}B") == -1) {
											flag = flag.concat("${product.pId}B");
										}
										document.getElementById("submit${product.pId}").disabled=true;
									} else{
										if (flag.indexOf("${product.pId}B") != -1) {
											flag = flag.replace("${product.pId}B","");
											document.getElementById("priceEditError${product.pId}").innerHTML = "";
										}
										if (flag.indexOf("${product.pId}A") == -1 && flag.indexOf("${product.pId}B") == -1) {
											document.getElementById("priceEditError${product.pId}").innerHTML = "";
											document.getElementById("submit${product.pId}").disabled=false;
										}
									}
								});
							})	
							

						</script>
					</c:forEach>
					<tr>
						<td colspan="6" class="errorMessage" type="redError">${noItemString}</td>
					</tr>
				</table>
			</div>
			<div class="row">
				<div class="col-4">
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
							
						</ul>
					</nav>
				</div>
				<div class="col-4">
					<ul style="list-style-type:none">
						<li class="page-item">第${currentPageNo}頁 /共${totalPages}頁</li>
					</ul>
				</div>
				<div class="col">
					<jsp:include page="../productManage/productFooterPrintExcel.jsp" flush="true" />
				</div>
			</div>
		</fieldset>
	</section>
	
<jsp:include page="../footer.jsp" flush="true" />
</body>
</html>
