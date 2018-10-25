package com.example.jddemo_hy.di.model;

import com.example.jddemo_hy.bean.DetailsBean;
import com.example.jddemo_hy.di.view.DetailsIContract;
import com.example.jddemo_hy.net.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/10/23
 */
public class DetailsImodel implements DetailsIContract.imodel {
    @Override
    public void requestData(String pid, final callListen callListen) {
        Observable<DetailsBean> details = HttpUtils.getHttpUtilsInstace().api.details(pid);
        details.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        DetailsBean.DataBean data = detailsBean.getData();
                        callListen.responMsg( data);
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
