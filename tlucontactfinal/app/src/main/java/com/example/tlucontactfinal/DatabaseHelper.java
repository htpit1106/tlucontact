package com.example.tlucontactfinal;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.tlucontactfinal.model.cbnv;
import com.example.tlucontactfinal.model.donvi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tlucontact.db"; // Tên database
    private static final int DATABASE_VERSION = 1; // Phiên bản database
    private static final String DB_PATH_SUFFIX = "/databases/";
    private final Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        copyDatabaseFromAssets();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +"tbbacsi");
        onCreate(db);
    }

    public boolean deleteCbnv(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("tbcbnv", "id = ?", new String[]{id});
        db.close();
        return result > 0;

    }

    public boolean insertCbnv(cbnv cbnv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tencbnv", cbnv.getTencb());
        values.put("sodienthoai", cbnv.getSdt());
        values.put("email", cbnv.getEmail());
        values.put("donvi", cbnv.getChucvu());
        values.put("avatar", cbnv.getAvatar());
        values.put("thongtin", cbnv.getThongtin());
        long result = db.insert("tbcbnv", null, values);
        db.close();
        return result != -1;

    }

    public boolean insertDonvi(donvi donvi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", donvi.getTendv());
        values.put("sodienthoai", donvi.getSdt());
        values.put("email", donvi.getEmail());
        values.put("avatar", donvi.getAvatar());
        values.put("thongtin", donvi.getThongtin());
        long result = db.insert("tbdonvi", null, values);
        db.close();
        return result != -1;

    }
    public boolean deleteDonvi(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("tbdonvi", "id = ?", new String[]{id});
        db.close();
        return result > 0;

    }



    public ArrayList<cbnv> getAllCbnv() {
        ArrayList<cbnv> listcbnv = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("tbcbnv", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                cbnv cb = new cbnv(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );
                listcbnv.add(cb);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listcbnv;
    }

    public ArrayList<donvi> getAllDonvi() {
        ArrayList<donvi> listdonvi = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("tbdonvi", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                donvi dv = new donvi(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                listdonvi.add(dv);
            } while (cursor.moveToNext());

            }
        cursor.close();
        db.close();
        return listdonvi;


    }

    public boolean updateCbnv(String id, cbnv cbnv) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tencbnv", cbnv.getTencb());
        values.put("sodienthoai", cbnv.getSdt());
        values.put("email", cbnv.getEmail());
        values.put("donvi", cbnv.getChucvu());
        values.put("avatar", cbnv.getAvatar());
        values.put("thongtin", cbnv.getThongtin());
        int result = db.update("tbcbnv", values, "id = ?", new String[]{id});
        db.close();
        return result > 0;

    }
    public boolean updateDonvi(String id, donvi donvi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", donvi.getTendv());
        values.put("sodienthoai", donvi.getSdt());
        values.put("email", donvi.getEmail());
        values.put("avatar", donvi.getAvatar());
        values.put("thongtin", donvi.getThongtin());
        int result = db.update("tbdonvi", values, "id = ?", new String[]{id});
        db.close();
        return result > 0;
    }




    public void copyDatabaseFromAssets() {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if (dbFile.exists()) {
            Log.d("DatabaseHelper", "Database đã tồn tại, không cần copy.");
            return;
        }

        try {
            InputStream myInput = context.getAssets().open(DATABASE_NAME);
            String outFileName = context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;

            File dbDir = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!dbDir.exists()) dbDir.mkdirs();

            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();

            Log.d("DatabaseHelper", "✅ Database copied successfully!");
        } catch (IOException e) {
            Log.e("DatabaseHelper", "❌ Copy database failed! " + e.getMessage());
        }
    }


}
