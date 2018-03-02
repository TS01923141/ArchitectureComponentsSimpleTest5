package com.example.cartek.architecturecomponentssimpletest4.di;

import android.util.Log;

import com.example.cartek.architecturecomponentssimpletest4.model.database.dao.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by CarTek on 2018/3/2.
 */
@Module(includes = ViewModelModule.class)
public class UserApplicationModule {
    private static final String TAG = "UserApplicationModule";
    @Provides
    @Singleton
    UserDao provideUserDao(Realm database){
        Log.e(TAG, "provideUserDao");
        UserDao dao = new UserDao(database);
        return dao;
    }

    @Provides
    @Singleton
    Realm provideDatabase(){
        Log.e(TAG, "provideDatabase");
        Realm database;
        database = Realm.getDefaultInstance();
        return database;
    }
}
