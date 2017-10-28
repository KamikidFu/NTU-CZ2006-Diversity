package com.example.deversity.wevo;

/**
 * Created by johny on 21/10/2017.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class userTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_tab, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }
}
