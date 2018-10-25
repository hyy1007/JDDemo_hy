package com.example.jddemo_hy.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jddemo_hy.R;
import com.facebook.drawee.view.SimpleDraweeView;

import org.w3c.dom.Text;

/**
 * @author hyy
 * @date 2018/10/17
 */
public class NineHolder extends RecyclerView.ViewHolder {

    public SimpleDraweeView simp_view;
    public TextView text_name;

    public NineHolder(View itemView) {
        super(itemView);
        simp_view = itemView.findViewById(R.id.simp_view);
        text_name = itemView.findViewById(R.id.text_name);
    }
}
