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
public class TuiJianHolder extends RecyclerView.ViewHolder {

    public SimpleDraweeView simp_view_tui;
    public TextView text_name_tui;
    public TextView text_price_tui;

    public TuiJianHolder(View itemView) {
        super(itemView);
        simp_view_tui = itemView.findViewById(R.id.simp_view_tui);
        text_name_tui = itemView.findViewById(R.id.text_name_tui);
        text_price_tui = itemView.findViewById(R.id.text_price_tui);
    }
}
