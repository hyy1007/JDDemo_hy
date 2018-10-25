package com.example.jddemo_hy.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.bean.TuiBean;
import com.example.jddemo_hy.holder.TuiJianHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/10/17
 */
public class TuiJianAdapter extends RecyclerView.Adapter<TuiJianHolder> {

    private List<TuiBean.DataBean.TuijianBean.ListBeanX> list;
    private Context context;
    OnItemClickListener onItemClickListener;

    public TuiJianAdapter(List<TuiBean.DataBean.TuijianBean.ListBeanX> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TuiJianHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TuiJianHolder(LayoutInflater.from(context).inflate(R.layout.tuijian_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TuiJianHolder holder, final int position) {
        Uri uri = Uri.parse(list.get(position).getImages().split("\\|")[0]);
        holder.simp_view_tui.setImageURI(uri);
        holder.text_name_tui.setText(list.get(position).getTitle());
        holder.text_price_tui.setText("￥"+list.get(position).getPrice());
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
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
