package servlet;

import util.PDFUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 调用PDF工具类生成报告
            String pdfFilePath = PDFUtil.generateYearEndReport();

            // 将PDF文件发送到客户端进行下载
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=YearEndReport.pdf");

            java.nio.file.Path path = java.nio.file.Paths.get(pdfFilePath);
            java.nio.file.Files.copy(path, response.getOutputStream());
            response.getOutputStream().flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("生成报告时发生错误：" + e.getMessage());
        }
    }
}
