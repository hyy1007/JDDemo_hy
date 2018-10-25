package com.example.jddemo_hy.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.adapter.LeftAdapter;
import com.example.jddemo_hy.adapter.RightAdapter;
import com.example.jddemo_hy.bean.NineBean;
import com.example.jddemo_hy.bean.RightBean;
import com.example.jddemo_hy.di.presenter.LeftIPresenter;
import com.example.jddemo_hy.di.presenter.NinePresenter;
import com.example.jddemo_hy.di.presenter.RightIPresenter;
import com.example.jddemo_hy.di.view.LeftIContract;
import com.example.jddemo_hy.di.view.NineIContract;
import com.example.jddemo_hy.di.view.RightIContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hyy
 * @date 2018/10/12
 */
public class CatryFragment extends Fragment implements  RightIContract.iview, LeftIContract.iview {

    private RecyclerView recyc_view_left;
    private RecyclerView recyc_view_right;
    private RightIPresenter rightIPresenter;
    private LeftIPresenter leftIPresenter;
    private String cid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catry_layout, container, false);
        recyc_view_left = view.findViewById(R.id.recyc_view_left);
        recyc_view_right = view.findViewById(R.id.recyc_view_right);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //左侧分类
        leftIPresenter = new LeftIPresenter();
        leftIPresenter.attachView(this);
        leftIPresenter.requestInfo();
        //右侧一级分类
        rightIPresenter = new RightIPresenter();
        rightIPresenter.attachView(this);
        rightIPresenter.requestInfo(String.valueOf(1));

    }

    @Override
    public void rightData(List<RightBean.DataBean> message) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyc_view_right.setLayoutManager(layoutManager);
        RightAdapter rightAdapter = new RightAdapter(message, getActivity());
        recyc_view_right.setAdapter(rightAdapter);

    }


    @Override
    public void leftData(final List<NineBean.DataBean> message) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyc_view_left.setLayoutManager(linearLayoutManager);
        LeftAdapter leftAdapter = new LeftAdapter(message, getActivity());
        recyc_view_left.setAdapter(leftAdapter);
        leftAdapter.setOnItemClickListen(new LeftAdapter.OnItemClickListen() {

            @Override
            public void itemClisk(int position) {
                cid = message.get(position).getCid();
                rightIPresenter.requestInfo(cid);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        leftIPresenter.detachView(this);
        rightIPresenter.detachView(this);
    }
}
