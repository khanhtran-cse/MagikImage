package com.qkt.app.magikimage.constant;

import android.content.Context;
import android.provider.ContactsContract;

import com.qkt.app.magikimage.helper.DbHelper;

/**
 * Created by qkt on 26/07/2017.
 */

public class Database {
    private static Database instance = null;
    private DbHelper dbHelper = null;
    private Database(Context context){
        dbHelper = new DbHelper(context);
    }

    public static Database getInstance(Context context){
        if(instance == null){
            instance = new Database(context);
        }
        return instance;
    }

    public DbHelper getDbHelper(){
        return dbHelper;
    }
}
