package com.example.mostafa.apptask.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDataBase extends SQLiteOpenHelper {

    private final static String DATABASE_NAME="myData.DB";
    private static final int DATABASE_VERSION = 2;
    SQLiteDatabase obj;

    public myDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String first=
                "Create table "+myDataBaseContract.first_table.TABLE_cities+"("+
                myDataBaseContract.first_table._ID+" integer primary key autoincrement,"+
                myDataBaseContract.first_table.COLUMN_Name+" text, "+
                myDataBaseContract.first_table.COLUMN_temp+" text "+
                        ");";
        db.execSQL(first);





    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ myDataBaseContract.first_table.TABLE_cities);
        onCreate(sqLiteDatabase);

    }

    public Cursor fetch_products() {
        obj=getReadableDatabase();
        Cursor cursor=obj.query(myDataBaseContract.first_table.TABLE_cities,
                null,null,null,null,null,
                null);
        if (cursor!=null)
            cursor.moveToFirst();
        obj.close();
        return cursor;
    }


    public void add_city(String name,String temp)
    {
        ContentValues values=new ContentValues();
        values.put(myDataBaseContract.first_table.COLUMN_Name,name);
        values.put(myDataBaseContract.first_table.COLUMN_temp,temp);

        obj=getWritableDatabase();
        obj.insert(myDataBaseContract.first_table.TABLE_cities,null,values);
        obj.close();


    }

    public void delete_city(String id)
    {
        obj=getWritableDatabase();
        obj.delete(myDataBaseContract.first_table.TABLE_cities, "_id=?", new String[]{id});
        obj.close();
    }

    public Cursor check_data(String name)
    {
        obj=getReadableDatabase();
        String[]arg={name};
        Cursor cursor= obj.query(myDataBaseContract.first_table.TABLE_cities,null,
                myDataBaseContract.first_table.COLUMN_Name+"=?",arg,null,null,null);
        if (cursor!=null)
            cursor.moveToFirst();
        obj.close();
        return cursor;
    }


    }
