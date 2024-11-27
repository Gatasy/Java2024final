package servlet;

import dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromAccountId = request.getParameter("fromAccountId");
        String toAccountId = request.getParameter("toAccountId");
        double transferAmount = Double.parseDouble(request.getParameter("transferAmount"));

        if (UserDAO.transfer(fromAccountId, toAccountId, transferAmount)) {
            response.getWriter().write("转账成功！");
        } else {
            response.getWriter().write("转账失败！");
        }
    }
}
