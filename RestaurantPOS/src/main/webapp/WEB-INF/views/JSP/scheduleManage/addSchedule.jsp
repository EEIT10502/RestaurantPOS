<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<!-- jQuery v1.9.1 -->
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<!-- pickadate.js v3.5.6 -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/pickadate.js/3.5.6/compressed/themes/classic.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/pickadate.js/3.5.6/themes/classic.date.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/pickadate.js/3.5.6/themes/classic.time.css"
	rel="stylesheet" />
	
<link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/screen.css" media="screen, projection" />
<link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/print.css" media="print" />
<link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/plugin/tabs.css" media="screen, projection" />
<link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/style.css" />
<link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/jquery.fancybox-1.3.4.css" media="screen, projection" />
	
<link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/jquery.simple-color-picker.css" media="screen, projection" />        

<title>新增班別</title>
<link rel='stylesheet' href='css/styles.css' type="text/css" />

</head>
<body>
	<jsp:include page="../sideBar.jsp" flush="true" />
	<section class="container">
		<!--       三個地方要完全一樣 -->
		<form:form method='POST' modelAttribute="scheduleBean"
			class='form-horizontal'>
			<fieldset class="w3-container" style="margin-left: 260px">
				<legend>新增資料</legend>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='schedule'>
						班別名稱 </label>
					<div class="col-lg-10">
						<form:input id="schedule" path="schedule" type='text'
							class='form:input-large' autocomplete = "off"/>
					</div>
				</div>

				<div class="form-group">
					<label class="col-lg-2 col-lg-2" for='color'> 
						顏色</label>
					<div class="col-lg-10">
						<form:input id="color" path="color" name="color" type="text"
							class='form:input-large' autocomplete = "off"/>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='startTime'>
						開始時間 </label>
					<div class="col-lg-10">
						<form:input id="startTime" path="startTime" type='text'
 							class="example" autocomplete = "off"/> 
<!-- 						<select name="startTime" id="startTime" path="startTime"> -->
<!-- 							<option value="08:00:00">08:00:00</option> -->
<!-- 							<option value="09:00:00">09:00:00</option> -->
<!-- 							<option value="10:00:00">10:00:00</option> -->
<!-- 							<option value="11:00:00">11:00:00</option> -->
<!-- 							<option value="12:00:00">12:00:00</option> -->
<!-- 							<option value="13:00:00">13:00:00</option> -->
<!-- 							<option value="14:00:00">14:00:00</option> -->
<!-- 							<option value="15:00:00">15:00:00</option> -->
<!-- 							<option value="16:00:00">16:00:00</option> -->
<!-- 							<option value="17:00:00">17:00:00</option> -->
<!-- 							<option value="18:00:00">18:00:00</option> -->
<!-- 							<option value="19:00:00">19:00:00</option> -->
<!-- 							<option value="20:00:00">20:00:00</option> -->
<!-- 							<option value="21:00:00">21:00:00</option> -->
<!-- 							<option value="22:00:00">22:00:00</option> -->
<!-- 							<option value="23:00:00">23:00:00</option> -->
<!-- 						</select> -->
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='endTime'>
						結束時間 </label>
					<div class="col-lg-10">
						<form:input id="endTime" path="endTime" type='text'
 							class="example" autocomplete = "off"/> 
