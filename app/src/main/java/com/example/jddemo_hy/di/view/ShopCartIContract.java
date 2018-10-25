package com.example.jddemo_hy.di.view;

import com.example.jddemo_hy.bean.ShopCartBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/19
 */
public interface ShopCartIContract {

    interface iview{
        void shopCartData(List<ShopCartBean.DataBean> message);
    }

    interface ipresenter<iview>{
        void attachView(ShopCartIContract.iview iview);
        void detachView(ShopCartIContract.iview iview);
        void requestInfo(String uid,String token);
    }

    interface imodel{
        interface callListen{
            void responMsg(List<ShopCartBean.DataBean> message);
        }
        void requestData(String uid,String token,callListen callListen);
    }
}
