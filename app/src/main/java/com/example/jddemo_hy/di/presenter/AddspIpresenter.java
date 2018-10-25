package com.example.jddemo_hy.di.presenter;

import com.example.jddemo_hy.bean.AddShopCartBean;
import com.example.jddemo_hy.di.model.AddspImodel;
import com.example.jddemo_hy.di.view.AddSpIContract;

import java.lang.ref.WeakReference;

/**
 * @author hyy
 * @date 2018/10/24
 */
public class AddspIpresenter implements AddSpIContract.ipresenter<AddSpIContract.iview> {

    AddSpIContract.iview iview;
    private AddspImodel addspImodel;
    private WeakReference<AddSpIContract.iview> iviewWeakReference;
    private WeakReference<AddSpIContract.imodel> imodelWeakReference;

    @Override
    public void attachView(AddSpIContract.iview iview) {
        this.iview=iview;
        addspImodel = new AddspImodel();
        iviewWeakReference = new WeakReference<>(iview);
        imodelWeakReference = new WeakReference<AddSpIContract.imodel>(addspImodel);
    }

    @Override
    public void detachView(AddSpIContract.iview iview) {
        iviewWeakReference.clear();
        imodelWeakReference.clear();
    }

    @Override
    public void requestInfo(String uid, String pid) {
        imodelWeakReference.get().requestData(uid, pid, new AddSpIContract.imodel.callListen() {
            @Override
            public void responMsg(AddShopCartBean message) {
                iviewWeakReference.get().addspData(message);
            }
        });
    }
}
