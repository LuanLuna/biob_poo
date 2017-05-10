package com.tcc.luan.biob.model.util;

import com.tcc.luan.biob.R;

/**
 * Created by luan on 04/09/16.
 */
public class Type {
    static final String NOME_TABELA = "type";
    private int id;
    private String name;


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
    //----------------------------------------------------Data base methods
    public static String getQueryCreateTable() {
        String query = "CREATE TABLE IF NOT EXISTS "+NOME_TABELA+"(" +
                "_id integer primary key autoincrement," +
                "name text not null" +
                ");";
        return  query;
    }
    public static String getQueryDeleteTable() {
        String query = "DROP TABLE IF EXISTS "+NOME_TABELA+";";
        return  query;
    }


    public static String getQueryCountAll() {
        String query = "SELECT _id, name FROM "+NOME_TABELA+";";
        return  query;
    }

}
