package com.example.jddemo_hy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.adapter.SearchAdapter;
import com.example.jddemo_hy.bean.SearchBean;
import com.example.jddemo_hy.di.presenter.SearchIpresenter;
import com.example.jddemo_hy.di.view.SearchIcontract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchIcontract.iview {

    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.text_search)
    TextView textSearch;
    @BindView(R.id.recyc_view_search)
    RecyclerView recycViewSearch;
    private SearchIpresenter searchIpresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        searchIpresenter = new SearchIpresenter();

    }

    @OnClick(R.id.text_search)
    public void onViewClicked() {
        searchIpresenter.attachView(this);
        Editable text = editSearch.getText();
        searchIpresenter.requestInfo(String.valueOf(text));
    }

    @Override
    public void searchData(final List<SearchBean.DataBean> message) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
        recycViewSearch.setLayoutManager(linearLayoutManager);
        SearchAdapter searchAdapter = new SearchAdapter(message,SearchActivity.this);
        recycViewSearch.setAdapter(searchAdapter);
        searchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void ItemClick(int position) {
                Intent intent=new Intent(SearchActivity.this,DetailsActivity.class);
                String pid = message.get(position).getPid();
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });
    }
}
