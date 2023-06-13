package com.example.kp;

import com.example.kp.Database.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

public class ChatWindom extends Window implements Initializable {
    public TextArea SMS_Field;
    public TextField SMS_text;
    private DbHandler chat;

    private DAOFACT daofact;

    private int ID_Chat;

    private Map<Integer,String> map = Map.of(
            0,"Админ",
            1,"Мастер",
            2,"Пользователь"
    );




    private Map<Integer, String> type_lk = Map.of(
            0, "Админ",
            1, "Master",
            2, "Pol"
    );

    public ChatWindom(LK l) {
        super(l);

    }

    public ChatWindom(LK l, int id, Chat ch) {
        super(l);
        chat = new DbHandler();
        daofact = new DAOFACT(lk);
        chat = daofact.new_con("Chat");;
        ID_Chat = id;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       refresh();
    }
    public void Send_SMS(ActionEvent actionEvent){
        chat.ChengeDateSQL("INSERT INTO Chat№"+ID_Chat +
                "(Name, Date, Text, ID_LK) " +
                "VAlUES ( '"+map.get(lk.Type_LK)+"', date(), '" + SMS_text.getText() + "',"+lk.ID_LK+")");
        refresh();
    }

    public void refresh(){
        String SMS = "";
        ResultSet resultSet = chat.GetDataSQL("SELECT Chat№" + ID_Chat + ".Name, " +
                "Chat№" + ID_Chat + ".Date, Chat№" + ID_Chat + ".Text FROM Chat№" + ID_Chat);
        while (true) {
            try {
                if (!resultSet.next()) break;
                SMS += resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + "\n";
            } catch (SQLException e) {
                throw new RuntimeException(e);

            }
            SMS_Field.setText(SMS);
        }
    }
}
