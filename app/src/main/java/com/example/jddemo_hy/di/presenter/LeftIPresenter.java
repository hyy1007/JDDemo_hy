package com.example.jddemo_hy.di.presenter;

import com.example.jddemo_hy.bean.NineBean;
import com.example.jddemo_hy.di.model.LeftIModel;
import com.example.jddemo_hy.di.view.LeftIContract;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author hyy
 * @date 2018/10/18
 */
public class LeftIPresenter implements LeftIContract.ipresenter<LeftIContract.iview> {

    LeftIContract.iview iview;
    private LeftIModel leftIModel;
    private WeakReference<LeftIContract.iview> iviewWeakReference;
    private WeakReference<LeftIContract.imodel> imodelWeakReference;

    @Override
    public void attachView(LeftIContract.iview iview) {
        this.iview = iview;
        leftIModel = new LeftIModel();
        iviewWeakReference = new WeakReference<>(iview);
        imodelWeakReference = new WeakReference<LeftIContract.imodel>(leftIModel);
    }

    @Override
    public void detachView(LeftIContract.iview iview) {
        iviewWeakReference.clear();
        imodelWeakReference.clear();
    }

    @Override
    public void requestInfo() {
        imodelWeakReference.get().requestData(new LeftIContract.imodel.callListen() {
            @Override
            public void reponsMsg(List<NineBean.DataBean> message) {
                iviewWeakReference.get().leftData(message);
            }
        });
    }
}
