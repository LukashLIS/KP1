package com.example.kp.Database;

import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Master extends DbHandler{
    public Master(){
        super("Master");
    }

    public void Update(ObservableList list) throws SQLException {
        String sql = "UPDATE Master SET ID_Master=?, Name=?, Telphone=?, ID_LK=? WHERE ID_Master =?";
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
        connection.createStatement().execute("DELETE FROM Master WHERE ID_Master="+row);
    }

    public void Add() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(ID_Master) FROM Master");
        rs.next();
        int size  = Integer.parseInt(rs.getString(1))+1;
        String sql = "INSERT INTO Master VALUES("+size+",0,0,0)";
        connection.createStatement().execute(sql);
    }
}
