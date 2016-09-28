package com.runhuaoil.listviewtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RunHua on 2016/9/26.
 */

public class FruitAdapter extends ArrayAdapter {

    private int layoutResource;


    public FruitAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        layoutResource = resource;

    }

//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        Fruit fruit = (Fruit) getItem(position);
//        //获取该项对应的 Fruit 对象，用于获取 水果名称以及图片id。
//        View view = LayoutInflater.from(getContext()).inflate(layoutResource,null);
//        //给该项载入我们自己编辑的布局，一个ImageView加一个TextView;
//        ImageView imageView = (ImageView) view.findViewById(R.id.image_view_id);
//        TextView textView = (TextView) view.findViewById(R.id.text_view_id);
//
//        imageView.setImageResource(fruit.getImageID());
//        textView.setText(fruit.getName());
//        //设置控件的图片以及名称
//        return view;
//        //最后返回view 显示在ListView上
//    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Fruit fruit = (Fruit) getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView != null){
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();//将存储的对象取出来，取出时Object类型，需要转换

        }else{
            view =  LayoutInflater.from(getContext()).inflate(layoutResource,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.image_view_id);
            viewHolder.textView = (TextView) view.findViewById(R.id.text_view_id);
            view.setTag(viewHolder);//把 viewHolder存储进对应的view中
        }

        viewHolder.imageView.setImageResource(fruit.getImageID());
        viewHolder.textView.setText(fruit.getName());

        return view;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
