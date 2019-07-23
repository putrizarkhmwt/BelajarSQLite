package com.example.lenovo.belajarsqllite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.lenovo.belajarsqllite.model.Barang;

import java.util.ArrayList;

public class DBSource {
    private SQLiteDatabase database;

    private DBHelper dbHelper;

    private String[] allColumns = {DBHelper.COLUMN_ID, DBHelper.COLUMN_NAME, DBHelper.COLUMN_BRAND, DBHelper.COLUMN_PRICE};

    public DBSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Barang createBarang(String name, String brand, String price) {

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, name);
        values.put(DBHelper.COLUMN_BRAND, brand);
        values.put(DBHelper.COLUMN_PRICE, price);

        long insertId = database.insert(DBHelper.TABLE_NAME, null,
                values);

        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();


        Barang newBarang = cursorToBarang(cursor);
        cursor.close();

        return newBarang;
    }

    private Barang cursorToBarang(Cursor cursor) {
        Barang barang = new Barang();
        barang.setId(cursor.getLong(0));
        barang.setName(cursor.getString(1));
        barang.setBrand(cursor.getString(2));
        barang.setPrice(cursor.getString(3));

        return barang;
    }

    public ArrayList<Barang> getAllBarang(){
        ArrayList<Barang> daftarBarang = new ArrayList<Barang>();

        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Barang barang = cursorToBarang(cursor);
            daftarBarang.add(barang);
            cursor.moveToNext();
        }

        cursor.close();
        return daftarBarang;
    }

    public Barang getBarang(long id) {
        Barang barang = new Barang();

        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns,
                "_id =" + id, null, null, null, null);
        cursor.moveToFirst();
        barang = cursorToBarang(cursor);
        cursor.close();

        return barang;
    }

    public Barang editBarang(long id){
        Barang barang = new Barang();

        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, "_id="+id, null, null, null, null);
        cursor.moveToFirst();

        barang = cursorToBarang(cursor);

        cursor.close();

        return barang;
    }

    public void updateBarang(Barang b) {
        String strFilter = "_id="+b.getId();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, b.getName());
        values.put(DBHelper.COLUMN_BRAND, b.getBrand());
        values.put(DBHelper.COLUMN_PRICE, b.getPrice());

        database.update(DBHelper.TABLE_NAME, values, strFilter, null);
    }

    public void deleteBarang(long id) {
        String strFilter = "_id=" + id;
        database.delete(DBHelper.TABLE_NAME, strFilter, null);
    }
}
