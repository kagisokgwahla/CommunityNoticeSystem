package com.example.communitynoticesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {
    private Button logoutadmin,Reportbtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mAuth = FirebaseAuth.getInstance();
        logoutadmin = (Button) findViewById(R.id.logout_admin);
        Reportbtn = (Button) findViewById(R.id.reports);

        logoutadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                SendUserTologinActivity();
            }
        });

        Reportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(AdminActivity.this, reportAdminActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginIntent);
                finish();

            }
        });





    }

    private void SendUserTologinActivity() {

        Intent loginIntent = new Intent(AdminActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }


}
