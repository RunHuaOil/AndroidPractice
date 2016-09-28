package com.runhuaoil.locationchat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RunHua on 2016/9/26.
 */

public class MsgAdapter extends ArrayAdapter<Msg> {

    private int resource;

    public MsgAdapter(Context context, int resource, List<Msg> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }




    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView != null){
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();


        }else{
            view = LayoutInflater.from(getContext()).inflate(resource,null);

            viewHolder = new ViewHolder();
            viewHolder.linearLayout_left = (LinearLayout) view.findViewById(R.id.linear_left);
            viewHolder.linearLayout_right = (LinearLayout) view.findViewById(R.id.linear_right);
            viewHolder.textView_left = (TextView) view.findViewById(R.id.left_msg);
            viewHolder.textView_right = (TextView) view.findViewById(R.id.right_msg);

            view.setTag(viewHolder);
        }


        int msg_type;
        msg_type = msg.getType();
        if (msg_type == Msg.MSG_SEND){


            viewHolder.linearLayout_left.setVisibility(View.GONE);
            viewHolder.linearLayout_right.setVisibility(View.VISIBLE);

            viewHolder.textView_right.setText(msg.getContent());

        }else if(msg_type == Msg.MSG_RECEIVE){

            viewHolder.linearLayout_left.setVisibility(View.VISIBLE);
            viewHolder.linearLayout_right.setVisibility(View.GONE);

            viewHolder.textView_left.setText(msg.getContent());

        }





        return view;
    }

    class ViewHolder{

         LinearLayout linearLayout_left;
         LinearLayout linearLayout_right;

        TextView textView_left;
        TextView textView_right;
    }


}
