<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>每月目標輸入</title>
<!-- Custom Theme files -->
<link href="<c:url value="/css/loginCSS.css"/>" rel="stylesheet" type="text/css" media="all"/>
<!-- Custom Theme files -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
 <style type="text/css">
 h1,a{
 font-family: Microsoft JhengHei;
 }
 
 </style>
</head>
<body>
<div class="login">
	
	<div class="login-top">
		<h1>每月目標營業額</h1>		
		<!-- Form -->
		<form action="<c:url value="/manage/insertTTB.check"/>" method="post">
		   	<div>
			<input type="text" value="請輸入目標營業額" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'User Id';}" name="targetTurnover" 
			onkeyup="value=value.replace(/[^\d.]/g,'')" required="required">			
			</div>
	   		<div class="forgot">	   
	    		<input type="submit" value="送出" >
	    	</div>
	    </form>
	    
	</div>
	<div class="login-bottom">
		<a href="<c:url value="/"/>">回首頁</a>
	</div>
</div>	



</body>
</html>