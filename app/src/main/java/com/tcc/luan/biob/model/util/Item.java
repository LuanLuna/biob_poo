package com.tcc.luan.biob.model.util;

import android.util.Log;

import com.tcc.luan.biob.R;

/**
 * Created by luan on 27/08/16.
 */
public class Item {
    static final String NOME_TABELA = "item";
    private int id;
    private String name;
    private String place;
    private double size;
    private double weight;
    private int type;
    private int group;
    private String type_label;
    private String group_label;
    private String desription;
    private int location;

    public int getImageResource(){
        try {
            return Images.imageMap.get(this.name.toLowerCase());
        }catch (NullPointerException e){
            return 0;
        }
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getType_label() {
        return type_label;
    }

    public void setType_label(String type_label) {
        this.type_label = type_label;
    }

    public String getGroup_label() {
        return group_label;
    }

    public void setGroup_label(String group_label) {
        this.group_label = group_label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    //----------------------------------------------------Data base methods
    public static String getQueryCreateTable() {
        String query = "CREATE TABLE IF NOT EXISTS "+NOME_TABELA+"(" +
                "_id integer primary key autoincrement," +
                "name text not null," +
                "place text not null," +
                "size real not null," +
                "weight real not null," +
                "type integer not null," +
                "groupp integer not null," +
                "description text not null" +
                ");";
        return  query;
    }
    public static String getQueryDeleteTable() {
        String query = "DROP TABLE IF EXISTS "+NOME_TABELA+";";
        return  query;
    }

    public static String getQueryCountAll() {
        String query = "SELECT i._id,i.name,i.place,i.size,i.weight," +
                "i.type,i.groupp,i.description, g.name as group_label, t.name as type_label" +
                " FROM "+NOME_TABELA+" i,groupp g, type t WHERE i.type = t._id and g._id = i.groupp;";
        return  query;
    }

    public static String getQueryGetItemByPoint(double latitude, double longitude){

        String sql = "SELECT i._id,i.name,i.place,i.size,i.weight," +
                "i.type,i.groupp,i.description, g.name as group_label, t.name as type_label, it._id as location_place" +
                " FROM item i, item_location it,groupp g, type t WHERE it.captured = 0 and i.type = t._id AND g._id = i.groupp" +
                " and i._id = it.item AND " +
                "(" +
                "("+latitude+" < (it.latitude + 0.0010000) AND "+latitude+" > (it.latitude - 0.0010000)) AND " +
                "("+longitude+" < (it.longitude + 0.0010000) AND "+longitude+" > (it.longitude - 0.0010000))" +
                ")";
        return sql;
    }

    public static String getQueryGetItemById(){
        return "SELECT i._id,i.name,i.place,i.size,i.weight," +
        "i.type,i.groupp,i.description, g.name as group_label, t.name as type_label" +
                " FROM item i, item_location it,groupp g, type t WHERE i.type = t._id AND g._id = i.groupp" +
                " and i._id = it.item and i._id = ?;";
    }
}
