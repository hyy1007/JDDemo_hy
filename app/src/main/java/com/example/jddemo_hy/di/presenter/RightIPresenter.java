package com.example.jddemo_hy.di.presenter;

import com.example.jddemo_hy.bean.RightBean;
import com.example.jddemo_hy.di.model.RightIModel;
import com.example.jddemo_hy.di.view.RightIContract;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author hyy
 * @date 2018/10/18
 */
public class RightIPresenter implements RightIContract.ipresenter<RightIContract.iview> {

    RightIContract.iview iview;
    private RightIModel rightIModel;
    private WeakReference<RightIContract.iview> iviewWeakReference;
    private WeakReference<RightIContract.imodel> imodelWeakReference;

    @Override
    public void attachView(RightIContract.iview iview) {
        this.iview = iview;
        rightIModel = new RightIModel();
        iviewWeakReference = new WeakReference<>(iview);
        imodelWeakReference = new WeakReference<RightIContract.imodel>(rightIModel);
    }

    @Override
    public void detachView(RightIContract.iview iview) {
        iviewWeakReference.clear();
        imodelWeakReference.clear();
    }

    @Override
    public void requestInfo(String cid) {
        imodelWeakReference.get().requestData(cid,new RightIContract.imodel.callListen() {
            @Override
            public void responMsg(List<RightBean.DataBean> message) {
                iviewWeakReference.get().rightData(message);
            }
        });
    }
}
