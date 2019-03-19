<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <!-- Bootstrap Core CSS -->
<link href="<c:url value="/css/bootstrap.min.css"/>" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="<c:url value="/css/IndexStyle.css"/>" rel='stylesheet' type='text/css' />
<link href="<c:url value="/css/font-awesome.css"/>" rel="stylesheet"> 
<!-- jQuery -->
<script src="<c:url value="/js/jquery.min.js"/>"></script>
<!----webfonts--->
<link href='http://fonts.useso.com/css?family=Roboto:400,100,300,500,700,900' rel='stylesheet' type='text/css'>
<!---//webfontss--->  
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<link rel='stylesheet' href='<c:url value="/css/forIndex.css"/>'  type="text/css" />
<!-- 使用微軟正黑體 -->
<link rel="stylesheet" href="<c:url value="/fonts/PageFont.css"/>" type="text/css"/> 
<script type="text/javascript">
//隱藏或顯示計算機
$(document).ready(function(){
	$("#showPunch").click(function(){
		$("#punchTable").toggle();
		$("#logo").toggle();
	
	});
	
});
	
</script>
</head>
<body id="login">
  <div class="login-logo">
   <!--   <a href="index.html"><img src="<c:url value="/images/logo.png"/>" alt=""/></a>--> 
  </div>
  <!--內文從這裡開始-->
  <div class="app-cam" >
    <!--內文-->
    <div class="content_bottom">
      <!--內文左半部-->
      	 <!-- LOGO -->
      <div class="col-md-4 span_4" >
          <div class="r3_counter_box" id="logo">
              <i class="pull-left fa fa-thumbs-up icon-rounded"></i>
              <div class="stats">
                <h5><strong>45%%</strong></h5>
                <span>New Orders</span>
              </div>
          </div>
          <!-- 打卡機 -->
          <div class="r3_counter_box" style="display: none;" id="punchTable">
				<jsp:include page="Calculator.jsp"/>
          </div>
      </div>
      <!--內文右半部-->
      <div class="col-md-8 span_4">
        <div class="col_2">
          <div class="box_1">
          <!-- 點餐系統 -->
          <div class="col-md-6 col_1_of_2 span_1_of_2">
              <a href="<spring:url value='/outfield/order' />">
              <div class="r3_counter_box">
                  <i class="pull-left fa fa-shopping-cart icon-rounded"></i>
                  <div class="stats">
                    <h5><strong>點餐系統</strong></h5>
                  </div>                 
              </div>
              </a>
          </div>
          <!-- 後台管理 -->
          <div class="col-md-6 col_1_of_2 span_1_of_2">
          		
                <a href="<spring:url value='/manage/managelogin' />">
                <div class="r3_counter_box">
                  <i class="pull-left fa fa-user icon-rounded"></i>
                  <div class="stats">
                   <h5><strong>後台管理</strong></h5>
                  </div>
                </div>
                </a>
                
          </div>
          <div class="clearfix"> </div>
        </div>

<<<<<<< HEAD
	
<!-- 	商品管理連結_開始 -->
<!--下面幾行係為了開發方便(在首頁直接出現連結，連到商品、員工管理相關頁面)，故之後確定商品、員工管理入口後再修改 -->
<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<a href="<c:url value="/manage/queryOne"/>">
	<input type="button" value="按我" id="clickMe"/>
	</a>
	<h6><a href="productManage/productInsert.action" >商品管理頁面:productInsert</a></h6>
	<h6><a href="empManage/empInsert">員工管理頁面:empInsert</a></h6>
	<h6><a href="empManage/allEmployeeList.action">員工查詢頁面:empQuery</a></h6>
	<h6><a href="manage/managelogin">管理登入頁面:manageLogin</a></h6>
<!-- 	商品管理連結_結束 -->

<!-- 	sidebar連結_開始 -->
<!--	<h6><a href="sideBar">sidebar頁面:sidebar</a></h6>  -->
<!-- 	sidebar連結_結束 -->
	

=======
        <div class="box_1">
          <!-- 出勤打卡 -->
          <div class="col-md-6 col_1_of_2 span_1_of_2">
              <a href="##" id="showPunch">
              <div class="r3_counter_box">
                  <i class="pull-left fa fa-clock-o icon-rounded"></i>
                  <div class="stats">
                    <h5><strong >出勤打卡</strong></h5>                 
                  </div>
              </div>
              </a>
          </div>
          <!-- 空白格 -->
          <div class="col-md-6 col_1_of_2 span_1_of_2">
                  <a href="<c:url value="/manage/queryOne"/>" >
              	  <div class="r3_counter_box">
                  <i class="pull-left fa fa-clock-o icon-rounded"></i>
                  <div class="stats">
                    <h5><strong >測試列印</strong></h5>                 
                  </div>
	              </div>
	              </a>                   
          </div>
            <div class="clearfix"> </div>
          </div>
          <div class="box_1">
          	  <!-- 空白格 -->
              <div class="col-md-6 col_1_of_2 span_1_of_2">
                  <div class="r3_counter_box">
                  </div>
              </div>
               <!-- 空白格 -->
              <div class="col-md-6 col_1_of_2 span_1_of_2">
                  <div class="r3_counter_box">  
                  </div>
              </div>
              <div class="clearfix"> </div>
            </div>
        </div>
		</div>
    </div>
  </div>
  <!--內文從這裡結束-->
   <div class="clearfix"> </div>
   <div class="copy_layout login">
      <p>Copyright &copy; 2019.Company name All rights reserved.</p>
   </div>
>>>>>>> branch 'master' of https://github.com/EEIT10502/RestaurantPOS.git
</body>
<script>
		//這裡開始是給計算機用的script
		
		//連接字串功能
        function getNum(num){
            $("#showResBox").val(function(i,val){
                return val + num
            })
        }
        //清除全部字串
        function clearRes(){
            document.getElementById("showResBox").value="";
        }
    	//清除前一個字串
        function del(){
            $("#showResBox").val(function(i,val){
                return val.substr(0,val.length-1)
            })
        }
		
    	//取得上下班字串和員工編號 
    	$("[id^='check']").click(function(){
			var Val = $(this).attr("value");
			var Str = $("#showResBox").val();
			
			if($.trim(Str) == ""){   //判斷該欄位不可為空，為空就return
				alert("不可為空!")
				return;
			}
			//測試是否有拿到值
            //alert(Str);
            //alert(Val);
            
            //送出後清空字串
            //$("#showResBox").attr("text","");
           document.getElementById("showResBox").value="";
		   
            //使用ajax 無刷新取得資訊
		$.ajax({
			url:"/RestaurantPOS/schedule/time.check",
			data:{empNO:Str,choice:Val},
			type:"POST",
			
			//失敗
			error:function(xhr) {
			      	alert('Ajax request 發生錯誤');
			      	alert(xhr);
				},
			//成功
			success:function(data){
				//alert('Ajax成功');
				alert(data.msg);
			}
		})
           
		})
</script>
</html>