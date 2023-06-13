package com.example.kp.Database;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Chat extends DbHandler{
    public Chat(String id) {
        super("Chat№" + id);
    }
    public Chat(){
        super("Chat№1");
    }
}


