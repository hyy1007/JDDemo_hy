package com.example.jddemo_hy.di.view;

import com.example.jddemo_hy.bean.DetailsBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/23
 */
public interface DetailsIContract {

    interface iview {
        void detailsData(DetailsBean.DataBean message);
    }

    interface ipresenter<iview> {
        void attachView(DetailsIContract.iview iview);

        void detachView(DetailsIContract.iview iview);

        void requestInfo(String pid);
    }

    interface imodel {
        interface callListen {
            void responMsg(DetailsBean.DataBean message);
        }

        void requestData(String pid, callListen callListen);
    }
}
