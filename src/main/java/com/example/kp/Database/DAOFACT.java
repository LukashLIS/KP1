package com.example.kp.Database;

import java.util.Map;

public class DAOFACT {


    public DAOFACT(LK lk1){
        lk=lk1;
    }
    LK lk = new LK();
    Map<String,DbHandler> map = Map.of(
            "Chat",new Chat(),
            "Help",new Help(),
            "lk", new LK(),
            "Master", new  Master(),
            "Pol",new Pol()

    );
    public DbHandler new_con(String name){
        return map.get(name);
    }
}
