package com.example.kp;

import com.example.kp.Database.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminWindow extends Window implements Initializable {
    public AnchorPane window;

    @FXML
    private TableView table;
    @FXML
    private ComboBox Chose_Data;

    private LK lk;
    private Help help;

    private Map<String, DbHandler> map = Map.of(
            "LK",new LK(),
            "Help",new Help(),
            "Master",new Master(),
            "Pol",new Pol()
    );

    public AdminWindow(LK Lk) {
        super(Lk);;
    }

    public void save_button(ActionEvent actionEvent) {
        System.out.println("1");
    }

    public void refresh(ActionEvent actionEvent) {
        Change_data(actionEvent);
    }

    public void Change_data(ActionEvent actionEvent){
        DbHandler db= map.get(Chose_Data.getValue());
    map.get(Chose_Data.getValue()).buildDataall(table);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Chose_Data.getItems().addAll("LK", "Help", "Master","Pol");
    }
}
