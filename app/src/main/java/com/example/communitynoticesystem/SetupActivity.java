package com.example.communitynoticesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {

    private EditText username,fullname,location;
    private Button saveinfobutton;
    private CircleImageView profilepic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        username = (EditText) findViewById(R.id.setup_Username);
        fullname = (EditText) findViewById(R.id.setup_fullname);
        location = (EditText) findViewById(R.id.setup_location);
        saveinfobutton = (Button) findViewById(R.id.setup_savebutton);
        profilepic  = (CircleImageView) findViewById(R.id.setup_profilepic);

    }
}
