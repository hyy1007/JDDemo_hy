package com.example.jddemo_hy.di.view;

import com.example.jddemo_hy.bean.AddShopCartBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/24
 */
public interface AddSpIContract {

    interface iview {
        void addspData(AddShopCartBean message);
    }

    interface ipresenter<iview> {
        void attachView(AddSpIContract.iview iview);

        void detachView(AddSpIContract.iview iview);

        void requestInfo(String uid, String pid);
    }

    interface imodel {
        interface callListen {
            void responMsg(AddShopCartBean message);
        }

        void requestData(String uid, String pid, callListen callListen);
    }
}
