package com.example.cartek.architecturecomponentssimpletest4.model.database.dao;

import com.example.cartek.architecturecomponentssimpletest4.model.database.entity.User;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by CarTek on 2018/3/1.
 */

public class UserDao {

    protected Realm db;
    @Inject
    public UserDao(Realm db) {
        this.db = db;
    }

    public void insert(final String newPrimaryKey, final String userName){
        db.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class, newPrimaryKey);
                user.setUserName(userName);
            }
        });
    }

    public void update(final String primaryKey, final String userName){
        db.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = where().equalTo("id", primaryKey).findFirst();
                user.setUserName(userName);
            }
        });
    }

    public RealmResults<User> searchAll(){
        return db.where(User.class).findAllAsync();
    }


    public RealmResults<User> searchData(String searchType, String searchEqual){
        return where().equalTo(searchType, searchEqual).findAllAsync();
    }

    public void delete(final String position){
        db.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm){
                RealmResults<User> users = realm.where(User.class).findAll();
                users.deleteFromRealm(Integer.parseInt(position));
            }
        });
    }
    public void close(){
        db.close();
    }

    private RealmQuery<User> where() {
        return db.where(User.class);
    }
}
