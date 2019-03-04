package com.example.poductlistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RequestQueue mQueue;
    public Context context;
    public String resultText;
    public EditText searchBar;
    public  ArrayList<String> productTitle = new ArrayList<>();
    public final ArrayList<ExampleItem> exampleList = new ArrayList<>();
    public Button ListButton;
    public Button resendButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_list);
        mQueue= Volley.newRequestQueue(this);

        ListButton =(Button) findViewById(R.id.search);
        searchBar = (EditText)findViewById(R.id.search_text);
        resendButton=(Button) findViewById(R.id.new_produc_button);
        getIncomingIntent();



        resendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewProduct();
            }
        });
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchBar, InputMethodManager.SHOW_IMPLICIT);
        ListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    if(searchBar.getText().toString().toLowerCase().length() < 3 ){
                        popUpErrorScreen();

                    }else {
                        jsonParse(exampleList,searchBar);


                    }
            }
        });





    }


    public void jsonParse(final ArrayList<ExampleItem> exampleList,final EditText text){
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
                       if(text.getText().toString().toLowerCase().length()>= 3 && productName.toLowerCase().contains(text.getText().toString().toLowerCase()) || text.getText().toString().toLowerCase().length()>= 3 && unitCost.toLowerCase().contains(text.getText().toString().toLowerCase())) {
                           exampleList.add(new ExampleItem(R.drawable.ic_launcher_background, productName, "DB:" + quantity, unitCost));

                       }
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

        mAdapter = new ExampleAdapter(exampleList,this);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mQueue.add(request);
    }
    public void popUpErrorScreen(){
        Intent intent = new Intent(this, Pop.class);
        startActivity(intent);


    }
    public void NewProduct(){
        Intent intent = new Intent(this, ProductList.class);
        startActivity(intent);


    }

    public void getIncomingIntent(){
        Log.d("Sent code","Incoming Data");
        if(getIntent().hasExtra("Code")){
            String code = getIntent().getStringExtra("Code");
            Log.d("Sent code","Found data");
            searchBar.setText(code);


        }else {
            Log.d("Sent code","Data ERROR");
        }
    }



}
