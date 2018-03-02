package com.example.cartek.architecturecomponentssimpletest4.ui;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.cartek.architecturecomponentssimpletest4.di.Injection;
import com.example.cartek.architecturecomponentssimpletest4.model.database.dao.UserDao;
import com.example.cartek.architecturecomponentssimpletest4.model.database.entity.User;
import com.example.cartek.architecturecomponentssimpletest4.model.database.util.RealmResultLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.functions.Action;
import io.realm.RealmResults;

/**
 * Created by CarTek on 2018/3/1.
 */

public class UserViewModel extends ViewModel {
//    private final UserDao mDao;
//    @Inject
    UserDao mDao;
    private boolean databaseIsEmpty = true;
    //    private User mUser;
    private LiveData<List<User>> userList;
    private static final String TAG = "UserViewModel";

    @Inject
    public UserViewModel(UserDao mDao) {
        this.mDao = mDao;
//        mDao = Injection.provideUserDatabase();
        if(this.mDao.searchAll().size() == 0){
            updateUserName("").subscribe();
        }else {
            updateUserList();
        }

    }

    public LiveData<List<User>> getUserList(){
        return userList;
    }

    public Completable updateUserName(final String userName){
        return Completable.fromAction(new Action() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() throws Exception {
                if (Objects.equals(databaseIsEmpty, true)){
                    mDao.insert("0", userName);
                    databaseIsEmpty = false;
                }else {
                    mDao.update("0", userName);
                }
                updateUserList();
            }
        });
    }

    private void updateUserList(){
        RealmResultLiveData<User> userRealmList = new RealmResultLiveData<>(mDao.searchAll());
        userList = Transformations.map(userRealmList, new Function<RealmResults<User>, List<User>>() {
            @Override
            public List<User> apply(RealmResults<User> users) {
                List<User> userList = new ArrayList<>();
                userList.addAll(users);

                return userList;
            }
        });
    }
}
