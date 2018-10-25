package com.example.jddemo_hy.di.view;

import com.example.jddemo_hy.bean.RegBean;

/**
 * @author hyy
 * @date 2018/10/23
 */
public interface RegIContract {

    interface iview {
        void regData(RegBean message);
    }

    interface ipresenter<iview> {
        void attachView(RegIContract.iview iview);

        void detachView(RegIContract.iview iview);

        void requestInfo(String mobile, String password);
    }

    interface imodel {
        interface callListen {
            void responMsg(RegBean message);
        }

        void requestData(String mobile, String password, callListen callListen);
    }
}
