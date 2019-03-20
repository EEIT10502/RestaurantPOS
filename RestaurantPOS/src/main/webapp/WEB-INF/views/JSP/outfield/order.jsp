<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/images/favicon.png"/>">
    <title>Order</title>
    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/assets/plugins/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<c:url value="/css/OrderStyle.css"/>" rel="stylesheet">
    <!-- You can change the theme colors from here -->
    <link href="<c:url value="/css/colors/blue.css"/>" id="theme" rel="stylesheet">
    <link href="<c:url value="/css/orders.css"/>"  rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mynumkb.css">
    <!--jQuery-->
    <script src="<c:url value="/assets/plugins/jquery/jquery.min.js"/>"></script> 
    <!-- Bootstrap tether Core JavaScript -->
    <script src="<c:url value="/assets/plugins/bootstrap/js/tether.min.js"/>"></script>
    <script src="<c:url value="/assets/plugins/bootstrap/js/bootstrap.min.js"/>"></script>
    <!--Custom JavaScript -->
    <script src="<c:url value="/js/OrderCustom.min.js"/>"></script>
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    
    <script>
	var itemNo = 0;
	
	$(function() {
		// 選類別
		$("#oc1").click(function() {
			$("#cTable").hide();
			$("#pTable").show();
		});
		
		// 上一步回類別
		$("#oBPage").click(function() {
			$("#pTable").hide();
			$('#cTable').show();
		});
		
		// 炒飯點餐
		$("#op1").click(function() {
			$("#opItem1").html($("#op1").val());
			$("#opPrice1").html($("#opHidden1").val());
			$("#opSubtotal1").html($("#opHidden1").val() * $("#opQty1").val());
		});
		
		// 折扣修改
		$("#opQMod1").click(function() {
			$("#opSubtotal1").html($("#opHidden1").val() * $("#opQty1").val());
		});
		
		// 刪除單品
		$("#opDelete1").click(function() {
			$("#ol1").remove();
		});
		
		// 口味調整
		$("#opFlaver1").click(function() {
			$("#fDiv").popover("toggle");
		});
		
		$(".pt").click(function() {			
			var v = $(this).val();
			var total=0;
			var total1=0;
			var total1 = parseInt($("#oTotal").val());			
			
			$.ajax({
			    url: '/RestaurantPOS/order/getPrice',
			    type: 'post',
			    data: {"product" : v},
			    error: function(xhr) {
			      	alert('Ajax request 發生錯誤');
			    },
			    success: function(response) {
			        var vPrice = response.price;
			        var vCategory = response.cate;
			        var vProductNo = response.productNo;
				    var subTotal = vPrice * 1
				
			        var row = '';
					row += '<tr>';
					row += '	<td><input type="button" value="刪除" id="opDelete' + itemNo + '" name="opDelete' + itemNo + '" onclick="delItem(this,'+itemNo+');"></td>';
					row += '	<td id="opItem' + itemNo + '" name="opItem' + itemNo + '">' + v + '</td>';
					row += '	<td id="opPrice' + itemNo + '" name="opPrice' + itemNo + '">' + vPrice + '</td>';					
					
					row += '	<td><input type="number" value="1" min="1" max="10" id="opQty' + itemNo + '" name="orderVos[' + itemNo + '].qty">';
					row += '	<input type="button" value="修改" id="opQMod' + itemNo + '" name="opQMod' + itemNo + '" onclick="modifyQty('+itemNo+',' + vPrice + ');"></td>';
					
					row += '	<td id="opSubtotal' + itemNo + '" name="opSubtotal' + itemNo + '">' + subTotal + '</td>';					
					
									
					row += '	<input type="hidden" id="hidOpItem' + itemNo + '" name="orderVos[' + itemNo + '].itemName" value="' + v + '" />';
					row += '	<input type="hidden" id="hidOpPrice' + itemNo + '" name="orderVos[' + itemNo + '].price" value="' + vPrice + '" />';
					row += '	<input type="hidden" id="hidSubtotal' + itemNo + '" name="orderVos[' + itemNo + '].subTotal" value="' + subTotal + '" />';
					row += '	<input type="hidden" id="hidCategory' + itemNo + '" name="orderVos[' + itemNo + '].category" value="' + vCategory + '" />';
					row += '	<input type="hidden" id="hidProductNo' + itemNo + '" name="orderVos[' + itemNo + '].productNo" value="' + vProductNo + '" />';
					row += '</tr>';
				
					$('#oL1').after(row);
					var subTotal1 = parseInt($("#opSubtotal" + itemNo).html());					
					total= total1 + subTotal1; //總金額加總					
					$("#oTotal").attr("value",total);
					$('#hidoTotal').attr("value",total);
					itemNo++;
				
			    }
			});
			
		});
		//檢查來客數、叫號機號碼是否為空白
		$('#oNext').click(function() {
			var people = $('#oPeople').val();
			var call = $('#oCall').val();
			var total = $('#oTotal').val();
			if (!people) {
				alert('「請輸入用餐人數！！」');
				return;
			}
			if(!call){
				alert('「請輸入叫號機號碼！！」');
				return;
			}
			
			if(call<1 || call >10){
				alert('「叫號機號碼錯誤！！」');
				return;
			}
			
			if(total==0){
				alert('「請輸入餐點！！」')
				return;
			}
			
	
			
			$('#dataForm').submit();
		});
	});
	
	function deltable(){		
		$("#oTotal").attr("value",0);
		$("#tablelist  tr:not(:first):not(:last)").empty("");
	}
	
	function delItem(obj,itemNo) {
		var y = parseInt($("#oTotal").val());
		var totalAmount=0;
		var subtotal= parseInt($("#opSubtotal"+ itemNo).html());
		totalAmount = y - subtotal;
		$("#oTotal").val(totalAmount);
		$(obj).closest('tr').remove();
		
	}
	
	function modifyQty(itemNo, price){
		var qty = parseInt($("#opQty"+ itemNo).val());//修改單品數量，小計連動。
		var subTotal =parseInt($('#opSubtotal' + itemNo).html());
		var subTotal1 = qty*price;
		$('#opSubtotal' + itemNo).html(subTotal1);
		$('#hidSubtotal' + itemNo).val(subTotal1);
		
<<<<<<< HEAD
		var y = parseInt($("#oTotal").val()); //修改單品數量，總金額連動。
=======
		if((subTotal-subTotal1)<0){
		var y = parseInt($("#oTotal").html()); //修改單品數量，總金額連動。
>>>>>>> branch 'master' of https://github.com/EEIT10502/RestaurantPOS.git
		var totalAmount=0;
		totalAmount = y + (qty-1)*price;
		$("#oTotal").val(totalAmount);
		$('#hidoTotal').attr("value",totalAmount);
<<<<<<< HEAD
		
	}
    
    
    
     function ShowTime()
=======
		}
		else{
			qty1 = (subTotal-subTotal1)/price;         //修改單品數量，總金額連動。
			var y = parseInt($("#oTotal").html());
			totalAmount = y-(qty1*price);
			$("#oTotal").html(totalAmount);
			$('#hidoTotal').attr("value",totalAmount);	
		}	
			
		}
	
	
	function riceList() {
		var x = document.getElementById("riceList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	
	function soupList() {
		var x = document.getElementById("soupList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	
	function noodleList() {
		var x = document.getElementById("noodleList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	function vegetableList() {
		var x = document.getElementById("vegetableList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	
	function sidedishList() {
		var x = document.getElementById("sidedishList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	
	function hiddenAllList() {
		var rice = document.getElementById("riceList");
		var noodle = document.getElementById("noodleList");
		var soup = document.getElementById("soupList");
		var vegetable = document.getElementById("vegetableList");
		var sidedish = document.getElementById("sidedishList");
		
		// 		if (allProduct.className.indexOf("hiddenList") == -1) {
		// 			allProduct.className += "hiddenList";
		// 		} 
		if (rice.className.indexOf("hiddenList") == -1) {
			rice.className += "hiddenList";
		}
		
		if (soup.className.indexOf("hiddenList") == -1) {
			soup.className += "hiddenList";
		}
		
		if (noodle.className.indexOf("hiddenList") == -1) {
			noodle.className += "hiddenList";
		}
		
		if (vegetable.className.indexOf("hiddenList") == -1) {
			vegetable.className += "hiddenList";
		}
		
		if (sidedish.className.indexOf("hiddenList") == -1) {
			sidedish.className += "hiddenList";
		}
	}
	
	 function ShowTime()
>>>>>>> branch 'master' of https://github.com/EEIT10502/RestaurantPOS.git
     {
         var NowDate = new Date();
         var d = NowDate.getDay();
         var dayNames = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
         document.getElementById('showbox').innerHTML = '目前時間：' + NowDate.toLocaleString() + '（' + dayNames[d] + '）';
         setTimeout('ShowTime()', 1000);
     }
    
    </script>
    


</head>

<body onload="ShowTime()">
<%-- <a href='<c:url value="/members.pdf"/>' >多筆產品資料查詢(PDF格式)</a><br> --%>
<a href="/RestaurantPOS/members.pdf">多筆產品資料查詢(PDF格式)</a><br>
<form:form id="dataForm" method="post" action="/RestaurantPOS/order/payment" modelAttribute="orderForm">
<%-- 	<form action="/RestaurantPOS/order/payment" method="post" > --%>
		<div class="container-fluid">
			<!-- 標頭 -->
			<div class="row">
				<div class="col-md-9">
					<h4 style="text-align: center">點餐頁面</h4>
				</div>
				<div class="col-md-3" id="showbox">
<!-- 					系統時間 -->
				</div>
			</div>
			<div class="row">
				<div class="col-md-9">
					<table style="margin: 0px auto">
						<tr>
							<td>
								<a href="../index.jsp" />
								<input type="button" value="回首頁" id="oIndex"
								name="oIndex">
								</a>
							</td>
							<td><input type="text" value="" id="oPeople" name="cusFlow">人</td>
							<td><input type="text" value="" id="oCall" name="callNo">號</td>
							<td><input type="reset" value="全部清除" id="oReset"
								name="oReset" onclick="deltable()"></td>
							<td><input type="button" value="下一步" id="oNext" name="oNext"></td>
						</tr>
					</table>
				</div>
			</div>


<body class="fix-header  card-no-border" onload="ShowTime()">
<form:form id="dataForm" method="post" action="/RestaurantPOS/order/payment" modelAttribute="orderForm">
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
    </div>
    <!-- Main wrapper - style you can find in pages.scss -->
    <div id="main-wrapper">
        <!-- Topbar header - style you can find in pages.scss -->
        <header class="topbar">
            <nav class="navbar top-navbar navbar-toggleable-sm navbar-light">              
                <!-- Logo -->                
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.html">
                        <!-- Logo icon -->                        
                        <!-- Light Logo icon -->
                        <b>
                            <img src="<c:url value="/images/logo-light-icon.png"/>" alt="homepage" class="light-logo" />
                        </b>
                        <!--End Logo icon -->
                        <!-- Logo text -->
                        <span>                         
                         <!-- Light Logo text -->    
                         <img src="<c:url value="/images/logo-light-text.png"/>" class="light-logo" alt="homepage" />
                        </span>
                    </a>
                </div>                
                <!-- End Logo -->               
                <div class="navbar-collapse">               
                    <ul class="navbar-nav my-lg-0">                       
                        <li class="nav-item dropdown">
                            <a class="nav-link  text-muted waves-effect waves-dark " id="showbox" ></a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>        
        <!-- End Topbar header -->
       
        <!-- Page wrapper  -->       
        <div class="page-wrapper">            
            <!-- Container fluid  -->            
            <div class="container-fluid">               
                <!-- Bread crumb and right sidebar toggle -->                
                <div class="row page-titles">
                    <div class="col-md-3 col-8 align-self-center">
                        <!--佔位符-->
                    </div>
                    <div class="col-md-3 col-8 align-self-center">
                        <input type="text" value="" id="oPeople" name="cusFlow">&nbsp;&nbsp;<i class="fas fa-user fa-2x"></i>
                    </div>
                    <div class="col-md-3 col-8 align-self-center">
                        <input type="text" value="" id="oCall" name="callNo">&nbsp;&nbsp;<i class="fas fa-desktop fa-2x"></i>
                    </div>
                    <div class="col-md-3 col-8 align-self-center">                         
                      <input type="reset" value="全部清除" id="oReset" name="oReset" onclick="deltable()" class="btn btn-danger">&nbsp;
                      <input type="button" value="下一步" id="oNext" name="oNext" class="btn btn-success">                    
                    </div>
                </div>                
                <!-- End Bread crumb and right sidebar toggle -->                
                <!-- Start Page Content -->               
                <!-- Row -->
                <div class="row">
                    <!-- Column -->
                    <!--左邊清單-->
                    <div class="col-lg-7 col-md-7">
                        <div class="card">
                            <div class="card-block">
                                <div class="row">
                                    <div class="col-lg-12">
                                            <h4 class="card-title">Orders</h4>                                            
                                            <div class="table-responsive">
                                                <table class="table" id="tablelist" >
                                                    
                                                        <tr>
                                                            <th>刪除</th>
                                                            <th>品名</th>
                                                            <th>價格</th>
                                                            <th>數量</th>
                                                            <th>小計</th>                                                            
                                                        </tr>
                                                    
                                                    
                                                        <tr id="oL1" style="display:none"></tr>
                                                        <c:forEach var='orderVos1' items='${orderVos1}' varStatus="idx">
														<tr>	
															<td>															
															<input type="button" value="刪除" id="opDelete' " name="opDelete" onclick="delItem(this)">
															</td>
															<td>
																${orderVos1.itemName}
																<input type="hidden" id="itemName${idx.index}" name="orderVos1[${idx.index}].itemName" value="${orderVos.itemName}">
															<td>
															    ${orderVos1.price}
															     <input type="hidden" id="price${idx.index}" name="orderVos1[${idx.index}].price" value="${orderVos.price}">
															</td>
															<td>
																${orderVos1.qty}
																 <input type="hidden" id="qty${idx.index}" name="orderVos1[${idx.index}].qty" value="${orderVos.qty}">
															</td>
															<td>
																${orderVos1.subTotal}
																<input type="hidden" id="subTotal${idx.index}" name="orderVos1[${idx.index}].subTotal" value="${orderVos.subTotal}">
																<input type="hidden" id="category${idx.index}" name="orderVos1[${idx.index}].category" value="${orderVos.category}">
																<input type="hidden" id="productNo${idx.index}" name="orderVos1[${idx.index}].productNo" value="${orderVos.productNo}">
															<td></td>
															
														</tr>
														</c:forEach>                                                                                                         
                                                    	<tr></tr>
                                                </table>
                                            </div>
                                            <div class="row">
                                            <div class="col-md-6 col-8 align-self-center">
                                            <!--佔位符-->
                                            </div>
                                            <!--底下結帳部分-->
                                            <div class="col-md-6 col-8 align-self-center" >
                                               <p id="countTotal">總計:&nbsp;&nbsp;</p>
                                               <input type="text" id="oTotal" name="oTotal" value="0" readonly>                                               
                                               <input type="hidden" id="hidoTotal" name="totalAmount" value=""/>
                                            </div>                                            
                                            </div>
                                            <!--底下結帳部分-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--右邊菜單-->
                    <div class="col-lg-5 col-md-5">
                            <div class="card">
                            <!-- Nav tabs -->
                            <!--上排類別選項-->
                            <ul class="nav nav-tabs profile-tab" role="tablist">
                                <li class="nav-item"> <a class="nav-link active" data-toggle="tab" href="#riceList" role="tab">飯類</a> </li>
                                <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#noodleList" role="tab">麵類</a> </li>
                                <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#soupList" role="tab">湯類</a> </li>
                                <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#vegetableList" role="tab">菜類</a> </li>
                                <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#sidedishList" role="tab">小菜</a> </li>
                            </ul>
                            <!--下排類別明細-->
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div class="tab-pane active" id="riceList" role="tabpanel">
                                    <div class="card-block">
                                       	<c:forEach var='menu1' items='${menu}'>
										<input style="width: 100px" type="button" class="btn btn-info pt"
											value="${menu1.productName}">
										</c:forEach>
                                    </div>
                                </div>
                                <!--second tab-->
                                <div class="tab-pane" id="noodleList" role="tabpanel">
                                    <div class="card-block">
                                        <c:forEach var='noodle' items='${noodle}'>
										<input style="width: 100px" type="button" class="btn btn-info pt"
											value="${noodle.productName}">
										</c:forEach>                                        
                                    </div>
                                </div>
                                <!--3rd tab-->
                                <div class="tab-pane" id="soupList" role="tabpanel">
                                    <div class="card-block">
                                        <c:forEach var='soup' items='${soup}'>
										<input style="width: 100px" type="button" class="btn btn-info pt"
											value="${soup.productName}">
										</c:forEach>
                                    </div>
                                </div>
                                <!--4th tab-->
                                <div class="tab-pane" id="vegetableList" role="tabpanel">
                                    <div class="card-block">
                                        <c:forEach var='vegetable' items='${vegetable}'>
										<input style="width: 100px" type="button" class="btn btn-info pt"
											value="${vegetable.productName}">
										</c:forEach>
                                    </div>
                                </div>
                                <!--5th tab-->
                                <div class="tab-pane" id="sidedishList" role="tabpanel">
                                    <div class="card-block">
                                        <c:forEach var='sidedish' items='${sidedish}'>
										<input style="width: 100px" type="button" class="btn btn-info pt"
											value="${sidedish.productName}">
										</c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
                <!-- Row -->                
                <!-- End PAge Content -->                
            </div>
        </div>        
        <!-- End Page wrapper  -->        
    </div>   
    <!-- End Wrapper -->
    </form:form>
   	<script src="${pageContext.request.contextPath}/css/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/css/mynumkb.js"></script>
	<script>
		$("#oPeople").mynumkb();
		$("#oCall").mynumkb();
	</script>
</body>
</html>
