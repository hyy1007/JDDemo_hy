package com.example.jddemo_hy.di.view;

import com.example.jddemo_hy.bean.SearchBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/22
 */
public interface SearchIcontract {

    interface iview{
        void searchData(List<SearchBean.DataBean> message);
    }

    interface ipresenter<iview>{
        void attachView(SearchIcontract.iview iview);
        void detachView(SearchIcontract.iview iview);
        void requestInfo(String keywords);
    }

    interface imodel{
        interface callListen{
            void responMsg(List<SearchBean.DataBean> message);
        }
        void requestData(String keywords,callListen callListen);
    }
}
