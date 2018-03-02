package com.example.cartek.architecturecomponentssimpletest4.di;

import android.app.Application;

import com.example.cartek.architecturecomponentssimpletest4.UserApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by CarTek on 2018/3/2.
 */
@Singleton
@Component(modules = {UserApplicationModule.class, AndroidInjectionModule.class, ActivityBuilderModule.class})
public interface UserApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        UserApplicationComponent build();
    }

    void inject(UserApplication userApplication);
}
