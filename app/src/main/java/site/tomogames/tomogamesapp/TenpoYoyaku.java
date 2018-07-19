package site.tomogames.tomogamesapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TenpoYoyaku extends AppCompatActivity{
    public static String item;
    public static String course_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tenpo_yoyaku);

        final Intent intent = getIntent();
        item = intent.getStringExtra("item");

        TextView textView = findViewById(R.id.textView_コース名);
        textView.setText(item);

        Button back = findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TenpoYoyaku.this,MainActivity.class);
                Toast.makeText(getBaseContext(),"予約がキャンセルされました。",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        Button back2 = findViewById(R.id.button_yoyakucancel);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TenpoYoyaku.this,MainActivity.class);
                intent.putExtra("key",1);
                intent.putExtra("name",item);
                startActivity(intent);
            }
        });

        Button yoyaku_ok = findViewById(R.id.button_yoyakuOK);
        yoyaku_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = findViewById(R.id.checkBox_飲み放題);
                if (!checkBox.isChecked()){
                    final AlertDialog.Builder alertDialog=new AlertDialog.Builder(TenpoYoyaku.this);

                    // ダイアログの設定
                    alertDialog.setTitle("予約確認画面");      //タイトル設定
                    alertDialog.setMessage("本当によろしいですか？");  //内容(メッセージ)設定

                    // OK(肯定的な)ボタンの設定
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // OKボタン押下時の処理
                            Intent intent = new Intent(TenpoYoyaku.this,MainActivity.class);
                            intent.putExtra("yoyaku_name",item);
                            intent.putExtra("yoyaku",10);
                            String pref_name="CourseName";
                            SharedPreferences sp = getSharedPreferences(pref_name, MODE_PRIVATE);
                            SharedPreferences.Editor e = sp.edit();
                            e.putString("item_name", item);
                            e.commit();
                            startActivity(intent);
                        }
                    });

                    // NG(否定的な)ボタンの設定
                    alertDialog.setNegativeButton("予約ページへ戻る", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // NGボタン押下時の処理
                            dialog.cancel();
                        }
                    });
                    alertDialog.show();

                }else{
                    String pref_name="CourseName";
                    SharedPreferences sp = getSharedPreferences(pref_name, MODE_PRIVATE);
                    SharedPreferences.Editor e = sp.edit();
                    e.putString("item_name", item);
                    e.commit();

                    Intent intent1 = new Intent(TenpoYoyaku.this,MainActivity.class);
                    startActivity(intent1);
                }
            }
        });



    }


}
