package com.lzy.okhttpdemo.cache;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lzy.ninegrid.NineGridView;
import com.lzy.okhttpdemo.R;
import com.lzy.okhttpdemo.WebActivity;
import com.lzy.okhttpdemo.base.BaseActivity;
import com.lzy.okhttpdemo.utils.PicassoImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class CacheDemoActivity extends BaseActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.viewPager) ViewPager viewPager;
    @Bind(R.id.tab) TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_demo);
        initToolBar(toolbar, true, "缓存演示");

        NineGridView.setImageLoader(new PicassoImageLoader());

        ArrayList<TabFragment> fragments = new ArrayList<>();
        TabFragment fragment1 = TabFragment.newInstance();
        fragment1.setTitle("国内最新");
        fragments.add(fragment1);
        TabFragment fragment2 = TabFragment.newInstance();
        fragment2.setTitle("游戏焦点");
        fragments.add(fragment2);
        TabFragment fragment3 = TabFragment.newInstance();
        fragment3.setTitle("娱乐焦点");
        fragments.add(fragment3);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());
        tab.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.fab)
    public void fab(View view) {
        WebActivity.runActivity(this, "我的Github,欢迎star", "https://github.com/jeasonlzy0216");
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private List<TabFragment> fragments;

        public MyPagerAdapter(FragmentManager fm, List<TabFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments.get(position).getTitle();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}