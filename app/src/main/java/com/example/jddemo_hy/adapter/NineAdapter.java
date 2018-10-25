package com.example.jddemo_hy.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.bean.NineBean;
import com.example.jddemo_hy.holder.NineHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/17
 */
public class NineAdapter extends RecyclerView.Adapter<NineHolder> {

    private List<NineBean.DataBean> list;
    private Context context;

    public NineAdapter(List<NineBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public NineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NineHolder(LayoutInflater.from(context).inflate(R.layout.nine_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NineHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getIcon());
        holder.simp_view.setImageURI(uri);
        holder.text_name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
