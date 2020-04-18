package com.exmaple.hotelmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Room_Info extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "room.db";
    public static final String TABLE_NAME = "guest";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";
    public static final String COL_4 = "email";
    public static final String COL_5 = "country";
    public static final String COL_6 = "dob";
    public static final String COL_7 = "gender";
    public Room_Info(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE guest (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT,email TEXT,country TEXT,dob TEXT,gender TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS guest");
        onCreate(sqLiteDatabase);
    }
    public long addUser(String user,String password,String email, String country, String dob, String gender)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("country", country);
        contentValues.put("dob", dob);
        contentValues.put("gender", gender);
        long res=db.insert("guest", null, contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String username,String password)
    {
        String[] columns = {COL_1};
        SQLiteDatabase db =getReadableDatabase();
        String selection = COL_2 + "=?" + "and "+ COL_3 + "=?";
        String[] selectionArgs = { username , password };
        Cursor cursor =db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
        {
            return true;
        }
        return false;
    }

    public ArrayList getAllCotacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
        Cursor res = db.rawQuery( "select (ID ||' : '||username || ' : ' ||password || ' : '||email || ' : '||country || ' : '||dob || ' : '||gender) AS fullname from "+TABLE_NAME, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("fullname")));
            res.moveToNext();
        }
        return array_list;
    }
}
