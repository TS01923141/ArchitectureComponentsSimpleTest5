package com.example.cartek.architecturecomponentssimpletest4;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.example.cartek.architecturecomponentssimpletest4.di.DaggerUserApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;

/**
 * Created by CarTek on 2018/3/1.
 */

public class UserApplication extends Application implements HasActivityInjector{

    private static final String TAG = "UserApplication";

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        initializeComponent();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    private void initializeComponent(){
        Log.e(TAG, "initializeComponent");
        DaggerUserApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }
}
