package com.example.thehumansbodyproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Spinner interestSpnn;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView=findViewById(R.id.showNameTxtView);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPreferences.getString("NAME", "");
        textView.setText("Welcome "+username);



        populateSpinner();
        listView = (ListView) findViewById(R.id.lstView);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (interestSpnn.getSelectedItem().toString().equals("Skeletal System")) {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(MainActivity.this, BonesActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(MainActivity.this, JointsActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(MainActivity.this, SkeletonFunctionsActivity.class));
                            break;
                    }
                }
                if (interestSpnn.getSelectedItem().toString().equals("Muscular System")) {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(MainActivity.this, TypesOfMusclesActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(MainActivity.this, MuscleStructureActivity.class));
                            break;
                    }
                }
                if (interestSpnn.getSelectedItem().toString().equals("Circulatory System")) {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(MainActivity.this, HeartAtonomyActivity.class));
                                  break;

                    }
                }
                if (interestSpnn.getSelectedItem().toString().equals("Nervous System")) {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(MainActivity.this, BrainAtonomyActivity.class));
                            break;


                    }
                }
            }
        });

    }
    public void populateSpinner(){
        String[] interests = {"Skeletal System","Muscular System","Circulatory System","Nervous System"};
        interestSpnn=(Spinner) findViewById(R.id.interestSpnn);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,interests);
        interestSpnn.setAdapter(adapter);
    }


    public void searchbtnClicked(View view) {

        String value = interestSpnn.getSelectedItem().toString();
        String[] values;

        switch (value) {
            case "Skeletal System":
                values = new String[]{"Bones of the body", "Types of joints", "Functions of the skeleton"};
                break;
            case "Muscular System":
                values = new String[]{"Types of muscles", "Muscle structure and function"};
                break;
            case "Circulatory System":
                values = new String[]{"Heart anatomy and function"};
                break;
            case "Nervous System":
                values = new String[]{"Brain anatomy and functions"};
                break;
            default:
                // If no valid category is selected, display an empty list
                values = new String[0];
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);


        listView.setVisibility(View.VISIBLE);
    }

    public void testOneBtnClicked(View view) {
        Intent intent =new Intent(MainActivity.this,TestActivity.class);
        intent.putExtra("TEST",1);
        startActivity(intent);
    }

    public void testTwoBtnClicked(View view) {
        Intent intent =new Intent(MainActivity.this,TestActivity.class);
        intent.putExtra("TEST",2);
        startActivity(intent);
    }

    public void testThreeBtnClicked(View view) {
        Intent intent =new Intent(MainActivity.this,TestActivity.class);
        intent.putExtra("TEST",3);
        startActivity(intent);
    }
}