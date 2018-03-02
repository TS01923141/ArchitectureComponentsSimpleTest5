package com.example.cartek.architecturecomponentssimpletest4.model.database.util;

import android.arch.lifecycle.LiveData;

import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by CarTek on 2018/2/27.
 */

public class RealmResultLiveData<T extends RealmModel> extends LiveData<RealmResults<T>> {

    private RealmResults<T> results;
    private final RealmChangeListener<RealmResults<T>> listener =
            new RealmChangeListener<RealmResults<T>>() {
                @Override
                public void onChange(RealmResults<T> realmResults) {
                    setValue(results);
                }
            };

    public RealmResultLiveData(RealmResults<T> results) {
        this.results = results;
    }

    @Override
    protected void onActive() {
        super.onActive();
        results.addChangeListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        results.removeChangeListener(listener);
    }
}
