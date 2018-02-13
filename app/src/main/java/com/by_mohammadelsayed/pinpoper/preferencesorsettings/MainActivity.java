package com.by_mohammadelsayed.pinpoper.preferencesorsettings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Armstring";

    private String txtView1Size;
    private Typeface font1;
    private Typeface font2;
    private Typeface font3;
    private RecyclerView recyclerView;
    private List<String> texts;

    private SharedPreferences preferences;
    private RecyclerAdapter adapter;

    private Typeface font;
    private int textSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        font1 = Typeface.createFromAsset(getAssets(), "fonts/Chunkfive.otf");
        font2 = Typeface.createFromAsset(getAssets(), "fonts/FontleroyBrown.ttf");
        font3 = Typeface.createFromAsset(getAssets(), "fonts/Wonderbar Demo.otf");
        preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        texts = new LinkedList<>();
        for(int i = 0; i < 50; i++){
            String str = "text: " + (i+1);
            texts.add(str);
        }
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewId);
        adapter = new RecyclerAdapter(texts, this, font2, 20);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        specifyFont();
        specifyFontSize();

    }

    private void specifyFont(){
        String fontName = preferences.getString("TEXT_FONT", "Chunkfive.otf");
        Log.d(TAG, "specifyFont: " + fontName);
        if(fontName.matches("Chunkfive.otf")){
            Log.d(TAG, "specifyFont: 1");
            font = font1;
            adapter = new RecyclerAdapter(texts, MainActivity.this, font, textSize);
            recyclerView.setAdapter(adapter);
        }else if(fontName.matches("FontleroyBrown.ttf")){
            Log.d(TAG, "specifyFont: 2");
            font = font2;
            adapter = new RecyclerAdapter(texts, MainActivity.this, font, textSize);
            recyclerView.setAdapter(adapter);
        }else {
            Log.d(TAG, "specifyFont: 3");
            font = font3;
            adapter = new RecyclerAdapter(texts, MainActivity.this, font, textSize);
            recyclerView.setAdapter(adapter);
        }
    }

    private void specifyFontSize(){
        txtView1Size = preferences.getString("TEXT_SIZE","20");
        textSize = Integer.parseInt(txtView1Size);
        adapter = new RecyclerAdapter(texts, MainActivity.this, font, textSize);
        recyclerView.setAdapter(adapter);
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
