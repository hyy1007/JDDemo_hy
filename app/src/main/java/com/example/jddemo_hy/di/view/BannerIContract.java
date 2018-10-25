package com.example.jddemo_hy.di.view;

import com.example.jddemo_hy.bean.BannerBean;

/**
 * @author hyy
 * @date 2018/10/17
 */
public interface BannerIContract {

    /**
     * iview
     */
    interface iview {
        void bannerData(BannerBean message);
    }

    interface ipresenter<iview> {
        void attachView(BannerIContract.iview iview);

        void detachView(BannerIContract.iview iview);

        void requestInfo();
    }

    interface imodel {
        interface callListen {
            void responMsg(BannerBean message);
        }

        void requestData(callListen callListen);
    }
}
