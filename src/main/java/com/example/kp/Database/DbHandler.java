package com.example.kp.Database;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import org.sqlite.JDBC;

import java.io.File;
import java.sql.*;


public class DbHandler {
    private static final String CON_STR = "jdbc:sqlite:"+ new File("").getAbsolutePath() +"\\BD\\BD.db";
    private static DbHandler instance = null;
    public String name;

    static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    Connection connection;

    public DbHandler() {
        try {
            DriverManager.registerDriver(new JDBC());
            connection = DriverManager.getConnection(CON_STR);
        } catch (SQLException e) {

        }
    }

    DbHandler(String nam) {
        try {
            DriverManager.registerDriver(new JDBC());
            connection = DriverManager.getConnection(CON_STR);
        } catch (SQLException e) {

        }
        name = nam;
    }

    public void buildDataall(TableView tableview) {
        tableview.getColumns().clear();
        tableview.getItems().clear();
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        try {

            ResultSet rs = connection.createStatement().executeQuery("SELECT * from " + name);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                }
                );
                col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent cellEditEvent) {
                        System.out.println(cellEditEvent.toString());
                        int row = cellEditEvent.getTablePosition().getRow();
                        int cel = cellEditEvent.getTablePosition().getColumn();
                        ObservableList list = cellEditEvent.getTableView().getItems();
                        ObservableList list1 = (ObservableList) list.get(row);
                        list1.set(cel,cellEditEvent.getNewValue());
                    }
                });
                col.setEditable(true);
                col.setCellFactory(TextFieldTableCell.forTableColumn());
                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setEditable(true);
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }

    public void BuildDataSQL(TableView tableview, String SQL){
        tableview.getColumns().clear();
        tableview.getItems().clear();
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        try {

            ResultSet rs = connection.createStatement().executeQuery(SQL);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }

    public void Search(TableView tableview, String search, String seacrh_pole){
        tableview.getColumns().clear();
        tableview.getItems().clear();
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        try {

            ResultSet rs = connection.createStatement().executeQuery("SELECT * from " + name + " WHERE " + seacrh_pole + " LIKE '%" + search + "%'");

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }


    public void ChengeDateSQL(String SQL){
        try {
            connection.createStatement().executeQuery(SQL);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    public ResultSet GetDataSQL(String SQL){
        try {
           return  connection.createStatement().executeQuery(SQL);
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return null;
        }
    }
    public void Update(ObservableList list) throws SQLException {

    }

    public void Delete(int row) throws SQLException {
    }
    public void Add() throws SQLException {
    }
}
