package com.example.kp.Database;

import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pol extends  DbHandler{
    public Pol(){
        super("Pol");
    }


    public void Update(ObservableList list) throws SQLException {
        String sql = "UPDATE Pol SET ID_Pol= ?, Name=?, Telphone=?, Location=?, ID_LK=? WHERE ID_Pol=?";
        for(int i = 0; i<list.size();i++) {
            ObservableList list1 = (ObservableList) list.get(i);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,list1.get(0));
            preparedStatement.setObject(2,list1.get(1));
            preparedStatement.setObject(3,list1.get(2));
            preparedStatement.setObject(4,list1.get(3));
            preparedStatement.setObject(5,list1.get(4));
            preparedStatement.setObject(6,i+1);
            preparedStatement.execute();
        }
    }
    public void Delete(int row) throws SQLException {
        connection.createStatement().execute("DELETE FROM Pol WHERE ID_Pol="+row);
        connection.close();
    }

    public void Add() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(ID_Pol) FROM Pol");
        rs.next();
        int size  = Integer.parseInt(rs.getString(1))+1;
        String sql = "INSERT INTO Pol VALUES("+size+",0,0,0,0)";
        connection.createStatement().execute(sql);
        connection.close();
    }
}
