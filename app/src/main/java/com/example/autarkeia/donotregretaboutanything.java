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
import android.widget.Toast;

public class donotregretaboutanything extends AppCompatActivity {

    Button btnEnd;
    AutoCompleteTextView tvDrean, tvDef;
    TextView og1;
    SQLiteDatabase db;
    Cursor userCursor;
    DatabaseHelperPractic databaseHelperPractic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donotregretaboutanything);

        og1 = (TextView) findViewById(R.id.textViewOdl1);
        String fontPath1 = "fonts/amatb.ttf";
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), fontPath1);
        og1.setTypeface(typeface1);

        tvDef = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewDef);
        tvDrean = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewDream);

        databaseHelperPractic = new DatabaseHelperPractic(this);
        databaseHelperPractic.create_db();
        db = databaseHelperPractic.open();

        textLoad();

        btnEnd = (Button) findViewById(R.id.buttonEndG1);
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = databaseHelperPractic.open();
                ContentValues cv = new ContentValues();
                cv.put(databaseHelperPractic.COLUMN_DREAMS, tvDrean.getText().toString());
                cv.put(databaseHelperPractic.COLUMN_DEFFICULTIES, tvDef.getText().toString());
                db.insert(databaseHelperPractic.TABLEONEGLAW, null, cv);
                db.close();

                //создаём и отображаем текстовое уведомление
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Удачи !",
                        Toast.LENGTH_SHORT);
                toast.show();

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

        userCursor =  db.rawQuery("select * from " +databaseHelperPractic.TABLEONEGLAW  , null);
        if (userCursor.moveToFirst()) {
            int nDream = userCursor.getColumnIndex(databaseHelperPractic.COLUMN_DREAMS);
            int nDef = userCursor.getColumnIndex(databaseHelperPractic.COLUMN_DEFFICULTIES);
            do {

                tvDrean.setText(userCursor.getString(nDream));
                tvDef.setText(userCursor.getString(nDef));

            } while (userCursor.moveToNext());
        } else
            userCursor.close();
        db.close();

    }
}
