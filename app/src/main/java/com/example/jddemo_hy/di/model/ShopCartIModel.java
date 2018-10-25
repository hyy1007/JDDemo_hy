package com.example.jddemo_hy.di.model;

import android.util.Log;

import com.example.jddemo_hy.bean.ShopCartBean;
import com.example.jddemo_hy.di.view.ShopCartIContract;
import com.example.jddemo_hy.net.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/10/19
 */
public class ShopCartIModel implements ShopCartIContract.imodel
{


    @Override
    public void requestData(String uid,String token,final callListen callListen) {
        Observable<ShopCartBean> shopCart = HttpUtils.getHttpUtilsInstace().api.shopCart(uid,token);
        shopCart.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopCartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShopCartBean shopCartBean) {
                        List<ShopCartBean.DataBean> data = shopCartBean.getData();
                        Log.i("aaa",""+data);
                        callListen.responMsg(data);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
