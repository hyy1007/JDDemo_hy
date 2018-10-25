package com.example.jddemo_hy.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.bean.RightBean;
import com.example.jddemo_hy.holder.GoodsHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/18
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodsHolder> {

    private List<RightBean.DataBean.ListBean> list;
    private Context context;

    public GoodsAdapter(List<RightBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public GoodsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GoodsHolder(LayoutInflater.from(context).inflate(R.layout.goods_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getIcon());
        holder.simp_view_goods.setImageURI(uri);
        holder.text_name_goods.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
