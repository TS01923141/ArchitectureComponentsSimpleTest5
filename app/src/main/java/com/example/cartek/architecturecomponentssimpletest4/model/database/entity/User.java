package com.example.cartek.architecturecomponentssimpletest4.model.database.entity;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by CarTek on 2018/2/27.
 */

public class User extends RealmObject {
    @PrimaryKey
    private String id;

    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
