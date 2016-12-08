package com.example.cess.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cess on 07/12/16.
 */

public class DaoFrase extends SQLiteOpenHelper {


    private static final String TEXT_TYPE = " TEXT";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DaoFraseContract.FraseEntry.TABLE_NAME + " (" +
                    DaoFraseContract.FraseEntry._ID + " INTEGER PRIMARY KEY," +
                    DaoFraseContract.FraseEntry.COLUMN_CONTEUDO + TEXT_TYPE +  "," +
                    DaoFraseContract.FraseEntry.COLUMN_DATE + " DATETIME)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DaoFraseContract.FraseEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Frases.db";

    public DaoFrase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertFrase(String frase){
        //DaoFrase mDbHelper = new DaoFrase(context);
//        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DaoFraseContract.FraseEntry.COLUMN_CONTEUDO, frase);
        values.put(DaoFraseContract.FraseEntry.COLUMN_DATE, "DATENOW()");

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DaoFraseContract.FraseEntry.TABLE_NAME, null, values);
    }

    private Cursor select(String[] projection, String selection, String[] selectionArgs, String groupBy, String filterBy, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();

                // Filter results WHERE "title" = 'My Title'
//        String selection = DaoFraseContract.FraseEntry.COLUMN_CONTEUDO + " = ?";
//        String[] selectionArgs = { "My Title" };

        Cursor c = db.query(
                DaoFraseContract.FraseEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                groupBy,                                     // don't group the rows
                filterBy,                                     // don't filter by row groups
                orderBy                                 // The sort order
        );

        if(c!=null){
            c.moveToFirst();
        }
        db.close();

        return c;
    }

    public Cursor selectAll(){
        String[] projection = {
            DaoFraseContract.FraseEntry._ID,
            DaoFraseContract.FraseEntry.COLUMN_CONTEUDO,
            DaoFraseContract.FraseEntry.COLUMN_DATE
        };

        String orderBy = "_id DESC";
        return select(projection, null, null, null, null, orderBy);
    }

    public void deleteFrase(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = DaoFraseContract.FraseEntry._ID + " = ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = {id};
// Issue SQL statement.
        db.delete(DaoFraseContract.FraseEntry.TABLE_NAME, selection, selectionArgs);
        db.close();

    }
}
