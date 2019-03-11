<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>商品信息统计</title>
<!--第一步：引入Javascript / CSS （CDN）-->
<!-- DataTables CSS -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="http://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">
<!-- jQuery -->
<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<!-- DataTables -->
<script type="text/javascript" charset="utf8"
	src="http://cdn.datatables.net/1.10.13/js/jquery.dataTables.js">
	
</script>

<script
	src="${pageContext.request.contextPath}/resources/excellentexport/excellentexport.min.js"></script>
</head>
<body>
<%-- 	<jsp:include page="../sideBar.jsp" flush="true" /> --%>
	<div style="text-align: center">
		<h2>[ --------排班统计-------- ]</h2>

		<!--Table表格显示-->
		<div class="row">
			<div class="col-xs-12">

				<div class="box">
					<div class="box-header">
						<a class="btn btn-success export-csv-btn" downlaod="data.csv"
							href="#">数据导出</a>
					</div>

					<div class="box-body">
						<div class="table-responsive">
							<table class="table table-striped  order_table"
								id="export-csv-table">
								<thead>
									<tr>
										<th>日期</th>
										<th>員工編號</th>
										<th>姓名</th>
										<th>狀態</th>
										<th>開始時間</th>
										<th>結束時間</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="schedule" items="${schedule}">
										<tr>
											<td><fmt:formatDate value="${schedule.date}" pattern="yyyy-MM-dd" /></td>
											<td>${schedule.empNo}</td>
											<td>${schedule.empName}</td>
											<td>${schedule.choose}</td>
											<td><fmt:formatDate value="${schedule.startTime}" pattern="hh:mm" /></td>
											<td><fmt:formatDate value="${schedule.endTime}" pattern="hh:mm" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
			//加入分页
			$(document).ready(function() {
				$('.order_table').DataTable({
					//开启搜索框
					"searching" : true,
					//允许分页
					"paging" : true,
					//左下角信息 showing 1 to 7 of 7entries
					"info" : true,
					//支持国际化，将search转为中文
					language : {
						"search" : "搜尋:",
						"oPaginate" : {
							"sPrevious" : "上一頁",
							"sNext" : "下一頁",
						},
					},
					"columnDefs" : [ {
						//targets指定列禁止排序功能
						"orderable" : false,
						"targets" : [ 0, 1, 2 ]
					} ]
				});
			});

			// 导出CSV
			$(".export-csv-btn").click(
					function() {
						if ($("#export-csv-table").size() === 1) {
							if ($("#export-csv-table tbody tr").length > 0
									&& !$("#export-csv-table tbody tr td")
											.hasClass("dataTables_empty")) {
								return ExcellentExport.csv(this,
										'export-csv-table');
							} else {
								alert("數據不存在");
								return false;
							}
						} else {
							return false;
						}
					});
		</script>
	</div>
	<a href="schedule/add">新增班別</a>
	<h1><a href="index.jsp">回首頁</a></h1>
</body>
</html>