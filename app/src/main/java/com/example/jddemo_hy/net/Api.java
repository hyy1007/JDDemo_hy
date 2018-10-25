package com.example.jddemo_hy.net;

import com.example.jddemo_hy.bean.AddShopCartBean;
import com.example.jddemo_hy.bean.BannerBean;
import com.example.jddemo_hy.bean.DetailsBean;
import com.example.jddemo_hy.bean.LoginBean;
import com.example.jddemo_hy.bean.NineBean;
import com.example.jddemo_hy.bean.RegBean;
import com.example.jddemo_hy.bean.RightBean;
import com.example.jddemo_hy.bean.SearchBean;
import com.example.jddemo_hy.bean.ShopCartBean;
import com.example.jddemo_hy.bean.TuiBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author hyy
 * @date 2018/10/12
 */
public interface Api {
    /**
     * 轮播图
     *
     * @return
     */
    @GET("ad/getAd")
    Observable<BannerBean> banner();

    /**
     * 首页九宫格
     */
    @GET("product/getCatagory")
    Observable<NineBean> nineData();

    /**
     * 首页为你推荐
     */
    @GET("home/getHome")
    Observable<TuiBean> tuijian();

    /**
     * 右侧分类
     */
    @GET("product/getProductCatagory")
    Observable<RightBean> right(@Query("cid") String cid);

    /**
     * 购物车
     */
    @GET("product/getCarts")
    Observable<ShopCartBean> shopCart(@Query("uid")String uid,@Query("token")String token);

    /**
     * 搜索
     */
    @GET("product/searchProducts")
    Observable<SearchBean> search(@Query("keywords") String keywords, @Query("page") int page);

    /**
     * 登录
     */
    @GET("user/login")
    Observable<LoginBean> login(@Query("mobile") String mobile, @Query("password") String password);

    /**
     * 注册
     */
    @GET("user/reg")
    Observable<RegBean> reg(@Query("mobile") String mobile, @Query("password") String password);

    /**
     * 商品详情
     */
    @GET("product/getProductDetail")
    Observable<DetailsBean> details(@Query("pid") String pid);

    /**
     * 添加购物车
     */
    @GET("product/addCart")
    Observable<AddShopCartBean> addsp(@Query("uid")String uid,@Query("pid")String pid);
}
