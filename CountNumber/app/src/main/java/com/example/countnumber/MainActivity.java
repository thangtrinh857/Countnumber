package com.example.countnumber;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mainNum;
    Button btnDen, btnRed, btnLam, btnXanh, btnDem, btnReset;
    SharedPreferences pref;
    String mainColor;



    int count;
    private static final String black = "#FF000000";
    private static final String red = "#FF0000";
    private static final String blue = "#0000FF";
    private static final String green = "#339933";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainNum = findViewById(R.id.mainNum);
        btnDen = findViewById(R.id.btnDen);
        btnRed = findViewById(R.id.btnRed);
        btnLam = findViewById(R.id.btnLam);
        btnXanh = findViewById(R.id.btnXanh);
        btnDem = findViewById(R.id.btnDem);
        btnReset = findViewById(R.id.btnReset);

        btnDen.setOnClickListener(Orr);
        btnRed.setOnClickListener(Orr);
        btnLam.setOnClickListener(Orr);
        btnXanh.setOnClickListener(Orr);
        btnDem.setOnClickListener(Start);
        btnReset.setOnClickListener(Reset);

        mainNum.setGravity(Gravity.CENTER_VERTICAL);
        pref = getSharedPreferences("HELLO_SHARED_PREFS", MODE_PRIVATE);
        mainColor = pref.getString("color", "#FFFF00");
        count = pref.getInt("savedNum", 0);
        mainNum.setText(String.valueOf(count));
        mainNum.setTextColor(Color.parseColor(mainColor));

    }

    private View.OnClickListener Reset = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            reset();
        }
    };

    public final View.OnClickListener Start = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            count = pref.getInt("savedNum", 0);
            changeCount(count);
        }
    };

    public final View.OnClickListener Orr = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btnDen:
                {
                    changeColor(black);
                    break;
                }
                case R.id.btnRed:
                {
                    changeColor(red);
                    break;
                }
                case R.id.btnLam:
                {
                    changeColor(blue);
                    break;
                }
                case R.id.btnXanh:
                {
                    changeColor(green);
                    break;
                }
            }
        }
    };

    private void changeColor(String color)
    {
        mainNum.setTextColor(Color.parseColor(color));
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("color", color);
        editor.apply();
    }

    private void changeCount(int num)
    {
        SharedPreferences.Editor editor = pref.edit();
        num = num + 1;
        mainNum.setText(String.valueOf(num));
        editor.putInt("savedNum", num);
        editor.apply();
    }

    private void reset()
    {
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
        SharedPreferences.Editor init = pref.edit();
        init.putString("color", "#FFFF00");
        init.putInt("savedNum", 0);
        mainColor = pref.getString("color", "#FFFF00");
        count = pref.getInt("savedNum", 0);
        editor.apply();
        mainNum.setText(String.valueOf(count));
        mainNum.setTextColor(Color.parseColor(mainColor));


    }
}