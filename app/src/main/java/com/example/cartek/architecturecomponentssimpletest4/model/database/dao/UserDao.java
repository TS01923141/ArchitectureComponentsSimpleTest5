package com.example.cartek.architecturecomponentssimpletest4.model.database.dao;

import android.nfc.Tag;
import android.util.Log;

import com.example.cartek.architecturecomponentssimpletest4.model.database.entity.User;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by CarTek on 2018/3/1.
 */

public class UserDao {
    private static final String TAG = "UserDao";
    protected Realm db;
    @Inject
    public UserDao(Realm db) {
        this.db = db;
    }

    public void insert(final String newPrimaryKey, final String userName) {
//        db.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                User user = realm.createObject(User.class, newPrimaryKey);
//                user.setUserName(userName);
//            }
//        });
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Realm db = Realm.getDefaultInstance();
                try {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            User user = realm.createObject(User.class, newPrimaryKey);
                            user.setUserName(userName);
                        }
                    });
                } finally {
                    db.close();
                }
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "insert fault: " + throwable);
                    }
                });
    }

    public void update(final String primaryKey, final String userName) {
//        db.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                User user = where().equalTo("id", primaryKey).findFirst();
//                user.setUserName(userName);
//            }
//        });
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Realm db = Realm.getDefaultInstance();
                try {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            User user = realm.where(User.class).equalTo("id", primaryKey).findFirst();
                            user.setUserName(userName);
                        }
                    });
                } finally {
                    db.close();
                }
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "update fault: " + throwable);
                    }
                });
    }

    public RealmResults<User> searchAll() {
        return db.where(User.class).findAllAsync();
//                        Realm db = Realm.getDefaultInstance();
//                        RealmResults<User> realmResults;
//                        try {
//                            realmResults = db.where(User.class).findAll();
//                        } finally {
//                            db.close();
//                        }
//                        return realmResults;
//        return Flowable.just(Flowable.empty())
//                .map(new Function<Flowable<Object>, RealmResults<User>>() {
//                    @Override
//                    public RealmResults<User> apply(Flowable<Object> objectFlowable) throws Exception {
//                        Realm db = Realm.getDefaultInstance();
//                        RealmResults<User> realmResults;
//                        try {
//                            realmResults = db.where(User.class).findAll();
//                        } finally {
//                            db.close();
//                        }
//                        return realmResults;
//                    }
//                })
//                .subscribeOn(Schedulers.io());
    }


    public RealmResults<User> searchData(final String searchType, final String searchEqual) {
        return where().equalTo(searchType, searchEqual).findAllAsync();
//                        Realm db = Realm.getDefaultInstance();
//                        RealmResults<User> realmResults;
//                        try {
//                            realmResults = db.where(User.class).equalTo(searchType, searchEqual).findAll();
//                        } catch (){
//                            db.close();
//                        }
//                        return realmResults;

//        return Flowable.just(Flowable.empty())
//                .map(new Function<Flowable<Object>, RealmResults<User>>() {
//                    @Override
//                    public RealmResults<User> apply(Flowable<Object> objectFlowable) throws Exception {
//                        Realm db = Realm.getDefaultInstance();
//                        RealmResults<User> realmResults;
//                        try {
//                            realmResults = db.where(User.class).equalTo(searchType, searchEqual).findAll();
//                        } finally {
//                            db.close();
//                        }
//                        return realmResults;
//                    }
//                })
//                .subscribeOn(Schedulers.io());
    }

    public void delete(final String position) {
//        db.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                RealmResults<User> users = realm.where(User.class).findAll();
//                users.deleteFromRealm(Integer.parseInt(position));
//            }
//        });
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Realm db = Realm.getDefaultInstance();
                try {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            RealmResults<User> users = realm.where(User.class).findAll();
                            users.deleteFromRealm(Integer.parseInt(position));
                        }
                    });
                } finally {
                    db.close();
                }
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "delete fault: " + throwable);
                    }
                });
    }

    public void close() {
        db.close();
    }

    private RealmQuery<User> where() {
        return db.where(User.class);
    }
}
