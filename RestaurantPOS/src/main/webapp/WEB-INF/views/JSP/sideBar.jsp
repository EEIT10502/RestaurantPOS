<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel='stylesheet' href="<c:url value="/fonts/PageFont.css"/>">
<link rel='stylesheet' href="<c:url value="/css/DashBoard.css"/>">
<link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
</head>
<title>左側下拉式超連結選單</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="<c:url value="/js/sideBar.js"/>"  type="text/javascript"></script>

<body>
<!-- 左方菜單開始 -->
	<div class="w3-sidebar w3-bar-block sideColor w3-card sideWidth"	>
		
		<a href="<c:url value='/toDashBoard'/>" class="w3-button w3-block w3-left-align sideBarColHight"><i class="fa fa-tachometer"></i>&nbsp&nbsp<span>回後台首頁</span></a>
		
		<!-- 人事管理  -->
		<button class="w3-button w3-block w3-left-align sideBarColHight" onclick="openHRList()"><i class="lnr lnr-users"></i>&nbsp&nbsp<span>人事管理</span>&nbsp<i class="fa fa-caret-down"></i></button>
		<div id="demoHRList" class="w3-hide w3-white w3-card">


            <a href="<c:url value="/empManage/empInsert.action"/>" class="w3-bar-item w3-btn">新增員工資訊</a>
			<a href="<c:url value="/empManage/allEmployeeList.action"/>" class="w3-bar-item w3-btn">查詢員工資訊</a>
			<a href="<c:url value="/empManage/attendance"/>" class="w3-bar-item w3-btn">出勤紀錄查詢</a>
			<!-- 			<a href="#" class="w3-bar-item w3-button">員工班表</a> -->
			<!-- 			<a href="#" class="w3-bar-item w3-button">薪資表</a>  -->
		</div>
		
		<!-- 商品管理 -->
		<button class="w3-button w3-block w3-left-align sideBarColHight" onclick="openGoodsList()"><i class="lnr lnr-gift"></i>&nbsp&nbsp<span>商品管理</span>&nbsp <i class="fa fa-caret-down"></i></button>
		<div id="demoGoodsList" class="w3-hide w3-white w3-card">
			<a href="<c:url value='/productManage/allProductList.action'/>" class="w3-bar-item w3-btn">商品查詢/修改</a>
			<a href="<c:url value='/productManage/productInsert.action'/>" class="w3-bar-item w3-btn">商品新增</a>
		</div>
		
		<button class="w3-button w3-block w3-left-align sideBarColHight" onclick="openScheduleList()"><i	class="fa fa-table"></i>&nbsp&nbsp<span>排班管理</span>&nbsp <i class="fa fa-caret-down"></i></button>
		<div id="demoScheduleList" class="w3-hide w3-white w3-card">
			<a href="schedule" class="w3-bar-item w3-btn">班別管理</a>
			<a href="calendar" class="w3-bar-item w3-btn">排班管理</a>
		</div>

		<!-- 報表系統 -->
		<button class="w3-button w3-block w3-left-align sideBarColHight" onclick="openReportList()"><i	class="lnr lnr-chart-bars"></i>&nbsp&nbsp<span>報表查詢</span>&nbsp <i class="fa fa-caret-down"></i></button>
		<div id="demoReportList" class="w3-hide w3-white w3-card"> 
			<a href="<c:url value='/report/dailyReport'/>" class="w3-bar-item w3-btn">日報表</a> 
			<a href="<c:url value='/report/categoryReport'/>" class="w3-bar-item w3-btn">類別銷售狀況</a>
			<a href="<c:url value='/report/productReport'/>" class="w3-bar-item w3-btn">單品銷售狀況</a>
			<a href="<c:url value='/report/goalReport'/>" class="w3-bar-item w3-btn">營運目標達成率</a>
		</div>
		
		<a href="<c:url value='/close/dailyClosing.action'/>" class="w3-button w3-block w3-left-align sideBarColHight"><i class="lnr lnr-leaf"></i>&nbsp&nbsp<span>日結清機</span>&nbsp</a>
		<a href="<c:url value='/'/>" class="w3-button w3-block w3-left-align sideBarColHight"><i class="fa fa-tachometer"></i>&nbsp&nbsp<span>回系統首頁</span>&nbsp</a>
	</div>
<!-- 	左方菜單結束 -->

</body>
</html>
