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
<title>左側下拉式超連結選單</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script
  src="http://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>
<script>
//打開人資選單
	function openHRList() {
		var x = document.getElementById("demoHRList");
		if (x.className.indexOf("w3-show") == -1) {
			x.className += " w3-show";
			x.previousElementSibling.className += " w3-green";
		} else {
			x.className = x.className.replace(" w3-show", "");
			x.previousElementSibling.className = x.previousElementSibling.className
					.replace(" w3-green", "");
		}
	}
//打開商品選單
	function openGoodsList() {
		var x = document.getElementById("demoGoodsList");
		if (x.className.indexOf("w3-show") == -1) {
			x.className += " w3-show";
			x.previousElementSibling.className += " w3-green";
		} else {
			x.className = x.className.replace(" w3-show", "");
			x.previousElementSibling.className = x.previousElementSibling.className
					.replace(" w3-green", "");
		}
	}
//打開報表選單
	function openReportList() {
		var x = document.getElementById("demoReportList");
		if (x.className.indexOf("w3-show") == -1) {
			x.className += " w3-show";
			x.previousElementSibling.className += " w3-green";
		} else {
			x.className = x.className.replace(" w3-show", "");
			x.previousElementSibling.className = x.previousElementSibling.className
					.replace(" w3-green", "");
		}
	}
</script>

<body>
<!-- 左方菜單開始 -->
	<div class="w3-sidebar w3-bar-block w3-light-grey w3-card"
		style="width: 160px;">
		
		<a href="<c:url value='/'/>" class="w3-bar-item w3-button">回首頁</a>
		
		<!-- 人事管理  -->
		<button class="w3-button w3-block w3-left-align" onclick="openHRList()">人事管理 <i class="fa fa-caret-down"></i></button>
		<div id="demoHRList" class="w3-hide w3-white w3-card">

            <a href="<c:url value="/empManage/empInsert.action"/>" class="w3-bar-item w3-button">新增員工資訊</a>
			<a href="<c:url value="/empManage/empQuery.action"/>" class="w3-bar-item w3-button">查詢員工資訊</a>
			<a href="<c:url value="/empManage/attendance"/>" class="w3-bar-item w3-button">出勤紀錄查詢</a>
			<!-- 			<a href="#" class="w3-bar-item w3-button">員工班表</a> -->
			<!-- 			<a href="#" class="w3-bar-item w3-button">薪資表</a>  -->
		</div>
		
		<!-- 商品管理 -->
		<button class="w3-button w3-block w3-left-align" onclick="openGoodsList()">商品管理 <i class="fa fa-caret-down"></i></button>
		<div id="demoGoodsList" class="w3-hide w3-white w3-card">
			<a href="<c:url value='/productManage/allProductList.action'/>" class="w3-bar-item w3-button">商品查詢/修改</a>
			<a href="<c:url value='/productManage/productInsert.action'/>" class="w3-bar-item w3-button">商品新增</a>
		</div>

		<!-- 報表系統 -->
		<button class="w3-button w3-block w3-left-align" onclick="openReportList()">報表查詢 <i class="fa fa-caret-down"></i></button>
		<div id="demoReportList" class="w3-hide w3-white w3-card"> 
			<a href="<c:url value='/report/dailyReport'/>" class="w3-bar-item w3-button">日報表</a> 
			<a href="<c:url value='/report/categoryReport'/>" class="w3-bar-item w3-button">類別<br>銷售狀況</a>
			<a href="<c:url value='/report/productReport'/>" class="w3-bar-item w3-button">單品<br>銷售狀況</a>
			<a href="<c:url value='/report/goalReport'/>" class="w3-bar-item w3-button">營運目標達成率</a>
		</div>
		
		<a href="<c:url value='/close/dailyClosing.action'/>" class="w3-button w3-block w3-left-align">日結清機</a>
		<a href="#" class="w3-bar-item w3-button">登出</a>
	</div>
<!-- 	左方菜單結束 -->

</body>
</html>
