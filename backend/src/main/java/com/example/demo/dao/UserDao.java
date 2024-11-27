/*
 * 连接数据库CRUD逻辑，针对"改"时对于信息类型进行分类
 */

package com.example.demo.dao;
import com.example.demo.dao.DatabaseConnection;
import com.example.demo.model.User;
import java.sql.*;
import java.sql.Date;
import java.util.*;


public class UserDao {
    public void addUser(User user){
        String sql = "INSERT INTO BankUser (id,name,password,identity_id,phone_number,gender,birth_date,balance) values(?,?,?,?,?,?,?,?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){
                statement.setString(1, user.getId());
                statement.setString(2, user.getName());
                statement.setString(3, user.getPassword());
                statement.setString(4, user.getIdentityId());
                statement.setString(5, user.getPhoneNumber());
                statement.setString(6,user.getGender());
                statement.setDate(7, user.getBirthdate());
                statement.setDouble(8, user.getBalance());
                
                statement.executeUpdate();
        }catch(SQLException e ){
            System.err.println("添加用户时发生错误");
        }
    }
    public void deletUser(String userId){
        String sql = "DELETE FROM BankUser WHERE ID = ?";
        try(Connection conn  = DatabaseConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){
                statement.setString(1, userId); //设置占位符参数
                int k = statement.executeUpdate();
                if(k > 0){
                    System.out.println("用户删除成功！");
                }
                else{
                    System.out.println("未找到需要删除的用户");
                }
        }catch(SQLException e){
            System.err.println("删除用户时发生错误");
        }
    }
    public User searchUser(String userId){
        String sql = "SELECT * FROM BankUser WHERE ID = ?";
        try(Connection conn  = DatabaseConnection.getConnection();
        PreparedStatement statement  = conn.prepareStatement(sql)){
            statement.setString(1, sql);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return new User(
                    resultSet.getString("id:"),
                    resultSet.getString("name:"),
                    resultSet.getString("Identity_id:"),
                    resultSet.getString("Phone_number:"),
                    resultSet.getString("gender:"),
                    resultSet.getDate("Birth_date:"),
                    resultSet.getDouble("balance")

                );
            }

        }catch(SQLException e){
            System.err.println("查找用户时发生错误");
        }
        return null;
    }

    public boolean updatebBalance(String userId,double newBalance){ //转账/存款时更新余额
        String sql = "UPDATE BankUser SET balance = ? WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setDouble(1, newBalance);
            statement.setString(2, userId);
            int k = statement.executeUpdate();
            return k > 0;
        } catch (SQLException e) {
            System.err.println("修改账户余额时发生错误");
            return false;
        }
    }
    public boolean updatePassword(String userId,String newPassword){
        String sql = "UPDATE BankUser SET password = ? WEHRE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, newPassword);
            statement.setString(2, userId);
            int k = statement.executeUpdate();
            return k > 0;

        } catch (SQLException e) {
            System.err.println("密码修改时发生错误");
            return false;
        }
    }
    public boolean updateInfo(String userId,String filedName,Object newValue){
        String sql = "UPDATE BankUser SET "+ filedName + "= ? WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){
                statement.setObject(1, newValue);
                statement.setString(2, userId);
                int k = statement.executeUpdate();
                return k > 0;
            }catch(SQLException e){
                System.err.println("信息修改时发生错误");
                return false;
            }
    }

}
