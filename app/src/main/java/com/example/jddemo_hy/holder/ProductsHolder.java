package com.example.jddemo_hy.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jddemo_hy.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @author hyy
 * @date 2018/10/19
 */
public class ProductsHolder extends RecyclerView.ViewHolder {

    public CheckBox check_products;
    public SimpleDraweeView simp_view_products;
    public TextView text_pdt_name;
    public TextView text_pdt_price;
    public Button btn_add;
    public Button btn_jian;
    public TextView text_pdt_num;

    public ProductsHolder(View itemView) {
        super(itemView);
        check_products = itemView.findViewById(R.id.check_products);
        simp_view_products = itemView.findViewById(R.id.simp_view_products);
        text_pdt_name = itemView.findViewById(R.id.text_pdt_name);
        text_pdt_price = itemView.findViewById(R.id.text_pdt_price);
        btn_add = itemView.findViewById(R.id.btn_add);
        btn_jian = itemView.findViewById(R.id.btn_jian);
        text_pdt_num = itemView.findViewById(R.id.text_pdt_num);
    }
}
