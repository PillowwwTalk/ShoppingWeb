<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
	<!--静态包含base标签、css样式、js-->
	<%@ include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
<%--			<img class="logo_img" alt="" src="static/img/logo.png" >--%>
			<span class="wel_word">编辑商品</span>
			<%@ include file="/pages/common/manager.jsp"%>
		</div>
		
		<div id="main">
			<form action="manager/merchandiseServlet" method="get">
				<input type="hidden" name="pageNo" value="${param.pageNo}" />
				<input type="hidden" name="action" value="${param.method}" />
				<input type="hidden" name="id" value="${requestScope.merchandise.id}" />
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>店铺</td>
						<td>销量</td>
						<td>库存</td>
						<td>图片路径</td>
						<td colspan="0">操作</td>
					</tr>		
					<tr>
						<td><input name="name" style="font-size: 12px" type="text" value="${requestScope.merchandise.name}"/></td>
						<td><input name="price" style="font-size: 12px" type="text" value="${requestScope.merchandise.price}"/></td>
						<td><input name="shop" style="font-size: 12px" type="text" value="${requestScope.merchandise.shop}"/></td>
						<td><input name="sales" style="font-size: 12px" type="text" value="${requestScope.merchandise.sales}"/></td>
						<td><input name="stock" style="font-size: 12px" type="text" value="${requestScope.merchandise.stock}"/></td>
						<td><input name="imgPath" style="font-size: 12px" type="text" value="${requestScope.merchandise.imgPath}"/></td>
						<td><input type="submit" style="font-size: 12px" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
			<span>
				Pillow Talk
			</span>
		</div>
</body>
</html>