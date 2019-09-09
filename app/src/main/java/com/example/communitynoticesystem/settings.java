package com.example.communitynoticesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

public class settings extends AppCompatActivity {
    // private settingsStatePagerAdaper adaper;
    private ViewPager mviewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

       //  adaper = new settingsStatePagerAdaper(getSupportFragmentManager());
        mviewPager = findViewById(R.id.container);
        setUpViewPager(mviewPager);

    }

    public void setUpViewPager(ViewPager viewPager){
        settingsStatePagerAdaper pagerAdaper = new settingsStatePagerAdaper(getSupportFragmentManager());
        pagerAdaper.addFragment(new Setings_fragment(),"Settings");
        pagerAdaper.addFragment(new ProfileFragment(),"Profile");
        pagerAdaper.addFragment(new reminderFragment(),"Reminders");
        pagerAdaper.addFragment(new ReportsFragment(),"Reports");
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
