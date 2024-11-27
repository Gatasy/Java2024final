/*
 * 用户类型信息，映射数据库表
 */

package com.example.demo.model;

import java.sql.*;
import java.time.LocalDate;

public class User {
    private String id;
    private String name;
    private String password;
    private String identity_id;
    private String  phone_number;
    private String gender;
    private Date birth_date;
    private double balance;
    public User(String id, String name, String password, String identity_id, String phone_number, Date date, double balance) {
    // 构造函数逻辑
}

    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getPassword(){
        return this.password;
    }
    public String getIdentityId(){
        return this.identity_id;
    }
    public String getPhoneNumber(){
        return this.phone_number;
    }
    public String getGender(){
        return this.gender;
    }
    public Date getBirthdate(){
        return this.birth_date;
    }
    public Double getBalance(){
        return this.balance;
    }

    
}
