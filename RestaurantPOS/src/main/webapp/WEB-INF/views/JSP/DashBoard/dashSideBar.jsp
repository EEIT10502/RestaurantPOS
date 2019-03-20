<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    		<div class="sidebar-menu">
			<header class="logo1">
				<a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span></a>
			</header>
			
			<div style="border-top: 1px ridge rgba(255, 255, 255, 0.15)"></div>
			<div class="menu">
				<ul id="menu">
					<!-- 回首頁 -->
					<li><a href="<c:url value="/toDashBoard"/>"><i class="fa fa-tachometer"></i><span>後台首頁</span></a></li>
					<!-- 人事管理 -->
					<li id="menu-academico"><a href="#">
					       <i class="lnr lnr-users"></i><span>人事管理</span>
							<span class="fa fa-angle-right" style="float: right"></span></a>
						<ul id="menu-academico-sub">
							<li id="menu-academico-avaliacoes"><a href="xxx">新增員工資訊</a></li>
							<li id="menu-academico-avaliacoes"><a href="xxx">查詢員工資訊</a></li>
							<li id="menu-academico-boletim"><a href="xxx">員工出勤查詢</a></li>
						</ul>
					</li>
					<!-- 商品管理 -->
					<li id="menu-academico"><a href="#"><i class="lnr lnr-gift"></i> <span>商品管理</span> 
					     <span	class="fa fa-angle-right" style="float: right"></span></a>
						<ul id="menu-academico-sub">
							<li id="menu-academico-avaliacoes"><a href="<c:url value='/productManage/allProductListtest.action'/>">商品查詢/修改</a></li>
							<li id="menu-academico-boletim"><a href="xxx">商品新增</a></li>
						</ul>
					</li>
					<!-- 排班管理 -->
					<li id="menu-academico"><a href="#"><i	class="fa fa-table"></i> <span>排班管理</span> 
						<span	class="fa fa-angle-right" style="float: right"></span></a>
						<ul id="menu-academico-sub">
							<li id="menu-academico-avaliacoes"><a href="xxx">班別管理</a></li>
							<li id="menu-academico-boletim"><a href="xxx">排班管理</a></li>
						</ul>
					</li>
					<!-- 報表查詢 -->
					<li id="menu-academico"><a href="#"><i	class="lnr lnr-chart-bars"></i> <span>報表查詢</span> 
						<span	class="fa fa-angle-right" style="float: right"></span></a>
						<ul id="menu-academico-sub">
							<li id="menu-academico-avaliacoes"><a href="../report/dailyReport">日報表</a></li>
							<li id="menu-academico-avaliacoes"><a href="../report/categoryReport">類別銷售表</a></li>
							<li id="menu-academico-avaliacoes"><a href="../report/productReport">單品銷售表</a></li>
							<li id="menu-academico-boletim"><a href="../report/goalReport">營運目標查詢</a></li>
						</ul>
					</li>
					<!-- 日結 -->
					<li><a href="<c:url value="/close/dailyClosing.action"/>"><i class="lnr lnr-leaf"></i> <span>日結清機</span></a></li>
					<li><a href="<c:url value="/"/>"><i class="fa fa-tachometer"></i><span>系統首頁</span></a></li>
				</ul>
			</div>
			</div>