<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<title>員工查詢</title>
<style type="text/css">
td.errorMessage[type="redError"] {
	color: red;
}

.hiddenList {
	display: none;
}
</style>

</head>

<body>

	
	<div class="clearfix">	
	<jsp:include page="../sideBar.jsp" flush="true" />
    </div>
	<jsp:include page="../empManage/empSearchHead.jsp" flush="true" />
	<section class="">
		<fieldset class="w3-container" style="margin-left: 260px">
			<div id="allList" class="">
				<table class="table table-hover">
					<tr>
						<th scope="col">#</th>
						<th scope="col">員工編號</th>
						<th scope="col">姓名</th>
						<th scope="col">職位</th>
						<th scope="col">性別</th>
						<th scope="col">電話</th>
						<th scope="col">地址</th>
						<th scope="col">狀態</th>
						<th scope="col">備註</th>
					</tr>
					<c:forEach var='employee' items='${employeeListGetByEmployeeStatus}' varStatus="status">
						<tr class="">
							<th scope="row">${status.index + 1+currentBeginOfEmployeeNo}</th>
							<td>${employee.empNo}</td>
							<td>${employee.empName}</td>
							<td>${employee.position}</td>
							<td>${employee.gender}</td>
							<td>${employee.tel}</td>
							<td>${employee.addr}</td>
							<td>${employee.status}</td>
							<td>${employee.remark}</td>
							<td>
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter${employee.empId}">修改</button>
							</td>
						</tr>


						<div class="modal fade" id="exampleModalCenter${employee.empId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLongTitle">員工資料修改</h5>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<form class='center' name="editForm${employee.empId}" action="${pageContext.request.contextPath}/empManage/EmployeeListByEmployeeStatusEdit.action/${employee.empId}?currentPageNoBtnEmployeeStatus=${currentPageNo}&whichStatus=${whichStatus}" method="post" enctype="multipart/form-data">
											<div class="modal-body">
											<input type="hidden" name="_method" id='put' value=""> <input type="hidden" name="empIdEdit" value="${employee.empId}"> <input type="hidden" name="empNoEdit" value="${employee.empNo}">
										
											<div class="row justify-content-center" style="margin-bottom: 15px">
												<img width="220px" height="245px" id="blah" src="<c:url value='/getPicture/${employee.empId}'/>" >
												<br>
											</div>
											<div class="form-group row">
												<label for="empNameEdit${employee.empId}" class="col-sm-2 col-form-label">姓名</label>
												<div class="col-sm-10">
													<input id="empNameEdit${employee.empId}" type="text" class="form-control" name="empNameEdit" value="${employee.empName}" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" /> <span id="empNameEditError${employee.empId}"></span>
												</div>
											</div>
											<div class="form-group row">
												<label for="positionEdit${employee.empId}" class="col-sm-2 col-form-label">職位</label>
												<div class="col-sm-10">
													<select name="positionEdit" id="positionEdit${employee.empId}" class="form-control">
														<c:forEach var='positions' items='${empPositionList}'>
															<c:if test="${employee.position == positions}">
																<option selected="selected"><c:out value="${positions}" /></option>
															</c:if>
															<c:if test="${employee.position != positions}">
																<option><c:out value="${positions}" /></option>
															</c:if>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group row">
												<label for="genderEdit${employee.empId}" class="col-sm-2 col-form-label">性別</label>
												<div class="col-sm-10">
													<select name="genderEdit" id="genderEdit${employee.empId}" class="form-control">
														<c:forEach var='genders' items='${empGender}'>
															<c:if test="${employee.gender == genders}">
																<option selected="selected"><c:out value="${genders}" /></option>
															</c:if>
															<c:if test="${employee.gender != genders}">
																<option><c:out value="${genders}" /></option>
															</c:if>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group row">
												<label for="telEdit${employee.empId}" class="col-sm-2 col-form-label">電話</label>
												<div class="col-sm-10">
													<input id="telEdit${employee.empId}" type="text" class="form-control" name="telEdit" value="${employee.tel}" onkeyup="value=value.replace(/[^\d.]/g,'')" /> <span id="telEditError${employee.empId}"></span>
												</div>
											</div>
											<div class="form-group row">
												<label for="addrEdit${employee.empId}" class="col-sm-2 col-form-label">地址</label>
												<div class="col-sm-10">
													<input id="addrEdit${employee.empId}" type="text" class="form-control" name="addrEdit" value="${employee.addr}"> <span id="addrEditError${employee.empId}"></span>
												</div>
											</div>
											<div class="form-group row">
												<label for="statusEdit${employee.empId}" class="col-sm-2 col-form-label">狀態</label>
												<div class="col-sm-10">
													<select name="statusEdit" id="statusEdit${employee.empId}" class="form-control">
														<c:forEach var='statuss' items='${empStatusList}'>
															<c:if test="${employee.status == statuss}">
																<option selected="selected"><c:out value="${statuss}" /></option>
															</c:if>
															<c:if test="${employee.status != statuss}">
																<option><c:out value="${statuss}" /></option>
															</c:if>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group row">
												<label for="remarkEdit${employee.empId}" class="col-sm-2 col-form-label">備註</label>
												<div class="col-sm-10">
													<input id="remarkEdit${employee.empId}" type="text" class="form-control" name="remarkEdit" value="${employee.remark}" />
												</div>
											</div>
											<div class="form-group row">
												<label for="empImgEdit${employee.empId}" class="col-sm-2 col-form-label">圖片</label>
												<div class="col-sm-10">

													<input id="empImgEdit${employee.empId}" type="file" class="form-control" name="empImgEdit">
												</div>
											</div>

											<!-- TEST TEST TEST 保存原版 -->
											<!-- <div class="form-group row"> -->
											<%-- <label for="empImgEdit${employee.empId}" --%>
											<!-- class="col-sm-2 col-form-label">圖片</label> -->
											<!-- <div class="col-sm-10"> -->

											<%-- <input id="empImgEdit${employee.empId}" type="file" --%>
											<!-- class="form-control" name="empImgEdit"> <img -->
											<!-- width="100px" height="100px" -->
											<%-- src="<c:url value='/getPicture/${employee.empId}'/>"> --%>
											<%-- <span id="empImgEditError${employee.empId}"></span> --%>
											<!-- </div> -->
											<!-- </div> -->
											<!-- TEST TEST TEST 保存原版-->
											

										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
											<button type="submit" id="submit${employee.empId}" class="btn btn-primary">儲存更新</button>
										</div>
									</form>
								</div>

							</div>
						</div>
						<!-- 						Modal 結束 -->

						<script>
							// 本方法為預覽顯示圖片
