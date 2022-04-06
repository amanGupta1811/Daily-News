package com.myapplication;

import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements ClickInterface {


    private NewsAdapter mAdapter = new NewsAdapter(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    // ArrayList<String> items  = ki();

     NewsAdapter adapter;

    fetcheData();

       // adapter = new NewsAdapter( items);

        mAdapter = new NewsAdapter(this);

        recyclerView.setAdapter(mAdapter);

    }


    private void fetcheData(){



        RequestQueue requestQueue;

        requestQueue = Volley.newRequestQueue(this);

       // JsonObjectRequest object;

        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.GET,
               "https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=dbb46abefe8642fb9033210ec6cd7093"
                , null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {
                   JSONArray jsonArray = response.getJSONArray("articles");
                   ArrayList newsArray = new ArrayList<News>();

                   for(int i = 0; i<jsonArray.length(); i++){

                      JSONObject jsonObject = jsonArray.getJSONObject(i);
                    News news;
                       news = new News(
                               jsonObject.getString("title"),
                           jsonObject.getString("author"),
                           jsonObject.getString("url"),
                           jsonObject.getString("urlToImage")
                       );

                       newsArray.add(news);

                   }

                   mAdapter.upDate(newsArray);

                } catch (JSONException e) {
                    e.printStackTrace();
              }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();

            }
       })

        {

            public HashMap<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            headers.put("User-agent", "Mozilla/5.0");
            return headers;
        }

            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }


        };


        requestQueue.add(jsonObjectRequest);

    }
    @Override
    public void onItemClick(News item) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.getUrl()));

    }
}