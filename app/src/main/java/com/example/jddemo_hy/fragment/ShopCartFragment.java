package com.example.jddemo_hy.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.adapter.ShopCartAdapter;
import com.example.jddemo_hy.bean.BannerBean;
import com.example.jddemo_hy.bean.ShopCartBean;
import com.example.jddemo_hy.di.presenter.BannerPresenter;
import com.example.jddemo_hy.di.presenter.ShopCartIPreseter;
import com.example.jddemo_hy.di.view.BannerIContract;
import com.example.jddemo_hy.di.view.ShopCartIContract;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hyy
 * @date 2018/10/12
 */
public class ShopCartFragment extends Fragment implements ShopCartIContract.iview {

    private CheckBox check_all;
    private CheckBox check_products;
    private TextView text_total;
    private TextView text_nums;
    private RecyclerView recyc_shopcart;
    private ShopCartIPreseter shopCartIPreseter;
    private ShopCartBean bean;
    private SharedPreferences sp;
    private ShopCartAdapter shopCartAdapter;
    private String uid;
    private String token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopcart_layout, container, false);
        check_all = view.findViewById(R.id.check_all);
        check_products = view.findViewById(R.id.check_products);
        text_total = view.findViewById(R.id.text_total);
        text_nums = view.findViewById(R.id.text_nums);
        recyc_shopcart = view.findViewById(R.id.recyc_shopcart);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shopCartIPreseter = new ShopCartIPreseter();
        shopCartIPreseter.attachView(this);
        sp = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        token = sp.getString("token", "");

    }



    @Override
    public void shopCartData(final List<ShopCartBean.DataBean> message) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyc_shopcart.setLayoutManager(linearLayoutManager);
        shopCartAdapter = new ShopCartAdapter(message,getActivity());
        recyc_shopcart.setAdapter(shopCartAdapter);
        check_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_all.isChecked()) {
                    for (int i = 0; i < message.size(); i++) {
                        message.get(i).setOutchecked(true);
                        for (int j = 0; j < message.get(i).getList().size(); j++) {
                            message.get(i).getList().get(j).setInnerchecked(true);
                        }
                    }
                } else {
                    for (int i = 0; i <message.size(); i++) {
                        message.get(i).setOutchecked(false);
                        for (int j = 0; j <message.get(i).getList().size(); j++) {
                            message.get(i).getList().get(j).setInnerchecked(false);
                        }
                    }

                }
                shopCartAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (uid==null||token==null){
            Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
        }else {
            shopCartIPreseter.requestInfo(uid,token);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shopCartIPreseter.detachView(this);
    }
}
