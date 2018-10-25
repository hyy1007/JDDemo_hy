package com.example.jddemo_hy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.bean.RightBean;
import com.example.jddemo_hy.holder.RightHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/18
 */
public class RightAdapter extends RecyclerView.Adapter<RightHolder> {

    private List<RightBean.DataBean> list;
    private Context context;

    public RightAdapter(List<RightBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RightHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RightHolder(LayoutInflater.from(context).inflate(R.layout.right_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RightHolder holder, int position) {
        holder.text_title.setText(list.get(position).getName());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false);
        holder.recyc_view_goods.setLayoutManager(gridLayoutManager);
        List<RightBean.DataBean.ListBean> list = this.list.get(position).getList();
        GoodsAdapter goodsAdapter = new GoodsAdapter(list,context);
        holder.recyc_view_goods.setAdapter(goodsAdapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
