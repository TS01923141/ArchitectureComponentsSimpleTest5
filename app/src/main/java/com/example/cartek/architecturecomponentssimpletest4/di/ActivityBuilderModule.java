package com.example.cartek.architecturecomponentssimpletest4.di;

import com.example.cartek.architecturecomponentssimpletest4.ui.UserActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by CarTek on 2018/3/2.
 */
@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract UserActivity userActivity();
}
