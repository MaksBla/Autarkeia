package com.example.autarkeia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button start,sled;
    int ID = 1;
    TextView tvQuotes,tvAuthor;

    DatabaseHelperQuotesAnbBooksmarks databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    Dialog myDialog;

    String status = "",statusProm = "";

    SharedPreferences mSettings;
    public static final String APP_PREFERENCES=  "saved_te";
    public static final String APP_PREFERENCES_STATUS = "has entered";

    SharedPreferences mSettingsPromt;
    public static final String APP_PROMT=  "saved_promt";
    public static final String APP_PROMT_STATUS = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.buttonStart);
        sled = (Button) findViewById(R.id.btSled);


        tvQuotes = (TextView) findViewById(R.id.tvQuotes);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor) ;

        databaseHelper = new DatabaseHelperQuotesAnbBooksmarks(getApplicationContext());
        // создаем базу данных
        databaseHelper.create_db();


        String fontPath1 = "fonts/amatb.ttf";
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), fontPath1);
        tvAuthor.setTypeface(typeface1);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        mSettingsPromt = getSharedPreferences(APP_PROMT, Context.MODE_PRIVATE);

        if(mSettingsPromt.contains(APP_PROMT_STATUS)) {
            statusProm = mSettingsPromt.getString(APP_PROMT_STATUS, "");
        }

        //Отображение подсказки
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.promt_next);

        if(statusProm.equals("") || statusProm.equals("on"))
        {
            showProntNext();
        }

    }

    public void showProntNext ()
    {
        Button btnClose;
        btnClose= (Button) myDialog.findViewById(R.id.bynClosePromtNex);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }

    public void sled (View view)

    {
        final Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.blink);
        sled.startAnimation(animation2);
        buttonSled();
    }

   public void buttonStart(View view)
    {
//        final Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.blink);
//        start.startAnimation(animation2);

        loadText();

        if(status.equals("has")) {
            Intent intent = new Intent(this, MainContents.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, IntroductionActivity.class);
            intent.putExtra("hasCome","no");
            startActivity(intent);
        }
    }

    void buttonSled()
    {
        int i = 1 + (int) ( Math.random() * 19 );
        ID = i;
        onResume();
    }
    @Override
    public void onResume() {
        super.onResume();
        // открываем подключение
        db = databaseHelper.open();
        //получаем данные из бд в виде курсора
        userCursor =  db.rawQuery("select * from " +DatabaseHelperQuotesAnbBooksmarks.TABLEQUOTES +" where id =" +ID , null);

        if (userCursor.moveToFirst()) {
            int idIndex = userCursor.getColumnIndex(DatabaseHelperQuotesAnbBooksmarks.COLUMN_QUOTES);
            int idAuther = userCursor.getColumnIndex(DatabaseHelperQuotesAnbBooksmarks.COLUMN_AUTHOR);

            do {

                tvQuotes.setText(userCursor.getString(idIndex));
                tvAuthor.setText(userCursor.getString(idAuther));

            } while (userCursor.moveToNext());
        } else
            userCursor.close();
        db.close();
    }


    public void onClick(View v) {
    }



    void loadText() {

        if(mSettings.contains(APP_PREFERENCES_STATUS)) {
            status = mSettings.getString(APP_PREFERENCES_STATUS, "");
        }

    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
