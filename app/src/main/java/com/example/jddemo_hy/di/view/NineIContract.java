package com.example.jddemo_hy.di.view;

import com.example.jddemo_hy.bean.NineBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/17
 */
public interface NineIContract {

    interface iview {
        void nineShowData(List<NineBean.DataBean> message);
    }

    interface ipresenter<iview> {
        void attachView(NineIContract.iview iview);

        void detachView(NineIContract.iview iview);

        void requestInfo();
    }

    interface imodel {
        interface callListen {
            void responMsg(List<NineBean.DataBean> message);
        }

        void requestData(callListen callListen);
    }
}
