package com.example.communitynoticesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class settings extends AppCompatActivity {
    private Toolbar mToolbar;
    FragmentManager manager;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mToolbar = findViewById(R.id.settings_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Settings");

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
    }

    public void onUpdateProfile(View view){
        ProfileFragment fragment = new ProfileFragment();
        transaction.replace(R.id.proFeg,fragment);
        transaction.commit();
    }

    public void checkReaminders(View view){
    }

    public void back(View view){

    }
}
