package com.example.jddemo_hy.di.view;

import com.example.jddemo_hy.bean.TuiBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/17
 */
public interface TuiJianIContract {
    interface iview {
        void tuijianData(List<TuiBean.DataBean.TuijianBean.ListBeanX> message);
    }

    interface ipresenter<iview> {
        void attachView(TuiJianIContract.iview iview);

        void detachView(TuiJianIContract.iview iview);

        void requestInfo();
    }

    interface imodel {
        interface callListen {
            void responMsg(List<TuiBean.DataBean.TuijianBean.ListBeanX> message);
        }

        void requestData(callListen callListen);
    }
}
