package com.example.communitynoticesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

public class settings extends AppCompatActivity {
    private Toolbar mToolbar;
    private settingsStatePagerAdaper adaper;
    private ViewPager mviewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mToolbar = findViewById(R.id.settings_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Settings");

        adaper = new settingsStatePagerAdaper(getSupportFragmentManager());
        mviewPager = findViewById(R.id.container);
        setUpViewPager(mviewPager);

    }

    public void setUpViewPager(ViewPager viewPager){
        settingsStatePagerAdaper pagerAdaper = new settingsStatePagerAdaper(getSupportFragmentManager());
        pagerAdaper.addFragment(new ProfileFragment(),"profile");
        viewPager.setAdapter(pagerAdaper);
    }

    public void setMviewPager(int FragNumber){
         mviewPager.setCurrentItem(FragNumber);
    }

    public void onUpdateProfile(View view){

    }

    public void checkReaminders(View view){
    }

    public void back(View view){

    }
}