// 							function readURL(input) {

// 								if (input.files && input.files[0]) {
// 									var reader = new FileReader();
// 									reader.onload = function(e) {
// 										$('#blah').attr('src', e.target.result);
// 									}

// 									reader.readAsDataURL(input.files[0]);
// 								}
// 							}

// 							$("#empImgEdit${employee.empId}").change(
// 									function() {
// 										readURL(this);
// 									});
							
							document.getElementById("empImgEdit${employee.empId}").onchange = function () {
							    var reader = new FileReader();

							    reader.onload = function (e) {
							        document.getElementById("blah").src = e.target.result;
							    };

							    reader.readAsDataURL(this.files[0]);
							};
						</script>
						<script>
							var flag = "";
							document
									.addEventListener(
											"DOMContentLoaded",
											function() {
												document
														.getElementById(
																"empNameEdit${employee.empId}")
														.addEventListener(
																"blur",
																function() {
																	if (document
																			.getElementById("empNameEdit${employee.empId}").value == "") {
																		document
																				.getElementById("empNameEditError${employee.empId}").innerHTML = "<span style='color:red'><i>員工名稱不可空白";

																		if (flag
																				.indexOf("${employee.empId}A") == -1) {
																			flag = flag
																					.concat("${employee.empId}A");
																		}
																		document
																				.getElementById("submit${employee.empId}").disabled = true;
																	} else {
																		if (flag
																				.indexOf("${employee.empId}A") != -1) {
																			flag = flag
																					.replace(
																							"${employee.empId}A",
																							"");
																			document
																					.getElementById("empNameEditError${employee.empId}").innerHTML = "";
																		}
																		if (flag
																				.indexOf("${employee.empId}A") == -1
																				&& flag
																						.indexOf("${employee.empId}B") == -1) {
																			document
																					.getElementById("empNameEditError${employee.empId}").innerHTML = "";
																			document
																					.getElementById("submit${employee.empId}").disabled = false;
																		}

																	}
																});
											})

							document
									.addEventListener(
											"DOMContentLoaded",
											function() {
												document
														.getElementById(
																"telEdit${employee.empId}")
														.addEventListener(
																"blur",
																function() {
																	var reg = /[^\d.]/g;
																	if (document
																			.getElementById("telEdit${employee.empId}").value == "") {
																		document
																				.getElementById("telEditError${employee.empId}").innerHTML = "<span style='color:red'><i>電話不可空白";
																		if (flag
																				.indexOf("${employee.empId}B") == -1) {
																			flag = flag
																					.concat("${employee.empId}B");
																		}
																		document
																				.getElementById("submit${employee.empId}").disabled = true;
																	} else {
																		if (flag
																				.indexOf("${employee.empId}B") != -1) {
																			flag = flag
																					.replace(
																							"${employee.empId}B",
																							"");
																			document
																					.getElementById("telEditError${employee.empId}").innerHTML = "";
																		}
																		if (flag
																				.indexOf("${employee.empId}A") == -1
																				&& flag
																						.indexOf("${employee.empId}B") == -1) {
																			document
																					.getElementById("telEditError${employee.empId}").innerHTML = "";
																			document
																					.getElementById("submit${employee.empId}").disabled = false;
																		}
																	}
																});
											})

							document
									.addEventListener(
											"DOMContentLoaded",
											function() {
												document
														.getElementById(
																"addrEdit${employee.empId}")
														.addEventListener(
																"blur",
																function() {
																	var reg = /[^\d.]/g;
																	if (document
																			.getElementById("addrEdit${employee.empId}").value == "") {
																		document
																				.getElementById("addrEditError${employee.empId}").innerHTML = "<span style='color:red'><i>地址不可空白";
																		if (flag
																				.indexOf("${employee.empId}B") == -1) {
																			flag = flag
																					.concat("${employee.empId}B");
																		}
																		document
																				.getElementById("submit${employee.empId}").disabled = true;
																	} else {
																		if (flag
																				.indexOf("${employee.empId}B") != -1) {
																			flag = flag
																					.replace(
																							"${employee.empId}B",
																							"");
																			document
																					.getElementById("addrEditError${employee.empId}").innerHTML = "";
																		}
																		if (flag
																				.indexOf("${employee.empId}A") == -1
																				&& flag
																						.indexOf("${employee.empId}B") == -1) {
																			document
																					.getElementById("addrEditError${employee.empId}").innerHTML = "";
																			document
																					.getElementById("submit${employee.empId}").disabled = false;
																		}
																	}
																});
											})
						</script>
