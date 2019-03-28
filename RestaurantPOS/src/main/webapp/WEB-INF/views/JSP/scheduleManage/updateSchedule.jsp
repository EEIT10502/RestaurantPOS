<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/pickadate.js/3.5.6/compressed/themes/classic.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/pickadate.js/3.5.6/themes/classic.date.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/pickadate.js/3.5.6/themes/classic.time.css"
	rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/jquery.simple-color-picker.css" media="screen, projection" />
<title>修改班別</title>
<script type="text/javascript">
  function confirmUpdate(scheduleId){
	  var result = confirm("確定送出?");
	  if (result) {
		  document.forms[0].putOrDelete.value = "POST";
	      return true;
	  }
	  return false;
  }
  function confirmDelete(scheduleId){
	  var result = confirm("確定刪除?");
	  if (result) {
		  document.forms[0].putOrDelete.value = "DELETE";
	      return true;
	  }
	  return false;
  }
</script>
<link rel='stylesheet' href='css/styles.css' type="text/css" />

</head>
<body>
<div  class="clearfix">
	<jsp:include page="../headerTime.jsp" flush="true" />	
	<jsp:include page="../sideBar.jsp" flush="true" />
	</div>
	<section class="container">
		<!--       三個地方要完全一樣 -->
		<form:form method='POST' modelAttribute="scheduleBean" class='form-horizontal'>
			<input type="hidden" name="_method"  id='putOrDelete'  value="" >
			<fieldset class="w3-container" style="margin-left: 260px">
				<legend>修改班別資料</legend>
				<hr><br>
				<div class="form-group">
<!-- 					<label class="control-label col-lg-2 col-lg-2 hide" for='scheduleId'> -->
<!-- 						ID</label> -->
					<div class="col-lg-10">
						<form:input id="shcedule${schedule.scheduleId}" path="scheduleId" type='hidden'
							class='form:input-large hide' readonly="true"/> 
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='schedule'>
						班別名稱 </label>
					<div class="col-lg-10">
						<form:input id="shcedule${schedule.schedule}" path="schedule" type='text'
							class='form:input-large' autocomplete = "off"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='startTime'>
						開始時間 </label>
					<div class="col-lg-10">
						<form:input id="shcedule${schedule.startTime}" path="startTime" type='text'
							class='example' />
							
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='endTime'>
						結束時間 </label>
					<div class="col-lg-10">
						<form:input id="shcedule${schedule.endTime}" path="endTime" type='text'
							class='example' />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='restTime'>
						休息時間 </label>
					<div class="col-lg-10">
							<select name="restTime" id="shcedule${schedule.restTime}" path="restTime" >
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
            			<input type="submit" value="更新" name='updateBtn' onclick="return confirmUpdate('${schedule.scheduleId}');"/>
            			<input type="submit" value="刪除" name='deleteBtn' onclick="return confirmDelete('${schedule.scheduleId}');" >
					</div>
				</div>
				<c:if test="${not empty requestScope.modify}">   
           <c:remove var="scheduleBean" scope="request" />       
         </c:if>
         </fieldset>
		</form:form>
		</section>
				<script type="text/javascript">
				/*選時器*/
				$(document).ready(function() {
					$.noConflict();
					$('.example').pickatime({
						twelvehour : false, // change to 12 hour AM/PM clock from 24 hour
						donetext : 'OK',
						format : "HH:i",
						clear:false,
						autoclose : false,
						vibrate : true
					});
					// For adding seconds (00)
					$('.example').on('change', function() {
						let receivedVal = $(this).val();
						$(this).val(receivedVal + ":00");
					});
				});
				</script>
			
<!-- 選時器 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/pickadate.js/3.5.6/compressed/picker.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pickadate.js/3.5.6/compressed/picker.date.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pickadate.js/3.5.6/compressed/picker.time.js"></script>
<!-- 選色器 -->
<script type="text/javascript" src="https://shift.ekko.com.tw/asset/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="https://shift.ekko.com.tw/asset/js/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript" src="https://shift.ekko.com.tw/asset/js/jquery.simple-color-picker.js"></script>
<%-- 	<jsp:include page="../footer.jsp" flush="true" /> --%>
</body>
</html>
