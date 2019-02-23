package com.example.poductlistapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public ImageButton searchButton;
    public ImageButton pipeButton;
    public Button submitButton;
    public Button cancelButton;
    public EditText codeInput;
    public TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = (ImageButton) findViewById(R.id.searchButton);
        pipeButton =(ImageButton) findViewById(R.id.okButton);
        submitButton=(Button) findViewById(R.id.submitButton);
        cancelButton=(Button) findViewById(R.id.cancelButton);
        codeInput=(EditText) findViewById(R.id.editTextID);
        resultText =(TextView) findViewById(R.id.success_text);
        submitButton.setEnabled(false);
        pipeButton.setEnabled(false);


        pipeButton.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.INVISIBLE);


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResendActivity();
            }
        });



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductActivity();
            }
        });


        pipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                if(codeInput.getText().toString().contains("456")){
                    resultText.setVisibility(View.VISIBLE);
                    pipeButton.setVisibility(View.INVISIBLE);
                    codeInput.setVisibility(View.INVISIBLE);
                    searchButton.setVisibility(View.INVISIBLE);
                    submitButton.setVisibility(View.VISIBLE);
                }else  {
                    popUpErrorScreen();
                }
            }
        });


        codeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.toString().equals("")) {
                    pipeButton.setEnabled(false);
                    submitButton.setEnabled(false);

                }
                else {
                    pipeButton.setEnabled(true);
                    submitButton.setEnabled(true);
                    pipeButton.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    public void ResendActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    public void ProductActivity(){
        Intent intent = new Intent(this, ProuctListScreen.class);
        startActivity(intent);
    }

    public void popUpErrorScreen(){
        Intent intent = new Intent(this, Pop.class);
        startActivity(intent);

    }


}
