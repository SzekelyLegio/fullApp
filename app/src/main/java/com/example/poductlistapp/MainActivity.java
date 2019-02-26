package com.example.poductlistapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ImageButton searchButton;
    public ImageButton pipeButton;
    public Button submitButton;
    private RequestQueue mQueue;
    public Context context;
    public Button cancelButton;
    public EditText codeInput;
    public TextView resultText;
    public ArrayList<String> codes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue= Volley.newRequestQueue(this);
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
        getIncomingIntent();
        fillList(codes);
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
                    for(int i= 0 ; i<codes.size(); i++) {
                        if (codes.contains(codeInput.getText().toString())) {
                            resultText.setVisibility(View.VISIBLE);
                            pipeButton.setVisibility(View.INVISIBLE);
                            codeInput.setVisibility(View.INVISIBLE);
                            searchButton.setVisibility(View.INVISIBLE);
                            submitButton.setVisibility(View.VISIBLE);

                        } else {
                            popUpErrorScreen();
                            break;

                        }
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
        Intent intent = new Intent(this, ProductList.class);
        startActivity(intent);
    }

    public void popUpErrorScreen(){
        Intent intent = new Intent(this, Pop.class);
        startActivity(intent);

    }

    public void getIncomingIntent(){
        Log.d("Sent code","Incomeing Data");
        if(getIntent().hasExtra("Code")){
            String code = getIntent().getStringExtra("Code");
            Log.d("Sent code","Found data");
            codeInput.setText(code);
            pipeButton.setEnabled(true);
            pipeButton.setVisibility(View.VISIBLE);

        }else {
            Log.d("Sent code","Data ERROR");
        }
    }

    public void fillList(final ArrayList<String> list){

        String url ="https://api.myjson.com/bins/1hcscm";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("employees");

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject id = jsonArray.getJSONObject(i);


                        String productName = id.getString("firstname");

                        String quantity = id.getString("age");
                        String unitCost = id.getString("mail");

                        list.add(unitCost);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }


}
