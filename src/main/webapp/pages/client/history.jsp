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

			<span class="wel_word" id="headline" >&nbsp&nbsp&nbsp&nbsp浏览记录</span>
		<div>

			<a href="pages/client/history.jsp"> 刷新页面 </a>
			<span></span>
			<a href="index.jsp">返回商城</a>
		</div>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>单价</td>
				<td>店铺</td>
				<td>操作</td>
			</tr>
			<c:if test="${not empty sessionScope.history.items}">
				<%--如果购物车非空的情况--%>
				<c:forEach items="${sessionScope.history.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.shop}</td>
						<td><a class="deleteItem" href="historyServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<c:if test="${not empty sessionScope.history.items}">
			<div class="cart_info">
				<span class="cart_span"><a id="clearCart" href="historyServlet?action=clear">清空记录</a></span>
			</div>
		</c:if>
	</div>
	
	<div id="bottom">
		<span>
			Pillow Talk
		</span>
	</div>
</body>
</html>