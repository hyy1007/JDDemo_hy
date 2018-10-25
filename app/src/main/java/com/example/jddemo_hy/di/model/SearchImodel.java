package com.example.jddemo_hy.di.model;

import com.example.jddemo_hy.bean.SearchBean;
import com.example.jddemo_hy.di.view.SearchIcontract;
import com.example.jddemo_hy.net.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/10/22
 */
public class SearchImodel implements SearchIcontract.imodel {
    @Override
    public void requestData(String keywords, final callListen callListen) {
        Observable<SearchBean> search = HttpUtils.getHttpUtilsInstace().api.search(keywords, 1);
        search.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        List<SearchBean.DataBean> data = searchBean.getData();
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
