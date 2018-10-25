package com.example.jddemo_hy.di.presenter;

import com.example.jddemo_hy.bean.SearchBean;
import com.example.jddemo_hy.di.model.SearchImodel;
import com.example.jddemo_hy.di.view.SearchIcontract;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author hyy
 * @date 2018/10/22
 */
public class SearchIpresenter implements SearchIcontract.ipresenter<SearchIcontract.iview> {

    SearchIcontract.iview iview;
    private SearchImodel searchImodel;
    private WeakReference<SearchIcontract.iview> iviewWeakReference;
    private WeakReference<SearchIcontract.imodel> imodelWeakReference;

    @Override
    public void attachView(SearchIcontract.iview iview) {
        this.iview = iview;
        searchImodel = new SearchImodel();
        iviewWeakReference = new WeakReference<>(iview);
        imodelWeakReference = new WeakReference<SearchIcontract.imodel>(searchImodel);
    }

    @Override
    public void detachView(SearchIcontract.iview iview) {
        iviewWeakReference.clear();
        imodelWeakReference.clear();
    }

    @Override
    public void requestInfo(String keywords) {
        imodelWeakReference.get().requestData(keywords, new SearchIcontract.imodel.callListen() {
            @Override
            public void responMsg(List<SearchBean.DataBean> message) {
                iviewWeakReference.get().searchData(message);
            }
        });
    }
}
