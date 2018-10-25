package com.example.jddemo_hy.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.activity.DetailsActivity;
import com.example.jddemo_hy.activity.MainActivity;
import com.example.jddemo_hy.activity.SearchActivity;
import com.example.jddemo_hy.adapter.NineAdapter;
import com.example.jddemo_hy.adapter.TuiJianAdapter;
import com.example.jddemo_hy.bean.BannerBean;
import com.example.jddemo_hy.bean.NineBean;
import com.example.jddemo_hy.bean.TuiBean;
import com.example.jddemo_hy.di.presenter.BannerPresenter;
import com.example.jddemo_hy.di.presenter.NinePresenter;
import com.example.jddemo_hy.di.presenter.TuiJianPresenter;
import com.example.jddemo_hy.di.view.BannerIContract;
import com.example.jddemo_hy.di.view.NineIContract;
import com.example.jddemo_hy.di.view.TuiJianIContract;
import com.recker.flybanner.FlyBanner;
import com.sunfusheng.marqueeview.MarqueeView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hyy
 * @date 2018/10/12
 */
public class HomeFragment extends Fragment implements BannerIContract.iview, NineIContract.iview, TuiJianIContract.iview {

    private FlyBanner banner_1;
    private TextView text_code;
    private TextView edit_search;
    private BannerPresenter bannerPresenter;
    private List<String> images;
    private RecyclerView recyc_view_nine;
    private RecyclerView recyc_view_tuijian;
    private NinePresenter ninePresenter;
    private MarqueeView marqueeView;
    private TuiJianPresenter tuiJianPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);
        banner_1 = view.findViewById(R.id.banner_1);
        text_code = view.findViewById(R.id.text_code);
        edit_search = view.findViewById(R.id.edit_search);
        recyc_view_nine = view.findViewById(R.id.recyc_view_nine);
        recyc_view_tuijian = view.findViewById(R.id.recyc_view_tuijian);
        marqueeView = view.findViewById(R.id.marqueeView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //实例化轮播图P层
        bannerPresenter = new BannerPresenter();
        bannerPresenter.attachView(this);
        bannerPresenter.requestInfo();
        images = new ArrayList<>();
        //实例化九宫格P层
        ninePresenter = new NinePresenter();
        ninePresenter.attachView(this);
        ninePresenter.requestInfo();
        //京东时报
        initmar();
        //实例化为你推荐P层
        tuiJianPresenter = new TuiJianPresenter();
        tuiJianPresenter.attachView(this);
        tuiJianPresenter.requestInfo();
        //二维码
        text_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        //搜索
        edit_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    //二维码重写onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理二维码的扫描结果
        if (requestCode==1) {
            //处理扫描结果
            if (null!=data) {
                Bundle bundle = data.getExtras();
                if (bundle==null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果"+result, Toast.LENGTH_SHORT).show();
                }else if (bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_FAILED){
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    /**
     * 京东时报
     */
    private void initmar() {
        List<String> info = new ArrayList<>();
        info.add("懒癌星人也能养活的植物!可爱的多肉了解一下!");
        info.add("大促销下单拆福袋，亿万新年红包随便拿");
        info.add("原来AJ质量不如莆田货");
        info.add("超吸晴秋冬卫衣搭配!时髦小姐姐手把手带你买衣服!");
        info.add("为5G网络开路,三大运营商将关闭2G/3G网络");
        info.add("兼备颜值与营养,填满她的少女心");
        marqueeView.startWithList(info);
    }


    /**
     * 轮播图
     */
    @Override
    public void bannerData(BannerBean message) {
        List<BannerBean.DataBean> data = message.getData();
        for (int i = 0; i < data.size(); i++) {
            String icon = data.get(i).getIcon().replace("https", "http");
            images.add(icon);
        }
        banner_1.setImagesUrl(images);
    }

    /**
     * 九宫格
     * @param message
     */
    @Override
    public void nineShowData(List<NineBean.DataBean> message) {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        recyc_view_nine.setLayoutManager(gridLayoutManager);
        NineAdapter niNeAdapter = new NineAdapter(message, getActivity());
        recyc_view_nine.setAdapter(niNeAdapter);
    }

    /**
     * 为你推荐
     * @param message
     */

    @Override
    public void tuijianData(final List<TuiBean.DataBean.TuijianBean.ListBeanX> message) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyc_view_tuijian.setLayoutManager(gridLayoutManager);
        TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(message, getActivity());
        recyc_view_tuijian.setAdapter(tuiJianAdapter);
        tuiJianAdapter.setOnItemClickListener(new TuiJianAdapter.OnItemClickListener() {
            @Override
            public void ItemClick(int position) {
                Intent intent=new Intent(getActivity(),DetailsActivity.class);
                String pid = message.get(position).getPid();
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });
    }
    /**
     * 销毁
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        bannerPresenter.detachView(this);
        ninePresenter.detachView(this);
        tuiJianPresenter.detachView(this);
    }

}
