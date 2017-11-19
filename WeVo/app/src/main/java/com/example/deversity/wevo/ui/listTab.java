package com.example.deversity.wevo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.deversity.wevo.Entity.ServerInterface;
import com.example.deversity.wevo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class listTab extends Fragment{
    private View mView;
    private ArrayList<String> VWOArrayList;
    private ListView vwoListView;
    private ServerInterface DBInterface = ServerInterface.getINSTANCE();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.activity_listtab, container, false);
        vwoListView = (ListView) mView.findViewById(R.id.vwoList);
        VWOArrayList = DBInterface.getVWONames("name");
        ListAdapter vwoAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,VWOArrayList);
        vwoListView.setAdapter(vwoAdapter);
        return mView;
    }
    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vwoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String vwoName = adapterView.getItemAtPosition(i).toString();
                if(vwoName!=null){
                    Toast.makeText(getContext(),"Visit "+vwoName,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), VWOView.class);
                    intent.putExtra("MODE","VOL");
                    intent.putExtra("VWOName",vwoName);
                    startActivity(intent);
                }
            }
        });
    }

}
