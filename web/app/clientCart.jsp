<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/style/css/index.css"/>
    <script type="text/javascript">

        function removeSorder(id) {
            window.location.href = "/shopping?method=deleteFood&foodId=" + id;
        }

        /** // 删除菜品项
         // 修改菜品项数量
         function alterSorder(node) {
			var snumber = node.value;
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.jsp?method=alterSorder&gid="+gid+"&snumber="+snumber;
		}
         */
        // 下单
        function genernateOrder() {
            var form = document.forms[0];
            form.submit();
        }
    </script>
</head>

<body style="text-align: center">
<div id="all">
    <div id="menu">
        <form action="/shopping?method=order" method="post">
            <!-- 餐车div -->
            <div id="count">
                <table align="center" width="100%">
                    <tr height="40">
                        <td align="center" width="20%">菜名</td>
                        <td align="center" width="20%">单价</td>
                        <td align="center" width="20%">数量</td>
                        <td align="center" width="20%">小计</td>
                        <td align="center" width="20%">操作</td>
                    </tr>
                    <c:choose>
                        <c:when test="${not empty requestScope.orders}">
                            <c:forEach items="${orders}" var="order">
                                <tr height="30">
                                    <td align="center" width="20%">${order.foodName}</td>
                                    <td align="center" width="20%">￥${order.price}</td>
                                    <td align="center" width="20%">
                                        <input type="text" value="${order.count}" size="3" lang="3"
                                               name="count"/>
                                    </td>
                                    <td align="center" width="20%">${order.smPrice}</td>
                                    <td align="center" width="20%">
                                        <input type="button" value="删除" class="btn_next" lang="3"
                                               onclick="removeSorder(${order.food_id})"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p style="font-size: larger;color: red;">您没有任何点餐信息！</p>
                        </c:otherwise>
                    </c:choose>

                    <tr>
                        <td colspan="6" align="right">总计:
                            <span style="font-size:36px;">&yen;&nbsp;${requestScope.totalPrice}</span>
                            <label
                                    id="counter" style="font-size:36px"></label>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" style="margin-left: 100px; text-align: center;" align="right">
                            <input type="hidden" name="bId" value="">


                            <input type="button" value="下单" class="btn_next" onclick="genernateOrder()"/>


                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>

    <!-- 右边菜系列表，菜品搜索框  -->
    <div id="dish_class">
        <div id="dish_top">
            <ul>
                <li class="dish_num"></li>
                <li>
                    <a href="clientOrderList.jsp">
                        <img src="${pageContext.request.contextPath}/app/style/images/call2.gif"/>
                    </a>
                </li>
            </ul>
        </div>

        <div id="dish_2">
            <ul>

                <!-- 迭代菜系列表 -->
                <c:forEach var="foodType" items="${requestScope.foodTypes}">
                    <li>
                        <a href="${pageContext.request.contextPath }/food?method=foodDetail&foodTypeId=${foodType.id}">${foodType.typeName }</a>
                            <%--<input type="hidden" name="foodTypeId" value="${foodType.id}">--%>
                    </li>
                </c:forEach>

            </ul>
        </div>

        <div id="dish_3">
            <!-- 搜索菜品表单  -->
            <form action="#" method="post">
                <table width="166px">
                    <tr>
                        <td>
                            <input type="text" id="dish_name" name="foodName" class="select_value"/>
                            <input type="hidden" value="selectFood" name="method">
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" id="sub" value=""/></td>
                    </tr>
                    <tr>
                        <td>
                            <a href="#">
                                <img src="${pageContext.request.contextPath}/app/style/images/look.gif"/>
                            </a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>
