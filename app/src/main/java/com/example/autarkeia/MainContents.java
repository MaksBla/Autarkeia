package com.example.autarkeia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.concurrent.TimeUnit;

import static java.security.AccessController.getContext;

public class MainContents extends AppCompatActivity implements View.OnClickListener {

    Dialog myDialogMenu, myDMenu, myDCountMenu;
    private float mBackLightValue = 0.5f;
    Button Glav1, Glav2, Glav3, Glav4, Glav5, Glav6, Glav7, Glav8, Glav9, Glav10, Glav11, Glav12, Glav13 ;
    ToggleButton tgButton;

    String statusProm = "", status = "";

    SharedPreferences mSettingsPromt;
    public static final String APP_PROMT = "saved_promt";
    public static final String APP_PROMT_STATUS = "";

    SharedPreferences mSettingsConti;
    public static final String APP_CONTI = "saved_conti";
    public static final String APP_CONTI_NAMETABLE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contents);

        myDialogMenu = new Dialog(this);
        myDialogMenu.setContentView(R.layout.menu);

        myDMenu = new Dialog(this);
        myDMenu.setContentView(R.layout.promt_menu);


        mSettingsPromt = getSharedPreferences(APP_PROMT, Context.MODE_PRIVATE);

        if (mSettingsPromt.getString(APP_PROMT_STATUS, "") == "")
        {

            SharedPreferences.Editor editor = mSettingsPromt.edit();
            editor.putString(APP_PROMT_STATUS, "on");
            editor.apply();
            statusProm = "on";
        }



        if ( statusProm.equals("on")) {
            showProntMenu();
        }



        myDCountMenu = new Dialog(this);
        myDCountMenu.setContentView(R.layout.promt_count_menu);

        Glav1 = (Button) findViewById(R.id.btnGlavOne);
        Glav1.setOnClickListener(this);

        Glav2 = (Button) findViewById(R.id.btnGlavTwo);
        Glav2.setOnClickListener(this);

        Glav3 = (Button) findViewById(R.id.btnGlavWhre);
        Glav3.setOnClickListener(this);

        Glav4 = (Button) findViewById(R.id.btnGlavFo);
        Glav4.setOnClickListener(this);

        Glav5 = (Button) findViewById(R.id.btnGlavFive);
        Glav5.setOnClickListener(this);

        Glav6 = (Button) findViewById(R.id.btnGlavSixe);
        Glav6.setOnClickListener(this);

        Glav7 = (Button) findViewById(R.id.btnGlavSevan);
        Glav7.setOnClickListener(this);

        Glav8 = (Button) findViewById(R.id.btnGlavNait);
        Glav8.setOnClickListener(this);

        Glav9 = (Button) findViewById(R.id.btnGlavAet);
        Glav9.setOnClickListener(this);

        Glav10 = (Button) findViewById(R.id.btnGlavTean);
        Glav10.setOnClickListener(this);

        Glav11 = (Button) findViewById(R.id.btnGlavOneS);
        Glav11.setOnClickListener(this);

        Glav12 = (Button) findViewById(R.id.btnGlav12);
        Glav12.setOnClickListener(this);

        Glav12 = (Button) findViewById(R.id.btnGlav13);
        Glav12.setOnClickListener(this);

        mSettingsConti = getSharedPreferences(APP_CONTI, Context.MODE_PRIVATE);
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }
    }

    public void ShowPopup(View v) {

        if (mSettingsPromt.contains(APP_PROMT_STATUS)) {
            statusProm = mSettingsPromt.getString(APP_PROMT_STATUS, "");
        }

        Button btnClose, btnintroduction, btnconti;

        SeekBar backLightSeekBar;
        Button updateButton;
        Button btbookmarks;
        TextView textpromts;

        backLightSeekBar = (SeekBar) myDialogMenu.findViewById(R.id.seekBar);
        updateButton = (Button) myDialogMenu.findViewById(R.id.buttonUpdate);
        updateButton.setOnClickListener(this);

        btnClose = (Button) myDialogMenu.findViewById(R.id.bynClose);
        btnClose.setOnClickListener(this);

        btnintroduction = (Button) myDialogMenu.findViewById(R.id.btnintroduction);
        btnintroduction.setOnClickListener(this);

        btbookmarks = (Button) myDialogMenu.findViewById(R.id.btnbookmarks);
        btbookmarks.setOnClickListener(this);

        tgButton = (ToggleButton) myDialogMenu.findViewById(R.id.TGButton);
        tgButton.setOnClickListener(this);




        if (statusProm.equals("on")) {
            tgButton.setChecked(true);


        } else if (statusProm.equals("off"))  {
            tgButton.setChecked(false);
        }



        btnconti = (Button) myDialogMenu.findViewById(R.id.btcontinue);
        btnconti.setOnClickListener(this);


        textpromts = (TextView) myDialogMenu.findViewById(R.id.textviewprom);

        String fontPath1 = "fonts/amatb.ttf";
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), fontPath1);
        textpromts.setTypeface(typeface1);

        backLightSeekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.backgraund), PorterDuff.Mode.SRC_ATOP); // полоска
        backLightSeekBar.getThumb().setColorFilter(getResources().getColor(R.color.backgraund), PorterDuff.Mode.SRC_ATOP); // кругляшок

        backLightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBackLightValue = (float) progress / 100;
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.screenBrightness = mBackLightValue;
                getWindow().setAttributes(layoutParams);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        myDialogMenu.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialogMenu.show();

        if (statusProm.equals("on")) {
            showProntCout();
        }
    }

    //включение и отключение подсказок

    public void onChecke(boolean isChecked) {

                //Состояние: Включён
                if ( isChecked)
                    status = "on";
                else
                    status = "off";
                //Состояние:  Выключен
                saveText();

    }

    void saveText() {

        if (status.equals("on")) {

            //создаём и отображаем текстовое уведомление
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Отображение подсказок: включено",
                    Toast.LENGTH_SHORT);
            toast.show();

            SharedPreferences.Editor editor = mSettingsPromt.edit();
            editor.putString(APP_PROMT_STATUS, "on");
            editor.apply();
        } else if (status.equals("off")) {

            //создаём и отображаем текстовое уведомление
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Отображение подсказок: выключено",
                    Toast.LENGTH_SHORT);
            toast.show();

            SharedPreferences.Editor editor = mSettingsPromt.edit();
            editor.putString(APP_PROMT_STATUS, "off");
            editor.apply();
        }

    }

    public void showProntMenu() {
        Button btnClose;
        btnClose = (Button) myDMenu.findViewById(R.id.bynClosePromtMenu);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDMenu.dismiss();
            }
        });

        myDMenu.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDMenu.show();
    }

    public void showProntCout() {
        Button btnClose;
        btnClose = (Button) myDCountMenu.findViewById(R.id.bynClosePromtCountMenu);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDCountMenu.dismiss();
            }
        });

        myDCountMenu.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDCountMenu.show();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void checkNameTable(String nameTable) {
        switch (nameTable) {
            case "donotregretaboutanything":
                Intent donotregretaboutanything = new Intent(this, MainText.class);
                donotregretaboutanything.putExtra("nameTable", "donotregretaboutanything");
                donotregretaboutanything.putExtra("nameButton", "Ни о чем не жалей");
                startActivity(donotregretaboutanything);
                break;
            case "followthedreams":
                Intent followthedreams = new Intent(this, MainText.class);
                followthedreams.putExtra("nameTable", "followthedreams");
                followthedreams.putExtra("nameButton", "Следуй за мечтой");
                startActivity(followthedreams);
                break;
            case "createyourfuture":
                Intent glav3 = new Intent(this, MainText.class);
                glav3.putExtra("nameTable", "createyourfuture");
                glav3.putExtra("nameButton", "Создавай свое будущее");
                startActivity(glav3);
                break;
            case "emotionalintellect":
                Intent glav4 = new Intent(this, MainText.class);
                glav4.putExtra("nameTable", "emotionalintellect");
                glav4.putExtra("nameButton", "Эмоциональный интеллект");
                startActivity(glav4);
                break;
            case "dotheopposite":
                Intent glav5 = new Intent(this, MainText.class);
                glav5.putExtra("nameTable", "dotheopposite");
                glav5.putExtra("nameButton", "Поступай наоборот");
                startActivity(glav5);
                break;
            case "gettoknowyourself":
                Intent glav6 = new Intent(this, MainText.class);
                glav6.putExtra("nameTable", "gettoknowyourself");
                glav6.putExtra("nameButton", "Узнавай себя");
                startActivity(glav6);
                break;
            case "donbeafraidtogetolder":
                Intent glav7 = new Intent(this, MainText.class);
                glav7.putExtra("nameTable", "donbeafraidtogetolder");
                glav7.putExtra("nameButton", "Не бойся становиться старше");
                startActivity(glav7);
                break;
            case "getoutofyourcomfortzone":
                Intent glav8 = new Intent(this, MainText.class);
                glav8.putExtra("nameTable", "getoutofyourcomfortzone");
                glav8.putExtra("nameButton", "Выйди из зоны комфорта");
                startActivity(glav8);
                break;
            case "appreciateyourlovedones":
                Intent glav9 = new Intent(this, MainText.class);
                glav9.putExtra("nameTable", "appreciateyourlovedones");
                glav9.putExtra("nameButton", "Цени своих близких");
                startActivity(glav9);
                break;
            case "yourselfasyouare":
                Intent glav10 = new Intent(this, MainText.class);
                glav10.putExtra("nameTable", "yourselfasyouare");
                glav10.putExtra("nameButton", "Принимайте себя таким, какой вы есть");
                startActivity(glav10);
                break;
            case "getenoughsleep":
                Intent glav11 = new Intent(this, MainText.class);
                glav11.putExtra("nameTable", "getenoughsleep");
                glav11.putExtra("nameButton", "Высыпайся");
                startActivity(glav11);
                break;
            case "begenerous":
                Intent glav12 = new Intent(this, MainText.class);
                glav12.putExtra("nameTable", "begenerous");
                glav12.putExtra("nameButton", "Будьте великодушным");
                startActivity(glav12);
                break;
            case "eatright":
                Intent glav13 = new Intent(this, MainText.class);
                glav13.putExtra("nameTable", "eatright");
                glav13.putExtra("nameButton", "Питайтесь правильно");
                startActivity(glav13);
                break;
        }
    }

    public void onClick(View v) {
        // по id определеяем кнопку, вызвавшую этот обработчик
        switch (v.getId()) {

            case R.id.btnbookmarks:
                Intent intentBookmarks = new Intent(this, bookmarks.class);
                startActivity(intentBookmarks);
                break;
            case R.id.TGButton:
                onChecke(tgButton.isChecked());
                break;
            case R.id.btcontinue:
                String nTable = mSettingsConti.getString(APP_CONTI_NAMETABLE, "");

                checkNameTable(nTable);
                break;

            case R.id.btnMenu:
                Intent intent = new Intent(this, MainContents.class);
                startActivity(intent);
                break;
            case R.id.bynClose:
                myDialogMenu.dismiss();
                break;
            case R.id.btnintroduction:
                Intent i = new Intent(this, IntroductionActivity.class);
                i.putExtra("hasCome", "yes");
                startActivity(i);
                break;
            case R.id.buttonUpdate:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.System.canWrite(getApplicationContext())) {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
                        a_builder.setMessage("Разрешить приложению доступ к изменению настроек?")
                                .setCancelable(false)
                                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent1 = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName()));
                                        startActivityForResult(intent1, 200);
                                    }
                                })
                                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Разрешение");
                        alert.show();
                    } else {
                        Toast.makeText(this, "Яркость изменена", Toast.LENGTH_SHORT).show();
                        int sysBackLightValue = (int) (mBackLightValue * 255);
                        android.provider.Settings.System.putInt(getContentResolver(),
                                android.provider.Settings.System.SCREEN_BRIGHTNESS,
                                sysBackLightValue);
                    }
                }
                break;

            case R.id.btnGlavOne:
                checkNameTable("donotregretaboutanything");
                break;

            case R.id.btnGlavTwo:
                checkNameTable("followthedreams");
                break;

            case R.id.btnGlavWhre:
                checkNameTable("createyourfuture");
                break;

            case R.id.btnGlavFo:
                checkNameTable("emotionalintellect");
                break;

            case R.id.btnGlavFive:
                checkNameTable("dotheopposite");
                break;

            case R.id.btnGlavSixe:
                checkNameTable("gettoknowyourself");
                break;

            case R.id.btnGlavSevan:
                checkNameTable("donbeafraidtogetolder");
                break;

            case R.id.btnGlavNait:
                checkNameTable("getoutofyourcomfortzone");
                break;

            case R.id.btnGlavAet:
                checkNameTable("appreciateyourlovedones");
                break;

            case R.id.btnGlavTean:
                checkNameTable("yourselfasyouare");
                break;

            case R.id.btnGlavOneS:
                checkNameTable("getenoughsleep");
                break;

            case R.id.btnGlav12:
                checkNameTable("begenerous");
                break;

            case R.id.btnGlav13:
                checkNameTable("eatright");
                break;

        }
    }
}
