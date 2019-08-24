<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取域中的值</title>
</head>
<body>
    <%
        //在域中存储数据
        request.setAttribute("name","张三");
        session.setAttribute("age","121");
    %>
<h3>获取值</h3>
${requestScope.name}
${sessionScope.age}
</body>
</html>
