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


<body>
	<jsp:include page="../sideBar.jsp" flush="true" />
	<jsp:include page="../empManage/empSearchHead.jsp" flush="true" />
	<section class="">
		<fieldset class="w3-container" style="margin-left: 160px">
			<div id="allList" class="">
				<table class="table table-hover">
					<tr>
						<th scope="col">#</th>
						<th scope="col">員工編號</th>
						<th scope="col">姓名</th>
						<th scope="col">職位</th>
						<th scope="col">性別</th>
						<th scope="col">電話</th>
						<th scope="col">地址</th>
						<th scope="col">狀態</th>
						<th scope="col">備註</th>
					</tr>
					<c:forEach var='employee' items='${employeeListBySearch}'
						varStatus="status">
						<tr class="">
							<th scope="row">${status.index + 1+currentBeginOfEmployeeNo}</th>
							<td>${employee.empNo}</td>
							<td>${employee.empName}</td>
							<td>${employee.position}</td>
							<td>${employee.gender}</td>
							<td>${employee.tel}</td>
							<td>${employee.addr}</td>
							<td>${employee.status}</td>
							<td>${employee.remark}</td>
							<td>
								<button type="button" class="btn btn-primary"
									data-toggle="modal"
									data-target="#exampleModalCenter${employee.empId}">修改</button>
							</td>
						</tr>
					
					</c:forEach>
					<tr>
						<td colspan="6" class="errorMessage" type="redError">${noEmployeeString}</td>
					</tr>
				</table>
				<nav aria-label="...">
					<ul class="pagination">
						<c:if test="${currentPageNo <= 1}">
							<li class="page-item disabled"><a class="page-link"
								href="<c:url value='productListBySearch.action?currentPageNoBtnSearch=1&searchBar=${searchBarString}' />"
								tabindex="-1" aria-disabled="true">First Page</a></li>
						</c:if>
						<c:if test="${currentPageNo > 1}">
							<li class="page-item"><a class="page-link"
								href="<c:url value='productListBySearch.action?currentPageNoBtnSearch=1&searchBar=${searchBarString}' />"
								tabindex="-1" aria-disabled="true">First Page</a></li>
						</c:if>
						<c:if test="${currentPageNo <= 1}">
							<li class="page-item disabled"><a class="page-link"
								href="<c:url value='productListBySearch.action?currentPageNoBtnSearch=${currentPageNo-1}&searchBar=${searchBarString}' />"
								tabindex="-1" aria-disabled="true">Previous</a></li>
						</c:if>
						<c:if test="${currentPageNo > 1}">
							<li class="page-item"><a class="page-link"
								href="<c:url value='productListBySearch.action?currentPageNoBtnSearch=${currentPageNo-1}&searchBar=${searchBarString}' />"
								tabindex="-1" aria-disabled="true">Previous</a></li>
						</c:if>

						<c:if test="${currentPageNo != totalPages}">
							<li class="page-item"><a class="page-link"
								href="<c:url value='productListBySearch.action?currentPageNoBtnSearch=${currentPageNo+1}&searchBar=${searchBarString}' />">Next</a>
							</li>
						</c:if>
						<c:if test="${currentPageNo == totalPages}">
							<li class="page-item disabled"><a class="page-link"
								href="<c:url value='productListBySearch.action?currentPageNoBtnSearch=${currentPageNo+1}&searchBar=${searchBarString}' />">Next</a>
							</li>
						</c:if>
						<c:if test="${currentPageNo == totalPages}">
							<li class="page-item disabled"><a class="page-link"
								href="<c:url value='productListBySearch.action?currentPageNoBtnSearch=${totalPages}&searchBar=${searchBarString}' />"
								tabindex="-1" aria-disabled="true">Last Page</a></li>
						</c:if>
						<c:if test="${currentPageNo != totalPages}">
							<li class="page-item"><a class="page-link"
								href="<c:url value='productListBySearch.action?currentPageNoBtnSearch=${totalPages}&searchBar=${searchBarString}' />"
								tabindex="-1" aria-disabled="true">Last Page</a></li>
						</c:if>
						<li class="page-item">第${currentPageNo}頁 /共${totalPages}頁</li>
					</ul>
				</nav>
			</div>
		</fieldset>
	</section>




</body>
</html>
