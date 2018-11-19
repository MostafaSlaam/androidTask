package com.example.mostafa.apptask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mostafa.apptask.Rest.ApiClient;
import com.example.mostafa.apptask.Rest.ApiInterface;
import com.example.mostafa.apptask.Rest.CityResponse;
import com.example.mostafa.apptask.classes.city;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CityAdapter.ListItemClickListener ,CityAdapter.imageClickListener {

    private TextView textView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public List<city> list=new ArrayList<>();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.offline)
        {
            Intent i =new Intent(MainActivity.this,OfflineData.class);
            startActivity(i);

        }
        else if(item.getItemId()==R.id.cu)
        {
            Intent i =new Intent(MainActivity.this,ContuctUs.class);
            startActivity(i);}
        return false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        textView=(TextView)findViewById(R.id.textView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
//        adapter = new CityAdapter(list, getApplicationContext(),this);


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<CityResponse> call=apiService.getCities();
        call.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                list=response.body().getCities();
                Log.d("cc", "Number of cities received: " + list.size());
                setData();

            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                Log.e("cc", t.toString());
                progressBar.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
            }
        });
    }
    public void setData()
    {
        adapter = new CityAdapter(list,"city", getApplicationContext(),this
                ,this,null);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void OnListItemClick(int index) {

        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        intent.putExtra("id",list.get(index).id);
        intent.putExtra("name",list.get(index).name+","+list.get(index).sys.country);
        startActivity(intent);


    }


    @Override
    public void OnImageClick(String date, String temp) {

    }
}
