package com.example.communitynoticesystem;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class reminderFragment extends Fragment {
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reminder_fragment,container,false);
        listView = view.findViewById(R.id.reminderListView);

        ArrayList<String> data = new ArrayList<>();
        data.add("lwdjfhn klwkfjoehvi mdpwfkokkod");
        data.add("lwdjfhn klwkfjoehvi mdpwfkokkod");data.add("lwdjfhn klwkfjoehvi mdpwfkokkod");
        data.add("lwdjfhn klwkfjoehvi mdpwfkokkod");
        data.add("lwdjfhn klwkfjoehvi mdpwfkokkod");
        data.add("lwdjfhn klwkfjoehvi mdpwfkokkod");
        data.add("lwdjfhn klwkfjoehvi mdpwfkokkod");
        data.add("lwdjfhn klwkfjoehvi mdpwfkokkod");
        data.add("lwdjfhn klwkfjoehvi mdpwfkokkod");
        data.add("lwdjfhn klwkfjoehvi mdpwfkokkod");
        CostomAdapter adapter = new CostomAdapter(getActivity(),R.layout.timeline_elements,data);
        listView.setAdapter(adapter);
        return view;
    }
}
