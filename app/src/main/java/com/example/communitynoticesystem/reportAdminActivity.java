package com.example.communitynoticesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class reportAdminActivity extends AppCompatActivity {

    private RecyclerView postList;
    private FirebaseAuth mAuth;
    private DatabaseReference UserRef,PostsRef,ReportRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_admin);

        mAuth = FirebaseAuth.getInstance();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        PostsRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        ReportRef = FirebaseDatabase.getInstance().getReference().child("Reports");
        postList = (RecyclerView) findViewById(R.id.all_users_postlist);
        postList.setHasFixedSize(true);
    }
}
