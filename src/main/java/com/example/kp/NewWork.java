package com.example.kp;

import com.example.kp.Database.Help;
import com.example.kp.Database.LK;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewWork extends Window implements Initializable {
    public Button back;
    public TableView Work_table;
    private Help help;

    public NewWork(LK l){
        super(l);
        help = new Help();
    }

    public void Chose(ActionEvent actionEvent) {
        help = new Help();
        ObservableList obj = (ObservableList) Work_table.getItems().get(Work_table.getSelectionModel().getSelectedIndex());
        help.ChengeDateSQL("UPDATE Help" +
                " SET (ID_Master) = (SELECT ID_Master FROM Master WHERE ID_LK = "+ lk.ID_LK +
                ") WHERE ID_Help = " + obj.get(0));
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    public void Back(ActionEvent actionEvent) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        help.BuildDataSQL(Work_table, "SELECT Help.ID_Help, Pol.Name, Help.Prich, Pol.Telphone, Pol.Location,  Help.Date " +
                "FROM Help INNER JOIN Pol ON Help.ID_Pol = Pol.ID_Pol " +
                "INNER JOIN Master ON Help.ID_Master = Master.ID_Master " +
                "WHERE Master.ID_LK = 0");


    }
}
