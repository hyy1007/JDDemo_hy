package com.example.jddemo_hy.di.model;

import android.util.Log;

import com.example.jddemo_hy.bean.TuiBean;
import com.example.jddemo_hy.di.view.TuiJianIContract;
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
public class TuiJianIModel implements TuiJianIContract.imodel {
    @Override
    public void requestData(final callListen callListen) {
        Observable<TuiBean> tuijian = HttpUtils.getHttpUtilsInstace().api.tuijian();
        tuijian.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TuiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TuiBean tuiBean) {
                        List<TuiBean.DataBean.TuijianBean.ListBeanX> list = tuiBean.getData().getTuijian().getList();
                        callListen.responMsg(list);
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
