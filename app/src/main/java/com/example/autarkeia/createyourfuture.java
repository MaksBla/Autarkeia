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
import android.widget.TextView;

public class createyourfuture extends AppCompatActivity {

    TextView og1;
    Button btnEnd;
    AutoCompleteTextView tvTask, tvPositiv;

    SQLiteDatabase db;
    Cursor userCursor;
    DatabaseHelperPractic databaseHelperPractic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createyourfuture);


        og1 = (TextView) findViewById(R.id.textViewOg1);
        String fontPath1 = "fonts/amatb.ttf";
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), fontPath1);
        og1.setTypeface(typeface1);

        tvPositiv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewT2) ;
        tvTask = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewT1) ;

        databaseHelperPractic = new DatabaseHelperPractic(this);
        databaseHelperPractic.create_db();
        db = databaseHelperPractic.open();

        textLoad();

        btnEnd = (Button) findViewById(R.id.buttonEndTask);
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = databaseHelperPractic.open();
                ContentValues cv = new ContentValues();
                cv.put(databaseHelperPractic.COLUMN_TASK, tvTask.getText().toString());
                cv.put(databaseHelperPractic.COLUMN_POSITIVE, tvPositiv.getText().toString());
                db.insert(databaseHelperPractic.TABLE3GLAW, null, cv);
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

        userCursor =  db.rawQuery("select * from " +databaseHelperPractic.TABLE3GLAW  , null);
        if (userCursor.moveToFirst()) {
            int nTask = userCursor.getColumnIndex(databaseHelperPractic.COLUMN_TASK);
            int nPos = userCursor.getColumnIndex(databaseHelperPractic.COLUMN_POSITIVE);
            do {

                tvTask.setText(userCursor.getString(nTask));
                tvPositiv.setText(userCursor.getString(nPos));

            } while (userCursor.moveToNext());
        } else
            userCursor.close();
        db.close();

    }
}
