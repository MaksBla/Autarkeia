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

public class DatabaseHelperQuotesAnbBooksmarks extends SQLiteOpenHelper {

    private static String DB_PATH; // полный путь к базе данных
    private static String DB_NAME = "tablequotesandbooksmarksssss.sqlite";
    private static final int SCHEMA = 2; // версия базы данных
    // название таблицы в бд
    //1 таблица quotestable
    static final String TABLEQUOTES = "quotestable";
    //2 таблица bookmarkstable
    static final String TABLEBOOKM = "bookmarkstable";
    // 1 глава donotregretaboutanything
    static final String TABLEONEGLAW = "donotregretaboutanything";
    // 2 глава followthedreams
    static final String TABLETWOGLAW = "followthedreams";
    // 3 глава followthedreams
    static final String TABLE3GLAW = "createyourfuture";
    // 4 глава followthedreams
    static final String TABLE4GLAW = "emotionalintellect";
    // 5 глава followthedreams
    static final String TABLE5GLAW = "dotheopposite";
    // 6 глава followthedreams
    static final String TABLE6GLAW = "gettoknowyourself";
    // 7 глава followthedreams
    static final String TABLE7GLAW = "donbeafraidtogetolder";
    // 8 глава followthedreams
    static final String TABLE8GLAW = "getoutofyourcomfortzone";
    // 9 глава followthedreams
    static final String TABLE9GLAW = "appreciateyourlovedones";
    // 10 глава followthedreams
    static final String TABLE10GLAW = "yourselfasyouare";
    // 11 глава followthedreams
    static final String TABLE11GLAW = "getenoughsleep";
    // 12 глава followthedreams
    static final String TABLE12GLAW = "begenerous";
    // 13 глава followthedreams
    static final String TABLE13GLAW = "eatright";


    // названия столбцов
    static final String COLUMN_ID = "_id";
    static final String COLUMN_QUOTES = "quotes";
    static final String COLUMN_AUTHOR = "author";

    static final String COLUMN_NAMEBUTTON = "NameButton";
    static final String COLUMN_NAMETABLE = "NameTable";
    static final String COLUMN_PAGE= "ValuePage";
    private Context myContext;

    DatabaseHelperQuotesAnbBooksmarks(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH =context.getFilesDir().getPath() + DB_NAME;
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