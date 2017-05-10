package com.tcc.luan.biob.model.util;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luan on 27/08/16.
 */
public class User{
    static final String TABLE_NAME = "user";

    private String name;
    private int id;
    private List<Item> inventory = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //----------------------------------------------------Data base methods
    public static String getQueryCreateTable() {
        String query = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(" +
                "_id integer primary key autoincrement," +
                "name text not null," +
                "password text not null"+
                ");";
        return  query;
    }
    public static String getQueryDeleteTable() {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        return query;
    }
    public static String getQueryCountAll() {
        String query = "SELECT _id,name FROM "+TABLE_NAME+";";
        return  query;
    }
    public String getQueryGetAllItems() {
        String query = "SELECT i._id,i.name,i.place,i.size,i.weight," +
                "i.type,i.groupp,i.description, g.name as group_label, t.name as type_label" +
                " FROM item i,groupp g, type t, user_item u WHERE i.type = t._id AND g._id = i.groupp" +
                " AND u.item = i._id AND u.user = "+this.getId()+";";

        return  query;
    }
}
