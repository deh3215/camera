package com.example.a32150.a2017110902;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity implements MenuItemReceivedListener{

    MyAdapter adapter;
    ListView lv;
    Zoo z;
    SearchView searchView;
//    Set<String> city;
    Set<String>cityName;
    int flag=0;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        lv = (ListView) findViewById(R.id.listView);
        lv.setTextFilterEnabled(true);
        searchView = (SearchView)findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(false);// 關閉icon切換
        searchView.setFocusable(false); // 不要進畫面就跳出輸入鍵盤
        searchView.setQueryHint("輸入搜尋地址");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest("http://od.moi.gov.tw/api/v1/rest/datastore/A01010000C-000674-011",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        z = gson.fromJson(response, Zoo.class);
                        adapter = new MyAdapter(MainActivity.this, z.result.records);
                        adapter.zooInfo.remove(0);
                        adapter.notifyDataSetChanged();
                        lv.setAdapter(adapter);


//                        Iterator iterator = cityName.iterator();
//                        while(iterator.hasNext()) {
//                            Log.d("Data", iterator.next() + " ");
//                        }
                        flag=1;
                        ((MenuItemReceivedListener) context).onItemReceivedEvent();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ZOO", "Error:" + error.toString());
                    }
        });
        queue.add(request);
        queue.start();

        lv.setOnItemClickListener(listener);
        lv.setTextFilterEnabled(true);


    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String latitude = z.result.records.get(i).Latitude;
            String longitude = z.result.records.get(i).Longitude;
            String uri = String.format(Locale.TAIWAN, "geo:%f,%f", Double.valueOf(latitude), Double.valueOf(longitude));
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
        }
    };

    public void getItem(Menu menu)  {
        MenuItem logoutMI;
            for(String obj : cityName) {
                Log.d("DATA", obj);
                logoutMI = menu.add(Menu.NONE,1,1, obj);
                logoutMI.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                logoutMI.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
            }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action, menu);
        Log.d("flag", ""+flag);
        //if(flag != 0)   {
//            MenuItem logoutMI;
//            for(String obj : cityName) {
//                Log.d("DATA", obj);
//                logoutMI = menu.add(Menu.NONE,1,1, obj);
//                logoutMI.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//                logoutMI.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
//            }
        //}
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

//        MenuItem logoutMI;
//            for(String obj : cityName) {
//                Log.d("DATA", obj);
//                logoutMI = menu.add(Menu.NONE,1,1, obj);
//                logoutMI.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//                logoutMI.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
//            }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onItemReceivedEvent() {
        //get unique city name
        cityName = new TreeSet();
        for(int i=0; i<z.result.records.size();i++)
            cityName.add(z.result.records.get(i).CityName);
        for(String obj : cityName)
            Log.d("DATA", obj);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())    {
            case R.id.action:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}