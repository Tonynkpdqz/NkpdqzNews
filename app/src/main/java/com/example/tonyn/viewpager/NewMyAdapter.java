package com.example.tonyn.viewpager;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by tonyn on 2017/4/2.
 */

public class NewMyAdapter extends ArrayAdapter<News.ResultBean.DataBean> {
    private int resourceId;
    public NewMyAdapter(Context context, int resource, List<News.ResultBean.DataBean> objects) {
        super(context, resource,objects);
        resourceId = resource;
    }

    class ViewHolder{
        ImageView imageView;
        TextView title;
        TextView author;
        TextView shuxing;
        TextView time;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News.ResultBean.DataBean dataBean = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.imageView2);
            viewHolder.title = (TextView) view.findViewById(R.id.newstext);
            viewHolder.time = (TextView) view.findViewById(R.id.time);
            viewHolder.author = (TextView) view.findViewById(R.id.author);
            viewHolder.shuxing = (TextView) view.findViewById(R.id.shuxing);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(dataBean.getTitle());
        viewHolder.time.setText(dataBean.getDate()+"/");
        viewHolder.author.setText(dataBean.getAuthor_name()+"/");
        viewHolder.shuxing.setText(dataBean.getCategory());
        Glide.with(getContext())
                .load(dataBean.getThumbnail_pic_s())
                .into(viewHolder.imageView);
//        viewHolder.title.setText(dataBean.getTitle());
//        viewHolder.time.setText(dataBean.getDate()+"/");
//        viewHolder.author.setText(dataBean.getAuthor_name()+"/");
//        viewHolder.shuxing.setText(dataBean.getCategory());
        return view;
    }
}
