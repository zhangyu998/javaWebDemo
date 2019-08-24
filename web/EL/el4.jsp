<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el隐式对象</title>
</head>

<body>
    ${pageContext.request}
    <h3>jsp动态获取虚拟目录</h3>
    ${pageContext.request.contextPath}
</body>
</html>
