package com.example.jddemo_hy.di.presenter;

import com.example.jddemo_hy.bean.BannerBean;
import com.example.jddemo_hy.di.model.BannerIMoldel;
import com.example.jddemo_hy.di.view.BannerIContract;

import java.lang.ref.WeakReference;

/**
 * @author hyy
 * @date 2018/10/16
 */
public class BannerPresenter implements BannerIContract.ipresenter<BannerIContract.iview> {

    BannerIContract.iview iview;
    private BannerIMoldel bannerIMoldel;
    private WeakReference<BannerIContract.iview> iviewWeakReference;
    private WeakReference<BannerIContract.imodel> imodelWeakReference;

    @Override
    public void attachView(BannerIContract.iview iview) {
        this.iview = iview;
        bannerIMoldel = new BannerIMoldel();
        iviewWeakReference = new WeakReference<>(iview);
        imodelWeakReference = new WeakReference<BannerIContract.imodel>(bannerIMoldel);
    }

    @Override
    public void detachView(BannerIContract.iview iview) {
        iviewWeakReference.clear();
        imodelWeakReference.clear();
    }

    @Override
    public void requestInfo() {
        imodelWeakReference.get().requestData(new BannerIContract.imodel.callListen() {
            @Override
            public void responMsg(BannerBean message) {
                iviewWeakReference.get().bannerData(message);
            }
        });
    }
}