<!-- 						<select name="endTime" id="endTime" path="endTime"> -->
<!-- 							<option value="08:00:00">08:00:00</option> -->
<!-- 							<option value="09:00:00">09:00:00</option> -->
<!-- 							<option value="10:00:00">10:00:00</option> -->
<!-- 							<option value="11:00:00">11:00:00</option> -->
<!-- 							<option value="12:00:00">12:00:00</option> -->
<!-- 							<option value="13:00:00">13:00:00</option> -->
<!-- 							<option value="14:00:00">14:00:00</option> -->
<!-- 							<option value="15:00:00">15:00:00</option> -->
<!-- 							<option value="16:00:00">16:00:00</option> -->
<!-- 							<option value="17:00:00">17:00:00</option> -->
<!-- 							<option value="18:00:00">18:00:00</option> -->
<!-- 							<option value="19:00:00">19:00:00</option> -->
<!-- 							<option value="20:00:00">20:00:00</option> -->
<!-- 							<option value="21:00:00">21:00:00</option> -->
<!-- 							<option value="22:00:00">22:00:00</option> -->
<!-- 							<option value="23:00:00">23:00:00</option> -->
<!-- 						</select> -->
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='restTime'>
						休息時間 </label>
					<div class="col-lg-10">
						<select name="restTime" id="restTime" path="restTime">
							<option value="0.0">0.0</option>
							<option value="0.5">0.5</option>
							<option value="1.0">1.0</option>
							<option value="1.5">1.5</option>
							<option value="2.0">2.0</option>

						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input id="btnAdd" type="submit" class='btn btn-primry' value="送出" />
					</div>
				</div>
				<script type="text/javascript">
				/*選時器*/
				$(document).ready(function() {
// 				    $('.example').pickatime({
// 				        twelvehour: false, // change to 12 hour AM/PM clock from 24 hour
// 				        donetext: 'OK',
// 				        format: "HH:i",
// 				        autoclose: false,
// 				        vibrate: true
// 				    });
// 				    // For adding seconds (00)
// 				    $('.example').on('change', function() {
// 				        let receivedVal = $(this).val();
// 				        $(this).val(receivedVal + ":00");
// 						});
					$('.example').timepicker({ 'timeFormat': 'H:i:s' });
				    
				});
				/*選色器*/
					$(document).ready(function(){
						$("input#name").focus();
						$('input#color').simpleColorPicker({ 
							onChangeColor: function(color) { $('input#color').val(color); },
							showEffect: 'fade', 
							hideEffect: 'slide',
							enableEscapeButton: true,
							colors: [
								'#660000', '#783f04', '#7f6000', '#274e13', '#0c343d', '#073763', '#20124d', '#4C1130'
								, '#990000', '#b45f06', '#bf9000', '#38761d', '#134f5c', '#0b5394', '#351c75', '#741b47'
								,'#ff0000', '#ff9900', '#ffff00', '#00ff00', '#00ffff', '#0000ff', '#9900ff', '#ff00ff'
								, '#cc0000', '#e69138', '#f1c232', '#6aa84f', '#45818e', '#3d85c6', '#674ea7', '#a64d79'
								,'#e06666', '#f6b26b', '#ffd966', '#93c47d', '#76a5af', '#6fa8dc', '#8e7cc3', '#c27ba0']
						});
						$("form#work_form #message").hide();
						$("form#work_form #name").focus();
						$("form#work_form input[name='submit']").bind('click', function(e){
							e.preventDefault();
							$.ajax({
								url: 'https://shift.ekko.com.tw/group/ajax_edit_worker.html',
								type: 'POST',
								dataType : 'json',
								data: { 
										id: 14175,
										name: $("input[name='name']").val(), 
										color: $("input[name='color']").val(),
										rate: $("input[name='rate']").val(), 
										ci_csrf_token: $("input[name='ci_csrf_token']").val() 
								},
								success: function(data){
									if (data.status == 'error'){
										$("div#message").html(data.msg).show();
									}
									if (data.status == 'success'){
										location.replace('https://shift.ekko.com.tw/group/worker.html');
										$.fancybox.close();
										
									}
								}
							});
							return false;
						});
						$("input[name='cancel']").bind('click', function(e){
							e.preventDefault();	
							$.fancybox.close();
						});
					});
				</script>
			</fieldset>
		</form:form>
	</section>
<!-- 選時器 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pickadate.js/3.5.6/compressed/picker.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pickadate.js/3.5.6/compressed/picker.date.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pickadate.js/3.5.6/compressed/picker.time.js"></script>
<!-- 選色器 -->

<script type="text/javascript" src="https://shift.ekko.com.tw/asset/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="https://shift.ekko.com.tw/asset/js/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript" src="https://shift.ekko.com.tw/asset/js/jquery.simple-color-picker.js"></script>
</body>
</html>
