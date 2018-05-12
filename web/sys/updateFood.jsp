<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <!-- 包含公共的JSP代码片段 -->

    <title>无线点餐平台</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/page_common.js"></script>
    <link href="${pageContext.request.contextPath}/sys/style/css/common_style_blue.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/style/css/index_1.css"/>
</head>
<body>

<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">


            <img border="0" width="13" height="13"
                 src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif"/> 更新新菜品


        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <!-- 表单内容 -->
    <form action="/food?method=updateFood" method="post" enctype="multipart/form-data">
        <%--设置菜品id隐藏域--%>
        <input type="hidden" name="foodId" value="${food.id}"/>
        <!-- 本段标题（分段标题） -->
        <div class="ItemBlock_Title">
            <img width="4" height="7" border="0"
                 src="${pageContext.request.contextPath}/sys/style/images/item_point.gif"> 菜品信息&nbsp;
        </div>
        <!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <div class="ItemBlock2">
                    <table cellpadding="0" cellspacing="0" class="mainForm">
                        <tr>
                            <td width="80px">菜系</td>
                            <td>
                                <select name="foodTypeId" style="width:80px">
                                    <c:forEach items="${requestScope.foodTypes}" var="foodType">
                                        <option value="${foodType.id}" ${foodType.id eq requestScope.food.food_type_id ? 'selected="selected"':""}>${foodType.typeName}</option>
                                    </c:forEach>
                                </select>
                                *<input type="hidden" name="id" value="1"/></td>
                        </tr>
                        <tr>
                            <td width="80px">菜名</td>
                            <td><input type="text" name="foodName" class="InputStyle" value="${food.foodName}"/> *</td>
                        </tr>
                        <tr>
                            <td>价格</td>
                            <td><input type="text" name="price" class="InputStyle" value="${food.price}"/> *</td>
                        </tr>
                        <tr>
                            <td>会员价格</td>
                            <td><input type="text" name="mprice" class="InputStyle" value="${food.mprice}"/> *</td>
                        </tr>

                        <tr>
                            <td>简介</td>
                            <td><textarea name="introduce" class="TextareaStyle">${food.remark}</textarea></td>
                        </tr>
                        <tr>
                            <td width="80px">菜品图片</td>
                            <td>

                                <%--<img style='max-width:68px;width:68px;width:expression(width>68?"68px":width "px");max-width: 68px;'
                                     src="${pageContext.request.contextPath}/attachment/${food.img}">--%>
                                <input type="hidden" name="image" value="${food.img}"/>
                                <c:choose>
                                    <c:when test="${food.img eq '无图片'}">
                                        您还未上传图片，请选择<br/>
                                        <input type="file" name="img"/> *
                                    </c:when>
                                    <c:otherwise>
                                        您已经上传过文件，重新上传
                                        <br/>
                                        <input type="file" name="img"/> *
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>


        <!-- 表单操作 -->
        <div id="InputDetailBar">


            <input type="submit" value="修改" class="FunctionButtonInput">


            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
    </form>
</div>
</body>
</html>
