package com.tcc.luan.biob.model;

import com.tcc.luan.biob.model.util.User;

/**
 * Created by luan on 27/08/16.
 */
public class SESSION {
    private User user = null;
    public static boolean test = false;
    private static SESSION ourInstance = new SESSION();

    public static SESSION getInstance() {
        return ourInstance;
    }

    private SESSION() {
    }
    public void setUser(User user){
        this.user = user;
    }
    public User getUser(){
        return this.user;
    }
}
