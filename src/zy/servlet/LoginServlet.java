package zy.servlet;

import org.apache.commons.beanutils.BeanUtils;
import zy.dao.UserDao;
import zy.domain.User;
import zy.service.UserService;
import zy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author zhangyu
 * @date 2019-08-08-18:01
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取数据
            //2.1获取验证码
            String verifycode = request.getParameter("verifycode");


        //3.验证码校验
        Map<String, String[]> parameterMap = request.getParameterMap();
        HttpSession session=request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //验证码不正确
            //提示信息
            request.setAttribute("login_msg","验证码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);

            return;
        }
        //4.封装user对象
        User user=new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //5.调用service查询
        UserService service=new UserServiceImpl();
        User loginUser = service.login(user);


        //6.判断是否登录成功
        if(loginUser!=null){
            session.setAttribute("user",loginUser);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else {
            request.setAttribute("login_msg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
