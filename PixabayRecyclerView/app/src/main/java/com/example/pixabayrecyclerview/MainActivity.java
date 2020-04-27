package com.example.pixabayrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

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
    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;
    EditText searchitem;
    String searchstring;
    String query="Red Flowers";

    Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

    }

    private void parseJSON(){

        String url = "https://pixabay.com/api/?key=15511356-dbed82430b2776f4b6fc0d73f&q="+query+"&image_type=photo&pretty=true";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hit = jsonArray.getJSONObject(i);

                        String creatorName = hit.getString("user");
                        String imageUrl = hit.getString("webformatURL");
                        int likeCount = hit.getInt("likes");

                        mExampleList.add(new ExampleItem(imageUrl, creatorName, likeCount));

                    }
                    mExampleAdapter=new ExampleAdapter(MainActivity.this,mExampleList);
                    mRecyclerView.setAdapter(mExampleAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    public void parseJSON(View view) {
        mExampleList.clear();
        searchitem = findViewById(R.id.eddittext);
        searchButton = findViewById(R.id.searchButton);
        query=searchitem.getText().toString();
        String url = "https://pixabay.com/api/?key=15511356-dbed82430b2776f4b6fc0d73f&q="+query+"&image_type=photo&pretty=true";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    if (jsonArray.length()==0)
                    {
                       String creatorName = "NO image Found";
                       String imageUrl="Hits:";
                        int likeCount=0;
                        mExampleList.add(new ExampleItem(imageUrl, creatorName, likeCount));

                    }else{
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hit = jsonArray.getJSONObject(i);

                        String creatorName = hit.getString("user");
                        String imageUrl = hit.getString("webformatURL");
                        int likeCount = hit.getInt("likes");

                        mExampleList.add(new ExampleItem(imageUrl, creatorName, likeCount));

                    }}
                    mExampleAdapter=new ExampleAdapter(MainActivity.this,mExampleList);
                    mRecyclerView.setAdapter(mExampleAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
}
