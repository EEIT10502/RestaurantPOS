<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<title>首頁</title>
</head>
<style>
#content{
width: 322px;
}

button{
width: 80px;
height: 70px;

}

</style>


<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
</script>
<script type="text/javascript">
//隱藏或顯示計算機
$(document).ready(function(){
	  $("#hideAndSendPunch,#hideAndSendPunch2").click(function(){
	  $("#punchTable").hide();
	  $("#logo").show();
	  });
	  $("#showPunch").click(function(){
	  $("#punchTable").show();
	  $("#logo").hide();
	  });
	});
	//打卡機
	function demo(obj, tip) {
		if (tip == 1) {
			var con = document.getElementById('content').value;
			document.getElementById('content').value = con + obj.innerHTML;
		} else if (tip == 2) {
			document.getElementById('content').value = "";
		} else if (tip == 3) {
			var con = document.getElementById('content').value;
			document.getElementById('content').value = con.slice(0, -1);
		}
	}
</script>
<body>
<form>
	<table border="1">
		<tr>
			<td rowspan="3" id="logo" name="logo" id="logo" name="logo">LOGO</td>
			<td rowspan="3" id="punchTable" style="display:none">
			<table border="1" >
<!-- 		<table border="1" id="p" style="visibility:hidden"> -->
            <th colspan="4"><input type="text" id="content" name="content"></th>
        <tbody>
        <tr>
        <td rowspan="2"><button onclick="" id="hideAndSendPunch" name="hideAndSendPunch"style="height: 140px">上班打卡</button></td>
            <td><button onclick="demo(this,1)">7</button></td>
            <td><button onclick="demo(this,1)">8</button></td>
            <td><button onclick="demo(this,1)">9</button></td>
        </tr>
        <tr>
            <td><button onclick="demo(this,1)">4</button></td>
            <td><button onclick="demo(this,1)">5</button></td>
            <td><button onclick="demo(this,1)">6</button></td>
        </tr>
       
        <tr>
         <td rowspan="2"><button onclick="" id="hideAndSendPunch2" name="hideAndSendPunch2" style="height: 140px">下班打卡</button></td>
            <td><button onclick="demo(this,1)">1</button></td>
            <td><button onclick="demo(this,1)">2</button></td>
            <td><button onclick="demo(this,1)">3</button></td>
        </tr>
        <tr>
            <td><button onclick="demo(this,2)">全部清除</button></td>
            <td><button onclick="demo(this,1)">0</button></td>
            <td><button onclick="demo(this,3)">清除</button></td>
        </tr>
    </tbody>
    </table>
			</td>
			<td><input type="button" value="點餐" onclick="location.href='order'" id="toOrderPage" name="toOrderPage"></td>
			<td><input type="button" value="日結" onclick="location.href='close/dailyClosing.jsp'"></td>
		</tr>
		<tr>
			<td><input type="button" value="打卡" onclick="location.href='#'" id="showPunch" name="showPunch"></td>
			<td><input type="button" value="管理" onclick="location.href='manage/mangelogin.jsp'" id="toManage" name="toManage"></td>
		</tr>
	</table>
	</form>

	
<!-- 	商品管理連結_開始 -->
<!--下面幾行係為了開發方便(在首頁直接出現連結，連到商品管理相關頁面)，故之後確定商品管理入口後再修改 -->
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h6><a href="goodsManage/goodsInsert">商品管理頁面:goodsInsert</a></h6>
<!-- 	商品管理連結_結束 -->
	

</body>
</html>