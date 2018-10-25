package com.example.jddemo_hy.di.model;

import android.util.Log;

import com.example.jddemo_hy.bean.RightBean;
import com.example.jddemo_hy.di.view.RightIContract;
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
public class RightIModel implements RightIContract.imodel {
    @Override
    public void requestData(String cid,final callListen callListen) {
        Observable<RightBean> right = HttpUtils.getHttpUtilsInstace().api.right(cid);
        right.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<RightBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RightBean rightBean) {
                List<RightBean.DataBean> data = rightBean.getData();
                callListen.responMsg(data);
                Log.i("right",""+data);
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
