package com.example.a32150.a2017110902;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 32150 on 2017/11/9.
 */

public class MyAdapter extends BaseAdapter {

    ZooInfo[] zooInfo;
    Context context;

    public MyAdapter(Context context, ZooInfo[] zooInfo)  {
        this.context = context;
        this.zooInfo = zooInfo;
    }

    @Override
    public int getCount() {
        return zooInfo.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        LayoutInflater inflator = LayoutInflater.from(context);
        //if(view == null)    {
            view = inflator.inflate(R.layout.myitem, null);//解出Layout 解壓器,消耗資源
            holder = new ViewHolder();
            holder.loc = (TextView)view.findViewById(R.id.loc);
            holder.city = (TextView)view.findViewById(R.id.city);
            holder.img = (ImageView)view.findViewById(R.id.imageView);
            holder.speed = (TextView)view.findViewById(R.id.speed);
            view.setTag(holder);//要加,不然listview滑動會當掉
            if(i != 0) {//i=0為欄位名稱
                holder.loc.setText(zooInfo[i].Address);
                holder.city.setText(zooInfo[i].CityName);
                holder.speed.setText(zooInfo[i].limit);
                holder.speed.setTextColor(Color.parseColor("#CC0000"));
            }
            //Picasso.with(context).load(zooInfo[i].E_Pic_URL).into(holder.img);
        //}   else    {
        //    holder = (ViewHolder) view.getTag();
        //}
        return view;
    }

    static class ViewHolder
    {
        TextView loc;
        ImageView img;
        TextView city;
        TextView speed;
        //TextView city;
    }
}