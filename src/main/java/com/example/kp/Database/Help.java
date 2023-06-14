package com.example.kp.Database;

import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Help extends DbHandler{

    public Help() {
        super("Help");
    }
    public void Update(ObservableList list) throws SQLException {
        String sql = "UPDATE Help SET ID_Help =?, ID_Pol= ?, ID_Master=?, Prich=?, Date=?, ID_Chat=? WHERE ID_Help=?";
        for(int i = 0; i<list.size();i++) {
            ObservableList list1 = (ObservableList) list.get(i);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,list1.get(0));
            preparedStatement.setObject(2,list1.get(1));
            preparedStatement.setObject(3,list1.get(2));
            preparedStatement.setObject(4,list1.get(3));
            preparedStatement.setObject(5,list1.get(4));
            preparedStatement.setObject(6,list1.get(5));
            preparedStatement.setObject(7,i+1);
            preparedStatement.execute();
        }
    }

    public void Delete(int row) throws SQLException {
        connection.createStatement().execute("DELETE FROM Help WHERE ID_Help="+row);
    }

    public void Add() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(ID_Help) FROM Help");
        rs.next();
        int size  = Integer.parseInt(rs.getString(1))+1;
        String sql = "INSERT INTO Help VALUES("+size+",0,0,0,0,0)";
        connection.createStatement().execute(sql);
    }
}


