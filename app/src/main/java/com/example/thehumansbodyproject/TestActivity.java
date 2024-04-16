package com.example.thehumansbodyproject;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TestActivity extends AppCompatActivity {
    int testNumber;
    TextView questionTxtView;
    TextView timerTxtView;
    ImageView questionImage;
    RadioGroup radioGrp;
    int seconds;
    boolean running;
    boolean move;
    boolean checkSecond;
    boolean question1;
    boolean question2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        testNumber=getIntent().getIntExtra("TEST",0);
        if (testNumber==1){
            question1=false;
            question2=true;
            running=true;
            startTest1();
        }
        else if (testNumber==2){
            question1=true;
            question2=false;
            running=true;
            startTest2();
        }
        else if (testNumber==3){
            question1=true;
            question2=true;
            running=true;
            startTest3();
        }


    }
    private void linkResources(){
        questionTxtView=(TextView) findViewById(R.id.questionTxtView);
        timerTxtView=(TextView) findViewById(R.id.timerTxtView);
        questionImage=(ImageView) findViewById(R.id.questionImage);
        radioGrp=(RadioGroup) findViewById(R.id.radioGrp);
    }

    private void startTest1() {
        linkResources();
        question1=false;
        question2=true;
        running=true;
        if (move){
            questionTxtView.setText("The amygdala is primarily associated with processing emotions and emotional memories ");
            questionImage.setImageResource(R.drawable.amygdala);
           move =false;
           checkSecond=true;
        }
        else{
        questionTxtView.setText("The cerebellum is responsible for regulating voluntary muscle movements ");
        questionImage.setImageResource(R.drawable.cerebellum);
        move=true;
            runTimer();}


    }
    private void startTest2() {
        linkResources();
        question1=true;
        question2=false;
        running=true;
        if (move){
            questionTxtView.setText("The femur is the longest bone in the human body.");
            questionImage.setImageResource(R.drawable.femur);
            move =false;
            checkSecond=true;
        }
        else{
            questionTxtView.setText("The skull consists of only one bone.");
            questionImage.setImageResource(R.drawable.skull);
            move=true;
            runTimer();}


    }
    private void startTest3() {
        linkResources();
        question1=true;
        question2=true;
        running=true;
        if (move){
            questionTxtView.setText("The heart is primarily composed of muscle tissue..");
            questionImage.setImageResource(R.drawable.heart);
            move =false;
            checkSecond=true;
        }
        else{
            questionTxtView.setText("The heart has four chambers.");
            questionImage.setImageResource(R.drawable.heart);
            move=true;
            runTimer();}


    }
    public void runTimer(){
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=seconds/60;
                String time = hours+":"+minutes+":"+seconds;
                timerTxtView.setText(time);
                if (running){
                    seconds++;
                }
                if (seconds==11){
                    running=false;
                    seconds=0;

                    checkAnswerTest1(question1,question2);


                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if (move){
                            running=true;
                        if (testNumber==1) startTest1();
                        if (testNumber==2) startTest2();
                        if (testNumber==3) startTest3();

                    }
                        else{
                        running=false;}


                }
                handler.postDelayed(this,1000);
            }
        });
    }
    public void checkAnswerTest1(boolean question1,boolean question2){
        boolean answer= radioGrp.getCheckedRadioButtonId() == R.id.trueBtn;
        if(checkSecond){
            if (answer==question2) {
                Toast.makeText(this, "Congrats! passed", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if (answer==question1) {
                Toast.makeText(this, "Congrats! passed", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void onPause() {
        super.onPause();
        running=false;
        seconds=0;
    }


}