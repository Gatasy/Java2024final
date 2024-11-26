package servlet;

import dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountId = request.getParameter("accountId");
        double withdrawAmount = Double.parseDouble(request.getParameter("withdrawAmount"));

        if (UserDAO.withdraw(accountId, withdrawAmount)) {
            response.getWriter().write("取款成功！");
        } else {
            response.getWriter().write("取款失败！");
        }
    }
}

