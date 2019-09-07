package com.example.communitynoticesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OrgSetUp extends AppCompatActivity {

    private EditText Name,Representative,Phone,Mission,Location;
    private Button SaveInfo;
    private FirebaseAuth mAuth;
    private DatabaseReference UserRef;
    String currentUserID;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_set_up);
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);

        Name = (EditText) findViewById(R.id.name_org);
        Representative = (EditText) findViewById(R.id.representative_org);
        Location = (EditText) findViewById(R.id.location_org);
        Phone = (EditText) findViewById(R.id.phone_org);
        Mission = (EditText) findViewById(R.id.mission_org);
        SaveInfo = (Button) findViewById(R.id.save_orgbtn);
        loadingBar= new ProgressDialog(this);

        SaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveAccountSetUpCom();
            }
        });

    }

    private void SaveAccountSetUpCom() {

        String name = Name.getText().toString();
        String representative = Representative.getText().toString();
        String location = Location.getText().toString();
        String phone = Phone.getText().toString();
        String mission = Mission.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please input name",Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(representative)){
            Toast.makeText(this,"Please input representative",Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(location)){
            Toast.makeText(this,"Please input location",Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(mission)){
            Toast.makeText(this,"Please input mission",Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please input phone number",Toast.LENGTH_SHORT).show();
        }

        else{
            loadingBar.setTitle("Saving Information");
            loadingBar.setMessage("Please wait while we create your account..");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            HashMap usermap = new HashMap();
            usermap.put("type of user","Organisation");
            usermap.put("name",name);
            usermap.put("representative",representative);
            usermap.put("location",location);
            usermap.put("phone",phone);
            usermap.put("mission",mission);
            UserRef.updateChildren(usermap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        SendUserToMainActivity();
                        Toast.makeText(OrgSetUp.this,"Account created successfully",Toast.LENGTH_LONG).show();
                        loadingBar.dismiss();
                    }

                    else{
                        String message = task.getException().getMessage();
                        Toast.makeText(OrgSetUp.this,"Error Occured: "+message,Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });


        }
    }

    private void SendUserToMainActivity(){
        Intent mainIntent = new Intent(OrgSetUp.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();


    }
}
