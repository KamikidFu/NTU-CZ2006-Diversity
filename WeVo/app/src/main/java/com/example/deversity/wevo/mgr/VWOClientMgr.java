package com.example.deversity.wevo.mgr;

import com.example.deversity.wevo.dataObj.VWO;

/**
 * Created by kidfu on 2017/10/28.
 */

public class VWOClientMgr {
    private VWO use;

    public VWOClientMgr(VWO use) {
        this.use = use;
    }

    public void showEvent(){

    }

    public VWO getUse() {
        return use;
    }
}
