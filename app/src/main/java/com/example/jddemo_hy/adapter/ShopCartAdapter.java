package com.example.jddemo_hy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.bean.ShopCartBean;
import com.example.jddemo_hy.holder.ShopCartHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/19
 */
public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartHolder> {

    private List<ShopCartBean.DataBean> list;
    private Context context;
    private ProductsAdapter productsAdapter;



    public ShopCartAdapter(List<ShopCartBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ShopCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopCartHolder(LayoutInflater.from(context).inflate(R.layout.seller_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopCartHolder holder, final int position) {
        holder.text_seller.setText(list.get(position).getSellerName());
        holder.check_seller.setChecked(list.get(position).isOutchecked());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.recyc_view_products.setLayoutManager(linearLayoutManager);
        productsAdapter = new ProductsAdapter(list.get(position).getList(),context);
        holder.recyc_view_products.setAdapter(productsAdapter);


        /*holder.check_seller.setOnClickListener(null);//首先给商家默认为空
        holder.check_seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //商家控制子条目
                boolean checked = holder.check_seller.isChecked();
                list.get(position).setOutchecked(checked);//改变默认值
                if (checked){
                    //判断里层的选中，
                    for (int i = 0; i < list.get(holder.getLayoutPosition()).getList().size(); i++) {
                        list.get(holder.getLayoutPosition()).getList().get(i).setInnerchecked(true);
                    }
                }else{
                    for (int i = 0; i < list.get(holder.getLayoutPosition()).getList().size(); i++) {
                        list.get(holder.getLayoutPosition()).getList().get(i).setInnerchecked(false);
                    }
                }
                //回传的商家的状态值
                onchangeclicklistener.onitemchecked(holder.getLayoutPosition(),checked);
            }
        });




        //外层获取里层  回调
        productsAdapter.setOnchangelistener(new ProductsAdapter.onchangelistener() {
            @Override
            public void onchecked(int layoutPosition, boolean checked) {
                //接收状态及其下标
                //首先定义一个标识值
                boolean b= true;
                //获取里层的选中状态
                for (int i = 0; i < list.get(holder.getLayoutPosition()).getList().size(); i++) {
                    boolean innerChecked = list.get(holder.getLayoutPosition()).getList().get(i).isInnerchecked();
                    b=(b&innerChecked);
                }
                holder.check_seller.setChecked(b);//给商家赋值
                list.get(position).setOutchecked(b);//商家获得到的值要改变默认值

                onchangeclicklistener.onchecked(holder.getLayoutPosition(),checked);//把状态值回传给商家全选框
            }
        });*/



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //定义接口回调
    private onchangeclicklistener onchangeclicklistener;
    public interface onchangeclicklistener{
        void onchecked(int layoutPosition, boolean checked);
        void onitemchecked(int layoutPosition, boolean checked);
    }
    public void setOnchangeclicklistener(ShopCartAdapter.onchangeclicklistener onchangeclicklistener) {
        this.onchangeclicklistener = onchangeclicklistener;
    }

}
