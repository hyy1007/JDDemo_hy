package com.example.jddemo_hy.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jddemo_hy.R;
import com.example.jddemo_hy.fragment.CatryFragment;
import com.example.jddemo_hy.fragment.HomeFragment;
import com.example.jddemo_hy.fragment.MineFragment;
import com.example.jddemo_hy.fragment.ShopCartFragment;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb4)
    RadioButton rb4;
    @BindView(R.id.rb5)
    RadioButton rb5;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    private HomeFragment homeFragment;
    private CatryFragment catryFragment;
    private ShopCartFragment shopCartFragment;
    private MineFragment mineFragment;
    private ImmersionBar immersionBar;
    private FragmentManager fragmentManager;
    private String saveName;
    //当前显示的fragment
    private static final String STATE_FRAGMENT_SHOW = "STATE_FRAGMENT_SHOW";

    private Fragment currentFragment = new Fragment();


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        //沉浸式
        ImmersionBar.with(this)
                .init();

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb4.setOnClickListener(this);
        rb5.setOnClickListener(this);
        //实例化fragment对象
        homeFragment = new HomeFragment();
        catryFragment = new CatryFragment();
        shopCartFragment = new ShopCartFragment();
        mineFragment = new MineFragment();
        //获取管理者
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            //获取“内存重启”时保存的fragment名字
            saveName = savedInstanceState.getString(STATE_FRAGMENT_SHOW);
            //从fragmentManager里面找到fragment

            homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(HomeFragment.class.getName());

            catryFragment = (CatryFragment) fragmentManager.findFragmentByTag(CatryFragment.class.getName());

            shopCartFragment = (ShopCartFragment) fragmentManager.findFragmentByTag(ShopCartFragment.class.getName());

            mineFragment = (MineFragment) fragmentManager.findFragmentByTag(MineFragment.class.getName());
            if (TextUtils.isEmpty(saveName)) {
                //解决重叠问题  1
                fragmentManager.beginTransaction()
                        .show(homeFragment)
                        .hide(catryFragment)
                        .hide(shopCartFragment)
                        .hide(mineFragment)
                        .commit();

                //把当前显示的fragment记录下来
                currentFragment = homeFragment;
            } else {
                if (saveName.equals(homeFragment.getClass().getName())) {
                    fragmentManager.beginTransaction()
                            .show(homeFragment)
                            .hide(catryFragment)
                            .hide(shopCartFragment)
                            .hide(mineFragment)
                            .commit();

                    //把当前显示的fragment记录下来
                    currentFragment = homeFragment;
                } else if (saveName.equals(catryFragment.getClass().getName())) {
                    fragmentManager.beginTransaction()
                            .show(catryFragment)
                            .hide(homeFragment)
                            .hide(shopCartFragment)
                            .hide(mineFragment)
                            .commit();

                    //把当前显示的fragment记录下来
                    currentFragment = catryFragment;
                } else if (saveName.equals(shopCartFragment.getClass().getName())) {
                    fragmentManager.beginTransaction()
                            .show(shopCartFragment)
                            .hide(homeFragment)
                            .hide(catryFragment)
                            .hide(mineFragment)
                            .commit();

                    //把当前显示的fragment记录下来
                    currentFragment = shopCartFragment;
                } else if (saveName.equals(mineFragment.getClass().getName())) {
                    fragmentManager.beginTransaction()
                            .show(mineFragment)
                            .hide(homeFragment)
                            .hide(catryFragment)
                            .hide(shopCartFragment)
                            .commit();

                    //把当前显示的fragment记录下来
                    currentFragment = mineFragment;
                }

            }

        } else {//正常启动时调用
            homeFragment = new HomeFragment();
            catryFragment = new CatryFragment();
            shopCartFragment = new ShopCartFragment();
            mineFragment = new MineFragment();
            showFragment(homeFragment);
        }
    }


    /**
     * fragment的显示
     *
     * @param fg
     */
    private void showFragment(Fragment fg) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //如果之前没有添加过
        if (!fg.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.frame_layout, fg, fg.getClass().getName());  //第三个参数为添加当前的fragment时绑定一个tag，即绑定fragment的类名
        } else {
            transaction
                    .hide(currentFragment)
                    .show(fg);
        }

        currentFragment = fg;

        transaction.commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        //“内存重启”时保存当前的fragment名字
        outState.putString(STATE_FRAGMENT_SHOW, currentFragment.getClass().getName());
        super.onSaveInstanceState(outState);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null)
            immersionBar.destroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb1:
                showFragment(homeFragment);
                break;
            case R.id.rb2:
                showFragment(catryFragment);
                break;
            case R.id.rb4:
                showFragment(shopCartFragment);
                break;
            case R.id.rb5:
                showFragment(mineFragment);
                break;

        }
    }
}
