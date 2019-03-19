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
</head>
<title>後台首頁</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
function ShowTime() {
	var NowDate = new Date();
	var d = NowDate.getDay();
	var dayNames = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
			"星期六");
	document.getElementById('showbox').innerHTML = '目前時間：'
			+ NowDate.toLocaleString() + '（' + dayNames[d] + '）';
	setTimeout('ShowTime()', 1000);
}
</script>

<body onload="ShowTime()">
		<div  class="clearfix">
    	<div class="header-section float-right" >
			<!-- top_bg  -->
			<div class="top_bg">
				<div class="header_top">
					<div class="top_left">
						<p id="showbox"></p>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<div class="clearfix"></div>
			<!-- /top_bg -->
		</div>
		<div class="col-4 float-left">
			<jsp:include page="sideBar.jsp" flush="true" />
		</div>
		</div>
		
		
		<div class="col-8">
		
		</div>
	
	<jsp:include page="footer.jsp" flush="true"/>
	
</body>
</html>
