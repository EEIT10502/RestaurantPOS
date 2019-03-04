<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../../css/final.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<title>結帳</title>
</head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>
<body>
	<form>
		<div class="container-fluid">
			<!-- 標頭 -->
			<div class="row">
				<div class="col-md-1">
					<input type="button" value="上一步" id="pBack" name="pBack">
				</div>
				<div class="col-md-8">
					<h4 style="text-align: center">結帳頁面</h4>
				</div>
				<div class="col-md-3">
					<h5>系統時間：</h5>
				</div>
			</div>

			<!-- 點餐部分 -->
			<div class="row">
				<!-- 左方清單 -->
				<div class="col-md-8">
					<table style="margin: 0px auto" width="100%" border="1"
						style="table-layout:fixed">
						<tr>
							<th>項目</th>
							<th>價格</th>
							<th>數量</th>
							<th>小計</th>
							<th>備註</th>
						</tr>
						<tr id="ol1" name="ol1">
							<td id="opItem1" name="opItem1"></td>
							<td id="opPrice1" name="opPrice1"></td>
							<td></td>
							<td id="opSubtotal1" name="opSubtotal1"></td>
							<td id="" name=""></td>
						</tr>
						<tr>
							<th colspan="3" style="text-align: right">總金額：</th>
							<td colspan="2" id="oTotal" name="oTotal"></td>
						</tr>
						<tr>
							<th colspan="3" style="text-align: right">收：</th>
							<td colspan="2" id="oReceived" name="oReceived">
							<input type="text" id="oReceived" name="oReceived"></td>
						</tr>
						<tr>
							<th colspan="3" style="text-align: right">找：</th>
							<td colspan="2" id="oChange" name="oChange"></td>
						</tr>
					</table>
				</div>


				<!-- 右方按鈕 -->
				<div class="col-md-4">
					<table border="1">
						<tr>
							<td rowspan="3" id="punchTable">
								<table border="1">
									<!-- 		<table border="1" id="p" style="visibility:hidden"> -->
									<tbody>
										<tr>
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
											<td><button onclick="demo(this,1)">3</button></td>
											<td><button onclick="demo(this,1)">2</button></td>
											<td><button onclick="demo(this,1)">1</button></td>
										</tr>
										<tr>
											<td><button onclick="demo(this,2)">全部清除</button></td>
											<td><button onclick="demo(this,1)">0</button></td>
											<td><button onclick="demo(this,3)">清除</button></td>
										</tr>
									</tbody>
								</table> <!-- 			<table style="margin:0px auto" width="100%" border="1"> -->
								<!-- 				<tr> --> <!-- 					<th colspan="3" style="text-align:center">輸入金額</th> -->
								<!-- 				</tr> --> <!-- 				<tr> --> <!-- 					<td><input type="button" value="1" id="oc1" name="oc1"></td> -->
								<!-- 					<td><input type="button" value="2" id="" name=""></td> -->
								<!-- 					<td><input type="button" value="3" id="" name=""></td> -->
								<!-- 				</tr> --> <!-- 				<tr> --> <!-- 					<td><input type="button" value="4" id="oc1" name="oc1"></td> -->
								<!-- 					<td><input type="button" value="5" id="" name=""></td> -->
								<!-- 					<td><input type="button" value="6" id="" name=""></td> -->
								<!-- 				</tr> --> <!-- 				<tr> --> <!-- 					<td><input type="button" value="7" id="oc1" name="oc1"></td> -->
								<!-- 					<td><input type="button" value="8" id="" name=""></td> -->
								<!-- 					<td><input type="button" value="9" id="" name=""></td> -->
								<!-- 				</tr> --> <!-- 				<tr> --> <!-- 					<td><input type="button" value="確定" id="oc1" name="oc1"></td> -->
								<!-- 					<td><input type="button" value="0" id="" name=""></td> -->
								<!-- 					<td><input type="button" value="清除" id="oc1" name="oc1"></td> -->
								<!-- 				</tr> --> <!-- 			</table> -->
				</div>
			</div>
		</div>
	</form>
</body>
</html>