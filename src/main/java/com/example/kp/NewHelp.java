package com.example.kp;

import com.example.kp.Database.Help;
import com.example.kp.Database.LK;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class NewHelp extends Window implements Initializable {
    public TextArea prich;
    private Help help;

    public NewHelp(LK l) {
        super(l);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void back(ActionEvent actionEvent) {
    }

    public void save(ActionEvent actionEvent) {
        help = new Help();
        help.ChengeDateSQL("INSERT INTO Help " +
                "(ID_Pol, ID_Master, Prich, Date) " +
                "VAlUES ((SELECT ID_Pol FROM Pol WHERE ID_LK = "+ lk.ID_LK +
                "), 0, '"+ prich.getText() + "', date())");
    }
}
