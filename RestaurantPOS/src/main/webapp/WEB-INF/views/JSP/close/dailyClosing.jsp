<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>DayCheck</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Bootstrap Core CSS -->
<link rel="stylesheet"	href="<c:url value="/css/bootstrap.min.css"/>">
<!-- Custom CSS -->
<link rel="stylesheet"	href="<c:url value="/css/style.css"/>">
<!-- Graph CSS -->
<link rel="stylesheet"	href="<c:url value="/css/font-awesome.css"/>">
<!-- jQuery -->
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- lined-icons -->
<link rel="stylesheet"	href="<c:url value="/css/icon-font.min.css"/>">
<!-- //lined-icons -->
<script src="<c:url value="/js/amcharts.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/serial.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/scripts.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/jquery.nicescroll.js"/>" type="text/javascript"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/js/bootstrap.min.js"/>" type="text/javascript"></script>
<!-- /Bootstrap Core JavaScript -->
<script src="<c:url value="/js/menu_jquery.js"/>" type="text/javascript"></script>
<!--pie-chart--->
<script src="<c:url value="/js/pie-chart.js"/>" type="text/javascript"></script>


<script type="text/javascript">
	$(document).ready(
			function() {
				$('#demo-pie-1').pieChart(
						{
							barColor : '#3bb2d0',
							trackColor : '#eee',
							lineCap : 'round',
							lineWidth : 8,
							onStep : function(from, to, percent) {
								$(this.element).find('.pie-value').text(
										Math.round(percent) + '%');
							}
						});
				var toggle = true;
				$(".sidebar-icon").click(
						function() {
							if (toggle) {
								$(".page-container").addClass(
										"sidebar-collapsed").removeClass(
										"sidebar-collapsed-back");
								$("#menu span").css({
									"position" : "absolute"
								});
							} else {
								$(".page-container").removeClass(
										"sidebar-collapsed").addClass(
										"sidebar-collapsed-back");
								setTimeout(function() {
									$("#menu span").css({
										"position" : "relative"
									});
								}, 400);
							}

							toggle = !toggle;
						});
				
				
				$("#checkout").click(function() {		
			        var moneyReceivedBtn=$("#moneyReceivedBtn").val();
					var turnoverBtn = $("#turnoverBtn").val();
					if($("#moneyReceivedBtn").val()==""){
						alert("請輸入當日實收金額");
					}else{
			        $.ajax({     	
			                type :'POST',
			                url  :'/RestaurantPOS/close/checkDiffAmount.action',
			                data : {
			                	"moneyReceivedInsert" : moneyReceivedBtn,
			                	"totalSalesAmountToday": turnoverBtn
			                },
			                error: function() {
						      	alert('Ajax request 發生錯誤');
						    },
						    success: function(response) {
						    	var Diff = -response;
						    	document.getElementById("afterCheck").innerHTML = Diff + "  元";
						    	document.getElementById("shortoverAmountBtn").value = Diff;

						    	if(Diff != 0){
						    		if(Diff >= 0){
						    			document.getElementById("afterCheckString").innerHTML ="本日有溢收金額";
						    		}
						    		if(Diff <= 0){
						    			document.getElementById("afterCheckString").innerHTML ="本日有短收金額";
						    		}
						    	}else{
						    		document.getElementById("afterCheckString").innerHTML ="當日實收金額 與 當日應收金額 '無'差異";
						    	}
						    }
			            })
					}
			    });
				

			});
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
<style type="text/css">
span.errorMessage[type="redError"] {
	color: red;
}
</style>
</head>
<body onload="ShowTime()">
	<div class="page-container">
		<!--/content-inner-->
		<div class="left-content">
			<div class="inner-content">
			
				<!-- header-starts -->
				<c:import url="../DashBoard/dashHeader.jsp"/>
				<!-- //header-ends -->

				<!--content-->
				<div class="content">
				<div class="women_main">
					<!--//內文<div>從這裡開始-->
					<div class="w_content">
				<form:form method='POST' modelAttribute="CumulativeTurnoverBean"
				class='form-horizontal'>
				<fieldset class="w3-container" style="margin-left: 160px">
					<div class="container">
						<h1>日結清機</h1>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label" for='dateBtn'> 
							當日日期
						</label>
						<div class="col-sm-10">
							${todayString}
							<form:hidden id="dateBtn" path="date" name="iDate" class='form:input-large' /> 
<%-- 							<p class="errorMessage" type="redError">${modelErrors.errorOfProductName} --%>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label" for='turnoverBtn'>
							當日應收金額
						</label>
						<div class="col-sm-10">
							${totalSalesAmountToday}  元
							<form:hidden  id="turnoverBtn" path="turnover" class='form:input-large' name="iTurnover" /> 
