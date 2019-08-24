<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>foreach标签</title>
</head>
<body>
    <c:forEach begin="1" end="10" step="2" var="i" varStatus="s">
        ${i}  ${s.index} ${s.count} <br>
    </c:forEach>
<hr>
    <%
        List list=new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        request.setAttribute("list",list);
    %>

    <c:forEach items="${list}" var="str" varStatus="s">
        ${str} ${s.index} ${s.count}<br>
    </c:forEach>
</body>
</html>
