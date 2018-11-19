package com.example.mostafa.apptask;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.mostafa.apptask.Database.myDataBase;
import com.example.mostafa.apptask.classes.city;

import java.util.ArrayList;
import java.util.List;

public class OfflineData extends AppCompatActivity {

    private List<city> list=new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    myDataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_data);
        recyclerView=(RecyclerView)findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
       dataBase=new myDataBase(getApplicationContext());

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                int id = (int) viewHolder.itemView.getTag();
                String stringId =String.valueOf( id);
               dataBase.delete_city(stringId);
            fetch();

            }
        }).attachToRecyclerView(recyclerView);


        fetch();

    }

    public void fetch()
    {
        Cursor cursor=dataBase.fetch_products();
        adapter=new CityAdapter(null,"database",
                getApplicationContext(),null,null,cursor);
        recyclerView.setAdapter(adapter);
    }
}
