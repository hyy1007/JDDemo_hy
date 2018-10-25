package com.example.jddemo_hy.di.presenter;

import com.example.jddemo_hy.bean.RegBean;
import com.example.jddemo_hy.di.model.RegImodel;
import com.example.jddemo_hy.di.view.RegIContract;

import java.lang.ref.WeakReference;

/**
 * @author hyy
 * @date 2018/10/23
 */
public class RegIpresenter implements RegIContract.ipresenter<RegIContract.iview> {

    RegIContract.iview iview;
    private RegImodel regImodel;
    private WeakReference<RegIContract.iview> iviewWeakReference;
    private WeakReference<RegIContract.imodel> imodelWeakReference;

    @Override
    public void attachView(RegIContract.iview iview) {
        this.iview = iview;
        regImodel = new RegImodel();
        iviewWeakReference = new WeakReference<>(iview);
        imodelWeakReference = new WeakReference<RegIContract.imodel>(regImodel);
    }

    @Override
    public void detachView(RegIContract.iview iview) {
        iviewWeakReference.clear();
        imodelWeakReference.clear();
    }

    @Override
    public void requestInfo(String mobile, String password) {
        imodelWeakReference.get().requestData(mobile, password, new RegIContract.imodel.callListen() {
            @Override
            public void responMsg(RegBean message) {
                iviewWeakReference.get().regData(message);
            }
        });
    }
}
