package com.example.kp.Database;

import javafx.collections.ObservableList;

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

    public void Update(ObservableList list) throws SQLException {
        String sql = "UPDATE LK SET ID_LK =?, Login= ?, Password=?, Type_LK=? WHERE ID_LK=?";
        for(int i = 0; i<list.size();i++) {
            ObservableList list1 = (ObservableList) list.get(i);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,list1.get(0));
            preparedStatement.setObject(2,list1.get(1));
            preparedStatement.setObject(3,list1.get(2));
            preparedStatement.setObject(4,list1.get(3));
            preparedStatement.setObject(5,i+1);
            preparedStatement.execute();
        }
    }

    public void Delete(int row) throws SQLException {
        connection.createStatement().execute("DELETE FROM LK WHERE ID_LK="+row);
    }

    public void Add() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(ID_LK) FROM LK");
        rs.next();
        int size  = Integer.parseInt(rs.getString(1))+1;
        String sql = "INSERT INTO LK VALUES("+size+",0,0,0)";
        connection.createStatement().execute(sql);
    }
}
