package com.example.mostafa.apptask;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.mostafa.apptask.Database.myDataBase;
import com.example.mostafa.apptask.Database.myDataBaseContract;

public class ListViewWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListViewRemoteFactory(this.getApplicationContext());
    }
}

class ListViewRemoteFactory implements RemoteViewsService.RemoteViewsFactory {
   Context context;
   Cursor cursor;
   myDataBase myDatabase;
    public ListViewRemoteFactory(Context applicationContext) {
        this.context=applicationContext;
        myDatabase=new myDataBase(context);
        cursor=myDatabase.fetch_products();

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.lv_item);
        int idIndex = cursor.getColumnIndex(myDataBaseContract.first_table._ID);
        int NameIndex = cursor.getColumnIndex(myDataBaseContract.first_table.COLUMN_Name);
        int TempIndex = cursor.getColumnIndex(myDataBaseContract.first_table.COLUMN_temp);
        cursor.moveToPosition(i);
        String[] strings=cursor.getString(NameIndex).split(":");
        views.setTextViewText(R.id.textView6,strings[0]+","+strings[1]+cursor.getString(TempIndex));

        return views;

    }

    @Override
    public RemoteViews getLoadingView() {
      return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

