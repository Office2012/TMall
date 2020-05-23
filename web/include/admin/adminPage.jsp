<%@ page import="util.Page" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>


<script>
    $(function () {
        $("ul.pagination li.disabled a").click(function () {
            return false;
        });
    });

</script>

<nav>
    <ul class="pagination">

        <li
        <%
            System.out.println();
        %>
            <a href="<%=reqUri%>?start=0${page.param}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
    </ul>
</nav>
