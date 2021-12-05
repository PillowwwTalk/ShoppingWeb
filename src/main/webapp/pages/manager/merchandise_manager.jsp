<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<!--静态包含base标签、css样式、js-->
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			// 给删除的a标签绑定单击事件，用于删除的确认提示操作
			$("a.deleteClass").click(function () {
				// 在事件的function函数中，有一个this对象。这个this对象，是当前正在响应事件的dom对象。
				/**
				 * confirm是确认提示框函数
				 * 参数是它的提示内容
				 * 它有两个按钮，一个确认，一个是取消。
				 * 返回true表示点击了，确认，返回false表示点击取消。
				 */
				return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?");
				// return false// 阻止元素的默认行为===不提交请求
			});
		});
	</script>
</head>
<body>
	
	<div id="header">

			<span class="wel_word" id="headline" >商城管理系统</span>
		<div>

			<a href="manager/merchandiseServlet?action=page"> 刷新页面 </a>
			<span></span>
			<a href="index.jsp">返回商城</a>
		</div>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>店铺</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var = "merchandise">
				<tr>
					<td>${merchandise.name}</td>
					<td>${merchandise.price}</td>
					<td>${merchandise.shop}</td>
					<td>${merchandise.sales}</td>
					<td>${merchandise.stock}</td>
					<td><a href="manager/merchandiseServlet?action=getMerchandise&id=${merchandise.id}&method=update&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/merchandiseServlet?action=del&id=${merchandise.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/merchandise_edit.jsp?method=add&pageNo=${requestScope.page.pageTotal}">添加商品</a></td>
			</tr>	
		</table>

		<%@ include file="/pages/common/page_nav.jsp"%>

	</div>
	
	<div id="bottom">
		<span>
			Pillow Talk
		</span>
	</div>
</body>
</html>