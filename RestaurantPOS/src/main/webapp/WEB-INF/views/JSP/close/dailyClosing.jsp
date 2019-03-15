<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<title>日結清機</title>
</head>
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
<script type="text/javascript">
$(document).ready(function(){
	$("#checkout").click(function() {		
        var moneyReceivedBtn=$("#moneyReceivedBtn").val();
		var turnoverBtn = $("#turnoverBtn").val();

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
			    	var Dif = response;
			    	document.getElementById("aftercheck").innerHTML = Dif;
			    	document.getElementById("shortoverAmountBtn").value = Dif;
			    	alert('當日實收金額 與 當日應收金額 有差異!!');
			    }
            })
    });
});
</script>
<body>
	<jsp:include page="../sideBar.jsp" flush="true" />
	<section class="">
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
<%-- 							<form:input id="dateBtn" path="date" type='text' --%>
<%-- 								name="iDate" class='form:input-large' /> --%>
<%-- 							<p class="errorMessage" type="redError">${modelErrors.errorOfProductName} --%>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label" for='turnoverBtn'>
							當日應收金額
						</label>
						<div class="col-sm-10">
							${totalSalesAmountToday}  元
							<form:input id="turnoverBtn" path="turnover" type='text'
								class='form:input-large' name="iTurnover" /> 
<%-- 							<p class="errorMessage" type="redError">${modelErrors.errorOfPrice}${modelErrors.typeErrorOfPrice} --%> 
						</div>

					</div>
					<div class="form-group row">
						<label class='col-sm-2 col-form-label' for="moneyReceivedBtn">
							當日實收金額
						</label>
						<div class='col-sm-10'>
							<form:input id="moneyReceivedBtn" path="moneyReceived" type='text'
								class='form:input-large' name="iMoneyReceived" placeholder="請輸入當日實收金額"/>
							<p class="errorMessage" type="redError">${modelErrors.errorOfPrice}${modelErrors.typeErrorOfPrice}
						 	<input type="button" id="checkout" value="check" />
						</div>
					</div>
					<div class="form-group row">
						<label class='col-sm-2 col-form-label' for="shortoverAmountBtn"> 
							( 短 )溢收金額
						</label>
						<div class='col-sm-10'>
							<form:input id="shortoverAmountBtn" path="shortoverAmount" type='text'
								class='form:input-large' name="iShortoverAmount" />
								<span id="aftercheck"></span>
<%-- 							<p class="errorMessage" type="redError">${modelErrors.errorOfPrice}${modelErrors.typeErrorOfPrice} --%>
						</div>
					</div>
					
				</fieldset>
			</form:form>


			<!-- 	========================================================================================= -->
<%-- 			<form> --%>
				<!-- 				<div> -->
				<!-- 					<input type="button" value="回首頁" id="cBack" name="cBack" -->
				<!-- 						onclick="location.href='../index.jsp'"> -->
				<!-- 				</div> -->
<!-- 				<div> -->
<!-- 					<h3>日結清機</h3> -->
<!-- 				</div> -->
<!-- 				<div> -->
<!-- 					<label for="amountM">當日應收金額</label> <input type="text" id="amountM" -->
<!-- 						name="amountM" readonly="readonly"> -->
<!-- 					<p> -->
<!-- 						<label for="actualM">實收金額</label> <input type="text" id="actualM " -->
<!-- 							name="actualM"> -->
<!-- 					<p> -->
<!-- 						<label for="differenceM">短/溢收</label> <input type="text" -->
<!-- 							id="differenceM" name="differenceM"> -->
<!-- 					<p> -->
<!-- 				</div> -->
<!-- 				<div> -->
<!-- 					<input type="submit" value="確定" id="cOK" name="cOK"> <input -->
<!-- 						type="reset" value="重設" id="cReset" name="cReset"> -->
<!-- 				</div> -->
<%-- 			</form> --%>
<!-- 		</fieldset> -->
	</section>
</body>
</html>