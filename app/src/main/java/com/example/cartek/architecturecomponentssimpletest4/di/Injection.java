package com.example.cartek.architecturecomponentssimpletest4.di;



import com.example.cartek.architecturecomponentssimpletest4.model.database.dao.UserDao;

import io.realm.Realm;

/**
 * Created by CarTek on 2018/2/27.
 */

public class Injection {
    public static UserDao provideUserDatabase(){
        Realm database;
        database = Realm.getDefaultInstance();
        UserDao dao = new UserDao(database);
        return dao;
    }
//    public static UserDao provideUserDatabase(){
//        UserDao dao = new UserDao();
//        return dao;
//    }
}
