package com.example.jddemo_hy.di.model;

import android.util.Log;

import com.example.jddemo_hy.bean.LoginBean;
import com.example.jddemo_hy.di.view.LoginIContrct;
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
public class LoginImodel implements LoginIContrct.imodel {
    @Override
    public void requestData(String mobile, String password, final callListen callListen) {
        Observable<LoginBean> login = HttpUtils.getHttpUtilsInstace().api.login(mobile, password);
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {

                        Log.i("aaa", loginBean.getData().getMobile() + "  " + loginBean.getData().getPassword());
                        callListen.responMsg(loginBean);
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
