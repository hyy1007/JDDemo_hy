package com.example.jddemo_hy.di.presenter;

import com.example.jddemo_hy.bean.NineBean;
import com.example.jddemo_hy.di.model.NimeIModel;
import com.example.jddemo_hy.di.view.NineIContract;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author hyy
 * @date 2018/10/17
 */
public class NinePresenter implements NineIContract.ipresenter<NineIContract.iview> {

    NineIContract.iview iview;
    private NimeIModel nimeIModel;
    private WeakReference<NineIContract.iview> iviewWeakReference;
    private WeakReference<NineIContract.imodel> imodelWeakReference;

    @Override
    public void attachView(NineIContract.iview iview) {
        this.iview = iview;
        nimeIModel = new NimeIModel();
        iviewWeakReference = new WeakReference<>(iview);
        imodelWeakReference = new WeakReference<NineIContract.imodel>(nimeIModel);
    }

    @Override
    public void detachView(NineIContract.iview iview) {
        iviewWeakReference.clear();
        imodelWeakReference.clear();
    }

    @Override
    public void requestInfo() {
        imodelWeakReference.get().requestData(new NineIContract.imodel.callListen() {
            @Override
            public void responMsg(List<NineBean.DataBean> message) {
                iviewWeakReference.get().nineShowData(message);
            }
        });
    }
}
