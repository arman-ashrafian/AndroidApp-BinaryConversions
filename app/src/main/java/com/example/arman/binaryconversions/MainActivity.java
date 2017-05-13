package com.example.arman.binaryconversions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etDecimalNum = (EditText) findViewById(R.id.etDecimalNum);
        final TextView tvTextView = (TextView) findViewById(R.id.textView);
        final Button convertButton = (Button) findViewById(R.id.convertButton);

        convertButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                if(TextUtils.isEmpty(etDecimalNum.getText().toString())) {
                    tvTextView.setText("Powers of 2");
                    return;
                }

                tvTextView.setText("Powers of 2\n");

                String numStr = etDecimalNum.getText().toString();
                double num = Double.parseDouble(numStr);
                String output = getPowers(num);
                tvTextView.append(output);
            }
        });
    }

    public String getPowers(double dec) {
        ArrayList<Integer> powers = new ArrayList<>();
        String output = "";

        int power = getStartingPower(dec);

        double numCopy = dec;

        while(numCopy > 0) {
            if(numCopy - Math.pow(2, power) >= 0) {
                numCopy -= Math.pow(2, power);
                powers.add(power);
            }
            power -= 1;
        }

        for(int i = 0; i < powers.size(); i++) {
            output += "2^" + powers.get(i) + "\n";
        }

        return output;
    }

    public int getStartingPower(double dec) {
        int count = 0;
        while(dec > 1) {
            dec = dec * .5;
            count++;
        }
        return count;
    }
}