<!-- 						<script> -->
<!-- // 							function readURL(input) { -->

<!-- // 								if (input.files && input.files[0]) { -->
<!-- // 									var reader = new FileReader(); -->

<!-- // 									reader.onload = function(e) { -->
<!-- // 										$('#blah').attr('src', e.target.result); -->
<!-- // 									} -->

<!-- // 									reader.readAsDataURL(input.files[0]); -->
<!-- // 								} -->
<!-- // 							} -->

<!-- // 							$("#imgInp").change(function() { -->
<!-- // 								readURL(this); -->
<!-- // 							}); -->
<!-- 						</script> -->

					</c:forEach>
					<tr>
						<td colspan="6" class="errorMessage" type="redError">${noEmployeeString}</td>
					</tr>
				</table>
				<nav aria-label="...">
					<ul class="pagination">
						<c:if test="${currentPageNo <= 1}">
							<li class="page-item disabled"><a class="page-link" href="<c:url value='EmployeeListByEmployeeStatus.action?currentPageNoBtnEmployeeStatus=1&whichStatus=${whichStatus}' />" tabindex="-1" aria-disabled="true">First Page</a></li>
						</c:if>
						<c:if test="${currentPageNo > 1}">
							<li class="page-item"><a class="page-link" href="<c:url value='EmployeeListByEmployeeStatus.action?currentPageNoBtnEmployeeStatus=1&whichStatus=${whichStatus}' />" tabindex="-1" aria-disabled="true">First Page</a></li>
						</c:if>
						<c:if test="${currentPageNo <= 1}">
							<li class="page-item disabled"><a class="page-link" href="<c:url value='EmployeeListByEmployeeStatus.action?currentPageNoBtnEmployeeStatus=${currentPageNo-1}&whichStatus=${whichStatus}' />" tabindex="-1" aria-disabled="true">Previous</a></li>
						</c:if>
						<c:if test="${currentPageNo > 1}">
							<li class="page-item"><a class="page-link" href="<c:url value='EmployeeListByEmployeeStatus.action?currentPageNoBtnEmployeeStatus=${currentPageNo-1}&whichStatus=${whichStatus}' />" tabindex="-1" aria-disabled="true">Previous</a></li>
						</c:if>

						<c:if test="${currentPageNo != totalPages}">
							<li class="page-item"><a class="page-link" href="<c:url value='EmployeeListByEmployeeStatus.action?currentPageNoBtnEmployeeStatus=${currentPageNo+1}&whichStatus=${whichStatus}' />">Next</a></li>
						</c:if>
						<c:if test="${currentPageNo == totalPages}">
							<li class="page-item disabled"><a class="page-link" href="<c:url value='EmployeeListByEmployeeStatus.action?currentPageNoBtnEmployeeStatus=${currentPageNo+1}&whichStatus=${whichStatus}' />">Next</a></li>
						</c:if>
						<c:if test="${currentPageNo == totalPages}">
							<li class="page-item disabled"><a class="page-link" href="<c:url value='EmployeeListByEmployeeStatus.action?currentPageNoBtnEmployeeStatus=${totalPages}&whichStatus=${whichStatus}' />" tabindex="-1" aria-disabled="true">Last Page</a></li>
						</c:if>
						<c:if test="${currentPageNo != totalPages}">
							<li class="page-item"><a class="page-link" href="<c:url value='EmployeeListByEmployeeStatus.action?currentPageNoBtnEmployeeStatus=${totalPages}&whichStatus=${whichStatus}' />" tabindex="-1" aria-disabled="true">Last Page</a></li>
						</c:if>
						<li class="page-item">第${currentPageNo}頁 /共${totalPages}頁</li>
					</ul>
				</nav>
			</div>
		</fieldset>
	</section>
	<jsp:include page="../footer.jsp" flush="true" />
</body>
</html>
