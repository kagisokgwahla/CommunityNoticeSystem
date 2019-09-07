package com.example.communitynoticesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Org_or_User extends AppCompatActivity {

    Button btnOrg, btnUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_or__user);

        btnOrg = findViewById(R.id.Organ_btn);
        btnUser = findViewById(R.id.User_btn);

        btnOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendToOrgSetUp();



            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendToUserSetUp();
            }
        });


    }

    private void SendToUserSetUp() {
        Intent sendToCom = new Intent(Org_or_User.this,ComMemSetUp.class);
        sendToCom.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(sendToCom);
        finish();


    }

    private void SendToOrgSetUp() {
        Intent sendToOrg = new Intent(Org_or_User.this,OrgSetUp.class);
        sendToOrg.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(sendToOrg);
        finish();

    }
}
