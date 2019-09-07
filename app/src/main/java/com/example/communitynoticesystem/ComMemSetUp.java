package com.example.communitynoticesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.communitynoticesystem.MainActivity;
import com.example.communitynoticesystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ComMemSetUp extends AppCompatActivity {
    private EditText Name,Surname,Phone,Bio,Location;
    private Button SaveInfo;
    private FirebaseAuth mAuth;
    private DatabaseReference UserRef;
    String currentUserID;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_mem_set_up);
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);

        Name = (EditText) findViewById(R.id.name_com);
        Surname = (EditText) findViewById(R.id.surname_com);
        Location = (EditText) findViewById(R.id.location_com);
        Phone = (EditText) findViewById(R.id.phone_com);
        Bio = (EditText) findViewById(R.id.bio_com);
        SaveInfo = (Button) findViewById(R.id.save_combtn);
        loadingBar= new ProgressDialog(this);

        SaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveAccountSetUpCom();
            }
        });
    }

    private void SaveAccountSetUpCom(){
        String name = Name.getText().toString();
        String surname = Surname.getText().toString();
        String location = Location.getText().toString();
        String phone = Phone.getText().toString();
        String bio = Bio.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please input name",Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(surname)){
            Toast.makeText(this,"Please input surname",Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(location)){
            Toast.makeText(this,"Please input location",Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(bio)){
            Toast.makeText(this,"Please input bio",Toast.LENGTH_SHORT).show();
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
            usermap.put("name",name);
            usermap.put("surname",surname);
            usermap.put("location",location);
            usermap.put("phone",phone);
            usermap.put("bio",bio);
            UserRef.updateChildren(usermap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        SendUserToMainActivity();
                        Toast.makeText(ComMemSetUp.this,"Account created successfully",Toast.LENGTH_LONG).show();
                        loadingBar.dismiss();
                    }

                    else{
                        String message = task.getException().getMessage();
                        Toast.makeText(ComMemSetUp.this,"Error Occured: "+message,Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });


        }


    }

    private void SendUserToMainActivity(){
        Intent mainIntent = new Intent(ComMemSetUp.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();


    }
}
