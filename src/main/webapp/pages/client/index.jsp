<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<head>
    <meta charset="UTF-8">
    <title>商城首页</title>
    <!--静态包含base标签、css样式、js-->
    <%@ include file="/pages/common/head.jsp"%>
    <Script type="text/javascript">
        $(function () {
            // 给加入购物车按钮绑定单击事件
            $("button.addToCart").click(function () {
                /**
                 * 在事件响应的function函数 中，有一个this对象，这个this对象，是当前正在响应事件的dom对象
                 * @type {jQuery}
                 */
                var merchandiseId = $(this).attr("merchandiseId");
                location.href = "${pageScope.basePath}cartServlet?action=addItem&id=" + merchandiseId;

            });
            $("img.book_img").click(function (){
                var merchandiseId = $(this).attr("merchandiseId");
                location.href = "${pageScope.basePath}historyServlet?action=addItem&id=" + merchandiseId;
            });
        });
    </Script>
</head>
<body>

<div id="header">
    <img id="img1" width="85" height="90" alt="" src="static/img/logo.png" > &nbsp
    <span class="wel_word" id="headline">Shopping Mall</span>
    <div>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临</span>
            <a href="logoutServlet?action=logout">注销</a>&nbsp;&nbsp;
        </c:if>
        <a href="pages/cart/cart.jsp">购物车&nbsp</a>
        <a href="pages/client/history.jsp">浏览记录&nbsp</a>
        <c:if test="${not empty sessionScope.user}">
            <a href="pages/order/order.jsp">购买记录&nbsp</a>
        </c:if>
        <c:if test="${ sessionScope.user.username eq 'admin'}">
        <a href="manager/merchandiseServlet?action=page">后台管理</a>
        </c:if>

    </div>
</div>
<div id="main1">
    <div id="book">
        <div class="book_cond">
            <form action="client/merchandiseServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        <div class="book_cart" style="text-align: center">
            <c:if test ="${empty sessionScope.cart.items}">
                <span> </span>
            </c:if>
            <c:if test ="${not empty sessionScope.cart.items}">
                <span>您的购物车中有 ${sessionScope.cart.totalCount} 件商品</span>
                <div>
                    您刚刚将<span style="color: red">${sessionScope.lastName}</span>加入到了购物车中
                </div>
            </c:if>

        </div>
        <c:forEach items="${requestScope.page.items}" var="merchandise">
        <div class="b_list">
            <div class="img_div">
                <img class="book_img" merchandiseId="${merchandise.id}" alt="" src="${merchandise.imgPath}" />
            </div>
            <div class="book_info">
                <div class="book_name">
                    <span class="sp1">商品名:</span>
                    <span class="sp2">${merchandise.name}</span>
                </div>
                <div class="book_author">
                    <span class="sp1">店铺:</span>
                    <span class="sp2">${merchandise.shop}</span>
                </div>
                <div class="book_price">
                    <span class="sp1">价格:</span>
                    <span class="sp2">￥${merchandise.price}</span>
                </div>
                <div class="book_sales">
                    <span class="sp1">销量:</span>
                    <span class="sp2">${merchandise.sales}</span>
                </div>
                <div class="book_amount">
                    <span class="sp1">库存:</span>
                    <span class="sp2">${merchandise.stock}</span>
                </div>
                <div class="book_add">
                    <button merchandiseId="${merchandise.id}" class="addToCart">加入购物车</button>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
    <%@ include file="/pages/common/page_nav.jsp"%>
</div>

<div id="bottom">
		<span>
		</span>
</div>
</body>
</html>