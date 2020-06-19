package com.example.autarkeia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class begenerous extends AppCompatActivity {

    TextView tvC1, tvC2, tvC3;
    CheckBox cb1,cb2,cb3,cb4,cb5;
    Button btnEnd;

    String prov;

    SQLiteDatabase db;
    Cursor userCursor;
    DatabaseHelperPractic databaseHelperPractic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begenerous);

        btnEnd = (Button) findViewById(R.id.buttonEndG3);

        tvC1 = (TextView)findViewById(R.id.tvG3V1);
        tvC2 = (TextView)findViewById(R.id.tvG3V2);
        tvC3 = (TextView)findViewById(R.id.tvG3V3);

        String fontPath1 = "fonts/amatb.ttf";
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), fontPath1);
        tvC1.setTypeface(typeface1);
        tvC2.setTypeface(typeface1);
        tvC3.setTypeface(typeface1);

        cb1 = (CheckBox) findViewById(R.id.checkBox);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);
        cb4 = (CheckBox) findViewById(R.id.checkBox4);
        cb5 = (CheckBox) findViewById(R.id.checkBox5);

        databaseHelperPractic = new DatabaseHelperPractic(this);
        databaseHelperPractic.create_db();
        db = databaseHelperPractic.open();
        textLoad();

        btnEnd = (Button) findViewById(R.id.buttonEndG3);
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          db = databaseHelperPractic.open();

                ContentValues cv = new ContentValues();

                cv.put(databaseHelperPractic.COLUMN_CHECBOX1, cb1.isChecked());
                cv.put(databaseHelperPractic.COLUMN_CHECBOX2, cb2.isChecked());
                cv.put(databaseHelperPractic.COLUMN_CHECBOX3, cb3.isChecked());
                cv.put(databaseHelperPractic.COLUMN_CHECBOX4, cb4.isChecked());
                cv.put(databaseHelperPractic.COLUMN_CHECBOX5, cb5.isChecked());

                db.insert(databaseHelperPractic.TABLEGLAW3, null, cv);
                db.close();

                e();

            }
        });
    }

    void e()
    {
        super.finish();
    }

    void textLoad()
    {
        db = databaseHelperPractic.open();

        userCursor =  db.rawQuery("select * from " +databaseHelperPractic.TABLEGLAW3  , null);
        if (userCursor.moveToFirst()) {
            int vcb1 = userCursor.getColumnIndex(databaseHelperPractic.COLUMN_CHECBOX1);
            int vcb2 = userCursor.getColumnIndex(databaseHelperPractic.COLUMN_CHECBOX2);
            int vcb3 = userCursor.getColumnIndex(databaseHelperPractic.COLUMN_CHECBOX3);
            int vcb4 = userCursor.getColumnIndex(databaseHelperPractic.COLUMN_CHECBOX4);
            int vcb5 = userCursor.getColumnIndex(databaseHelperPractic.COLUMN_CHECBOX5);

            do {
                prov = userCursor.getString(vcb1);

                if(userCursor.getString(vcb1).equals("0"))
                cb1.setChecked(false);
                else
                cb1.setChecked(true);

                if(userCursor.getString(vcb2).equals("0"))
                    cb2.setChecked(false);
                else
                    cb2.setChecked(true);

                if(userCursor.getString(vcb3).equals("0"))
                    cb3.setChecked(false);
                else
                    cb3.setChecked(true);

                if(userCursor.getString(vcb4).equals("0"))
                    cb4.setChecked(false);
                else
                    cb4.setChecked(true);

                if(userCursor.getString(vcb5).equals("0"))
                    cb5.setChecked(false);
                else
                    cb5.setChecked(true);

            } while (userCursor.moveToNext());
        } else
            userCursor.close();
        db.close();

    }
}
