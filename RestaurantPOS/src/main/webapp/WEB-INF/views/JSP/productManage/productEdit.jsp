<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</head>
<title>商品新增</title>
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
<body>
<jsp:include page="../sideBar.jsp" flush="true" /> 
<form action="">
	<div class="w3-container" style="margin-left: 160px">
		<h2>商品新增</h2>
		<div>
		<table>
		<tr>
		<td rowspan="8"><label for="gPicInsert">商品圖片</label><br>
			<input type="file" id="gPicInsert" name="gPicInsert">
			</td>
		</tr>
		<tr><td><label for="gIdInsert">商品編號</label>
			<input type="text" id="gIdInsert" name="gIdInsert"></td></tr>
		<tr><td><label for="gNInsert">商品名稱</label>
			<input type="text" id="gNInsert" name="gNInsert"></td></tr>
		<tr><td><label for="gPInsert">商品價格</label>
			<input type="text" id="gPInsert" name="gCate"></td></tr>
		<tr><td><label for="gCate">商品類別</label>
			<select id="gCate">
				<option>飯類</option>
				<option>麵類</option>
				<option>飲料</option>
				<option>甜點</option>
			</select><br></td></tr>
			
			<tr><td><label for="gdiscount">折扣</label>
			<input type="text" id="gdiscount" name="gdiscount"></td></tr>
			<tr><td><label for="gPDiscount">折後價格</label>
			<input type="text" id="gPDiscount" name="gPDiscount"></td></tr>
			
			
		</div>
		</table>
		<input type="submit" value="確定修改" id="goodsEdit" name="goodsEdit">
			<input type="reset" value="全部清除" id="goodsReset" name="goodsReset">
			<input type="button" value="取消(回前頁)" id="goodsCancel" name="goodsCancel"  onclick="location.href='goodsQuery.jsp'">
			
	
</div>
</form>




</body>
</html>