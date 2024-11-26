package util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.sql.*;
import java.io.*;

public class PDFUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/fm_bank";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static String generateYearEndReport() throws DocumentException, FileNotFoundException {
        String pdfFilePath = "YearEndReport.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
        document.open();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            // 获取账户总数
            ResultSet rsTotalAccounts = stmt.executeQuery("SELECT COUNT(*) AS total_accounts FROM users");
            int totalAccounts = rsTotalAccounts.next() ? rsTotalAccounts.getInt("total_accounts") : 0;

            // 获取总余额
            ResultSet rsTotalBalance = stmt.executeQuery("SELECT SUM(balance) AS total_balance FROM users");
            double totalBalance = rsTotalBalance.next() ? rsTotalBalance.getDouble("total_balance") : 0.0;

            // 获取今年新开户数
            ResultSet rsNewAccounts = stmt.executeQuery("SELECT COUNT(*) AS new_accounts FROM users WHERE YEAR(created_at) = YEAR(CURDATE())");
            int newAccounts = rsNewAccounts.next() ? rsNewAccounts.getInt("new_accounts") : 0;

            // 写入PDF内容
            document.add(new Paragraph("飞马银行年终报告", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD)));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("账户总数: " + totalAccounts));
            document.add(new Paragraph("总余额: " + totalBalance + " 飞马币"));
            document.add(new Paragraph("今年新开户数: " + newAccounts));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        document.close();
        return pdfFilePath;
    }
}
