package zy.servlet;

import zy.domain.PageBean;
import zy.domain.User;
import zy.service.UserService;
import zy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author zhangyu
 * @date 2019-08-12-10:52
 */
@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");


        //优化,因为当第一次访问finduserbyPageServlet时currentPage和rows为空。
        // 直接访问时会报NumberFormatException: null异常。
        // 解决方法：先赋默认值
        if (currentPage==null || "".equals(currentPage)){
            currentPage="1";
        }
        if (rows==null || "".equals(rows)){
            rows="5";
        }

        Map<String, String[]> condition = request.getParameterMap();

        //2.调用service进行查询
        UserService service=new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage, rows,condition);
        System.out.println(pb);
        //3.将PageBean存入request
        request.setAttribute("pb",pb);

        //4.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
