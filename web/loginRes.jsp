<%--
  Created by IntelliJ IDEA.
  User: vegetablefriend
  Date: 2020/5/14
  Time: 4:35 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String admin = request.getParameter("name");
    String password = request.getParameter("password");

    if (admin.equals("admin") && password.equals("123456")) {
%>
    <jsp:forward page="/loginServlet"></jsp:forward>

<% } %>

</body>
</html>
