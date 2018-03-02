package com.example.cartek.architecturecomponentssimpletest4.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.cartek.architecturecomponentssimpletest4.ui.UserViewModel;
import com.example.cartek.architecturecomponentssimpletest4.viewmodel.UserViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by CarTek on 2018/3/2.
 */
@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(UserViewModelFactory userViewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindUserViewModel(UserViewModel userViewModel);
}