<%-- 							<p class="errorMessage" type="redError">${modelErrors.errorOfPrice}${modelErrors.typeErrorOfPrice} --%> 
						</div>

					</div>
					<div class="form-group row">
						<label class='col-sm-2 col-form-label' for="moneyReceivedBtn">
							當日實收金額
						</label>
						<div class='col-sm-10'>
							
<%-- 							<p class="errorMessage" type="redError">${modelErrors.errorOfPrice}${modelErrors.typeErrorOfPrice} --%>
						 	<c:if test="${closingCompletedToday == null}">
						 		<form:input id="moneyReceivedBtn" path="moneyReceived" type='text' class='form:input-large' name="iMoneyReceived" placeholder="請輸入當日實收金額" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
						 		<input type="button" id="checkout" value="check" class="btn btn-info"/>
						 	</c:if>
						 	<c:if test="${closingCompletedToday != null}">
						 		<form:input disabled="true" id="moneyReceivedBtnCompleted" path="moneyReceived" type='text' class='form:input-large' name="iMoneyReceived" placeholder="請輸入當日實收金額" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
						 		<input type="button" disabled="disabled" id="checkoutCompleted" value="check" class="btn btn-info"/>
						 	</c:if>
						</div>
					</div>
					<div class="form-group row">
						<label class='col-sm-2 col-form-label' for="shortoverAmountBtn"> 
							( 短 )溢收金額
						</label>
						<c:if test="${closingCompletedToday == null}">
							<div class='col-sm-10'>
								<form:hidden id="shortoverAmountBtn" path="shortoverAmount" class='form:input-large' name="iShortoverAmount" />
									<span id="afterCheck">尚未輸入當日實收金額</span>
	<%-- 							<p class="errorMessage" type="redError">${modelErrors.errorOfPrice}${modelErrors.typeErrorOfPrice} --%>
							</div>
							<label class='col-sm-2 col-form-label' ></label>
							<div  class='col-sm-10'>
								<span id="afterCheckString" class="errorMessage" type="redError"></span>
							</div>
						</c:if>
						<c:if test="${closingCompletedToday != null}">
							<div class='col-sm-10'>
<%-- 								<form:hidden id="shortoverAmountBtn" path="shortoverAmount" class='form:input-large' name="iShortoverAmount" /> --%>
<!-- 									<span id="afterCheck">尚未輸入當日實收金額</span> -->
	<%-- 							<p class="errorMessage" type="redError">${modelErrors.errorOfPrice}${modelErrors.typeErrorOfPrice} --%>
								${shortoverAmountToday}  元
							</div>
							<label class='col-sm-2 col-form-label' ></label>
							<c:if test="${closingCompletedToday == null}">
								<div  class='col-sm-10'>
									<span id="afterCheckString" class="errorMessage" type="redError"></span>
								</div>
							</c:if>
							<c:if test="${closingCompletedToday != null}">					
									<div  class='col-sm-10'>
										<span id="afterCheckStringCompleted" class="errorMessage" type="redError">${shortoverAmountTodayString}</span>
									</div>
							</c:if>
						</c:if>
					</div>
					<div class="form-group row">
					<label class='col-sm-2 col-form-label' ></label>
					<div class='col-sm-10'>
						<c:if test="${closingCompletedToday == null}">
							<input id="submitClosing" type='submit' class='btn btn-primary' value="日結確認" />
							<input id="resetClosing" type='reset' class='btn btn-primary' value="清除" />
						</c:if>
						<c:if test="${closingCompletedToday != null}">
							<input disabled="disabled" id="submitClosingCompleted" type='submit' class='btn btn-primary' value="日結確認" />
							<input disabled="disabled" id="resetClosingCompleted" type='reset' class='btn btn-primary' value="清除" />
							<span id="afterCheckStringCompleted" class="errorMessage" type="redError">${closingCompletedToday}</span>		
						</c:if>
					</div>
				</div>
				</fieldset>
			</form:form>
					</div>
					<!--//內文</div>從這裡結束-->
					<div class="clearfix"></div>
					
					<!-- footer -->
						<c:import url="../DashBoard/dashFooter.jsp"/>
					<!-- footer -->
				</div>
				<!--content-->
				</div>
			</div>
		</div>
		<!--//content-inner-->
		
				<!--sidebar-->
					<c:import url="../DashBoard/dashSideBar.jsp"/>
				<!--sidebar-->
		<div class="clearfix"></div>
	</div>



</body>
</html>