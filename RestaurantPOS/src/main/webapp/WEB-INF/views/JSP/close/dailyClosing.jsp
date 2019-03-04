<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<title>日結清機</title>
</head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<body>
<form>
<div>
	<input type="button" value="回首頁" id="cBack" name="cBack" onclick="location.href='../index.jsp'">
</div>
<div>
	<h3>日結清機</h3>
</div>
<div>
	<label for="amountM">當日應收金額</label>
	<input type="text" id="amountM" name="amountM" readonly="readonly"><p>
	<label for="actualM">實收金額</label>
	<input type="text" id="actualM " name="actualM"><p>
	<label for="differenceM">短/溢收</label>
	<input type="text" id="differenceM" name="differenceM"><p>
</div>
<div>
	<input type="submit" value="確定" id="cOK" name="cOK">
	<input type="reset" value="重設" id="cReset" name="cReset">
</div>
</form>
</body>
</html>