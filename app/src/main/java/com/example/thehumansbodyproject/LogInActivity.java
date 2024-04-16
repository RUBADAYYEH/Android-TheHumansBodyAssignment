package com.example.thehumansbodyproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogInActivity extends AppCompatActivity {
    public static final String NAME = "NAME";
    public static final String FLAG = "FLAG";
    private boolean flag = false;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private SharedPreferences privateNamePrefs;
    private SharedPreferences.Editor editor2;
    private CheckBox chekBox;
    private EditText nameEditTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nameEditTxt=(EditText) findViewById(R.id.nameEditTxt);
        chekBox=findViewById(R.id.checkBox);
        setUpPrefs();
        checkPrefs();

    }
    public void setUpPrefs(){
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        privateNamePrefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor2 = privateNamePrefs.edit();
    }
    public void checkPrefs(){
        flag=prefs.getBoolean(FLAG,false);
        if (flag){
            String name=prefs.getString(NAME,"");

            nameEditTxt.setText(name);

        }
    }
  

    public void startExploringBtnClicked(View view) {

        if (nameEditTxt.getText().toString().isEmpty()){
            Toast.makeText(LogInActivity.this,"Please enter by your name",Toast.LENGTH_SHORT).show();
        }
        else{
        if (chekBox.isChecked()){
            if (!flag){
                editor.putString(NAME,nameEditTxt.getText().toString());
                editor.putBoolean(FLAG,true);
                editor.commit();

            }

        }
        editor2.putString("NAME",nameEditTxt.getText().toString());
        editor2.apply();
            Intent intent=new Intent(LogInActivity.this,MainActivity.class);
            intent.putExtra("NAME",nameEditTxt.getText().toString());
            startActivity(intent);



        }
    }
}