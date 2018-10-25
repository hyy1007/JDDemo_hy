package com.example.jddemo_hy.di.presenter;

import com.example.jddemo_hy.bean.DetailsBean;
import com.example.jddemo_hy.di.model.DetailsImodel;
import com.example.jddemo_hy.di.view.DetailsIContract;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author hyy
 * @date 2018/10/23
 */
public class DetailsIpresenter implements DetailsIContract.ipresenter<DetailsIContract.iview> {

    DetailsIContract.iview iview;
    private DetailsImodel detailsImodel;
    private WeakReference<DetailsIContract.iview> iviewWeakReference;
    private WeakReference<DetailsIContract.imodel> imodelWeakReference;

    @Override
    public void attachView(DetailsIContract.iview iview) {
        this.iview = iview;
        detailsImodel = new DetailsImodel();
        iviewWeakReference = new WeakReference<>(iview);
        imodelWeakReference = new WeakReference<DetailsIContract.imodel>(detailsImodel);
    }

    @Override
    public void detachView(DetailsIContract.iview iview) {
        iviewWeakReference.clear();
        imodelWeakReference.clear();
    }

    @Override
    public void requestInfo(String pid) {
        imodelWeakReference.get().requestData(pid, new DetailsIContract.imodel.callListen() {
            @Override
            public void responMsg(DetailsBean.DataBean message) {
                iviewWeakReference.get().detailsData(message);
            }
        });
    }
}
