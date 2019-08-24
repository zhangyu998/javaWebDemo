<%@ page import="zy.domain.User" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        //获取对象
        User user=new User();
        user.setName("张三");
        user.setAge(22);
        user.setBirthday(new Date());
        request.setAttribute("u",user);

        //获取list集合
        List list=new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add(user);
        request.setAttribute("list",list);

        //获取map集合
        Map map=new HashMap();
        map.put("sname","张三");
        map.put("sage","123");
        map.put("user",user);
        request.setAttribute("map",map);
    %>
   <%--获取对象--%>
    ${u.birstr}<br>

    <%--获取list集合--%>
    ${list}<br>
    ${list[0]}<br>
    ${list[1]}<br>
    ${list[2].name}<br>

    <%--获取map集合--%>
    ${map.sname}<br>
    ${map.sage}<br>
    ${map["sname"]}<br>
    ${map.user.name}
</body>
</html>
