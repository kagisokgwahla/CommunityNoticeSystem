package com.example.communitynoticesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
//import android.widget.Toolbar;

public class PostActivity extends AppCompatActivity {

    private Button UpdateButton;
    private EditText PostDescription;
    private String description;
    private  String saveCurrentDate, saveCurrenttime, postRandomName,current_userID;
    private DatabaseReference userRef, PostRef;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mToolbar =(Toolbar) findViewById(R.id.update_post_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Post");
        mAuth = FirebaseAuth.getInstance();
        current_userID = mAuth.getCurrentUser().getUid();
        UpdateButton = (Button) findViewById(R.id.update_btn);
        PostDescription = (EditText) findViewById(R.id.post_description);
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        PostRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        loadingBar = new ProgressDialog(this);

        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidatePostInfo();

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            SendUserToMainActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    private void ValidatePostInfo() {
        description = PostDescription.getText().toString();
        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        saveCurrentDate = currentDate.format(calFordDate.getTime());

        Calendar calFordTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrenttime = currentTime.format(calFordDate.getTime());

        postRandomName = saveCurrentDate + saveCurrenttime;

        if(TextUtils.isEmpty(description)){

            Toast.makeText(this,"Please Write Something",Toast.LENGTH_SHORT).show();

        }

        else{
            loadingBar.setTitle("Add New Post");
            loadingBar.setMessage("Please wait while we add new post..");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);
            SavingPostInformationToDatabase();


        }
    }

    private void SavingPostInformationToDatabase(){
        userRef.child(current_userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String userName = dataSnapshot.child("name").getValue().toString();

                    HashMap postMap = new HashMap();
                    postMap.put("uid",current_userID);
                    postMap.put("date",saveCurrentDate);
                    postMap.put("time",saveCurrenttime);
                    postMap.put("description",description);
                    postMap.put("name",userName);

                    PostRef.child(current_userID+postRandomName).updateChildren(postMap).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if(task.isSuccessful()){
                                
                                SendUserToMainActivity();
                                Toast.makeText(PostActivity.this,"Post Successful",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                            }

                            else{
                                Toast.makeText(PostActivity.this,"Post Unsuccessful",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                            }
                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(PostActivity.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

}
