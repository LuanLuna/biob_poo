package com.tcc.luan.biob.control;

import android.content.Context;
import android.util.Log;

import com.tcc.luan.biob.model.database.DataBase;
import com.tcc.luan.biob.model.util.User;

/**
 * Created by luan on 28/08/16.
 */
public class Access {
    public static User login(String login, String password, Context context){
        DataBase db = new DataBase(context);
        User retorno = db.login(login,password);

        return retorno;
    }
}
