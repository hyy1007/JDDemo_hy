package com.example.jddemo_hy.di.model;

import com.example.jddemo_hy.bean.RegBean;
import com.example.jddemo_hy.di.view.RegIContract;
import com.example.jddemo_hy.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/10/23
 */
public class RegImodel implements RegIContract.imodel {
    @Override
    public void requestData(String mobile, String password, final callListen callListen) {
        final Observable<RegBean> reg = HttpUtils.getHttpUtilsInstace().api.reg(mobile, password);
        reg.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegBean regBean) {
                        callListen.responMsg(regBean);
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
