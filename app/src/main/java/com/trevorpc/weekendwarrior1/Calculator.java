package com.trevorpc.weekendwarrior1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


    }

    double numNow = 0;
    double numThen = 0;
    boolean decimal = false;
    boolean moreNum = false;
    String memory="";
    String longMemory="";
    boolean end =true;
    String temp = "nope";
    int decCount = 1;
    double raise;

    public void clear(View view)
    {
        numNow=0;
        numThen=0;

        TextView display = findViewById(R.id.tvDisplay);
        TextView record = findViewById(R.id.tvResult);

        display.setText(" ");
        record.setText(" ");

        memory="";
        longMemory="";
        decimal = false;
        moreNum=false;
        end=true;
        temp="nope";
        decCount=1;
    }

    public void exoMethod(){
        numThen = Math.pow(numNow,numThen);
    }


    public void additionMethod() {
        numThen = numNow + numThen;
        Log.d("numThen", "additionMethod: " + numThen);
    }

    public void subtractionMethod() {
        numThen = numThen - numNow;
        Log.d("numThen", "subtractionMethod: " + numThen);
    }

    public void divisionMethod() {
        numThen = numThen / numNow;
        Log.d("numThen", "divisionMethod: " + numThen);
    }

    public void multiplicationMethod() {
        numThen = numThen * numNow;
        Log.d("numThen", "multiplicationMethod: " + numThen);
    }

    public void numBtn(View view) {
        TextView display = findViewById(R.id.tvDisplay);
        TextView record = findViewById(R.id.tvResult);


        Button b = (Button) view;
        int input = Integer.parseInt(b.getText().toString());
        if (decimal) {
            raise = Math.pow(10, decCount);
            numNow = (numNow*raise+ input) /(raise);
            decCount++;
            Log.d("decCount", "numBtn: " + decCount);
        } else {
            numNow = numNow * 10 + input;
        }

        String displaying = numNow +"";
        display.setText(displaying);
        Log.d("numNow", "numBtn: " + numNow);
        memory = longMemory+displaying;
        record.setText(memory);
    }


    public void decBtn(View view) {
        decimal = true;
        Log.d("numNow", "numBtn: " + numNow);
    }

    public void opBtn(View view) {
        TextView record = findViewById(R.id.tvResult);
        TextView display = findViewById(R.id.tvDisplay);
        Button b = (Button) view;
        String input = b.getText().toString();
        longMemory=memory+input;
        memory="";
        display.setText(input);
        record.setText(longMemory);
        Log.d("moreNum", "opBtn: " + moreNum);
        if (moreNum) {
            end=false;
            answerBtn(view);
        } else {
            numThen = numNow;
        }

        moreNum = true;
        decimal = false;
        decCount = 1;
        numNow = 0;
        temp = input;
    }

    public void answerBtn(View view) {
        TextView display = findViewById(R.id.tvDisplay);
        TextView record = findViewById(R.id.tvResult);


        switch (temp) {
            case ("+"):
                additionMethod();
                break;
            case ("-"):
                subtractionMethod();
                break;
            case ("*"):
                multiplicationMethod();
                break;
            case ("/"):
                divisionMethod();
                break;
            case ("^"):
                exoMethod();
                break;
        }


        Log.d("num", "answerBtn: The Answer is " + numThen);
        String displaying = numThen + " ";
        display.setText(displaying);

        temp = "nope";
        moreNum = false;
        if(end)
        {
            numNow=numThen;
            longMemory="";
            memory=""+numThen;
            record.setText(memory);
            numThen=0;

        }
        end =true;
        decimal = false;
        decCount = 1;


    //TODO: Known bug when user hits *,* or /,/ results in zero if no numThen is entered.
        // default numThen 1 or 0.
    }
}
