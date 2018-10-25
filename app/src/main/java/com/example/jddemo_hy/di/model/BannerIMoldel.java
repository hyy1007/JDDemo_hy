package com.example.jddemo_hy.di.model;

import com.example.jddemo_hy.bean.BannerBean;
import com.example.jddemo_hy.di.view.BannerIContract;
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
public class BannerIMoldel implements BannerIContract.imodel {
    @Override
    public void requestData(final callListen callListen) {
        Observable<BannerBean> banner = HttpUtils.getHttpUtilsInstace().api.banner();
        banner.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        callListen.responMsg(bannerBean);
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
