package com.example.jddemo_hy.di.view;

import com.example.jddemo_hy.bean.RightBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/18
 */
public interface RightIContract {

    interface iview {
        void rightData(List<RightBean.DataBean> message);
    }

    interface ipresenter<iview> {
        void attachView(RightIContract.iview iview);

        void detachView(RightIContract.iview iview);

        void requestInfo(String cid);
    }

    interface imodel {
        interface callListen {
            void responMsg(List<RightBean.DataBean> message);
        }

        void requestData(String cid,callListen callListen);
    }
}
