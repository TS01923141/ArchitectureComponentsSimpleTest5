package com.example.cartek.architecturecomponentssimpletest4.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cartek.architecturecomponentssimpletest4.R;
import com.example.cartek.architecturecomponentssimpletest4.model.database.entity.User;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class UserActivity extends AppCompatActivity {

    private static final String TAG = "UserActivity";

    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_name_input)
    EditText userNameInput;
    @BindView(R.id.update_user)
    Button updateUser;

    @Inject
    UserViewModel userViewModel;
//    private UserViewModel userViewModel;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        userViewModel.getUserList().observe(UserActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                if (users != null) {
                    userName.setText(users.get(0).getUserName());
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    @OnClick(R.id.update_user)
    public void onViewClicked() {
        updateUserName();
    }

    private void updateUserName(){
        String userName = userNameInput.getText().toString();
        updateUser.setEnabled(false);
        compositeDisposable.add(userViewModel.updateUserName(userName)
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        updateUser.setEnabled(true);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "Unable to update username", throwable);
                    }
                }));
    }
}
