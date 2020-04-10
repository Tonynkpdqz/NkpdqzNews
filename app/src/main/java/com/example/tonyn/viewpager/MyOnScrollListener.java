package com.example.tonyn.viewpager;

import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;

import java.util.List;

/**
 * Created by tonyn on 2017/5/13.
 */

public class MyOnScrollListener implements AbsListView.OnScrollListener {

    private int totalItemCount;
    private int lastItem;
    private boolean isLoading;
    private View footer;
    private OnloadDataListener listener;
    private List<News.ResultBean.DataBean> data;

    public MyOnScrollListener(View footer, List<News.ResultBean.DataBean> data) {
        this.footer = footer;
        this.data = data;
    }

    public void setOnLoadDataListener(OnloadDataListener listener) {
        this.listener = listener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (!isLoading&&lastItem==totalItemCount&&scrollState==SCROLL_STATE_IDLE){
            footer.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (listener != null){
                        loadMoreData();
                        listener.onLoadData(data);
                        loadComplete();
                    }
                }
            },2000);
        }
    }

    private void loadMoreData(){
        isLoading = true;
        News.ResultBean.DataBean bean = null;
        for(int i=0;i<10;i++){
            bean = new News.ResultBean.DataBean();
            data.add(bean);
        }
    }

    private void loadComplete() {
        isLoading = false;
        footer.setVisibility(View.GONE);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    public interface OnloadDataListener {
        void onLoadData(List<News.ResultBean.DataBean> data);
    }
}
