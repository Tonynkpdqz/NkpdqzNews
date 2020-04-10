package com.example.tonyn.viewpager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tonyn on 2017/4/2.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<News.ResultBean.DataBean> list;

    static class ViewHolder extends RecyclerView.ViewHolder{

        View view;
        ImageView imageView;
        TextView title;
        TextView author;
        TextView shuxing;
        TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.imageView2);
            title = (TextView) itemView.findViewById(R.id.newstext);
            time = (TextView) itemView.findViewById(R.id.time);
            author = (TextView) itemView.findViewById(R.id.author);
            shuxing = (TextView) itemView.findViewById(R.id.shuxing);
        }
    }

    public MyAdapter(List<News.ResultBean.DataBean> list){
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_layout,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
//        viewHolder.view.setOnClickListener(new MyItemClickLinster() {
//                                               @Override
//                                               public void myOnClick() {
//
//                                               }
//
                //new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = viewHolder.getAdapterPosition();
//                News.ResultBean.DataBean dataBean = list.get(position);
//                String url = dataBean.getUrl();
//                Intent intent = new Intent(parent.getContext(),Main4Activity.class);
//                intent.putExtra("url",url);
//                parent.getContext().startActivity(intent);
//            }
   //     });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News.ResultBean.DataBean bean = list.get(position);
        holder.imageView.setImageURI(Uri.parse(bean.getThumbnail_pic_s()));
        holder.title.setText(bean.getTitle());
        holder.time.setText(bean.getDate()+"/");
        holder.author.setText(bean.getAuthor_name()+"/");
        holder.shuxing.setText(bean.getCategory());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

}
