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

public class ReportsFragment extends Fragment {
    Button testBut;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports_fragment,container,false);
       /* testBut = view.findViewById(R.id.testButton);
        testBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"lelethu Ngalo ",Toast.LENGTH_LONG).show();
                ((settings)getActivity()).setMviewPager(0);
            }
        });*/
        return view;
    }
}
