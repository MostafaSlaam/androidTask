package com.example.mostafa.apptask;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mostafa.apptask.Database.myDataBase;
import com.example.mostafa.apptask.Rest.ApiClient;
import com.example.mostafa.apptask.Rest.ApiInterface;
import com.example.mostafa.apptask.Rest.CityResponse;
import com.example.mostafa.apptask.classes.city;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity implements CityAdapter.imageClickListener {

    private TextView textView;
    private List<city> list=new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        String id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        textView=(TextView)findViewById(R.id.textView4);
        textView.setText(name);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<CityResponse> call = apiService.getCitiesForcast(id);
        call.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
            list=response.body().getCities();
                Log.d("vc", "Number of cities received: " + list.size());
                setData();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                Log.e("vc", t.toString());

            }
        });
    }


    public void setData() {
        adapter = new CityAdapter(list,"five_days", getApplicationContext(), null, this,
                null);
    }

    @Override
    public void OnImageClick(String date, String temp) {

        String newName=name+":"+date;
        myDataBase dataBase=new myDataBase(getApplicationContext());
        Cursor cursor=dataBase.check_data(newName);
        if(cursor.getCount()==0) {
            dataBase.add_city(newName, temp);
            Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Added before!", Toast.LENGTH_SHORT).show();
        }

    }
}
