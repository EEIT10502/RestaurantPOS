<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<title>日結清機</title>
<style type="text/css">
span.errorMessage[type="redError"] {
	color: red;
}
</style>
</head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
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
			      	alert('日結清機發生錯誤!');
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
// $(document).ready(function(){
// 		var moneyReceivedBtn=$("#moneyReceivedBtn").val();
// 		if($("#moneyReceivedBtn").val()==""){
// 			$('#submitClosing').attr('disabled', true);
// 		}
// 		if($("#moneyReceivedBtn").val()!=""){
// 			$('#submitClosing').attr('disabled', false);
// 		}
// });
// ${shortoverAmountTodayString != null}

$(document).ready(function(){
	<c:set var="shortovercheck" value="${shortoverAmountTodayString}"/>
	var shortovercheck="${shortovercheck}";
	var moneyReceivedBtn=$("#moneyReceivedBtn").val();
	if($("#moneyReceivedBtn").val()==""){
		$('#submitClosing').attr('disabled', true);
	}
	if($("#moneyReceivedBtn").val()!=""){
		$('#submitClosing').attr('disabled', false);
	}
	$("#moneyReceivedBtn").blur(function() {
		var moneyReceivedBtn=$("#moneyReceivedBtn").val();
		if($("#moneyReceivedBtn").val()=="" || shortovercheck == null){
			$('#submitClosing').attr('disabled', true);
		}
		if($("#moneyReceivedBtn").val()!="" && shortovercheck != null){
			$('#submitClosing').attr('disabled', false);
		}
		
    });
	
});
</script>
<body>
	<div  class="clearfix">
	<jsp:include page="../headerTime.jsp" flush="true" />	
	<jsp:include page="../sideBar.jsp" flush="true" />
	</div>
	<section class="">
			<form:form method='POST' modelAttribute="CumulativeTurnoverBean"
				class='form-horizontal'>
				<fieldset class="w3-container" style="margin-left: 260px">
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
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label" for='turnoverBtn'>
							當日應收金額
						</label>
						<div class="col-sm-10">
							${totalSalesAmountToday}  元
							<form:hidden  id="turnoverBtn" path="turnover" class='form:input-large' name="iTurnover" /> 
						</div>
					</div>
					<div class="form-group row">
						<label class='col-sm-2 col-form-label' for="moneyReceivedBtn">
							當日實收金額
						</label>
						<div class='col-sm-10'>							
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
							</div>
							<label class='col-sm-2 col-form-label' ></label>
							<div  class='col-sm-10'>
								<span id="afterCheckString" class="errorMessage" type="redError"></span>
							</div>
						</c:if>
						<c:if test="${closingCompletedToday != null}">
							<div class='col-sm-10'>
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
<%-- 							<c:if test="${shortoverAmountTodayString != null}"> --%>
								<input id="submitClosing" type='submit' class='btn btn-primary' value="日結確認" />
								<input id="resetClosing" type='reset' class='btn btn-primary' value="清除" />
<%-- 							</c:if> --%>
<%-- 							<c:if test="${shortoverAmountTodayString == null}"> --%>
<!-- 								<input disabled="disabled" id="submitClosing" type='submit' class='btn btn-primary' value="日結確認" /> -->
<!-- 								<input disabled="disabled" id="resetClosing" type='reset' class='btn btn-primary' value="清除" /> -->
<%-- 							</c:if> --%>
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
	</section>
	<jsp:include page="../footer.jsp" flush="true" />
</body>
</html>