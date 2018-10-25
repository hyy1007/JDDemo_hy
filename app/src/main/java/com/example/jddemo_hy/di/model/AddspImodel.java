package com.example.jddemo_hy.di.model;

import com.example.jddemo_hy.bean.AddShopCartBean;
import com.example.jddemo_hy.di.view.AddSpIContract;
import com.example.jddemo_hy.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/10/24
 */
public class AddspImodel implements AddSpIContract.imodel {
    @Override
    public void requestData(String uid, String pid, final callListen callListen) {
        Observable<AddShopCartBean> addsp = HttpUtils.getHttpUtilsInstace().api.addsp(uid, pid);
        addsp.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddShopCartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddShopCartBean addShopCartBean) {
                        callListen.responMsg(addShopCartBean);
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
