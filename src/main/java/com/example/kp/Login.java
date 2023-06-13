package com.example.kp;

import com.example.kp.Database.DbHandler;
import com.example.kp.Database.LK;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Login {
    public TextField FIO ;
    public ImageView img;
    public TextField login_text;
    public TextField password_text;
    public TextField login;
    public TextField password;
    public TextField Telphone;
    public TextField Adres;
    DbHandler databaseWork;

    private LK lk;

    public Login(LK Lk) {
        lk = Lk;
    }

    public void Regist(ActionEvent actionEvent) throws IOException {
        Login login = new Login(lk);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
        fxmlLoader.setController(login);
        Scene scene = new Scene(fxmlLoader.load(), 300, 400);
        Stage stage = new Stage();
        stage.setTitle("Регистрация");
        stage.getIcons().add(new Image(new File("").getAbsolutePath()  + "\\img\\main_icon.jpg"));
        stage.setScene(scene);
        stage.show();
        stage.showAndWait();
    }

    public void Login_acces(ActionEvent actionEvent) {
        String login = login_text.getText();
        String Password = password_text.getText();
        if (lk.login(login, Password)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Вход");
            alert.setHeaderText("Вход успешен");
            alert.setContentText("Поздравляю");
            alert.show();
            Stage stage = (Stage) login_text.getScene().getWindow();
            stage.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Вход");
            alert.setHeaderText("Вход не успешен");
            alert.setContentText("Поздравляю");
            alert.show();
        }
    }

    public void regist(ActionEvent actionEvent) throws SQLException {
            lk.ChengeDateSQL("INSERT INTO LK (Login,Password,Type_LK) VALUES ('"+login.getText()+"','"+password.getText()+"',2)");
            lk.ChengeDateSQL("INSERT INTO POl (Name,Telphone,Location,ID_LK) VALUES ('"+FIO.getText()+"','"+Telphone.getText()+"','"+Adres.getText()+"',"+lk.GetDataSQL("SELECT COUNT(*) FROM LK").getFetchSize()+")");
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();
    }

    public void back(ActionEvent actionEvent) {
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();
    }
}
