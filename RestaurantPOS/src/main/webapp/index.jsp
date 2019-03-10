<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<title>首頁</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/forIndex.css'  type="text/css" />
<style>
#content{
width: 322px;
}

button{
width: 80px;
height: 70px;

}

</style>
<script type="text/javascript">
//隱藏或顯示計算機
$(document).ready(function(){
	$("#showPunch").click(function(){
		$("#punchTable").toggle();
		$("#logo").toggle();
		
	})
})
	
</script>
</head>
<body>
<form>
	<table border="1">
		<tr>
			<td rowspan="3" id="logo" name="logo" id="logo" name="logo">LOGO</td>
			<td rowspan="3" id="punchTable" style="display:none;">
         	<jsp:include page="Calculator.jsp"/>
			</td>
			<!-- 進入點餐畫面 -->
			<td>
			<a href="<spring:url value='/outfield/order' />">
			<input type="button" value="點餐"  id="toOrderPage" name="toOrderPage">
			</a>
		
			</td>
			<td>
			<input type="button" value="日結" onclick="location.href='close/dailyClosing.jsp'">
			</td>
		</tr>
		<tr>
			<!-- 顯示打卡視窗 -->
			<td>
			<input type="button" value="打卡"  id="showPunch" name="showPunch">
			</td>
			<!-- 先進入經理登入頁，再進入管理功能 -->
			<td>
			<a href="<spring:url value='/manage/managelogin' />">
			<input type="button" value="管理"  id="toManage" name="toManage">
			</a>
			</td>
		</tr>
	</table>
	</form>

	
<!-- 	商品管理連結_開始 -->
<!--下面幾行係為了開發方便(在首頁直接出現連結，連到商品、員工管理相關頁面)，故之後確定商品、員工管理入口後再修改 -->
<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<input type="button" value="ClickMe" id="321">
	<h6><a href="productManage/productInsert.action" id="123">商品管理頁面:productInsert</a></h6>
	<h6><a href="empManage/empInsert">員工管理頁面:empInsert</a></h6>
	<h6><a href="manage/managelogin">管理登入頁面:manageLogin</a></h6> -->	
 	商品管理連結_結束 -->

<!-- 	sidebar連結_開始 -->
<!--	<h6><a href="sideBar">sidebar頁面:sidebar</a></h6>  -->
<!-- 	sidebar連結_結束 -->
	

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

			//測試是否有拿到值
            alert(Str);
            alert(Val);
            
            //送出後清空字串
            $("#showResBox").attr("text","");
           
          
            
		})
</script>
</html>