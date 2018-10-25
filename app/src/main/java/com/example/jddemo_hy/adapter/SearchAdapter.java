package com.example.jddemo_hy.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.bean.SearchBean;
import com.example.jddemo_hy.holder.SearchHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/22
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchHolder> {

    private List<SearchBean.DataBean> list;
    private Context context;
    OnItemClickListener onItemClickListener;

    public SearchAdapter(List<SearchBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchHolder(LayoutInflater.from(context).inflate(R.layout.list_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, final int position) {
        Uri parse = Uri.parse(list.get(position).getImages().split("\\|")[0]);
        holder.simp_view_search.setImageURI(parse);
        holder.text_name_search.setText(list.get(position).getTitle());
        holder.text_price_search.setText("￥"+list.get(position).getPrice());
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.ItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface OnItemClickListener{
        void ItemClick(int position);
    }
    public void setOnItemClickListener(SearchAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
