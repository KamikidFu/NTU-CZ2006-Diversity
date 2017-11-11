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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * JobTab is a boundary class for showing volunteer applied jobs
 * @author John;
 */
public class JobTab extends Fragment {

    private View mView;
    private String[] placeholdJOB = {"JOB1", "JOB2", "JOB3","JOB4","JOB5","JOB6"};
    private ListView jobListView;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference mJobRef = FirebaseDatabase.getInstance().getReference().child("Vol").child(user.getUid()).child("Jobs");
    private ArrayList<String> JobArrayList= new ArrayList<>();
    String tempJob;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_jobtab, container, false);
        return mView;
    }
    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mJobRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot JobSnapshot: dataSnapshot.getChildren()){
                    tempJob = JobSnapshot.getValue().toString().split(";")[2];
                    JobArrayList.add(tempJob);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        jobListView = (ListView) mView.findViewById(R.id.jobList);
        ListAdapter vwoAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,JobArrayList);
        jobListView.setAdapter(vwoAdapter);
    }
}
