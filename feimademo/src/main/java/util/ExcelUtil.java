package util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.*;
import java.util.*;

public class ExcelUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/fm_bank";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static List<Map<String, String>> readExcel(InputStream is) throws IOException {
        List<Map<String, String>> userList = new ArrayList<>();
        Workbook wb = new XSSFWorkbook(is);
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        while (rows.hasNext()) {
            Row row = rows.next();
            Map<String, String> user = new HashMap<>();
            user.put("userId", row.getCell(0).getStringCellValue());
            user.put("name", row.getCell(1).getStringCellValue());
            user.put("password", row.getCell(2).getStringCellValue());
            user.put("identityId", row.getCell(3).getStringCellValue());
            user.put("phoneNumber", row.getCell(4).getStringCellValue());
            user.put("gender", row.getCell(5).getStringCellValue());
            user.put("birthDate", row.getCell(6).getStringCellValue());
            userList.add(user);
        }
        return userList;
    }

    public static boolean saveUsersToDatabase(List<Map<String, String>> userList) {
        String query = "INSERT INTO users (user_id, name, password, identity_id, phone_number, gender, birth_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (Map<String, String> user : userList) {
                stmt.setString(1, user.get("userId"));
                stmt.setString(2, user.get("name"));
                stmt.setString(3, user.get("password"));
                stmt.setString(4, user.get("identityId"));
                stmt.setString(5, user.get("phoneNumber"));
                stmt.setString(6, user.get("gender"));
                stmt.setString(7, user.get("birthDate"));
                stmt.addBatch();
            }
            stmt.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
