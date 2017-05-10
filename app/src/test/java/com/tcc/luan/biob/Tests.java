package com.tcc.luan.biob;

import android.content.Intent;
import android.location.Location;

import com.tcc.luan.biob.control.Biob_Batery;
import com.tcc.luan.biob.control.Biob_Location;
import com.tcc.luan.biob.model.SESSION;
import com.tcc.luan.biob.view.MainActivity;


import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Tests Artifact in the Build Variants view.
 */
public class Tests {

    @org.junit.After
    public void after(){
        SESSION.test = false;
    }

    @org.junit.Test
    public void location() throws Exception {
        assertEquals(SESSION.test,false);

        Biob_Location bl = new Biob_Location();
        Location loc = new Location("dummyprovider");
        bl.onLocationChanged(loc);

        assertEquals(SESSION.test,true);
    }

    @org.junit.Test
    public void battery() throws Exception {
        assertEquals(SESSION.test,false);

        Biob_Batery bb = new Biob_Batery();
        Intent i = new Intent();
        MainActivity m = new MainActivity();
        bb.onReceive(m,i);

        assertEquals(SESSION.test,true);
    }
}