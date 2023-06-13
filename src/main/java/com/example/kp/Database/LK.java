package com.example.kp.Database;

import java.sql.*;

public class LK extends DbHandler {
    private String Login;
    private String Password;
    public  int Type_LK;


    public int ID_LK;

    public  LK() {
        super("LK");
        Type_LK = -1;

    }
    public LK (String new_login, String new_password, int new_type_LK) {
        super();
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Products(`Login`, `Password`, `Type_LK`) " +
                        "VALUES(?, ?, ?)")) {
            Login = new_login;
            Password = new_password;
            Type_LK = new_type_LK;
            statement.setObject(1,Login);
            statement.setObject(2,Password);
            statement.setObject(3,Type_LK);
            statement.execute();
        } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    public boolean login(String login, String password) {
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery( "SELECT * FROM LK WHERE Login = '" + login + "' AND Password = '" + password + "'");
            if  (resultSet.getInt(1)==0)
                return false;
            ID_LK = resultSet.getInt(1);
            Login = resultSet.getString("Login");
            Password = resultSet.getString("Password");
            Type_LK = resultSet.getInt("Type_LK");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
