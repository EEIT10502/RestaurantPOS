<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<style type="text/css">
fieldset {
	border: 1px solid rgb(255, 232, 57);
	width: 400px;
	margin: auto;
}
</style>

<title>新增班別</title>
<link rel='stylesheet' href='css/styles.css' type="text/css" />

</head>
<body>
	<section>
		<div class="container">
			<h1 style="text-align: center">排班資料維護</h1>
		</div>
	</section>
	<hr width="3">
	<section class="container">
		<!--       三個地方要完全一樣 -->
		<form:form method='POST' modelAttribute="scheduleBean"
			class='form-horizontal'>
			<fieldset>
				<legend>新增排班資料</legend>



				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='schedule'>
						班別名稱 </label>
					<div class="col-lg-10">
						<form:input id="schedule" path="schedule" type='text'
							class='form:input-large' />
					</div>
				</div>

				<div class="form-group">
					<!-- 					<label class="control-label col-lg-2 col-lg-2" for='color'> -->
					<!-- 						顏色 </label> -->
					<!-- 					<div class="col-lg-10"> -->
					<%-- 						<form:select id="color" path="color"> --%>
					<%-- 							<form:option value="-1">請選擇</form:option> --%>
					<%-- 							<form:option value="#000000">#000000</form:option> --%>
					<%-- 							<form:option value="#111111">#111111</form:option> --%>
					<%-- 							<form:option value="#001100">#001100</form:option> --%>
					<%-- 						</form:select> --%>
					<!-- 					</div> -->
					<div class="col-lg-10">
						<label class="control-label col-lg-2 col-lg-2" for='color'>
							顏色</label>
						<form:input id="color" path="color" name="color" type="text"
							value="#FFFFFF" class='form:input-large' />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='startTime'>
						開始時間 </label>
					<div class="col-lg-10">
						<form:input id="startTime" path="startTime" type='text'
							class='form:input-large' />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='endTime'>
						結束時間 </label>
					<div class="col-lg-10">
						<form:input id="endTime" path="endTime" type='text'
							class='form:input-large' />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='restTime'>
						休息時間 </label>
					<div class="col-lg-10">
						<form:input id="restTime" path="restTime" type='text'
							class='form:input-large' />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='totalTime'>
						總時間 </label>
					<div class="col-lg-10">
						<form:input id="totalTime" path="totalTime" type='text'
							class='form:input-large' />
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input id="btnAdd" type="submit" class='btn btn-primry' value="送出" />
					</div>
				</div>
				<script type="text/javascript">
					$(document).ready(function() {
						$("a#add_worker, a#edit").live('click', function(e) {
							e.preventDefault();
							$.fancybox(this, {
								'scrolling' : 'no',
								'titleShow' : false,
								'centerOnScroll' : true,
								'autoScale' : false,
								'enableEscapeButton' : true,
								'type' : 'inline'
							});
						});
					})
				</script>
				<script type="text/javascript">
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
				url: 'https://shift.ekko.com.tw/group/ajax_add_worker.html',
				type: 'POST',
				dataType : 'json',
				data: { 
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
						
						$.ajax({
							url: 'https://shift.ekko.com.tw/shift/ajax_get_workers.html',
							type: 'GET',
							dataType : 'json',
							success: function(data){
								$('div#workers').html(data);
								$('div#workers a').each(function() {
		
								// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
								// it doesn't need to have a start or end
								var eventObject = {
									id: $(this).attr('id'),
									title: $.trim($(this).text()), // use the element's text as the event title
									backgroundColor: $(this).css('backgroundColor')
								};
								
								// store the Event Object in the DOM element so we can get to it later
								$(this).data('eventObject', eventObject);
								
								// make the event draggable using jQuery UI
								$(this).draggable({
									helper: 'clone',
									opacity: 0.5,
									zIndex: 999,
									revert: true,      // will cause the event to go back to its
									revertDuration: 0  //  original position after the drag
								});
								
							});	
							}
						})
						
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
</body>
</html>