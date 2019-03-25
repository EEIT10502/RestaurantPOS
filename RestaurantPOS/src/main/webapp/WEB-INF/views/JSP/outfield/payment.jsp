<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib  uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/images/favicon.png"/>">
<title>Payment</title>
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
<style type="text/css">
*{
font-family: Microsoft JhengHei ;
font-weight:bold;
}
</style>

<script>

function ShowTime()
{
    var NowDate = new Date();
    var d = NowDate.getDay();
    var dayNames = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    document.getElementById('showbox').innerHTML = '目前時間：' + NowDate.toLocaleString() + '（' + dayNames[d] + '）';
    setTimeout('ShowTime()', 1000);
}


$(function(){
	$('input#oReceived').on('blur',function() {
		var received = parseInt($('#oReceived').val());
		var totalAmount = parseInt(${totalAmount});
		var change = received - totalAmount ;
		$('#oChange').val(change + "元");
	});
	
	$('#oNext').click(function() {
		var received = parseInt($('#oReceived').val());
		var totalAmount = parseInt(${totalAmount});
		var change = received - totalAmount ;
		if(change<0){
			alert("「收現金額不足」");
			return;
		}else{
			$('#form1').submit();
		}
		
		
	});
});

	
</script>

</head>
<body class="fix-header  card-no-border" onload="ShowTime()">
	<form:form method="post" action="/RestaurantPOS/order/confirmPayment" modelAttribute="orderForm" id="form1">

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
                    <a class="navbar-brand" href="<c:url value="/"/>"> <!-- Logo icon -->
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
                        <input type="text" value="${cusFlow}" id="oPeople" readonly name="oPeople">&nbsp;&nbsp;<i class="fas fa-user fa-2x" ></i>
                   		<input type="hidden" id="cusFlow" name="cusFlow" value="${cusFlow}">
                    </div>
                    <div class="col-md-3 col-8 align-self-center">                    
                        <input type="text" value="${callNo}" id="oCall"  readonly name="oCall">&nbsp;&nbsp;<i class="fas fa-desktop fa-2x" ></i>
                   		<input type="hidden" id="callNo" name="callNo" value="${callNo}">
                    </div>
                    <div class="col-md-3 col-8 align-self-center">                         
                        <!--佔位符-->
                    </div>
                </div>                
                <!-- End Bread crumb and right sidebar toggle -->                
                <!-- Start Page Content -->               
                <!-- Row -->
                <div class="row">
                    <!-- Column -->
                    <!--清單-->
                    <div class="col-lg-7 col-md-7" style="margin: 0px auto">
                        <div class="card">
                            <div class="card-block">
                                <div class="row">
                                    <div class="col-lg-12">
                                            <h4 class="card-title"></h4>                                            
                                            <div class="table-responsive">
                                                <table class="table" id="tablelist" >
                                                    <thead>
                                                        <tr>                                                           
                                                            <th>品名</th>
                                                            <th>價格</th>
                                                            <th>數量</th>
                                                            <th>小計</th>                                                            
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                            <c:forEach var='orderVos' items='${orderVos}' varStatus="idx">
							<tr>	
								<td>
									${orderVos.itemName}
									<input type="hidden" id="itemName${idx.index}" name="orderVos1[${idx.index}].itemName" value="${orderVos.itemName}">
								</td>
								<td>
								    ${orderVos.price}
								    <input type="hidden" id="price${idx.index}" name="orderVos1[${idx.index}].price" value="${orderVos.price}">
								</td>
								<td>
									${orderVos.qty}
									 <input type="hidden" id="qty${idx.index}" name="orderVos1[${idx.index}].qty" value="${orderVos.qty}">
								</td>
								<td>
									${orderVos.subTotal}
									 <input type="hidden" id="subTotal${idx.index}" name="orderVos1[${idx.index}].subTotal" value="${orderVos.subTotal}">
									 <input type="hidden" id="category${idx.index}" name="orderVos1[${idx.index}].category" value="${orderVos.category}">
									 <input type="hidden" id="productNo${idx.index}" name="orderVos1[${idx.index}].productNo" value="${orderVos.productNo}">
								</td>							
							</tr>
						    </c:forEach>
                                                                                                        
                                                    </tbody>
                                                </table>
                                            </div>
                                             <!--底下結帳部分-->
                                            <div class="row">
                                            <div class="col-md-4 col-8 align-self-center">
                                                <p id="" class="inLine">收:&nbsp;&nbsp;</p>
                                                <input type="text" id="oReceived" name="oReceived"  class="inLine" style="width:150px">                                               
              
                                            </div>
                                           
                                            <div class="col-md-4 col-8 align-self-center" >
                                               <p id="" class="inLine">總計:&nbsp;&nbsp;</p>
                                               <input type="text" id="oTotal" name="oTotal" value="${totalAmount}" class="inLine" style="width:150px"  readonly>                                              
                                               
                                               <input type="hidden" id="totalAmount" name="totalAmount" value="${totalAmount}">
                                              	
                                            </div>   
                                            
                                            <div class="col-md-4 col-8 align-self-center" >
                                                <p id="" class="inLine">找零:&nbsp;&nbsp;</p>
                                                <input type="text" id="oChange" name="oChange" value="0" class="inLine" style="width:150px"  readonly>                                               
                                                
                                            </div>   
                                            </div>
                                            <!--底下結帳部分-->
                                            <div class="row">
                                                <div class="col-md-3 col-8 align-self-center"></div>


                                                <div class="col-md-3 col-8 align-self-center"></div>
                                                <div class="col-md-3 col-8 align-self-center"></div>
                                                <div class="col-md-3 col-8 align-self-center">
                                                	<div class="row">
                                                		<input type="button" value="結帳" id="oNext" name="oNext" 
                                                        class="btn btn-success btn-lg">&nbsp;
                                                        <input type="reset" value="取消" id="pBack" name="pBack"
                                                   	    onclick="location.href='/RestaurantPOS/outfield/cancelOrder'"
                                                        class="btn btn-danger btn-lg">    
                                                    </div>
                                                </div>
                                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--右邊菜單-->
                    
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
	<script> -->
		$("#oReceived").mynumkb();
	</script> 
</body>
</html>