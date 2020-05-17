<%--
  Created by IntelliJ IDEA.
  User: vegetablefriend
  Date: 2020/5/17
  Time: 4:05 下午
  To change this template use File | Settings | File Templates.
--%>
<%@page import="pojo.Product"%>
<%@page import="pojo.Category"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" import="java.util.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../include/admin/adminHeader.jsp"%>
<%@include file="../../include/admin/adminNavigator.jsp"%>

<title>编辑产品</title>

<script type="text/javascript">
    $(function() {
        $("#editForm").submit(function() {
            if (!checkEmpty("name", "产品名称"))
                return false;
            if (!checkEmpty("subTitle", "小标题"))
                return false;
            if (!checkNumber("originalPrice", "原价格"))
                return false;
            if (!checkNumber("promotePrice", "优惠价格"))
                return false;
            if (!checkInt("stock", "库存"))
                return false;
            return true;
        });
    });
</script>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    Product product = (Product) request.getAttribute("product");
%>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a
                href="admin_product_list?category.id=${product.category.id}">${product.category.name}</a></li>
        <li class="active">${product.name}</li>
        <li class="active">编辑产品</li>
    </ol>

    <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑产品</div>
        <div class="panel-body">
            <form method="post" id="editForm"
                  action="product/updateProduct.action">
                <table class="editTable">
                    <tr>
                        <td>产品名称</td>
                        <td><input id="name" name="product.name"
                                   value="${product.name}" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>产品小标题</td>
                        <td><input id="subTitle" name="product.subTitle" type="text"
                                   value="${product.subTitle}" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>原价格</td>
                        <td><input id="originalPrice"
                                   value="${product.originalPrice}" name="product.originalPrice"
                                   type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>优惠价格</td>
                        <td><input id="promotePrice" value="${product.promotePrice}"
                                   name="product.promotePrice" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>库存</td>
                        <td><input id="stock" value="${product.stock}"
                                   name="product.stock" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>类别</td>
                        <td>
                            <select name="product.category.id" class="form-control">
                                <%
                                    for (Category category : categories) {
                                %>
                                <option value="<%=category.getId()%>"  <%if(category.getId()==product.getCategory().getId()) out.println(" selected");%>>
                                    <%=category.getName()%>
                                </option>
                                <%}%>
                            </select>
                        </td>


                    </tr>

                    <tr class="submitTR">
                        <td colspan="2" align="center"><input type="hidden"
                                                              name="product.id" value="${product.id}">
                            <button type="submit" class="btn btn-success">提 交</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>