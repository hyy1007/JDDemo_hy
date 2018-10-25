package com.example.jddemo_hy.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jddemo_hy.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @author hyy
 * @date 2018/10/22
 */
public class SearchHolder extends RecyclerView.ViewHolder {

    public SimpleDraweeView simp_view_search;
    public TextView text_name_search;
    public TextView text_price_search;

    public SearchHolder(View itemView) {
        super(itemView);
        simp_view_search = itemView.findViewById(R.id.simp_view_search);
        text_name_search = itemView.findViewById(R.id.text_name_search);
        text_price_search = itemView.findViewById(R.id.text_price_search);
    }
}
