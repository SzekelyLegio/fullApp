package com.example.poductlistapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class Pop2 extends Activity {
    public TextView errorText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        errorText =(TextView) findViewById(R.id.errorText);

        errorText.setText("MINIMUM 3 KARAKTERT KELL MEGADNI");
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }
}
