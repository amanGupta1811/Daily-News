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
    private NewsAdapter mAdapter2 = new NewsAdapter(this);
    private NewsAdapter mAdapter3 = new NewsAdapter(this);
    private NewsAdapter mAdapter4 = new NewsAdapter(this);
    private NewsAdapter mAdapter5 = new NewsAdapter(this);
    private NewsAdapter mAdapter6 = new NewsAdapter(this);
    private NewsAdapter mAdapter7 = new NewsAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
        RecyclerView recyclerView3 = findViewById(R.id.recyclerView3);
        RecyclerView recyclerView4 = findViewById(R.id.recyclerView4);
        RecyclerView recyclerView5 = findViewById(R.id.recyclerView5);
        RecyclerView recyclerView6 = findViewById(R.id.recyclerView6);
        RecyclerView recyclerView7 = findViewById(R.id.recyclerView7);


        national(recyclerView);
        international(recyclerView2);
        buisness(recyclerView3);
        science(recyclerView4);
        technology(recyclerView5);
        sports(recyclerView6);
        entertainment(recyclerView7);
    }

    public void national(RecyclerView re){
        re.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new NewsAdapter(this);
       // String politics = "politics";
        fetcheData();
        re.setAdapter(mAdapter);
}
    public void international(RecyclerView re){
        re.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter2 = new NewsAdapter(this);
        fetcheData2();
        re.setAdapter(mAdapter2);
    }

    public void buisness(RecyclerView re){
        re.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter3 = new NewsAdapter(this);
        fetcheData3();
        re.setAdapter(mAdapter3);
    }

    public void science(RecyclerView re){
        re.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter4 = new NewsAdapter(this);
        fetcheData4();
        re.setAdapter(mAdapter4);
    }

    public void technology(RecyclerView re){
        re.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter5 = new NewsAdapter(this);
        fetcheData5();
        re.setAdapter(mAdapter5);
    }

    public void sports(RecyclerView re){
        re.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter6 = new NewsAdapter(this);
        fetcheData6();
        re.setAdapter(mAdapter6);
    }

    public void entertainment(RecyclerView re){
        re.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter7 = new NewsAdapter(this);
        fetcheData7();
        re.setAdapter(mAdapter7);
    }

    private void fetcheData(){

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v2/top-headlines?country=in&category=politics&apiKey=dbb46abefe8642fb9033210ec6cd7093"
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
                           jsonObject.getString("urlToImage"));
                       newsArray.add(news);}

                   mAdapter.upDate(newsArray);
                } catch (JSONException e) {
                    e.printStackTrace();
              }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();}
       })
        {   public HashMap<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            headers.put("User-agent", "Mozilla/5.0");
            return headers;}

            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };
        requestQueue.add(jsonObjectRequest);
     }

    private void fetcheData2(){

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v2/top-headlines?country=us&category=&apiKey=dbb46abefe8642fb9033210ec6cd7093"
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
                                jsonObject.getString("urlToImage"));
                        newsArray.add(news);}

                    mAdapter2.upDate(newsArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();}
        })
        {   public HashMap<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            headers.put("User-agent", "Mozilla/5.0");
            return headers;}

            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    private void fetcheData3(){

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=dbb46abefe8642fb9033210ec6cd7093"
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
                                jsonObject.getString("urlToImage"));
                        newsArray.add(news);}

                    mAdapter3.upDate(newsArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();}
        })
        {   public HashMap<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            headers.put("User-agent", "Mozilla/5.0");
            return headers;}

            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    private void fetcheData4(){

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v2/top-headlines?country=in&category=science&apiKey=dbb46abefe8642fb9033210ec6cd7093"
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
                                jsonObject.getString("urlToImage"));
                        newsArray.add(news);}

                    mAdapter4.upDate(newsArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();}
        })
        {   public HashMap<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            headers.put("User-agent", "Mozilla/5.0");
            return headers;}

            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    private void fetcheData5(){

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=dbb46abefe8642fb9033210ec6cd7093"
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
                                jsonObject.getString("urlToImage"));
                        newsArray.add(news);}

                    mAdapter5.upDate(newsArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();}
        })
        {   public HashMap<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            headers.put("User-agent", "Mozilla/5.0");
            return headers;}

            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    private void fetcheData6(){

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
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
                                jsonObject.getString("urlToImage"));
                        newsArray.add(news);}

                    mAdapter6.upDate(newsArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();}
        })
        {   public HashMap<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            headers.put("User-agent", "Mozilla/5.0");
            return headers;}

            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    private void fetcheData7(){

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=dbb46abefe8642fb9033210ec6cd7093"
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
                                jsonObject.getString("urlToImage"));
                        newsArray.add(news);}

                    mAdapter7.upDate(newsArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();}
        })
        {   public HashMap<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            headers.put("User-agent", "Mozilla/5.0");
            return headers;}

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