package com.example.communitynoticesystem;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Setings_fragment extends Fragment {
    Button profile,reminder,report;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment,container,false);
        profile = view.findViewById(R.id.profileButton);
        reminder = view.findViewById(R.id.reminderButton);
        report = view.findViewById(R.id.reportButton);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Profile ",Toast.LENGTH_LONG).show();
                ((settings)getActivity()).setMviewPager(1);
            }
        });

        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"reminder ",Toast.LENGTH_LONG).show();
                ((settings)getActivity()).setMviewPager(2);
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Report ",Toast.LENGTH_LONG).show();
                ((settings)getActivity()).setMviewPager(3);
            }
        });


        return view;
    }
}
