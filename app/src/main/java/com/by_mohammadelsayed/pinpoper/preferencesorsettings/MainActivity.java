package com.by_mohammadelsayed.pinpoper.preferencesorsettings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtView1;


    String txtView1Size;


    Typeface font1;
    Typeface font2;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Armstring", "onCreate 1");
        txtView1 = (TextView) findViewById(R.id.txtView1);
        Log.d("Armstring", "onCreate 2");
        font1 = Typeface.createFromAsset(getAssets(), "fonts/Chunkfive.otf");
        Log.d("Armstring", "onCreate 3");
        font2 = Typeface.createFromAsset(getAssets(), "fonts/FontleroyBrown.ttf");
        Log.d("Armstring", "onCreate 4");
        preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        Log.d("Armstring", "onCreate 5");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Armstring", "onResume 1");
        specifyFont();
        Log.d("Armstring", "onResume 2");
        specifyFontSize();
        Log.d("Armstring", "onResume 3");
    }

    private void specifyFont(){
        Log.d("Armstring", "specifyFont 3");
        boolean isFont1Checked = preferences.getBoolean("FIRST_FONT", false);
        Log.d("Armstring", "specifyFont 4");
        boolean isFont2Checked = preferences.getBoolean("SECOND_FONT", false);
        Log.d("Armstring", "specifyFont 5");
        if(isFont1Checked){
            Log.d("Armstring", "specifyFont 6");
            txtView1.setTypeface(font1);
            Log.d("Armstring", "specifyFont 7");
        }else if(isFont2Checked){
            Log.d("Armstring", "specifyFont 8");
            txtView1.setTypeface(font2);
            Log.d("Armstring", "specifyFont 9");
        }else{
            Log.d("Armstring", "specifyFont 10");
            txtView1.setTypeface(Typeface.DEFAULT);
            Log.d("Armstring", "specifyFont 11");
        }
    }

    private void specifyFontSize(){
        txtView1Size = preferences.getString("TEXT_SIZE","20");
        int txtSizeIntegerValue = Integer.parseInt(txtView1Size);
        txtView1.setTextSize(txtSizeIntegerValue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelectedId = item.getItemId();
        if(itemSelectedId == R.id.settings_id){
            Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
