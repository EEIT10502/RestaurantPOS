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
	  $("#checkIn,#checkOut").click(function(){
	  $("#punchTable").hide();
	  $("#logo").show();
	  });
	  $("#showPunch").click(function(){
	  $("#punchTable").show();
	  $("#logo").hide();
	  });
	});

	
</script>
</head>
<body>
<form>
	<table border="1">
		<tr>
			<td rowspan="3" id="logo" name="logo" id="logo" name="logo">LOGO</td>
			<td rowspan="3" id="punchTable" style="display:none;">
        <table border="1" >
        <tbody>
        <th colspan="4"><input type="text" id="showResBox" readonly></th>
        <tr>
            <td rowspan="2"><input type="button" value="上班" onclick="getNO()" style="height: 140px" id="checkIn"></td>
           <td><input type="button" value="7" onclick="getNum('7')"></td>
           <td><input type="button" value="8" onclick="getNum('8')"></td>
           <td><input type="button" value="9" onclick="getNum('9')"></td>
        </tr>
        <tr>
            <td><input type="button" value="4" onclick="getNum('4')"></td>
            <td><input type="button" value="5" onclick="getNum('5')"></td>
            <td><input type="button" value="6" onclick="getNum('6')"></td>
        </tr>
       
        <tr>
         <td rowspan="2"><input type="button" value="下班" onclick="" style="height: 140px" id="checkOut"></td>
           <td><input type="button" value="1" onclick="getNum('1')"></td>
           <td><input type="button" value="2" onclick="getNum('2')"></td>
           <td><input type="button" value="3" onclick="getNum('3')"></td>
        </tr>
        <tr>
        	 <td><input type="button" value="0" onclick="getNum('0')"></td>
             <td><input type="button" value="--" onclick="del()"></td>
             <td><input type="button" value="C" onclick="clearRes()"></td>
        </tr>
    </tbody>
    </table>
			</td>
			<td>
			<input type="button" value="點餐" onclick="location.href='order'" id="toOrderPage" name="toOrderPage">
			</td>
			<td>
			<input type="button" value="日結" onclick="location.href='close/dailyClosing.jsp'">
			</td>
		</tr>
		<tr>
			<td>
			<input type="button" value="打卡"  id="showPunch" name="showPunch">
			</td>
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
	<h6><a href="productInsert.action">商品管理頁面:productInsert</a></h6>
	<h6><a href="empManage/empInsert">員工管理頁面:empInsert</a></h6>
	<h6><a href="manage/managelogin">管理登入頁面:manageLogin</a></h6>
<!-- 	商品管理連結_結束 -->

<!-- 	sidebar連結_開始 -->
	<h6><a href="sideBar">sidebar頁面:sidebar</a></h6>
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
    	//測試是否可以拿到showResBox的值
        function getNO(){
            var Str = document.getElementById("showResBox").value;
            alert(Str);
        }
</script>
</html>