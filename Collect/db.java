import java.sql.*;

// 数据库
public class db {     //用于数据库相关操作的类
    Connection con;
    Statement state;

    public db() {
        String DBDriver = "com.mysql.cj.jdbc.Driver";  //驱动类类名
        try {
            Class.forName(DBDriver);     //加载驱动类
            String url = "jdbc:mysql://localhost:3306/mydatabase";  //连接名为mydatabase的数据库
            String user = "root";
            String password = "root";
            System.out.println("Connect to db...");
            con = DriverManager.getConnection(url, user, password);
            state = con.createStatement();
            System.out.println("Connect successfully.");  //成功连接
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :" + e.getMessage());
        } catch (SQLException ex) {
            System.out.println("SQLException :" + ex.getMessage());
        }
    }

    public synchronized String db_execute(String sql) {     //mysql直接执行sql语句,返回是否执行成功
        try {
            state.execute(sql);
            return "true";
        } catch (SQLException ex) {
            System.out.println("SQLException :" + ex.getMessage());
            return "false";
        } catch (Exception e) {
            System.out.println("Exception :" + e.getMessage());
            return "false";
        }
    }

    public synchronized String db_query(String sql) {  //mysql执行sql语句后，返回一个值，格式为String
        String res = "";
        try {
            ResultSet rs = state.executeQuery(sql);
            rs.next();
            res = rs.getString(1);
        } catch (SQLException ex) {
            System.out.println("SQLException :" + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Exception :" + e.getMessage());
        }
        return res;
    }

    public synchronized int db_count(String sql) {  //mysql执行sql语句后，返回一个整数
        try {
            ResultSet rs = state.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("SQLException :" + ex.getMessage());
            return 0;
        } catch (Exception e) {
            System.out.println("Exception :" + e.getMessage());
            return 0;
        }
    }

    public synchronized String[] db_query_m(String sql) {  //mysql执行sql语句后，返回一个值（包含一位用户的信息），格式为String[]
        String[] res = new String[8];
        try {
            ResultSet rs = state.executeQuery(sql);
            rs.next();
            res[0] = rs.getString("id");
            res[1] = rs.getString("name");
            res[2] = rs.getString("password");
            res[3] = rs.getString("identity_id");
            res[4] = rs.getString("phone_number");
            res[5] = rs.getString("gender");
            res[6] = rs.getString("birth_date");
            res[7] = String.valueOf(rs.getDouble("balance"));
        } catch (SQLException ex) {
            System.out.println("SQLException :" + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Exception :" + e.getMessage());
        }
        return res;
    }

    public synchronized String[] db_query_m_r(String sql, int r) {  //mysql执行sql语句后，返回条件库所有数据存入String[]
        String[] res = new String[r * 8];
        try {
            ResultSet rs = state.executeQuery(sql);
            int index = 0;
            while (rs.next()) {
                res[index++] = rs.getString("id");
                res[index++] = rs.getString("name");
                res[index++] = rs.getString("password");
                res[index++] = rs.getString("identity_id");
                res[index++] = rs.getString("phone_number");
                res[index++] = rs.getString("gender");
                res[index++] = rs.getString("birth_date");
                res[index++] = String.valueOf(rs.getDouble("balance"));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException :" + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Exception :" + e.getMessage());
        }
        return res;
    }
}