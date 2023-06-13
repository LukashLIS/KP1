package com.example.kp;

import com.example.kp.Database.LK;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class Window {

    public LK lk;
    public AnchorPane window;


    public Window(LK l)
    {
        lk = new LK();
        lk=l;
    }

    public void setlk(LK Lk){
        lk=Lk;
    }

    public ObservableList
            <Node> Windowreturn()
    {
        return  window.getChildren();

    }
}
