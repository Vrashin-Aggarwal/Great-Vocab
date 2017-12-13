package com.vrashinoriginals.greatvocab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;

/**
 * Created by vrashinaggarwal on 8/3/2017.
 */

public class DBAdapter {
    Context c;
    SQLiteDatabase db;
    DataBaseHelper helper;
    public DBAdapter(Context c) {
        this.c = c;
        helper=new DataBaseHelper(c);
    }
    //OPEN DATABASE
    public DBAdapter openDB()
    {
        /*try {
            db=helper.getWritableDatabase();
            //helper.openDataBase();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }*/

        try {

            helper.createDataBase();
            Log.d("logging","db created");

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            helper.openDataBase();
            Log.d("logging","db opened");

        }catch(SQLException sqle){

            throw new Error("Unable to open database");

        }
        
        return this;
    }
    //CLOSE DATABASE
    public void closeDB()
    {
        try {
            helper.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    //INSERT
    public long add(String name,String pos)
    {
        try
        {
            ContentValues cv=new ContentValues();
            /*cv.put(Constants.NAME,name);
            cv.put(Constants.POSITION, pos);*/
            return db.insert(Constants.TB_NAME,Constants.ROW_ID,cv);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public void insertData(){

    }
    //RETRIEVE
    public Cursor getAllWords()
    {

        Log.d("logging","inside get all words");
        String[] columns={/*Constants.ROW_ID,*/
                Constants.WORD,
                Constants.MEANING,
                Constants.SENTENCE,
                Constants.SCUT,/*
                Constants.B3,
                Constants.B8,
                Constants.M1,*/
                Constants.FAV};
        return db.query(Constants.TB_NAME,columns,null,null,null,null,null);
        /*if(c.moveToFirst()){
            Log.d("loggingdb","inside if");
        while (c.moveToNext())
        {
            Log.d("loggingdb","inside while");
            int id=c.getInt(0);
            String word=c.getString(1);
            String meaning=c.getString(2);
            String sentence = c.getString(3);
            String scut = c.getString(4);
            int fav = c.getInt(9);
            Log.d("loggingdb",word);
            Log.d("loggingdb",meaning);
            Log.d("loggingdb",sentence);
            Log.d("loggingdb",scut);
        }}*/
    }
    //update fav
    public void updateFav(String wordname,int val){
        Log.d("loggingdb","inside update fav");
        ContentValues cv = new ContentValues();
        cv.put(Constants.FAV,val);
        db.update(Constants.TB_NAME,cv,"word = '"+ wordname + "'",null);
        String[] columns={/*Constants.ROW_ID,*/
                Constants.WORD,
                Constants.FAV};
        Cursor c = db.query(Constants.TB_NAME,columns,null,null,null,null,null);
        if(c.moveToFirst()){
            Log.d("loggingdb","inside if fav");
            while (c.moveToNext())
            {
                Log.d("loggingdb","inside while fav");
                //int id=c.getInt(0);
                String word=c.getString(0);
                int fav = c.getInt(1);
                Log.d("loggingdb",word);
                Log.d("loggingdb",Integer.toString(fav));

            }}
        c.close();
    }
}
