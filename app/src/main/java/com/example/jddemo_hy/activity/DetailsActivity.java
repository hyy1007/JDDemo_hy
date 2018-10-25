package com.example.jddemo_hy.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.bean.AddShopCartBean;
import com.example.jddemo_hy.bean.DetailsBean;
import com.example.jddemo_hy.di.presenter.AddspIpresenter;
import com.example.jddemo_hy.di.presenter.DetailsIpresenter;
import com.example.jddemo_hy.di.view.AddSpIContract;
import com.example.jddemo_hy.di.view.DetailsIContract;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity implements DetailsIContract.iview, AddSpIContract.iview {

    @BindView(R.id.simp_view_det)
    SimpleDraweeView simpViewDet;
    @BindView(R.id.text_name_det)
    TextView textNameDet;
    @BindView(R.id.text_price_det)
    TextView textPriceDet;
    @BindView(R.id.text_add_cart)
    TextView textAddCart;
    @BindView(R.id.text_close)
    TextView textClose;
    private DetailsIpresenter detailsIpresenter;
    private AddspIpresenter addspIpresenter;
    private SharedPreferences sp;
    private String pid;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        detailsIpresenter = new DetailsIpresenter();
        detailsIpresenter.attachView(this);
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        sp = getSharedPreferences("flag", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        detailsIpresenter.requestInfo(pid);
    }

    @OnClick(R.id.text_add_cart)
    public void onViewClicked() {
        addspIpresenter = new AddspIpresenter();
        addspIpresenter.attachView(this);
        addspIpresenter.requestInfo(uid,pid);
    }

    @Override
    public void detailsData(DetailsBean.DataBean message) {
        Uri uri = Uri.parse(message.getImages().split("\\|")[0]);
        simpViewDet.setImageURI(uri);
        textNameDet.setText(message.getTitle());
        textPriceDet.setText("￥" + message.getPrice());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailsIpresenter.detachView(this);
        addspIpresenter.detachView(this);
    }

    @OnClick(R.id.text_close)
    public void closeClicked() {
        finish();
    }

    @Override
    public void addspData(AddShopCartBean message) {
        if (message.getCode().equals("0")){
            Toast.makeText(this,message.getMsg(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,message.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }
}
