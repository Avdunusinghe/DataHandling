package com.example.datahandling.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "userInfo.db";

    public DbHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    /*public DbHelper( Context context) {
        super(context, DATABASE_NAME, factory:null, version:1);
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES= " CREATE TABLE " + UsersMaster.Users.TABLE_NAME + "( " +
                        UsersMaster.Users._ID+"INTEGER PRIMARY KEY,"+
                        UsersMaster.Users.COLUMN_NAME_USERNAME + "TEXT" +
                        UsersMaster.Users.COLUMN_NAME_PASSWORD + "TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }

    public void addInfo(String userName, String password){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.COLUMN_NAME_USERNAME,userName);
        values.put(UsersMaster.Users.COLUMN_NAME_PASSWORD,password);

        long newRoleId = db.insert(UsersMaster.Users.TABLE_NAME,null,values);
    }

    public List readAllInfo(String req){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {

                UsersMaster.Users._ID,
                UsersMaster.Users.COLUMN_NAME_USERNAME,
                UsersMaster.Users.COLUMN_NAME_PASSWORD
        };

        String sortOrder = UsersMaster.Users.COLUMN_NAME_USERNAME + "DESC";

        Cursor cursor = db.query(
                UsersMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder


        );

        List username = new ArrayList<>();
        List password = new ArrayList<>();

        while(cursor.moveToNext()){

            String userNames= cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_USERNAME));
            String passwords= cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_PASSWORD));

            username.add(userNames);
            password.add(passwords);
        }

        cursor.close();

        if(req == "username"){

            return username;

        }else if(req == "password") {

            return password;

        }else{

            return null;
        }


    }

    public void deleteInfo(String userName){

        SQLiteDatabase db = getReadableDatabase();

        String selection = UsersMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs = {userName};
        db.delete(UsersMaster.Users.TABLE_NAME,selection,selectionArgs);
    }


    public int updateInfo(String userName, String password){

        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.COLUMN_NAME_PASSWORD, password);

        String selection = UsersMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs = {userName};

        int count = db.update(
                UsersMaster.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        return count;
    }
}
