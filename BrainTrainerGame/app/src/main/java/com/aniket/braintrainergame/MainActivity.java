package com.aniket.braintrainergame;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer>answers = new ArrayList<Integer>();
    int locationOfCorrectAnswers;
    int score=0;
    int numberOfQuestion=0;
    TextView resultTextView;
    TextView pointTextView;
    TextView sumTextView;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;

    public  void  playAgain(View view){
        score=0;
        numberOfQuestion=0;
        timerTextView.setText("30s");
        resultTextView.setText("");
        pointTextView.setText("0/0");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score : " +Integer.toString(score) + "/" + Integer.toString(numberOfQuestion) );
            }
        }.start();
    }

    public  void  generateQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+ " + " + Integer.toString(b));

        locationOfCorrectAnswers = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for(int i=0 ; i<4;i++){
            if(i == locationOfCorrectAnswers){
                answers.add(a + b);
            }else{
                incorrectAnswer = rand.nextInt(41);

                while(incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    public  void  chooseAnswer(View view) {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswers))){
            score++;
            resultTextView.setText("Correct!");
        }else{
            resultTextView.setText("Wrong!");
        }
        numberOfQuestion++;
        pointTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
        generateQuestion();
    }
    public void  start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
         sumTextView = (TextView)findViewById(R.id.sumTextView);
        button = (Button)findViewById(R.id.button);
         button2 = (Button)findViewById(R.id.button2);
         button3 = (Button)findViewById(R.id.button3);
         button4 = (Button)findViewById(R.id.button4);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointTextView =(TextView)findViewById(R.id.pointTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);





    }
}
