package com.example.jddemo_hy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.bean.NineBean;
import com.example.jddemo_hy.holder.LeftHolder;
import com.example.jddemo_hy.holder.NineHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/18
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftHolder> {

    private List<NineBean.DataBean> list;
    private Context context;
    OnItemClickListen onItemClickListen;

    public LeftAdapter(List<NineBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public LeftHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeftHolder(LayoutInflater.from(context).inflate(R.layout.left_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LeftHolder holder, final int position) {
        holder.text_name_left.setText(list.get(position).getName());
        if (onItemClickListen!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListen.itemClisk(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListen{
        void itemClisk(int position);
    }

    public void setOnItemClickListen(OnItemClickListen onItemClickListen) {
        this.onItemClickListen = onItemClickListen;
    }
}
