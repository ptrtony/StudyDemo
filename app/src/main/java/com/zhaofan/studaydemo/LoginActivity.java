//package com.zhaofan.studaydemo;
//
//import android.content.Context;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import com.zhaofan.studaydemo.dagger2.mvp.CommonModel;
//import com.zhaofan.studaydemo.dagger2.mvp.DaggerCommonComponent;
//import com.zhaofan.studaydemo.dagger2.mvp.ICommonView;
//import com.zhaofan.studaydemo.dagger2.mvp.LoginPresenter;
//import com.zhaofan.studaydemo.dagger2.mvp.User;
//
//import javax.inject.Inject;
//
//public class LoginActivity extends AppCompatActivity implements ICommonView {
//    private Button mLoginBtn;
//    @Inject
//    LoginPresenter loginPresenter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        mLoginBtn = findViewById(R.id.mBtnLogin);
//        DaggerCommonComponent.builder().commonModel(new CommonModel(this)).build().injectActivity(this);
//        mLoginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loginPresenter.login(new User());
//            }
//        });
//    }
//
//    @Override
//    public Context getContext() {
//        return this;
//    }
//}
