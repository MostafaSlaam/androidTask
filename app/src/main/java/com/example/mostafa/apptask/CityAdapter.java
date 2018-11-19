package com.example.mostafa.apptask;

import android.content.Context;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mostafa.apptask.Database.myDataBaseContract;
import com.example.mostafa.apptask.classes.city;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {

    private List<city> cities = new ArrayList<city>();
    private Context context;
    private String type;
    private Cursor cursor;


    public CityAdapter(List<city> c,String type, Context context,
                       ListItemClickListener mOnClickListener ,imageClickListener imageClickListener,
                       Cursor cursor
    ) {
        this.context = context;
        this.type=type;
        this.cities = c;
        this.cursor=cursor;
        this.mOnClickListener = mOnClickListener;
    this.imageClickListener= imageClickListener;
    }


    public interface  imageClickListener
    {
        void OnImageClick(String date,String temp);
    }
    final private imageClickListener imageClickListener;
    public interface  ListItemClickListener
    {
        void OnListItemClick(int index);
    }
    final private ListItemClickListener mOnClickListener;





    @Override
    public CityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CityAdapter.MyViewHolder holder, int position) {
        if(type.equals("database"))
        {
            int idIndex = cursor.getColumnIndex(myDataBaseContract.first_table._ID);
            int NameIndex = cursor.getColumnIndex(myDataBaseContract.first_table.COLUMN_Name);
            int TempIndex = cursor.getColumnIndex(myDataBaseContract.first_table.COLUMN_temp);
            cursor.moveToPosition(position);
            final int id = cursor.getInt(idIndex);
            holder.itemView.setTag(id);
            String[] strings=cursor.getString(NameIndex).split(":");
            holder.textView1.setText(strings[0]);
            holder.textView.setVisibility(View.VISIBLE);
            holder.textView.setText(strings[1]);
            holder.textView2.setText(cursor.getString(TempIndex));
            holder.imageView1.setVisibility(View.INVISIBLE);
        }
        else{
            if (type.equals("city")) {
                holder.textView1.setText(cities.get(position).name + " ," +
                        cities.get(position).sys.country);

            } else {
                holder.imageButton.setVisibility(View.VISIBLE);
                holder.textView1.setText(cities.get(position).date);

            }
            holder.textView2.setText("temperature : from " +
                    cities.get(position).temp.temp_min + "℃ to " +
                    cities.get(position).temp.temp_max + "℃"
            );

            Glide.with(context).load("http://openweathermap.org/img/w/" + cities.get(position).weatherList.get(0).icon
                    + ".png").into(holder.imageView1);
        }
    }

    @Override
    public int getItemCount() {
        if(type.equals("database")) {
            return cursor.getCount();
        }
        else
        {
            return cities.size();
        }

    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView1;
        TextView textView1;
        TextView textView2;
        ImageButton imageButton;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageButton=(ImageButton)itemView.findViewById(R.id.imageButton);
            imageButton.setOnClickListener(this);
            imageView1 = (ImageView) itemView.findViewById(R.id.imageView);
            textView1 = (TextView) itemView.findViewById(R.id.textView2);
            textView2 = (TextView) itemView.findViewById(R.id.textView3);
            itemView.setOnClickListener(this);
            textView=(TextView)itemView.findViewById(R.id.textView5);
        }

        @Override
        public void onClick(View view) {
            if (cities.size()==5) {
                int ClickedPosition = getAdapterPosition();
                mOnClickListener.OnListItemClick(ClickedPosition);
            } else if(view.getId()==R.id.imageButton) {

                imageClickListener.OnImageClick(textView1.getText().toString(),textView2.getText().toString());

            }
        }

    }

}
