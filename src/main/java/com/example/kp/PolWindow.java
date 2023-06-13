package com.example.kp;

import com.example.kp.Database.Chat;
import com.example.kp.Database.Help;
import com.example.kp.Database.LK;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class PolWindow extends Window implements Initializable {

    private Help help;
    public TableView Work_table;
    public Button Chose_wrk;
    public Button Reset;
    public DatePicker date_search;
    public TextField Search_field;
    public ComboBox search_chose;
    public CheckBox date_search_on;

    public PolWindow(LK l) {
        super(l);
        help = new Help();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        help = new Help();
        help.BuildDataSQL(Work_table,"SELECT Help.ID_Help, Pol.Name, Help.Prich, Pol.Telphone, Pol.Location,  Help.Date " +
                "FROM Help INNER JOIN Pol ON Help.ID_Pol = Pol.ID_Pol " +
                "WHERE Pol.ID_LK = " + super.lk.ID_LK);
    }

    public void Search_but(ActionEvent actionEvent) {
        if(!date_search_on.isSelected())
            help.BuildDataSQL(Work_table,"SELECT Help.ID_Help, Pol.Name, Help.Prich, Pol.Telphone, Pol.Location,  Help.Date " +
                    "FROM Help INNER JOIN Pol ON Help.ID_Pol = Pol.ID_Pol " +
                    "WHERE " + search_chose.getValue() +" like '%" + Search_field.getText()+ "%'" +
                    " AND Pol.ID_LK = " + super.lk.ID_LK);
        else
            help.BuildDataSQL(Work_table,"SELECT Help.ID_Help, Pol.Name, Help.Prich, Pol.Telphone, Pol.Location,  Help.Date " +
                    "FROM Help INNER JOIN Pol ON Help.ID_Pol = Pol.ID_Pol " +
                    "WHERE " + search_chose.getValue() +" like '%" + Search_field.getText()+ "%'" +
                    " AND Pol.ID_LK = " + super.lk.ID_LK +
                    " AND Help.Date = '" + date_search.getValue().toString() + "'");
    }

    public void refresh(ActionEvent actionEvent) {
        help.BuildDataSQL(Work_table,"SELECT Help.ID_Help, Pol.Name, Help.Prich, Pol.Telphone, Pol.Location,  Help.Date " +
                "FROM Help INNER JOIN Pol ON Help.ID_Pol = Pol.ID_Pol " +
                "WHERE Pol.ID_LK = " + super.lk.ID_LK);
    }

    public void Chose_wrk_act(ActionEvent actionEvent) throws IOException {
        NewHelp newWork = new NewHelp(lk);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("New_Help.fxml"));
        fxmlLoader.setController(newWork);
        Scene scene = new Scene(fxmlLoader.load(), 300, 400);
        Stage stage = new Stage();
        stage.setTitle("Логин");
        stage.getIcons().add(new Image(new File("").getAbsolutePath()  + "\\img\\main_icon.jpg"));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void Chat_on(ActionEvent actionEvent){
        try {
                ResultSet resultSet = help.GetDataSQL("SELECT Help.Chat, Pol.Name, Master.Name FROM Help INNER JOIN Pol ON Help.ID_Pol = Pol.ID_Pol INNER JOIN Master ON Help.ID_Master = Master.ID_Master WHERE Pol.ID_LK = " + super.lk.ID_LK);
                ChatWindom chatWindom = new ChatWindom(lk,1,new Chat("1"));
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Chat_window.fxml"));
                fxmlLoader.setController(chatWindom);
                Scene scene = new Scene(fxmlLoader.load(), 300, 400);
                Stage stage = new Stage();
                stage.setTitle("Логин");
                stage.getIcons().add(new Image(new File("").getAbsolutePath()  + "\\img\\main_icon.jpg"));
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
