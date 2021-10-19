package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class NewActivity extends AppCompatActivity {

    //declaring variables
    double result;
    double resultRounded;
    EditText rate; //interest rate
    EditText amount; //principle amount
    EditText years;
    TextView res;
    Button calculate;
    final double MONTH = 12;
    double r; //small r calculated below
    double n; //small n from formula of how to calculate mortgage
    double ratePercent; //divided by 100 to provide percentage value


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        //initializing these values to their corresponding view ID to obtain user input
        calculate = findViewById(R.id.calculate);
        rate = findViewById(R.id.rate);
        amount = findViewById(R.id.loan);
        years = findViewById(R.id.years);
        res = findViewById(R.id.result);

        //makes the result textView invisible until calculate button is clicked
        res.setVisibility(View.GONE);



        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //once button is clicked, gets the values inputted from user and converts it to String to be parsed to a double type
                String temp = rate.getText().toString();
                String temp2 = amount.getText().toString();
                String temp3 = years.getText().toString();

                //initializing variables that will hold the parsed numbers
                double rateValue=0;
                double amntValue = 0;
                double yearValue = 0;

                //if statement checks if input is empty or not
                if(!"".equals(temp) && !"".equals(temp2) && !"".equals(temp3)){

                    //converts the strings to numbers and initializes them to the respective variables
                    rateValue=Double.parseDouble(temp);
                    amntValue=Double.parseDouble(temp2);
                    yearValue=Double.parseDouble(temp3);

                }else{
                    //throws errors
                    res.setVisibility(View.VISIBLE);
                    res.setText("Error");
                }

                //divides rate by 100 for it's accurate percent value
                ratePercent = rateValue/100;


                r=ratePercent/MONTH; //calculates for r in accordance to mortgage formula for monthly installment
                n=yearValue * MONTH; //calculates for n in accordance to mortgage formula for monthly installment



                result = ((amntValue * r) * (Math.pow(1 + r, n))) / ((Math.pow(1 + r, n)) - 1); //mortgage formula for monthly installment
                DecimalFormat df = new DecimalFormat("#.##");//rounds answer to 2 decimal places
                df.format(result);


                res.setVisibility(View.VISIBLE); //now makes the TextView of the result visible now that button has been clicked
                res.setText("Your EMI is: $" +  df.format(result)); //displays results
            }
        });


    }


}