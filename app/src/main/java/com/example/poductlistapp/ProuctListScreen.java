package com.example.poductlistapp;

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
import com.example.poductlistapp.utilities.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class ProuctListScreen extends AppCompatActivity implements CustomAdapter.OnProductClickedListener {

    RecyclerView recyclerView ;
    List<Employee> productList = new ArrayList<>();

    public EditText searchBox;
    public Button searchButton;
    public  TextView termekList;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prouct_list_screen);


        /*recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);*/



        searchButton= (Button) findViewById(R.id.parse_button);
        searchBox = (EditText) findViewById(R.id.searchBar);
        termekList= (TextView) findViewById(R.id.termek_lista);

        mQueue= Volley.newRequestQueue(this);


                //GetDataFromApi();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });



    }



private void GetDataFromApi(){
    String url ="https://api.myjson.com/bins/1hcscm";


    /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {

            Gson gson = new Gson();
            Type type = new TypeToken<List<Employee>>(){}.getType();
            productList = gson.fromJson(response.toString(),type);

            CustomAdapter adapter = new CustomAdapter(ProuctListScreen.this,productList,ProuctListScreen.this);
            recyclerView.setAdapter(adapter);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(ProuctListScreen.this,error.getCause()+ "",Toast.LENGTH_LONG).show();

        }
    });

    Volley.newRequestQueue(this).add(request);*/

}
public void jsonParse(){
    String url ="https://api.myjson.com/bins/1hcscm";
         JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONArray jsonArray = response.getJSONArray("employees");

                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject id = jsonArray.getJSONObject(i);


                    String productName = id.getString("firstname");

                    int quantity = id.getInt("age");
                    String unitCost = id.getString("mail");

                    termekList.append("Termék neve: "+productName + "\n\n"  + "Mennyiség:"+String.valueOf(quantity) +"\n\n" +"Termék Kódja: " +unitCost+"\n\n");
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

    @Override
    public void onProductClicked(int position) {
        Toast.makeText(this,productList.get(position).getFirstname(),Toast.LENGTH_LONG).show();
    }




































    /*private void makeGithubSearchQuery() {
        String githubQuery = searchBox.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        termekList.setText(githubSearchUrl.toString());
        // COMPLETED (4) Create a new GithubQueryTask and call its execute method, passing in the url to query
        new GithubQueryTask().execute(githubSearchUrl);
    }

    // COMPLETED (1) Create a class called GithubQueryTask that extends AsyncTask<URL, Void, String>
    public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        // COMPLETED (3) Override onPostExecute to display the results in the TextView
        @Override
        protected void onPostExecute(String githubSearchResults) {
            if (githubSearchResults != null && !githubSearchResults.equals("")) {

                    termekList.setText(githubSearchResults);
                    Log.e("API response",githubSearchResults);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
