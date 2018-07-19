package site.tomogames.tomogamesapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TenpoInfo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tenpo_info);


        String pref_name="CourseName";
        SharedPreferences sp = getSharedPreferences(pref_name, MODE_PRIVATE);
        String name = sp.getString("item_name","");

        TextView textView =findViewById(R.id.textView_予約情報);
        textView.setText(""+name);
    }
}
