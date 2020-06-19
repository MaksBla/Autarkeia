package com.example.autarkeia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.tv.TvContentRating;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainText extends AppCompatActivity implements
        View.OnClickListener {

    Button btnChik, btnOk;
    TextView mText;
    public String nameDataBase = "chapters";
    public String nameTable = "";
    public String nameButton = "";
    String numberPage = "1";

    int inumber = 1, status = 1, switchNumber = 0;

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    Intent intent,pracItem;

    ImageView imageView;
    AnimatedVectorDrawableCompat avd;
    AnimatedVectorDrawable avd2;

    final String LOG_TAG = "myLogs";

    DatabaseHelperQuotesAnbBooksmarks databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    Boolean checkNameTable = false, pracNameTable=false;

    Dialog myDialogAddNoAdd,myDialogPrac;

    SharedPreferences mSettingsPromt;
    public static final String APP_PROMT =  "saved_promt";
    public static final String APP_PROMT_STATUS = "";
    String statusProm = "";

    SharedPreferences mSettingsConti;
    public static final String APP_CONTI=  "saved_conti";
    public static final String APP_CONTI_NAMETABLE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_text);

        mSettingsPromt = getSharedPreferences(APP_PROMT, Context.MODE_PRIVATE);
        mSettingsConti = getSharedPreferences(APP_CONTI, Context.MODE_PRIVATE);

        if(mSettingsPromt.contains(APP_PROMT_STATUS)) {
            statusProm = mSettingsPromt.getString(APP_PROMT_STATUS, "");
        }

        myDialogAddNoAdd = new Dialog(this);
        myDialogAddNoAdd.setContentView(R.layout.promt_addnoadd);

        myDialogPrac = new Dialog(this);
        myDialogPrac.setContentView(R.layout.promt_prac);

        if(statusProm.equals("") || statusProm.equals("on"))
        {
            showProntAdd ();
            showProntPrac ();
        }

        btnChik = findViewById(R.id.buttonCH);
        btnChik.setOnClickListener(this);

        imageView = findViewById(R.id.imageView2);
        imageView.setOnClickListener(this);

        intent = getIntent();
        nameTable = intent.getStringExtra("nameTable");
        nameButton = intent.getStringExtra("nameButton");

        models = new ArrayList<>();

        databaseHelper = new DatabaseHelperQuotesAnbBooksmarks(this);
        db = databaseHelper.open();

        Check();
        oonResume();

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(50, 50, 50, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.GlavText1),
                getResources().getColor(R.color.GlavText2),
                getResources().getColor(R.color.GlavText3),
                getResources().getColor(R.color.GlavText4),
                getResources().getColor(R.color.GlavText5),
                getResources().getColor(R.color.GlavText6),
                getResources().getColor(R.color.GlavText7),
                getResources().getColor(R.color.GlavText8),
                getResources().getColor(R.color.GlavText9),
                getResources().getColor(R.color.GlavText10),
                getResources().getColor(R.color.GlavText11),

        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                } else {
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void showProntAdd ()
    {
        Button btnClose;
        btnClose= (Button) myDialogAddNoAdd.findViewById(R.id.bynClosePromtAddNoAdd);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogAddNoAdd.dismiss();
            }
        });

        myDialogAddNoAdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialogAddNoAdd.show();
    }

    public void showProntPrac ()
    {
        Button btnClose;
        btnClose= (Button) myDialogPrac.findViewById(R.id.bynClosePromtPrac);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogPrac.dismiss();
            }
        });

        myDialogPrac.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialogPrac.show();
    }


    @Override
    public void onDestroy() {
        SharedPreferences.Editor editor = mSettingsConti.edit();
        editor.putString(APP_CONTI_NAMETABLE, nameTable);
        editor.apply();

        super.onDestroy();
    }

    public void oonResume()
    {
        // открываем подключение
        db = databaseHelper.open();

        switch ( nameTable) {
            case "donotregretaboutanything":
                userCursor =  db.rawQuery("select * from "+ DatabaseHelperQuotesAnbBooksmarks.TABLEONEGLAW, null);
                pracItem = new Intent(this, donotregretaboutanything.class);
                pracNameTable = true;
                break;
            case "followthedreams":
                userCursor =  db.rawQuery("select * from "+ DatabaseHelperQuotesAnbBooksmarks.TABLETWOGLAW, null);

                break;
            case "createyourfuture":
                userCursor =  db.rawQuery("select * from "+ DatabaseHelperQuotesAnbBooksmarks.TABLE3GLAW, null);
                pracItem = new Intent(this, createyourfuture.class);
                pracNameTable = true;
                break;
            case "emotionalintellect":
                userCursor =  db.rawQuery("select * from "+ DatabaseHelperQuotesAnbBooksmarks.TABLE4GLAW, null);

                break;
            case "dotheopposite":
                userCursor =  db.rawQuery("select * from "+ DatabaseHelperQuotesAnbBooksmarks.TABLE5GLAW, null);

                break;
            case "gettoknowyourself":
                userCursor =  db.rawQuery("select * from "+ DatabaseHelperQuotesAnbBooksmarks.TABLE6GLAW, null);

                break;
            case "donbeafraidtogetolder":
                userCursor =  db.rawQuery("select * from "+ DatabaseHelperQuotesAnbBooksmarks.TABLE7GLAW, null);

                break;
            case "getoutofyourcomfortzone":
                userCursor =  db.rawQuery("select * from "+ DatabaseHelperQuotesAnbBooksmarks.TABLE8GLAW, null);

                break;
            case "appreciateyourlovedones":
                userCursor =  db.rawQuery("select * from "+ DatabaseHelperQuotesAnbBooksmarks.TABLE9GLAW, null);

                break;
            case "yourselfasyouare":
                userCursor =  db.rawQuery("select * from "+ DatabaseHelperQuotesAnbBooksmarks.TABLE10GLAW, null);

                break;
            case "getenoughsleep":
                userCursor =  db.rawQuery("select * from "+ DatabaseHelperQuotesAnbBooksmarks.TABLE11GLAW, null);

                break;
            case "begenerous":
                userCursor =  db.rawQuery("select * from "+ DatabaseHelperQuotesAnbBooksmarks.TABLE12GLAW, null);
                pracItem = new Intent(this, begenerous.class);
                pracNameTable = true;
                break;
            case "eatright":
                userCursor =  db.rawQuery("select * from "+ DatabaseHelperQuotesAnbBooksmarks.TABLE13GLAW, null);

                break;
        }

        if (userCursor.moveToFirst()) {
            int nPage = userCursor.getColumnIndex(DatabaseHelperQuotesAnbBooksmarks.COLUMN_PAGE);
            do {
                models.add(new Model(R.drawable.bblanki, userCursor.getString(nPage)));

            } while (userCursor.moveToNext());
        } else
            userCursor.close();
        db.close();


    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imageView2:
                ClickImage();
                break;
            case R.id.buttonCH:
                chikNext();
                break;
        }
    }

    void Check()
    {
        db = databaseHelper.open();
         userCursor = db.rawQuery("select * from "+DatabaseHelperQuotesAnbBooksmarks.TABLEBOOKM + " where " + DatabaseHelperQuotesAnbBooksmarks.COLUMN_NAMETABLE+" = '" +nameTable+"' ",null);
        if (userCursor.moveToFirst()) {
            do {
                checkNameTable = true;
            } while (userCursor.moveToNext());
        } else
            userCursor.close();

        if(checkNameTable)
        {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.noaddtoadd));
        }
        else
        {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.addnoadd));
        }

    }

    void ClickImage() {
        if (!checkNameTable) {
            //создаём и отображаем текстовое уведомление
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Глава была добавлена в закладки!",
                    Toast.LENGTH_SHORT);
            toast.show();

            db = databaseHelper.open();
            ContentValues cv = new ContentValues();
            cv.put(DatabaseHelperQuotesAnbBooksmarks.COLUMN_NAMEBUTTON, nameButton);
            cv.put(DatabaseHelperQuotesAnbBooksmarks.COLUMN_NAMETABLE, nameTable);
            db.insert(DatabaseHelperQuotesAnbBooksmarks.TABLEBOOKM, null, cv);
            db.close();


            imageView.setImageDrawable(getResources().getDrawable(R.drawable.addnoadd));
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AnimatedVectorDrawableCompat) {
                avd = (AnimatedVectorDrawableCompat) drawable;
                avd.start();
            } else if (drawable instanceof AnimatedVectorDrawable) {
                avd2 = (AnimatedVectorDrawable) drawable;
                avd2.start();
            }
            checkNameTable = true;
        } else {
            //создаём и отображаем текстовое уведомление
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Глава удалена из закладок!",
                    Toast.LENGTH_SHORT);
            toast.show();

            db = databaseHelper.open();
            db.delete(DatabaseHelperQuotesAnbBooksmarks.TABLEBOOKM, DatabaseHelperQuotesAnbBooksmarks.COLUMN_NAMETABLE+" = ?", new String[]{String.valueOf(nameTable)});
            db.close();

            imageView.setImageDrawable(getResources().getDrawable(R.drawable.noaddtoadd));
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AnimatedVectorDrawableCompat) {
                avd = (AnimatedVectorDrawableCompat) drawable;
                avd.start();
            } else if (drawable instanceof AnimatedVectorDrawable) {
                avd2 = (AnimatedVectorDrawable) drawable;
                avd2.start();
            }
            checkNameTable = false;
        }
    }


    public void chikNext() {
        final Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        btnChik.startAnimation(animTranslate);

        if(pracNameTable) {
            startActivity(pracItem);
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Практическая часть отсуствует",
                    Toast.LENGTH_SHORT);
            toast.show();
        }

    }


}
