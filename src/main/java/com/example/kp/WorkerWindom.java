package com.example.kp;

import com.example.kp.Database.Chat;
import com.example.kp.Database.Help;
import com.example.kp.Database.LK;
import javafx.collections.ObservableList;
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
import java.util.ResourceBundle;

public class WorkerWindom extends Window implements Initializable {

    public TableView Work_table;
    public Button Chose_wrk;
    public Button Reset;
    public DatePicker date_search;
    public TextField Search_field;
    public ComboBox search_chose;
    public CheckBox date_search_on;


    private Help help;

    public WorkerWindom(LK Lk) {
        super(Lk);
        help = new Help();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       help = new Help();
       String SQL = "SELECT Help.ID_Help, Pol.Name, Help.Prich, Pol.Telphone, Pol.Location,  Help.Date FROM Help INNER JOIN Pol ON Help.ID_Pol = Pol.ID_Pol INNER JOIN Master ON Help.ID_Master = Master.ID_Master WHERE Master.ID_LK = " + super.lk.ID_LK;
        help.BuildDataSQL(Work_table,SQL);
   System.out.println(Work_table.getColumns().get(0).toString());
   search_chose.getItems().add("Pol.Name");
   search_chose.getItems().add("Help.Prich");
   search_chose.getItems().add("Pol.Location");

    }

    public void Chose_wrk_act(ActionEvent actionEvent) throws IOException {
        NewWork newWork = new NewWork(lk);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("New_work.fxml"));
        fxmlLoader.setController(newWork);
        Scene scene = new Scene(fxmlLoader.load(), 300, 400);
        Stage stage = new Stage();
        stage.setTitle("Логин");
        stage.getIcons().add(new Image(new File("").getAbsolutePath()  + "\\img\\main_icon.jpg"));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void change_date(ActionEvent actionEvent){

    }

    public void Searchintable(ActionEvent actionEvent) {
        help.BuildDataSQL(Work_table,"SELECT Help.ID_Help, Pol.Name, Help.Prich, Pol.Telphone, Pol.Location,  Help.Date " +
                "FROM Help INNER JOIN Pol ON Help.ID_Pol = Pol.ID_Pol " +
                "INNER JOIN Master ON Help.ID_Master = Master.ID_Master " +
                "WHERE " + search_chose.getValue() +" = " + Search_field.getText()+ " AND Master.ID_LK = " + lk.ID_LK);
    }

    public void Search_but(ActionEvent actionEvent){
        String str =  date_search.getValue().toString();
        if(!date_search_on.isSelected())
        help.BuildDataSQL(Work_table,"SELECT Help.ID_Help, Pol.Name, Help.Prich, Pol.Telphone, Pol.Location,  Help.Date " +
                "FROM Help INNER JOIN Pol ON Help.ID_Pol = Pol.ID_Pol " +
                "INNER JOIN Master ON Help.ID_Master = Master.ID_Master " +
                "WHERE " + search_chose.getValue() +" like '%" + Search_field.getText()+ "%'" +
                " AND Master.ID_LK = " + super.lk.ID_LK);
        else
            help.BuildDataSQL(Work_table,"SELECT Help.ID_Help, Pol.Name, Help.Prich, Pol.Telphone, Pol.Location,  Help.Date " +
                    "FROM Help INNER JOIN Pol ON Help.ID_Pol = Pol.ID_Pol " +
                    "INNER JOIN Master ON Help.ID_Master = Master.ID_Master " +
                    "WHERE " + search_chose.getValue() +" like '%" + Search_field.getText()+ "%'" +
                    " AND Master.ID_LK = " + lk.ID_LK +
                    " AND Help.Date = '" + date_search.getValue().toString() + "'");
    }

    public void refresh(ActionEvent actionEvent){
        String SQL = "SELECT Help.ID_Help, Pol.Name, Help.Prich, Pol.Telphone, Pol.Location,  Help.Date FROM Help INNER JOIN Pol ON Help.ID_Pol = Pol.ID_Pol INNER JOIN Master ON Help.ID_Master = Master.ID_Master WHERE Master.ID_LK = " + super.lk.ID_LK;
        help.BuildDataSQL(Work_table,SQL);
    }

    public void Chat_open(ActionEvent actionEvent) throws IOException {
        ObservableList obj = (ObservableList) Work_table.getItems().get(Work_table.getSelectionModel().getSelectedIndex());
        ChatWindom chatWindom = new ChatWindom(lk, Integer.parseInt(obj.get(0).toString()),new Chat(obj.get(0).toString()));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Chat_window.fxml"));
        fxmlLoader.setController(chatWindom);
        Scene scene = new Scene(fxmlLoader.load(), 300, 400);
        Stage stage = new Stage();
        stage.setTitle("Логин");
        stage.getIcons().add(new Image(new File("").getAbsolutePath()  + "\\img\\main_icon.jpg"));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}
