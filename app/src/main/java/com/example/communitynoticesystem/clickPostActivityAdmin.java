package com.example.communitynoticesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class clickPostActivityAdmin extends AppCompatActivity {
    private DatabaseReference clickPostRef, ReportRef, userRef;
    private FirebaseAuth mAuth;
    private TextView PostDescription;
    private Button DeletePostButton;
    private ProgressDialog loadingBar;
    private  String PostKey, currentUserID, databaseUserID, description,saveCurrentDate, saveCurrenttime, postRandomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_post_admin);
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        PostKey = getIntent().getExtras().get("PostKey").toString();
        clickPostRef = FirebaseDatabase.getInstance().getReference().child("Posts").child(PostKey);
        ReportRef = FirebaseDatabase.getInstance().getReference().child("Reports");
        PostDescription = (TextView) findViewById(R.id.click_post_description1);
        DeletePostButton = (Button) findViewById(R.id.click_delete_post_btn);
        DeletePostButton.setVisibility(View.VISIBLE);
        loadingBar = new ProgressDialog(this);

        clickPostRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    description = dataSnapshot.child("description").getValue().toString();
                    PostDescription.setText(description);
                    databaseUserID = dataSnapshot.child("uid").getValue().toString();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DeletePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteCurrentPost();
            }
        });

    }

    private void DeleteCurrentPost() {
        clickPostRef.removeValue();
        ReportRef.removeValue();
        SendUserToReportList();
        Toast.makeText(this,"Post has been deleted",Toast.LENGTH_SHORT).show();
    }


    private void SendUserToReportList(){
        Intent mainIntent = new Intent(clickPostActivityAdmin.this,reportAdminActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();

    }
}
