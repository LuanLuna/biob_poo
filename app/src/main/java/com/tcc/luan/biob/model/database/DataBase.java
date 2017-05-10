package com.tcc.luan.biob.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tcc.luan.biob.model.util.Column;
import com.tcc.luan.biob.model.util.Item;
import com.tcc.luan.biob.model.util.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luan on 28/08/16.
 */
public class DataBase {
    private OpenHelper helper;
    private SQLiteDatabase db;

    public DataBase(Context context){
        helper = new OpenHelper(context);
    }

    public boolean insert(String table, Column[] columns){
        ContentValues values = null;
        long result = -1;
        db = helper.getWritableDatabase();
        if (columns != null && table != null) {
            values = new ContentValues();
            for (Column column : columns) {
                values.put(column.TITLE,column.VALUE);
            }
            result = db.insert(table, null, values);
        }
        db.close();
        if (result != -1)
            return true;
        return false;
    }

    public User login(String login, String senha){
        User user = null;
        db = helper.getReadableDatabase();
        Cursor cursor = db.query(true,"user",new String[] {"_id","name"},"name = ? and password = ?", new String[]{login,toMD5(senha)},
                null,null,null,"1");
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            user = new User();
            user .setId(Integer.parseInt(cursor.getString(0)));
            user .setName(cursor.getString(1));
        }
        return user;
    }

    public List<Item> get_user_item(User user){
        List<Item> itens = new ArrayList<>();
        if (user != null) {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(user.getQueryGetAllItems(), new String[]{});
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    Item iten = new Item();
                    iten.setId(Integer.parseInt(cursor.getString(0)));
                    iten.setName(cursor.getString(1));
                    iten.setPlace(cursor.getString(2));
                    iten.setSize(Double.parseDouble(cursor.getString(3)));
                    iten.setWeight(Double.parseDouble(cursor.getString(4)));
                    iten.setType(Integer.parseInt(cursor.getString(5)));
                    iten.setGroup(Integer.parseInt(cursor.getString(6)));
                    iten.setDesription(cursor.getString(7));
                    iten.setType_label(cursor.getString(9));
                    iten.setGroup_label(cursor.getString(8));

                    itens.add(iten);
                } while (cursor.moveToNext());
            }
        }
        return itens;
    }

    public List<Item> getItensByLocationPoint(double latitude, double longitude){
        String sql = Item.getQueryGetItemByPoint(latitude,longitude);
        db = helper.getReadableDatabase();
        List<Item> itens = new ArrayList<>();
        try{
            Cursor cursor = db.rawQuery(sql,new String[]{});
            if (cursor != null){
                cursor.moveToFirst();
                do {
                    Item iten = new Item();
                    iten.setId(Integer.parseInt(cursor.getString(0)));
                    iten.setName(cursor.getString(1));
                    iten.setPlace(cursor.getString(2));
                    iten.setSize(Double.parseDouble(cursor.getString(3)));
                    iten.setWeight(Double.parseDouble(cursor.getString(4)));
                    iten.setType(Integer.parseInt(cursor.getString(5)));
                    iten.setGroup(Integer.parseInt(cursor.getString(6)));
                    iten.setDesription(cursor.getString(7));
                    iten.setType_label(cursor.getString(9));
                    iten.setGroup_label(cursor.getString(8));
                    Log.i("TESTE", "group: "+cursor.getString(8)+" type: "+cursor.getString(9));
                    iten.setLocation(Integer.parseInt(cursor.getString(10)));
                    itens.add(iten);

                } while (cursor.moveToNext());
            }
        }catch(Exception e){}
        return itens;

    }

    public static String toMD5(String string){
        String result = null;
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            byte messageDigest[] = algorithm.digest(string.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            result = hexString.toString();
        }catch(NoSuchAlgorithmException e){
            return null;
        }catch (UnsupportedEncodingException e){
            return null;
        }
        return result;
    }

    public Item get_item_by_id(int id){
        String sql = Item.getQueryGetItemById();
        db = helper.getReadableDatabase();
        List<Item> itens = new ArrayList<>();
        try{
            Cursor cursor = db.rawQuery(sql,new String[]{""+id});
            if (cursor != null){
                cursor.moveToFirst();

                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setPlace(cursor.getString(2));
                item.setSize(Double.parseDouble(cursor.getString(3)));
                item.setWeight(Double.parseDouble(cursor.getString(4)));
                item.setType(Integer.parseInt(cursor.getString(5)));
                item.setGroup(Integer.parseInt(cursor.getString(6)));
                item.setDesription(cursor.getString(7));
                item.setType_label(cursor.getString(9));
                item.setGroup_label(cursor.getString(8));

                return item;
            }
        }catch(Exception e){}

        return null;
    }

    public boolean exeqSql(String sql){
        try {
            db = helper.getReadableDatabase();
            db.execSQL(sql);
        }catch (Exception e){return false;}
        return true;
    }

}
