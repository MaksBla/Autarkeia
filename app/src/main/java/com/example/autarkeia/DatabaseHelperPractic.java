package com.example.autarkeia;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelperPractic extends SQLiteOpenHelper {
    private static String DB_PATH; // полный путь к базе данных
    private static String DB_NAME = "practicalexercisessss.sqlite";
    private static final int SCHEMA = 1; // версия базы данных

    // название таблицы в бд
    // 1 глава donotregretaboutanything
    static final String TABLEONEGLAW = "donotregretaboutanything";

    // 2 глава createyourfuture
    static final String TABLE3GLAW = "createyourfuture";

    // 3 глава createyourfuture
    static final String TABLEGLAW3 = "begenerous";

    // названия столбцов таблице 1
    static final String COLUMN_DREAMS = "dreams";
    static final String COLUMN_DEFFICULTIES = "defficulties";

    // названия столбцов таблице 2
    static final String COLUMN_TASK = "Task";
    static final String COLUMN_POSITIVE = "Positivedecision";

    // названия столбцов таблице 3
    static final String COLUMN_CHECBOX1 = "checBox1";
    static final String COLUMN_CHECBOX2 = "checBox2";
    static final String COLUMN_CHECBOX3 = "checBox3";
    static final String COLUMN_CHECBOX4 = "checBox4";
    static final String COLUMN_CHECBOX5 = "checBox5";


    private Context myContext;
    DatabaseHelperPractic(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH = context.getFilesDir().getPath() + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
    }

    void create_db(){
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                this.getReadableDatabase();
                //получаем локальную бд как поток
                myInput = myContext.getAssets().open(DB_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH;

                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(IOException ex){
            Log.d("DatabaseHelper", ex.getMessage());
        }
    }
    public SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}