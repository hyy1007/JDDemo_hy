package com.example.jddemo_hy.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.bean.LoginBean;
import com.example.jddemo_hy.di.presenter.LoginIpresenter;
import com.example.jddemo_hy.di.view.LoginIContrct;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginIContrct.iview {

    @BindView(R.id.img_x)
    ImageView imgX;
    @BindView(R.id.edit_mobile)
    EditText editMobile;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_register)
    TextView txtRegister;
    private LoginIpresenter loginIpresenter;
    private SharedPreferences sp;
    private String mobile;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginIpresenter = new LoginIpresenter();
        loginIpresenter.attachView(this);


    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {

        mobile = editMobile.getText().toString();
        password = editPassword.getText().toString();
        Log.i("aaa", mobile + "  " + password);

        //登录

        loginIpresenter.requestInfo(mobile, password);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginIpresenter.detachView(this);
    }

    @Override
    public void loginData(LoginBean message) {

        Log.i("aaa", message.getMsg() + "  " + message.getCode());
        if (message.getMsg().equals("登录成功")) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            String mobile = message.getData().getMobile();
            String uid = message.getData().getUid();
            String token = message.getData().getToken();
            sp = getSharedPreferences("flag", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("mobile", mobile);
            editor.putString("uid", uid);
            editor.putString("token", token);
            editor.commit();

            finish();
        } else {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.txt_register)
    public void regClicked() {
        Intent intent = new Intent(this, RegActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.img_x)
    public void closeClicked() {
        finish();
    }
}
