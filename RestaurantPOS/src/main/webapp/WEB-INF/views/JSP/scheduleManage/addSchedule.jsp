<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
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
					<label class="control-label col-lg-2 col-lg-2" for='date'>
						日期 </label>
					<div class="col-lg-10">
						<form:input id="date" path="date" type='text'
							class='form:input-large' />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='empNo'>
						員工編號 </label>
					<div class="col-lg-10">
						<form:input id="empNo" path="empNo" type='text'
							class='form:input-large' />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='empName'>
						姓名 </label>
					<div class="col-lg-10">
						<form:input id="empName" path="empName" type='text'
							class='form:input-large' />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='choose'>
						類別 </label>
					<div class="col-lg-10">
						<form:input id="choose" path="choose" type='text'
							class='form:input-large' />
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
					<div class="col-lg-offset-2 col-lg-10">
						<input id="btnAdd" type="submit" class='btn btn-primry' value="送出" />
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
</body>
</html>
