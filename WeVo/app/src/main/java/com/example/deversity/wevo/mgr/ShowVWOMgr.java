package com.example.deversity.wevo.mgr;

import android.widget.ListView;

import com.example.deversity.wevo.dataObj.*;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

/**
 * Created by kidfu on 2017/10/28.
 */

public class ShowVWOMgr {
    private VWO curVWO;
    private Event curEvent;
    private Job curJob;
    private ArrayList<VWO> vwos;

    public ShowVWOMgr(ArrayList<VWO> vwos) {
        this.vwos = vwos;
        this.curVWO = null;
        this.curEvent=null;
        this.curJob=null;
    }

    public void showVWO(GoogleMap map){

    }

    public void showVWO(ListView list){

    }
    public void showEvent(ListView list){

    }

    public VWO getCurVWO() {
        return curVWO;
    }

    public Event getCurEvent() {
        return curEvent;
    }

    public Job getCurJob() {
        return curJob;
    }

    public void setCurVWO(VWO curVWO) {
        this.curVWO = curVWO;
    }

    public void setCurEvent(Event curEvent) {
        this.curEvent = curEvent;
    }

    public void setCurJob(Job curJob) {
        this.curJob = curJob;
    }
}
