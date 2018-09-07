package com.example.jennydlee.transportmodeconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.CompoundButton;

public class TransportConverter extends AppCompatActivity {

    Button updateButton;
    EditText inputDistance;
    TextView walkText;
    RadioButton walkButton;
    TextView boostedBoardText;
    RadioButton boostedButton;
    TextView evolveText;
    RadioButton evolveButton;
    TextView hoverboardText;
    RadioButton hoverboardButton;
    TextView segwayText;
    RadioButton segwayButton;
    double walkSpeed = 3.1;
    double boostedSpeed = 18;
    double evolveSpeed = 26;
    double segwaySpeed = 12.5;
    double hoverboardSpeed = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_converter);

        updateButton = findViewById(R.id.updateButton);
        inputDistance = findViewById(R.id.editText7);
        walkText = findViewById(R.id.walkText);
        walkButton = findViewById(R.id.walkButton);
        boostedBoardText = findViewById(R.id.boostedBoardText);
        boostedButton = findViewById(R.id.boostedButton);
        evolveText = findViewById(R.id.evolveText);
        evolveButton = findViewById(R.id.evolveButton);
        hoverboardText = findViewById(R.id.hoverboardText);
        hoverboardButton = findViewById(R.id.hoverboardButton);
        segwayText = findViewById(R.id.segwayText);
        segwayButton = findViewById(R.id.segwayButton);
        RadioGroup group = new RadioGroup(this);
//        group.addView(walkButton);
//        group.addView(boostedButton);
//        group.addView(evolveButton);
//        group.addView(hoverboardButton);
//        group.addView(segwayButton);
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(inputDistance.getWindowToken(), 0);
        updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double distanceNum = 0;
                try {
                    distanceNum = Double.parseDouble(inputDistance.getText().toString());
                } catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                // Logic for mode travel times.
                String walkTime = calculateDistances(distanceNum, walkSpeed);
                walkText.setText(walkTime);
                String boostedTime = calculateDistances(distanceNum, boostedSpeed);
                boostedBoardText.setText(boostedTime);
                String evolveTime = calculateDistances(distanceNum, evolveSpeed);
                evolveText.setText(evolveTime);
                String hoverBoardTime = calculateDistances(distanceNum, hoverboardSpeed);
                hoverboardText.setText(hoverBoardTime);
                String segwayTime = calculateDistances(distanceNum, segwaySpeed);
                segwayText.setText(segwayTime);


            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        if (view.getId() != R.id.walkButton) {
            walkButton.setChecked(!checked);
        }
        if (view.getId() != R.id.boostedButton) {
            boostedButton.setChecked(!checked);
        }
        if (view.getId() != R.id.evolveButton) {
            evolveButton.setChecked(!checked);
        }
        if (view.getId() != R.id.hoverboardButton) {
            hoverboardButton.setChecked(!checked);
        }
        if (view.getId() != R.id.segwayButton) {
            segwayButton.setChecked(!checked);
        }

    }

    public String calculateDistances(double distanceNum, double speed) {
        Double ratio = distanceNum / speed;
        if (ratio > 1) {
            Double min = (ratio - Math.floor(ratio)) * 60;
            Double roundOffMin = Math.round(min * 100.0) / 100.0;
            Integer roundMin = (int) Math.round(roundOffMin);
            String hoursStr = new Double(ratio).toString();
            String hours = hoursStr.substring(0, hoursStr.indexOf('.'));
            if (hoursStr.equals("1")) {
                return hours + " hour \n" + roundMin.toString() + " min";
            } else {
                return hours + " hours \n" + roundMin.toString() + " min";
            }
        } else {
            Double min = ratio * 60;
            Integer roundOffMin = (int) Math.round(min);
            return roundOffMin.toString() + " minutes";
        }
    }

}
