package com.example.jddemo_hy.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.bean.ShopCartBean;
import com.example.jddemo_hy.holder.ProductsHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/19
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsHolder> {

    private List<ShopCartBean.DataBean.ListBean> list;
    private Context context;

    public ProductsAdapter(List<ShopCartBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductsHolder(LayoutInflater.from(context).inflate(R.layout.products_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductsHolder holder, final int position) {
        holder.text_pdt_name.setText(list.get(position).getTitle());
        Uri uri = Uri.parse(list.get(position).getImages().split("\\|")[0]);
        holder.simp_view_products.setImageURI(uri);
        holder.check_products.setChecked(list.get(position).isInnerchecked());
        holder.text_pdt_price.setText("￥" + list.get(position).getPrice());
        holder.text_pdt_num.setText(list.get(position).getNum());
       /* holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).setNum(list.get(position).getNum()+1);
                notifyDataSetChanged();
            }
        });
        holder.btn_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //定义接口回调
    private onchangelistener onchangelistener;
    public interface onchangelistener{
        void onchecked(int layoutPosition, boolean checked);
    }
    public void setOnchangelistener(ProductsAdapter.onchangelistener onchangelistener) {
        this.onchangelistener = onchangelistener;
    }
}
