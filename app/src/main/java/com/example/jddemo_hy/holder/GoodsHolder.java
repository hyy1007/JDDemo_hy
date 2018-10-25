package com.example.jddemo_hy.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jddemo_hy.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @author hyy
 * @date 2018/10/17
 */
public class GoodsHolder extends RecyclerView.ViewHolder {

    public SimpleDraweeView simp_view_goods;
    public TextView text_name_goods;

    public GoodsHolder(View itemView) {
        super(itemView);
        simp_view_goods = itemView.findViewById(R.id.simp_view_goods);
        text_name_goods = itemView.findViewById(R.id.text_name_goods);
    }
}
