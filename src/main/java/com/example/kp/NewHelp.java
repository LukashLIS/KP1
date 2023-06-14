package com.example.kp;

import com.example.kp.Database.DbHandler;
import com.example.kp.Database.Help;
import com.example.kp.Database.LK;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class NewHelp extends Window implements Initializable {
    public TextArea prich;
    @FXML
    private Help help;
    @FXML
    TextField Name_PO;
    @FXML
    ComboBox Chose_PO;
    @FXML
    ComboBox Chose_Prob;


    Map<String, List<String>> map = Map.of(
      "Word", FXCollections.observableArrayList("Не печатает","Не изменяется текст","Другое"),
            "Excel",FXCollections.observableArrayList("Не печатает","Не изменяется текст","Другое"),
            "Paint",FXCollections.observableArrayList("Не печатает","Не изменяется текст","Другое"),
            "Другое",FXCollections.observableArrayList("Другое")
    );

    public NewHelp(LK l) {
        super(l);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Chose_PO.getItems().addAll("Word", "Excel", "Paint","Другое");
        Chose_PO.setValue("Word");
        Chose_Prob.setItems((ObservableList) map.get(Chose_PO.getValue()));
    }

    public void Chose_PO_act(ActionEvent actionEvent){
        Chose_Prob.setItems((ObservableList) map.get(Chose_PO.getValue()));
        if(Chose_PO.getValue() == "Другое")
            Name_PO.setVisible(true);
        else {
            Name_PO.setVisible(false);
            Name_PO.setText("");
        }
    }

    public void Chose_Prob_act(ActionEvent actionEvent){
    }
    public void back(ActionEvent actionEvent) {
        Stage stage = (Stage) Chose_PO.getScene().getWindow();
        stage.close();
    }

    public void save(ActionEvent actionEvent) {
        help = new Help();
        String prc = Chose_PO.getValue()+" " + Name_PO.getText() + ": Проблема " + Chose_Prob.getValue() + " Комментарий: " + prich.getText();
        help.ChengeDateSQL("INSERT INTO Help " +
                "(ID_Pol, ID_Master, Prich, Date) " +
                "VAlUES ((SELECT ID_Pol FROM Pol WHERE ID_LK = "+ lk.ID_LK +
                "), 0, '"+ prc + "', date())");
        Stage stage = (Stage) Chose_PO.getScene().getWindow();
        stage.close();
    }
}
