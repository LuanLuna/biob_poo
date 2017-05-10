package com.tcc.luan.biob.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tcc.luan.biob.model.util.Group;
import com.tcc.luan.biob.model.util.Item;
import com.tcc.luan.biob.model.util.Type;
import com.tcc.luan.biob.model.util.User;

/**
 * Created by luan on 27/08/16.
 */
public class OpenHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "biob";
    private static final int DB_VERSION = 48;

    public OpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Item.getQueryCreateTable());
        db.execSQL(User.getQueryCreateTable());
        db.execSQL(Type.getQueryCreateTable());
        db.execSQL(Group.getQueryCreateTable());

        db.execSQL( "CREATE TABLE IF NOT EXISTS user_item(" +
                "_id integer primary key autoincrement," +
                "user integer not null," +
                "item integer not null" +
                ");");

        db.execSQL( "CREATE TABLE IF NOT EXISTS item_location(" +
                "_id integer primary key autoincrement," +
                "item integer not null," +
                "latitude real not null," +
                "captured integer default 0," +
                "longitude real not null" +
                ");");

        String senha = DataBase.toMD5("admin");
        db.execSQL("insert into user (name, password) values ('admin', '"+senha+"');");

        db.execSQL("insert into type (name) values ('Animal');");
        db.execSQL("insert into type (name) values ('Planta');");

        db.execSQL("insert into groupp (name) values ('Flor');");
        db.execSQL("insert into groupp (name) values ('Espinho');");
        db.execSQL("insert into groupp (name) values ('Bovino');");
        db.execSQL("insert into groupp (name) values ('Ave');");
        db.execSQL("insert into groupp (name) values ('Doméstico');");

        db.execSQL("INSERT INTO item (name,place,size,weight,type," +
                "groupp,description) VALUES (" +
                "'Vaca','Santa Rita',1.50,100.00,1,3,'Tais animais são ruminantes, possuem porte médio a grande, cauda comprida e, na maioria dos casos, pelagem curta. Quando adultos, geralmente apresentam um par de cornos ocos, não ramificados e permanentes; sendo chamados de mochos aqueles indivíduos desprovidos dessa estrutura.');");

        db.execSQL("INSERT INTO item (name,place,size,weight,type," +
                "groupp,description) VALUES (" +
                "'Cachorro','João Pessoa',1.50,3.00,1,5,'A animal that can be found on anyplace.');");

        db.execSQL("INSERT INTO item (name,place,size,weight,type," +
                "groupp,description) VALUES (" +
                "'Cacto','João Pessoa',0.50,0.40,2,2,'A plant.');");

        db.execSQL("INSERT INTO item (name,place,size,weight,type," +
                "groupp,description) VALUES (" +
                "'Burro','João Pessoa',1.00,90.00,1,3,'A animal.');");

        db.execSQL("INSERT INTO item (name,place,size,weight,type," +
                "groupp,description) VALUES (" +
                "'Rosa','João Pessoa',1.00,90.00,2,1,'Uma flor.');");


        db.execSQL("insert into item_location (item, latitude, longitude) values (2, -7.154765, -34.835311);");
        db.execSQL("insert into item_location (item, latitude, longitude) values (5, -7.154765, -34.835311);");
        db.execSQL("insert into item_location (item, latitude, longitude) values (3, -7.154765, -34.835311);");

        db.execSQL("insert into item_location (item, latitude, longitude) values (1, -7.1589205, -34.8315152);");
        db.execSQL("insert into item_location (item, latitude, longitude) values (3, -7.115109, -34.8523397);");
        db.execSQL("insert into item_location (item, latitude, longitude) values (4, -7.115059, -34.850442);");
        db.execSQL("insert into item_location (item, latitude, longitude) values (3, -7.153267, -34.837057);");
        db.execSQL("insert into item_location (item, latitude, longitude) values (4, -7.153213, -34.837247);");

        db.execSQL("insert into user_item (user, item) values (1, 1);");
        db.execSQL("insert into user_item (user, item) values (1, 4);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Item.getQueryDeleteTable());
        db.execSQL(User.getQueryDeleteTable());
        db.execSQL(Type.getQueryDeleteTable());
        db.execSQL(Group.getQueryDeleteTable());
        db.execSQL("DROP TABLE IF EXISTS item_location;");
        db.execSQL("DROP TABLE IF EXISTS user_item;");

        onCreate(db);
        Log.i("LOG","UPGRADE BIOB");
    }
}
