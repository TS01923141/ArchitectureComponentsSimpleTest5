package com.example.cartek.architecturecomponentssimpletest4.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by CarTek on 2018/3/2.
 */

public class UserViewModelFactory implements ViewModelProvider.Factory {
    private static final String TAG = "UserViewModelFactory";

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public UserViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        Log.e(TAG, "UserViewModelFactory");
        this.creators = creators;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Provider<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) creator.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
