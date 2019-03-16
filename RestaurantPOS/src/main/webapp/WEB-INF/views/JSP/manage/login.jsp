<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<!-- Custom Theme files -->
<link href="<c:url value="/css/loginCSS.css"/>" rel="stylesheet" type="text/css" media="all"/>
<!-- Custom Theme files -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<!--Google Fonts-->
<link href='http://fonts.useso.com/css?family=Roboto:500,900italic,900,400italic,100,700italic,300,700,500italic,100italic,300italic,400' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<!--Google Fonts-->
</head>
<body>
<div class="login">
	
	<div class="login-top">
		<h1>管理者登入</h1>
		<p><small><Font color='red' size="-3">${ErrorMsgKey.LoginError}</Font></small></p>
		<!-- Form -->
		<form action="managelogin.check" method="post">
		   	<div>
			<input type="text" value="Account" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'User Id';}" name="mAccount">
			<p><small><Font color='red' size="-3">${ErrorMsgKey.AccountEmptyError}</Font></small></p>
			
			<input type="password" value="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'password';}" name="mPwd">
	    	<p><small><Font color='red' size="-3">${ErrorMsgKey.PasswordEmptyError}</Font></small></p>
	    	</div>
	   		<div class="forgot">	   
	    		<input type="submit" value="Login" >
	    	</div>
	    </form>
	    
	</div>
	<div class="login-bottom">
		<a href="<c:url value="/"/>">回首頁</a>
	</div>
</div>	



</body>
</html>