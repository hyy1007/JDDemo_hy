package com.example.jddemo_hy.di.view;

import com.example.jddemo_hy.bean.LoginBean;

/**
 * @author hyy
 * @date 2018/10/23
 */
public interface LoginIContrct {

    interface iview {
        void loginData(LoginBean message);
    }

    interface ipresenter<iview> {
        void attachView(LoginIContrct.iview iview);

        void detachView(LoginIContrct.iview iview);

        void requestInfo(String mobile, String password);
    }

    interface imodel {
        interface callListen {
            void responMsg(LoginBean message);
        }

        void requestData(String mobile, String password, callListen callListen);
    }
}
