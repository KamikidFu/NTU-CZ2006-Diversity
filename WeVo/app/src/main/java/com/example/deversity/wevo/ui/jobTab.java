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

/**
 * JobTab is a boundary class for showing volunteer applied jobs
 * @author John;
 */
public class JobTab extends Fragment {

    private View mView;
    private String[] placeholdJOB = {"JOB1", "JOB2", "JOB3","JOB4","JOB5","JOB6"};
    private ListView jobListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_jobtab, container, false);
        return mView;
    }
    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jobListView = (ListView) mView.findViewById(R.id.jobList);
        ListAdapter vwoAdpter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,placeholdJOB);
        jobListView.setAdapter(vwoAdpter);
    }
}
