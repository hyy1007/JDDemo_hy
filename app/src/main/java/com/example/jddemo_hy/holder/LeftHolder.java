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
public class LeftHolder extends RecyclerView.ViewHolder {

    public TextView text_name_left;

    public LeftHolder(View itemView) {
        super(itemView);
        text_name_left = itemView.findViewById(R.id.text_name_left);
    }
}
