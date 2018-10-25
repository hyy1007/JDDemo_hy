package com.example.jddemo_hy.di.presenter;

import com.example.jddemo_hy.bean.ShopCartBean;
import com.example.jddemo_hy.di.model.ShopCartIModel;
import com.example.jddemo_hy.di.view.ShopCartIContract;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author hyy
 * @date 2018/10/19
 */
public class ShopCartIPreseter implements ShopCartIContract.ipresenter<ShopCartIContract.iview> {

    ShopCartIContract.iview iview;
    private ShopCartIModel shopCartIModel;
    private WeakReference<ShopCartIContract.iview> iviewWeakReference;
    private WeakReference<ShopCartIContract.imodel> imodelWeakReference;

    @Override
    public void attachView(ShopCartIContract.iview iview) {
        this.iview = iview;
        shopCartIModel = new ShopCartIModel();
        iviewWeakReference = new WeakReference<>(iview);
        imodelWeakReference = new WeakReference<ShopCartIContract.imodel>(shopCartIModel);
    }

    @Override
    public void detachView(ShopCartIContract.iview iview) {
        iviewWeakReference.clear();
        imodelWeakReference.clear();
    }

    @Override
    public void requestInfo(String uid,String token) {
        imodelWeakReference.get().requestData(uid,token,new ShopCartIContract.imodel.callListen() {
            @Override
            public void responMsg(List<ShopCartBean.DataBean> message) {
                iviewWeakReference.get().shopCartData(message);
            }
        });
    }
}
