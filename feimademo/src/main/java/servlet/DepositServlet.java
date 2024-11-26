package servlet;

import dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountId = request.getParameter("accountId");
        double depositAmount = Double.parseDouble(request.getParameter("depositAmount"));

        if (UserDAO.deposit(accountId, depositAmount)) {
            response.getWriter().write("存款成功！");
        } else {
            response.getWriter().write("存款失败！");
        }
    }
}

