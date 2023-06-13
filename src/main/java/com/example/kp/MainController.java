package com.example.kp;

import com.example.kp.Database.LK;
import com.example.kp.Database.Pol;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MainController {
    public AnchorPane main_window;
    public LK lk;
    private Map<Integer, Window> wind = Map.of(
            0,new AdminWindow(lk),
            1,new WorkerWindom(lk),
            2,new PolWindow(lk)
    );

    private Map<Integer,String> contr = Map.of(
            0, "Admin_window.fxml",
            1, "Worker_windom.fxml",
            2, "Pol_window.fxml"
    );
    public Button admin;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void LK_But_act(ActionEvent actionEvent) throws IOException {
        main_window.getChildren().clear();
        lk = new LK();
        Login login = new Login(lk);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        fxmlLoader.setController(login);
        Scene scene = new Scene(fxmlLoader.load(), 300, 400);
        Stage stage = new Stage();
        stage.setTitle("Логин");
        stage.getIcons().add(new Image(new File("").getAbsolutePath()  + "\\img\\main_icon.jpg"));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        Window window = wind.get(lk.Type_LK);
        window.setlk(lk);
        FXMLLoader mainfxmlLoader = new FXMLLoader(getClass().getResource(contr.get(lk.Type_LK)));
        mainfxmlLoader.setController(window);
        Scene mainscene = new Scene(mainfxmlLoader.load(), 300, 400);
        ObservableList <Node> child = window.Windowreturn();
        do {
            main_window.getChildren().add(child.get(0));
        } while (child.size()!=0);

    }

    public void admin_window(ActionEvent actionEvent) throws IOException {
        main_window.getChildren().clear();
        AdminWindow adminWindow = new AdminWindow(lk);
        FXMLLoader mainfxmlLoader = new FXMLLoader(getClass().getResource("Admin_window.fxml"));
        mainfxmlLoader.setController(adminWindow);
        Scene mainscene = new Scene(mainfxmlLoader.load(), 300, 400);
        ObservableList <Node> child = adminWindow.Windowreturn();
        do {


            main_window.getChildren().add(child.get(0));
        } while (child.size()!=0);

    }
}