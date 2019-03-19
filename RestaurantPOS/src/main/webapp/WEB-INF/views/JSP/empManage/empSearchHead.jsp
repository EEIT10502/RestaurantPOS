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

<style type="text/css">
span.errorMessage[type="redError"] {
	color: red;
}
</style>

</head>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
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
			<h1>員工查詢/修改</h1>

			<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
				
				<div class="collapse navbar-collapse" id="navbarNavDropdown">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='allEmployeeList.action?' />">所有員工</a></li>

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownMenuLink" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> 依職位檢視 </a>
							<div class="dropdown-menu"
								aria-labelledby="navbarDropdownMenuLink">
								<c:forEach var='position' items='${empPositionList}'>
									<a class="dropdown-item"
										href="<c:url value='EmployeeListByPosition.action?whichPosition=${position}' />"><c:out
											value="${position}" /></a>
								</c:forEach>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownMenuLink" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> 依員工狀態檢視 </a>
							<div class="dropdown-menu"
								aria-labelledby="navbarDropdownMenuLink">
								<c:forEach var='employeeStatus' items='${empStatusList}'>
									<a class="dropdown-item"
										href="<c:url value='EmployeeListByEmployeeStatus.action?whichStatus=${employeeStatus}' />"><c:out
											value="${employeeStatus}" /></a>
								</c:forEach>
							</div></li>
					</ul>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<form class="form-inline" action="EmployeeListBySearch.action">
						<c:if test="${searchBarString == null}">
							<input class="form-control mr-sm-2" type="search" id="searchBar" name="searchBar"
								placeholder="請依員工名稱搜尋"  aria-label="Search">
						</c:if>
						<c:if test="${searchBarString != null}">
							<input class="form-control mr-sm-2" type="search" id="searchBar" name="searchBar"
								value="${searchBarString}"  aria-label="Search">
						</c:if>
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
						<span class="errorMessage" type="redError">${noSearchBarString}</span>
					</form>
				</div>

			</nav>

		</fieldset>
	</section>
</body>
</html>
