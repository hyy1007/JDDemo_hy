package com.example.jddemo_hy.di.presenter;

import com.example.jddemo_hy.bean.LoginBean;
import com.example.jddemo_hy.di.model.LoginImodel;
import com.example.jddemo_hy.di.view.LoginIContrct;

import java.lang.ref.WeakReference;

/**
 * @author hyy
 * @date 2018/10/23
 */
public class LoginIpresenter implements LoginIContrct.ipresenter<LoginIContrct.iview> {

    LoginIContrct.iview iview;
    private LoginImodel loginImodel;
    private WeakReference<LoginIContrct.iview> iviewWeakReference;
    private WeakReference<LoginIContrct.imodel> imodelWeakReference;

    @Override
    public void attachView(LoginIContrct.iview iview) {
        this.iview = iview;
        loginImodel = new LoginImodel();
        iviewWeakReference = new WeakReference<>(iview);
        imodelWeakReference = new WeakReference<LoginIContrct.imodel>(loginImodel);
    }

    @Override
    public void detachView(LoginIContrct.iview iview) {
        iviewWeakReference.clear();
        imodelWeakReference.clear();
    }

    @Override
    public void requestInfo(String mobile, String password) {
        imodelWeakReference.get().requestData(mobile, password, new LoginIContrct.imodel.callListen() {
            @Override
            public void responMsg(LoginBean message) {
                iviewWeakReference.get().loginData(message);
            }
        });
    }
}
