package com.example.jddemo_hy.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.activity.LoginActivity;
import com.example.jddemo_hy.bean.LoginBean;
import com.example.jddemo_hy.di.presenter.LoginIpresenter;
import com.example.jddemo_hy.di.view.LoginIContrct;
import com.facebook.drawee.view.SimpleDraweeView;

import static android.app.Activity.RESULT_OK;

/**
 * @author hyy
 * @date 2018/10/12
 */
public class MineFragment extends Fragment {

    private SimpleDraweeView img_user;
    private TextView text_name_login;
    private SharedPreferences sp;
    private LinearLayout login_layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_layout, container, false);
        img_user = view.findViewById(R.id.img_user);
        text_name_login = view.findViewById(R.id.text_name_login);
        login_layout = view.findViewById(R.id.login_layout);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        login_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        img_user.setOnClickListener(new View.OnClickListener() {

            private Button btn_cancle;
            private Button btn_xiang;
            private Button btn_pai;

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final AlertDialog alertDialog = builder.create();
                View view1 = View.inflate(getActivity(), R.layout.headitem, null);
                btn_pai = view1.findViewById(R.id.btn_pai);
                btn_xiang = view1.findViewById(R.id.btn_xiang);
                btn_cancle = view1.findViewById(R.id.btn_cancle);
                alertDialog.setView(view1);
                btn_pai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,1);
                    }
                });
                btn_xiang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent,2);
                    }
                });
                alertDialog.show();
                btn_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.hide();
                    }
                });
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK){
            Bitmap bitmap = data.getParcelableExtra("data");
            img_user.setImageBitmap(bitmap);
        }
        if (requestCode==2&&resultCode==RESULT_OK){
            Uri uri = data.getData();
            img_user.setImageURI(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        sp = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
        String mobile = sp.getString("mobile", "");
        if (mobile != null) {
            text_name_login.setText(mobile);
        }

    }
}
