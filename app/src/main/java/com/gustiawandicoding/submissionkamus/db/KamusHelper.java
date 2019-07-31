package com.gustiawandicoding.submissionkamus.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.gustiawandicoding.submissionkamus.model.EngModel;
import com.gustiawandicoding.submissionkamus.model.IndModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.gustiawandicoding.submissionkamus.db.DatabaseHelper.DatabaseContract.KamusColumns.DESCRIPTION;
import static com.gustiawandicoding.submissionkamus.db.DatabaseHelper.DatabaseContract.KamusColumns.WORD;
import static com.gustiawandicoding.submissionkamus.db.DatabaseHelper.DatabaseContract.TABLE_ENG;
import static com.gustiawandicoding.submissionkamus.db.DatabaseHelper.DatabaseContract.TABLE_INA;

/**
 * Created by Gustiawan on 9/30/2018.
 */

public class KamusHelper {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;
    private Context context;

    public KamusHelper(Context context){
        this.context = context;
    }

    public void open() throws SQLException{
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<EngModel> getDataByNameENG (String name){
        Cursor cursor = database.query(TABLE_ENG,null,WORD+" LIKE ?",new String[]{name +"%"},null,null,_ID+" ASC",null  );
        cursor.moveToFirst();
        ArrayList<EngModel> EngModels = new ArrayList<>();
        EngModel EngModel;
        if (cursor.getCount()> 0){
            do {
                EngModel = new EngModel();
                EngModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                EngModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                EngModel.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));

                EngModels.add(EngModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return EngModels;
    }

    public ArrayList<IndModel> getDataByNameINA (String name){
        Cursor cursor = database.query(TABLE_INA,null,WORD+" LIKE ?",new String[]{name +"%"},null,null,_ID+" ASC",null );
        cursor.moveToFirst();
        ArrayList<IndModel> indModels = new ArrayList<>();
        IndModel IndMocdel;
        if (cursor.getCount()>0){
            do {
                IndMocdel = new IndModel();
                IndMocdel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                IndMocdel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                IndMocdel.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));

                indModels.add(IndMocdel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return indModels;
    }

    public ArrayList<IndModel> getAllDataINA(){
        Cursor cursor= database.query(TABLE_INA,null,null,null,null,null,_ID+" ASC",null);
        cursor.moveToFirst();
        ArrayList<IndModel> indModels = new ArrayList<>();
        IndModel IndModel;
        if (cursor.getCount()>0){
            do {
                IndModel = new IndModel();
                IndModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                IndModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                IndModel.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));

                indModels.add(IndModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return indModels;
    }

    public ArrayList<EngModel> getAllDataENG(){
        Cursor cursor = database.query(TABLE_ENG,null,null,null,null,null,_ID+" ASC",null  );
        cursor.moveToFirst();
        ArrayList<EngModel> engModels = new ArrayList<>();
        EngModel EngModel;
        if (cursor.getCount()>0){
            do {
                EngModel = new EngModel();
                EngModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                EngModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                EngModel.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));

                engModels.add(EngModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return engModels;
    }

    public void insertINA(IndModel indModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(WORD, indModel.getWord());
        contentValues.put(DESCRIPTION, indModel.getDescription());
        database.insert(TABLE_INA, null, contentValues);
    }

    public void insertENG(EngModel engModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(WORD, engModel.getWord());
        contentValues.put(DESCRIPTION, engModel.getDescription());
        database.insert(TABLE_ENG, null, contentValues);
    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }


}

