package site.tomogames.tomogamesapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
        static String yoyaku_kakutei_name;
        static String select_item;
        static String yoyaku_item;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tenpoinfo = findViewById(R.id.button_tenpoinfo);
        ListView listView = findViewById(R.id.listview_ryouri);


        tenpoinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TenpoInfo.class);
                startActivity(intent);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView listView = (ListView) adapterView;

                select_item = (String) listView.getItemAtPosition(i);
                //Toast.makeText(getBaseContext(),i+"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this,TenpoYoyaku.class);
                intent.putExtra("item",select_item);
                startActivity(intent);
            }

        });

        Intent intent=getIntent();
        int key = intent.getIntExtra("key",0);
        String item = intent.getStringExtra("name");
        if (key==1){
            Toast.makeText(getBaseContext(),item+"の予約がキャンセルされました。",Toast.LENGTH_SHORT).show();
        }
        Intent intent2 = getIntent();
        int yoyaku = intent2.getIntExtra("yoyaku",0);
        String yoyaku_name = intent2.getStringExtra("yoyaku_name");
        if(yoyaku == 10){
            Toast.makeText(getBaseContext(),yoyaku_name+"の予約を致しました。",Toast.LENGTH_SHORT).show();
            yoyaku_kakutei_name=yoyaku_name;
        }

        String pref_name="CourseName";
        SharedPreferences sp = getSharedPreferences(pref_name, MODE_PRIVATE);
        String name = sp.getString("item_name","");






    }



}
