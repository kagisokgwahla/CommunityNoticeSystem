package com.example.communitynoticesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class clickPostActivity extends AppCompatActivity {

    private TextView PostDescription;
    private Button DeletePostButton, EditPostButton, ReportButton;
    private  String PostKey, currentUserID, databaseUserID, description,saveCurrentDate, saveCurrenttime, postRandomName;
    private DatabaseReference clickPostRef, ReportRef, userRef;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_post);
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        PostKey = getIntent().getExtras().get("PostKey").toString();
        clickPostRef = FirebaseDatabase.getInstance().getReference().child("Posts").child(PostKey);
        ReportRef = FirebaseDatabase.getInstance().getReference().child("Reports");
        ReportButton = findViewById(R.id.report_main);
        PostDescription = (TextView) findViewById(R.id.click_post_description);
        DeletePostButton = (Button) findViewById(R.id.click_delete_post_btn);
        EditPostButton = (Button) findViewById(R.id.click_edit_post_btn);
        DeletePostButton.setVisibility(View.INVISIBLE);
        EditPostButton.setVisibility(View.INVISIBLE);
        ReportButton.setVisibility(View.VISIBLE);
        loadingBar = new ProgressDialog(this);



        clickPostRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    description = dataSnapshot.child("description").getValue().toString();
                    PostDescription.setText(description);
                    databaseUserID = dataSnapshot.child("uid").getValue().toString();
                    if(currentUserID.equals(databaseUserID)){
                        DeletePostButton.setVisibility(View.VISIBLE);
                        EditPostButton.setVisibility(View.VISIBLE);
                        ReportButton.setVisibility(View.INVISIBLE);
                    }

                    EditPostButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            EditCurrentPost(description);
                        }
                    });

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

        ReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidatePostInfo();

            }
        });

    }

    private void ValidatePostInfo() {
        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        saveCurrentDate = currentDate.format(calFordDate.getTime());

        Calendar calFordTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrenttime = currentTime.format(calFordDate.getTime());

        postRandomName = saveCurrentDate + saveCurrenttime;

        loadingBar.setTitle("Reporting");
        loadingBar.setMessage("Please wait..");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(true);
        SavingReporttInformationToDatabase();

    }

    private void SavingReporttInformationToDatabase() {

        userRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String userName = dataSnapshot.child("name").getValue().toString();
                    String location1 = dataSnapshot.child("location").getValue().toString();


                    HashMap reportMap = new HashMap();
                    reportMap.put("uid",currentUserID);
                    reportMap.put("date",saveCurrentDate);
                    reportMap.put("time",saveCurrenttime);
                    reportMap.put("description",description);
                    reportMap.put("name",userName);
                    reportMap.put("location",location1);

                    ReportRef.child(currentUserID+postRandomName).updateChildren(reportMap).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if(task.isSuccessful()){

                                SendUserToMainActivity();
                                Toast.makeText(clickPostActivity.this,"Post Reported",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                            }

                            else{
                                Toast.makeText(clickPostActivity.this,"Report Unsuccessful",Toast.LENGTH_SHORT).show();
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

    private  void EditCurrentPost(String description){
        AlertDialog.Builder builder = new AlertDialog.Builder(clickPostActivity.this);
        builder.setTitle("Edit Post");
        final EditText inputField = new EditText(clickPostActivity.this);
        inputField.setText(description);
        builder.setView(inputField);

        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                clickPostRef.child("description").setValue(inputField.getText().toString());
                Toast.makeText(clickPostActivity.this,"Post updated successfully",Toast.LENGTH_SHORT).show();
                SendUserToMainActivity();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.holo_green_dark);

    }

    private void DeleteCurrentPost() {
        clickPostRef.removeValue();
        SendUserToMainActivity();
        Toast.makeText(this,"Post has been deleted",Toast.LENGTH_SHORT).show();
    }

    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(clickPostActivity.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

}
