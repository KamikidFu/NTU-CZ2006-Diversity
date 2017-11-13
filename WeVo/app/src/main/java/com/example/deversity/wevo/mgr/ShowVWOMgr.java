package com.example.deversity.wevo.mgr;

import android.widget.ListView;

import com.example.deversity.wevo.Entity.Event;
import com.example.deversity.wevo.Entity.Job;
import com.example.deversity.wevo.Entity.VWO;
import com.google.android.gms.maps.GoogleMap;

import java.util.List;

/**
 * Control class for VWO
 * @author Fu, Yunhao;
 */

public class ShowVWOMgr {
    private VWO selectedVWO;
    private Event selectedEvent;
    private Job selectedJob;
    private List<VWO> vwoList;

    public void showVWO(GoogleMap map){

    }

    public void showVWO(ListView listView){

    }

    public void showEvent(ListView listView){

    }

    public void showEvent(Event event){

    }

    public void setSelectedVWO(VWO selectedVWO) {
        this.selectedVWO = selectedVWO;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public void setSelectedJob(Job selectedJob) {
        this.selectedJob = selectedJob;
    }
}
