package com.haward.weblog;

import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haward.weblog.fragment.aboutMeFragment;
import com.haward.weblog.fragment.homeFragment;
import com.haward.weblog.fragment.myblogFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private FragmentManager fM = getSupportFragmentManager();
    private Fragment fragment = null;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 导航栏
        mToolbar = (Toolbar)findViewById(R.id.tl_custom);
        mToolbar.setTitle("Haward");
        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // 侧滑布局
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);
        fM.beginTransaction()
                .replace(R.id.main_content, new homeFragment())
                .commit();
        String[] values = new String[] {
                "首页",
                "我的博客",
                "个人中心"
        };
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar ,R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                Log.i(TAG, "onDrawerClosed: ");
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.i(TAG, "onDrawerOpened: ");
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new drawerItemClickListener());
    }

    private class drawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            Log.i(TAG, "onItemClick: " + position);
            switch (position)
            {
                case 0:
                    fragment = new homeFragment();
                    break;
                case 1:
                    fragment = new myblogFragment();
                    break;
                case 2:
                    fragment = new aboutMeFragment();
                    break;
                default:
                    break;
            }
            FragmentTransaction tr = fM.beginTransaction();
            tr.replace(R.id.main_content, fragment);
            tr.commit();
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }
}
