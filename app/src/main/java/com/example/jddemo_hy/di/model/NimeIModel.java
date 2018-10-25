package com.example.jddemo_hy.di.model;

import android.util.Log;

import com.example.jddemo_hy.bean.NineBean;
import com.example.jddemo_hy.di.view.NineIContract;
import com.example.jddemo_hy.net.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/10/17
 */
public class NimeIModel implements NineIContract.imodel {
    @Override
    public void requestData(final callListen callListen) {
        Observable<NineBean> nineData = HttpUtils.getHttpUtilsInstace()
                .api.nineData();
        nineData.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NineBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NineBean nineBean) {
                        List<NineBean.DataBean> data = nineBean.getData();
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
