<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" type="image/png" href="/favicon.ico" />
<meta name="keywords" content="線上排班、排班表、派班表" />
<meta name="description" content="簡單易用線上排班系統，提供滑鼠拖拉設定排班、列印及網頁內崁碼功能。" />
<!--[if lt IE 8]><link type="text/css" rel="stylesheet" href="/asset/css/ie.css" media="screen, projection" /><![endif]-->
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/screen.css"
	media="screen, projection" />
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/print.css" media="print" />
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/plugin/tabs.css"
	media="screen, projection" />
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/style.css" />
<script type="text/javascript"
	src="https://shift.ekko.com.tw/asset/js/jquery-1.7.1.min.js"></script>
<title>排班網 | 月結</title>
</head>
<body>
	<div class="container prepend-top append-bottom">
		<div class="span-24 header">
			<div class="prepend-1 span-11 left prepend-top" id="header-left">
				<h1>
					<a href="https://shift.ekko.com.tw/" id="site_name">排班網</a> <sub>beta</sub>
				</h1>
			</div>
			<div class="span-12 right last" id="header-right">
				<div class='span-12 prepend-top last'>
					<div class="span-2  right">
						<a href="https://shift.ekko.com.tw/forum.html"
							class="button span-2">討論區</a>
					</div>
					<div class="span-2 right">
						<a href="https://shift.ekko.com.tw/demo.html"
							class="button span-2">線上試用</a>
					</div>
					<!--<div class="span-2 right"><a href="https://shift.ekko.com.tw/price_plan.html" class="button span-2">價格方案</a></div>-->
					<div class="span-2 right">
						<a href="https://shift.ekko.com.tw/guide.html"
							class="button span-2">使用手冊</a>
					</div>
					<div class="span-2  right">
						<a href="https://shift.ekko.com.tw/" class="button span-2">首頁</a>
					</div>
				</div>
			</div>
		</div>
		<div id="menu" class="span-24 last">
			<ul id="menutabs">
				<li><a href="https://shift.ekko.com.tw/shift.html">排班</a></li>
				<li class="current"><a
					href="https://shift.ekko.com.tw/statistics.html">結算統計</a></li>
				<li><a href="https://shift.ekko.com.tw/group.html">群組管理</a></li>
				<li><a href="https://shift.ekko.com.tw/profile.html">個人資訊</a></li>
			</ul>
			<div class="append-1 last" style="margin-top: 1em;">
				<span>Hello, <strong><a
						href="https://shift.ekko.com.tw/profile.html">睿</a></strong></span><span>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a
					href="https://shift.ekko.com.tw/logout.html">登出</a></span>
			</div>
		</div>
		<div id="main" class="span-24">
			<div id="breadcrumb" class="span-22 prepend-1 append-1">
				<p id="breadcrumb">
					<a href="https://shift.ekko.com.tw/">首頁</a> > <a
						href="https://shift.ekko.com.tw/statistics.html">結算統計</a> > <span
						class="current">月結</span>
				</p>
			</div>
			<div id="content" class="span-22 prepend-1 append-1">
				<div class='mainInfo'>
					<div class='fieldset span-21 last'>
						<div class="span-21 append-bottom">
							<div class="span-21 inline">
								<div class="span-1">
									<label>日期</label>
								</div>
								<div class="span-2">
									<select name="year" id="year">
										<option value="2018">2018</option>
										<option value="2019" selected="selected">2019</option>
										<option value="2020">2020</option>
									</select>年
								</div>

								<div class="span-2">
									<select name="month" id="month">
										<option value="1">01</option>
										<option value="2">02</option>
										<option value="3" selected="selected">03</option>
										<option value="4">04</option>
										<option value="5">05</option>
										<option value="6">06</option>
										<option value="7">07</option>
										<option value="8">08</option>
										<option value="9">09</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
									</select>月
								</div>

							</div>
							<hr>
							<table class="group span-10">
								<thead>
									<tr>
										<th class='span-3'>姓名</th>
										<th class='span-2'>工作時數</th>
										<th class='span-2'>基本時薪</th>
										<th class='span-1'>小計</th>
										<th width="40px">操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>范氏金山</td>
										<td>0.0</td>
										<td>180</td>
										<td>0</td>
										<td><a
											href="https://shift.ekko.com.tw/statistics/index/2019/03/14121.html"
											class="image detail span-1" title="詳細">詳細</a></td>
									</tr>
									<tr>
										<td>露易莎</td>
										<td>2.0</td>
										<td>1</td>
										<td>2</td>
										<td><a
											href="https://shift.ekko.com.tw/statistics/index/2019/03/14123.html"
											class="image detail span-1" title="詳細">詳細</a></td>
									</tr>
									<tr>
										<td>123</td>
										<td>2.0</td>
										<td>1</td>
										<td>2</td>
										<td><a
											href="https://shift.ekko.com.tw/statistics/index/2019/03/14135.html"
											class="image detail span-1" title="詳細">詳細</a></td>
									</tr>
									<tr>
										<td>A</td>
										<td>2.0</td>
										<td>15</td>
										<td>30</td>
										<td><a
											href="https://shift.ekko.com.tw/statistics/index/2019/03/14136.html"
											class="image detail span-1" title="詳細">詳細</a></td>
									</tr>
									<tr>
										<td>A1</td>
										<td>0.0</td>
										<td>15</td>
										<td>0</td>
										<td><a
											href="https://shift.ekko.com.tw/statistics/index/2019/03/14137.html"
											class="image detail span-1" title="詳細">詳細</a></td>
									</tr>
									<tr>
										<td>A3</td>
										<td>0.0</td>
										<td>15</td>
										<td>0</td>
										<td><a
											href="https://shift.ekko.com.tw/statistics/index/2019/03/14138.html"
											class="image detail span-1" title="詳細">詳細</a></td>
									</tr>
									<tr>
										<td colspan="4"><hr></td>
									</tr>
									<tr>
										<td><label>合計</label></td>
										<td>6.0</td>
										<td></td>
										<td>34</td>
										<td></td>
									</tr>
								</tbody>
							</table>


						</div>
					</div>
				</div>
				<script type="text/javascript">
					$(document)
							.ready(
									function() {
										$("select#month, select#year")
												.change(
														function() {
															var year;
															var month;

															month = $(
																	"select#month")
																	.val();
															year = $(
																	"select#year")
																	.val();
															window.location.href = "https://shift.ekko.com.tw/statistics/index/"
																	+ year
																	+ "/"
																	+ month;
														});
										$("a.detail")
												.each(
														function() {
															if ($(this).attr(
																	"href") == "https://shift.ekko.com.tw/statistics.html") {
																$(this)
																		.after(
																				'<img src="https://shift.ekko.com.tw/asset/img/icons/resultset_next.png" />');
															}
														});
									});
				</script>
			</div>
		</div>

		<div class="span-24" id="footer">
			<p>請使用Internet Explorer 90, Firefox 3x, Chrome 10x
				以上版本之瀏覽器，螢幕解析度1024x768以上，以達使用完整功能及最佳顯示效果。</p>
			<p id="links">
				<a href="https://shift.ekko.com.tw/about_us.html">關於樸式設計</a> | <a
					href="https://shift.ekko.com.tw/agreement.html">服務及聲明條款</a> | <a
					href="https://shift.ekko.com.tw/privacy.html">隱私權保護政策</a> | <a
					href="https://shift.ekko.com.tw/contact.html">聯絡我們</a> Copyright
				&copy; 2011~2012 permastyle studio. All Rights Reserved
			</p>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			if ($("div#status_message").length > 0) {
				$("div#status_message").delay(4000).slideUp(1000);
			}
		});
	</script>
</body>
</html>
