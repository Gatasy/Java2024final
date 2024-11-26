package dao;

import java.sql.*;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/fm_bank";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    // 检查登录
    public static boolean checkLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE name = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取账户余额
    public static double getBalance(String accountId) {
        String query = "SELECT balance FROM users WHERE user_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // 存钱操作
    public static boolean deposit(String accountId, double amount) {
        String query = "UPDATE users SET balance = balance + ? WHERE user_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, amount);
            stmt.setString(2, accountId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 取款操作
    public static boolean withdraw(String accountId, double amount) {
        String query = "UPDATE users SET balance = balance - ? WHERE user_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, amount);
            stmt.setString(2, accountId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 转账操作
    public static boolean transfer(String fromAccountId, String toAccountId, double amount) {
        // 开始事务
        String query1 = "UPDATE users SET balance = balance - ? WHERE user_id = ?";
        String query2 = "UPDATE users SET balance = balance + ? WHERE user_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt1 = conn.prepareStatement(query1);
             PreparedStatement stmt2 = conn.prepareStatement(query2)) {

            conn.setAutoCommit(false);  // 开始事务

            // 扣款操作
            stmt1.setDouble(1, amount);
            stmt1.setString(2, fromAccountId);
            int fromRowsUpdated = stmt1.executeUpdate();

            // 存款操作
            stmt2.setDouble(1, amount);
            stmt2.setString(2, toAccountId);
            int toRowsUpdated = stmt2.executeUpdate();

            // 如果两个操作都成功，则提交事务
            if (fromRowsUpdated > 0 && toRowsUpdated > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
