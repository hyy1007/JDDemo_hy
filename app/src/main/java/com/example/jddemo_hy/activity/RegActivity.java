package com.example.jddemo_hy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.bean.RegBean;
import com.example.jddemo_hy.di.presenter.RegIpresenter;
import com.example.jddemo_hy.di.view.RegIContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends AppCompatActivity implements RegIContract.iview {

    @BindView(R.id.img_x_register)
    ImageView imgXRegister;
    @BindView(R.id.edit_mobile_register)
    EditText editMobileRegister;
    @BindView(R.id.edit_password_register)
    EditText editPasswordRegister;
    @BindView(R.id.edit_password_all)
    EditText editPasswordAll;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private RegIpresenter regIpresenter;
    private String mobile;
    private String password;
    private String password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        //注册
        regIpresenter = new RegIpresenter();
        regIpresenter.attachView(this);
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        mobile = editMobileRegister.getText().toString();
        password = editPasswordRegister.getText().toString();
        password2 = editPasswordAll.getText().toString();
        if (password2.equals(password)) {
            regIpresenter.requestInfo(mobile, password);
        } else {
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void regData(RegBean message) {
        if (message.getCode().equals("0")) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        regIpresenter.detachView(this);
    }

    @OnClick(R.id.img_x_register)
    public void closeClicked() {
        finish();
    }
}
