package com.example.jddemo_hy.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jddemo_hy.R;

/**
 * @author hyy
 * @date 2018/10/19
 */
public class ShopCartHolder extends RecyclerView.ViewHolder {

    public CheckBox check_seller;
    public TextView text_seller;
    public RecyclerView recyc_view_products;

    public ShopCartHolder(View itemView) {
        super(itemView);
        check_seller = itemView.findViewById(R.id.check_seller);
        text_seller = itemView.findViewById(R.id.text_seller);
        recyc_view_products = itemView.findViewById(R.id.recyc_view_products);
    }
}
