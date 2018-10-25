package com.example.jddemo_hy.di.model;

import android.util.Log;

import com.example.jddemo_hy.bean.NineBean;
import com.example.jddemo_hy.di.view.LeftIContract;
import com.example.jddemo_hy.net.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/10/18
 */
public class LeftIModel implements LeftIContract.imodel {
    @Override
    public void requestData(final callListen callListen) {
        Observable<NineBean> left = HttpUtils.getHttpUtilsInstace().api.nineData();
        left.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NineBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NineBean nineBean) {
                        List<NineBean.DataBean> data = nineBean.getData();
                        callListen.reponsMsg(data);
                        Log.i("left",""+data);
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
