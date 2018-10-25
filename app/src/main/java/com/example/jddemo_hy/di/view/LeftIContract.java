package com.example.jddemo_hy.di.view;

import com.example.jddemo_hy.bean.NineBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/18
 */
public interface LeftIContract {

    interface iview {
        void leftData(List<NineBean.DataBean> message);
    }

    interface ipresenter<iview> {
        void attachView(LeftIContract.iview iview);

        void detachView(LeftIContract.iview iview);

        void requestInfo();
    }

    interface imodel {
        interface callListen {
            void reponsMsg(List<NineBean.DataBean> message);
        }

        void requestData(callListen callListen);
    }
}
