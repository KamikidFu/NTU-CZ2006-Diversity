package com.example.deversity.wevo.mgr;

import android.widget.ListView;

import com.example.deversity.wevo.dataObj.Volunteer;

/**
 * Created by kidfu on 2017/10/28.
 */

public class VolunteerClientMgr {
    private Volunteer user;
    private ShowVWOMgr vwoMgr;

    public VolunteerClientMgr(Volunteer user) {
        this.user = user;
    }

    public void showJob(ListView listView){

    }

    public Volunteer getUser() {
        return user;
    }
}
