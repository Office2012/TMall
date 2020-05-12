<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@page import = "dao.UserDAOImpl" %>
<%@page import = "dao.UserDAO" %>
<%@page import = "pojo.User" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>

<div class="workingArea">
    <h1 class="label label-info">用户管理</h1>
    <br>
    <br>
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>用户名称</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>

                <%
                    List<User> users = (List)request.getAttribute("users");

                    for(User user:users) {
                %>
                    <tr>
                    <td><%=user.getId() %></td>
                    <td><%=user.getName() %></td>
                    <td><a href="userServlet?flag=Reset&uid=<%=user.getId()%>">  重置密码</a></td>
                    </tr>

                <%
                    }
                %>

            </tbody>
        </table>
    </div>
    <div class="pageDiv">
      <%--  <%@include file="../include/admin/adminPage.jsp" %> --%>
    </div>
</div>

<%@include file="../include/admin/adminFooter.jsp" %>
</body>
</html>
