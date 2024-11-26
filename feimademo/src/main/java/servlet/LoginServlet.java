package servlet;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (UserDAO.checkLogin(username, password)) {
            // 登录成功，重定向到账户管理页面
            response.sendRedirect("accountManagement.jsp");
        } else {
            // 登录失败，返回错误消息
            response.getWriter().write("登录失败，用户名或密码错误");
        }
    }
}
