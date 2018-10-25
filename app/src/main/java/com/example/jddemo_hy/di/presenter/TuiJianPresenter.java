package com.example.jddemo_hy.di.presenter;

import com.example.jddemo_hy.bean.TuiBean;
import com.example.jddemo_hy.di.model.TuiJianIModel;
import com.example.jddemo_hy.di.view.TuiJianIContract;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author hyy
 * @date 2018/10/17
 */
public class TuiJianPresenter implements TuiJianIContract.ipresenter<TuiJianIContract.iview> {

    TuiJianIContract.iview iview;
    private TuiJianIModel tuiJianIModel;
    private WeakReference<TuiJianIContract.iview> iviewWeakReference;
    private WeakReference<TuiJianIModel> tuiJianIModelWeakReference;

    @Override
    public void attachView(TuiJianIContract.iview iview) {
        this.iview = iview;
        tuiJianIModel = new TuiJianIModel();
        iviewWeakReference = new WeakReference<>(iview);
        tuiJianIModelWeakReference = new WeakReference<>(tuiJianIModel);
    }

    @Override
    public void detachView(TuiJianIContract.iview iview) {
        iviewWeakReference.clear();
        tuiJianIModelWeakReference.clear();
    }

    @Override
    public void requestInfo() {
        tuiJianIModelWeakReference.get().requestData(new TuiJianIContract.imodel.callListen() {
            @Override
            public void responMsg(List<TuiBean.DataBean.TuijianBean.ListBeanX> message) {
                iviewWeakReference.get().tuijianData(message);
            }
        });
    }
}
