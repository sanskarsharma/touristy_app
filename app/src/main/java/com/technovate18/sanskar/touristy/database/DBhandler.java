package com.technovate18.sanskar.touristy.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.technovate18.sanskar.touristy.models.FeedPostModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 15/3/18.
 */

public class DBhandler extends SQLiteOpenHelper{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "dataDB";

    // Notes table name
    private static final String TABLE_DATA = "datatable1";

    // Notes Table Columns names
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_NOTICENUM = "noticenum";


    public DBhandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DATA_TABLE = "CREATE TABLE " + TABLE_DATA + "("
                + KEY_TIMESTAMP + " TEXT PRIMARY KEY, " + KEY_TITLE +" TEXT, "+ KEY_DESCRIPTION +" TEXT, "+ KEY_AUTHOR +" TEXT, "+ KEY_NOTICENUM +" TEXT "+")";
        db.execSQL(CREATE_DATA_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);

        // Create tables again
        onCreate(db);
    }



    public void addData(FeedPostModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_TIMESTAMP, dataModel.getTimestamp()); // Note is created at
        values.put(KEY_TITLE, dataModel.getTitle()); // Note created by
        values.put(KEY_DESCRIPTION, dataModel.getDescription());
        values.put(KEY_AUTHOR, dataModel.getAuthor());
        values.put(KEY_NOTICENUM, dataModel.getNoticenumber());

        // Inserting Row
        db.insert(TABLE_DATA, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Notes of a User
    public List<FeedPostModel> getAllEntries() {
        List<FeedPostModel> datalist = new ArrayList<FeedPostModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_DATA ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FeedPostModel datu = new FeedPostModel();
                datu.setTimestamp(cursor.getString(0));
                datu.setTitle(cursor.getString(1));
                datu.setDescription(cursor.getString(2));
                datu.setAuthor(cursor.getString(3));
                datu.setNoticenumber(cursor.getString(4));

                // Adding note to list
                datalist.add(datu);
            } while (cursor.moveToNext());
        }

        // return notes list
        return datalist;
    }

    // Deleting note
    public void deleteData(FeedPostModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATA, KEY_TIMESTAMP + " = ?",
                new String[]{String.valueOf(dataModel.getTimestamp())});
        db.close();
    }

    // Updating note
    public int updateData(FeedPostModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TIMESTAMP, dataModel.getTimestamp()); // Note is created at
        values.put(KEY_TITLE, dataModel.getTitle()); // Note created by
        values.put(KEY_DESCRIPTION, dataModel.getDescription());
        values.put(KEY_AUTHOR, dataModel.getAuthor());
        values.put(KEY_NOTICENUM, dataModel.getNoticenumber());


        // updating row
        return db.update(TABLE_DATA, values, KEY_TIMESTAMP + " = ?",
                new String[] { String.valueOf(dataModel.getTimestamp()) });
    }



    // Getting notes Count
    // unused function
    public int getEntryCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DATA ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

}
