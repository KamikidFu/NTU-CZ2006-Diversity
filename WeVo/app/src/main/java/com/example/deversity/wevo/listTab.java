package com.example.deversity.wevo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class listTab extends Fragment{
    private View mView;
    private String[] placeholdVWO = {"VWO1", "VWO2", "VWO3","VWO4","VWO5"};
    private ListView vwoListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.list_tab, container, false);
        return mView;
    }
    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vwoListView = (ListView) mView.findViewById(R.id.vwoList);
        ListAdapter vwoAdpter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,placeholdVWO);
        vwoListView.setAdapter(vwoAdpter);
    }

}
