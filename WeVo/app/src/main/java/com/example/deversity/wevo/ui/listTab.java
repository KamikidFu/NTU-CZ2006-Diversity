package com.example.deversity.wevo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.deversity.wevo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class listTab extends Fragment{
    private View mView;
    private String[] placeholdVWO = {"VWO1", "VWO2", "VWO3","VWO4","VWO5"};
    private ArrayList<String> VWOArrayList = new ArrayList<>();
    private ListView vwoListView;
    DatabaseReference mVWORef = FirebaseDatabase.getInstance().getReference().child("VWO");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.activity_listtab, container, false);
        return mView;
    }
    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVWORef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot VWOSnapshot : dataSnapshot.getChildren()){
                    if (VWOSnapshot.child("Name").getValue() != null)
                        VWOArrayList.add(VWOSnapshot.child("Name").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        vwoListView = (ListView) mView.findViewById(R.id.vwoList);
        ListAdapter vwoAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,VWOArrayList);
        vwoListView.setAdapter(vwoAdapter);
    }

}