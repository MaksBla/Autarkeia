package com.example.autarkeia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.ScrollView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class bookmarks extends AppCompatActivity {



    RecyclerView recyclerView;
    List<String> glavText;
    List<String> glavTag;

    DatabaseHelperQuotesAnbBooksmarks databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    ImageView IMPosto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        recyclerView = (RecyclerView)findViewById(R.id.rView);

        glavText = new ArrayList<>();
        glavTag = new ArrayList<>();

        databaseHelper = new DatabaseHelperQuotesAnbBooksmarks(this);

        IMPosto = (ImageView)findViewById(R.id.imageViewPoosto) ;


        creadBase();

        ModelBookmarks modelBookmarks = new ModelBookmarks(this, glavText,glavTag);
        recyclerView.setAdapter(modelBookmarks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void creadBase ()
    {
             db = databaseHelper.open();
             userCursor = db.rawQuery("select * from " +DatabaseHelperQuotesAnbBooksmarks.TABLEBOOKM  , null);

            if (userCursor.moveToFirst()) {
                int nPageNB = userCursor.getColumnIndex(DatabaseHelperQuotesAnbBooksmarks.COLUMN_NAMEBUTTON);
                int nPageNT = userCursor.getColumnIndex(DatabaseHelperQuotesAnbBooksmarks.COLUMN_NAMETABLE);

                IMPosto.setVisibility(View.INVISIBLE);

                do {
                    glavText.add(userCursor.getString(nPageNB));
                    glavTag.add(userCursor.getString(nPageNT));

                } while (userCursor.moveToNext());
            } else {
                IMPosto.setVisibility(View.VISIBLE);
                userCursor.close();
            }
            db.close();
    }
}
