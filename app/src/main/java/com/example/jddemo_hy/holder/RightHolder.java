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
public class RightHolder extends RecyclerView.ViewHolder {

    public RecyclerView recyc_view_goods;
    public TextView text_title;

    public RightHolder(View itemView) {
        super(itemView);
        recyc_view_goods = itemView.findViewById(R.id.recyc_view_goods);
        text_title = itemView.findViewById(R.id.text_title);
    }
}
