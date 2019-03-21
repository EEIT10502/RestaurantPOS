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
<body>
<div class="btn-group" role="group" aria-label="Button group with nested dropdown">

  <div class="btn-group" role="group">
  	<c:if test="${noItemString == null}">
  		<c:if test="${noSearchBarString == null}">
	  		<button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	     	 	匯出Excel
	    	</button>
	    </c:if>
	    <c:if test="${noSearchBarString != null}">
	  		<button id="btnGroupDrop1" type="button" disabled="disabled" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	     	 	匯出Excel
	    	</button>
	    </c:if>
    </c:if>
  	<c:if test="${noItemString != null}">
    	<button id="btnGroupDrop1" type="button" disabled="disabled" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
     	 	匯出Excel
    	</button>
    </c:if>
	    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
	      <a class="dropdown-item"  href="<c:url value='excel/allProductList?whichCate=${whichCate}&whichStatus=${whichStatus}&searchBar=${searchBarString}' />">本檢視模式的所有商品</a>
	      <a class="dropdown-item" href="<c:url value='excel/productListThisPage?currentPageNoBtn=${currentPageNo}&whichCate=${whichCate}&whichStatus=${whichStatus}&searchBar=${searchBarString}' />">本頁</a>
	    </div>
  </div>
</div>


</body>
</html>
