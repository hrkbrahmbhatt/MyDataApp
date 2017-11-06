package my.hrsikeshbrahmbhatt.mydataapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hrsikeshbrahmbhatt on 2017-11-05.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="products.db";
    public static final String TABLE_PRODUCTS="products";
    public static final String COLOUM_ID="_id";
    public static final String COLOUM_PRODUCTNAME="productname";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME , factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = " CREATE TABLE " + TABLE_PRODUCTS + " ( " + COLOUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLOUM_PRODUCTNAME + " TEXT " + ");" ;
        sqLiteDatabase.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL(" DROP TABLE IF EXITS " + TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);

    }


    public void addProduct(Products products){

        ContentValues values = new ContentValues();

        values.put(COLOUM_PRODUCTNAME,products.get_productName());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_PRODUCTS,null,values);

        db.close();
    }

    public void deleteProduct(String productname){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_PRODUCTS  + " WHERE " + COLOUM_PRODUCTNAME + "=\"" + productname + "\";");

    }

    public String databaseToString(){

        String dbString="";
        SQLiteDatabase db = getWritableDatabase();

        String query = " SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1 ";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

    while (!c.isAfterLast()){

        if (c.getString(c.getColumnIndex("productname"))!=null){

            dbString+=c.getString(c.getColumnIndex("productname"));
            dbString+="\n";

        }
        c.moveToNext();

    }
        db.close();
        return dbString;

    }

}
