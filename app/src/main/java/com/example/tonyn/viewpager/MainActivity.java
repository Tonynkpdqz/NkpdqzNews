package com.example.tonyn.viewpager;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.database.DatabaseUtilsCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MyOnScrollListener.OnloadDataListener {

    private View view1, view2, view3, view4, view5, view6, view7, view8, view9, view10;
    private CompatViewPager viewPager;
    private List<View> list;
    private List<String> titlelist;
    private List<ListView> ViewList;
    private List<News.ResultBean.DataBean> contentList;
    private List<News.ResultBean.DataBean> totalList;
    private ViewPagerAdapter pagerAdapter;
    private ListView list1, list2, list3, list4, list5, list6, list7, list8, list9, list10;
    private ListView listView;

    //private View footer;
    private int totalItemNumber;
    private int lastItem;
    private boolean isLoading = false;

    private NewMyAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout2, swipeRefreshLayout3, swipeRefreshLayout4,
            swipeRefreshLayout5, swipeRefreshLayout6, swipeRefreshLayout7, swipeRefreshLayout8,
            swipeRefreshLayout9, swipeRefreshLayout10, swipeRefreshLayout11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (CompatViewPager) findViewById(R.id.viewpager);
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        LayoutInflater inflater = getLayoutInflater();
        //footer = inflater.inflate(R.layout.foot_layout,null);
        view1 = inflater.inflate(R.layout.activity_main2, null);
        list1 = (ListView) view1.findViewById(R.id.list1);
        getData(list1, "top");
        view2 = inflater.inflate(R.layout.activity_main3, null);
        list2 = (ListView) view2.findViewById(R.id.list2);
        getData(list2, "shehui");
        view3 = inflater.inflate(R.layout.activity_main4, null);
        list3 = (ListView) view3.findViewById(R.id.list3);
        getData(list3, "guonei");
        view4 = inflater.inflate(R.layout.activity_main5, null);
        list4 = (ListView) view4.findViewById(R.id.list4);
        getData(list4, "guoji");
        view5 = inflater.inflate(R.layout.activity_main6, null);
        list5 = (ListView) view5.findViewById(R.id.list5);
        getData(list5, "yule");
        view6 = inflater.inflate(R.layout.activity_main7, null);
        list6 = (ListView) view6.findViewById(R.id.list6);
        getData(list6, "tiyu");
        view7 = inflater.inflate(R.layout.activity_main8, null);
        list7 = (ListView) view7.findViewById(R.id.list7);
        getData(list7, "junshi");
        view8 = inflater.inflate(R.layout.activity_main9, null);
        list8 = (ListView) view8.findViewById(R.id.list8);
        getData(list8, "keji");
        view9 = inflater.inflate(R.layout.activity_main10, null);
        list9 = (ListView) view9.findViewById(R.id.list9);
        getData(list9, "caijing");
        view10 = inflater.inflate(R.layout.activity_main11, null);
        list10 = (ListView) view10.findViewById(R.id.list10);
        initSwipe();
        getData(list10, "shishang");
        list = new ArrayList<>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        list.add(view4);
        list.add(view5);
        list.add(view6);
        list.add(view7);
        list.add(view8);
        list.add(view9);
        list.add(view10);

        titlelist = new ArrayList<>();
        titlelist.add("头条");
        titlelist.add("社会");
        titlelist.add("国内");
        titlelist.add("国际");
        titlelist.add("娱乐");
        titlelist.add("体育");
        titlelist.add("军事");
        titlelist.add("科技");
        titlelist.add("财经");
        titlelist.add("时尚");
        pagerAdapter = new ViewPagerAdapter(list, titlelist);
        viewPager.setAdapter(pagerAdapter);
        tabStrip.setViewPager(viewPager);
        tabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //swipeRefreshLayout2.setVisibility(View.GONE);
//        swipeRefreshLayout0.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Toast.makeText(MainActivity.this,"ceshi",Toast.LENGTH_SHORT).show();
//                swipeRefreshLayout0.setRefreshing(false);
//            }
//        });
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"ceshi",Toast.LENGTH_SHORT).show();
                Snackbar.make(v,"关于",Snackbar.LENGTH_SHORT)
                        .setAction("点击此处联系作者", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("18392171798"));
                                startActivity(intent);
                            }
                        });
                //刷新。
            }
        });*/

        ref();

//        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh2);

//        swipeRefreshLayout2 = (VpSwipeRefreshLayout) findViewById(R.id.swipe_refresh2);
//        swipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh(){
//
//           }
//       });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_item:
                this.finish();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void getData(final ListView l, String type) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        news_today news = retrofit.create(news_today.class);
        Call<News> call = news.getCall("17d98d4d09722c5cb9ad36395d24e2cf", type);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                News news1 = response.body();
                String reason = news1.getReason();
                Log.d("reason", reason);
                if (news1.getReason().equals("成功的返回")) {
                    Log.d("LOOKATTHISSSSS", "onResponse: " + "succeed");
                    News.ResultBean bean = news1.getResult();
                    String stat = bean.getStat();
                    Log.d("stat", bean.getStat());
                    final ArrayList<News.ResultBean.DataBean> list;// = new ArrayList<News.ResultBean.DataBean>();
                    list = (ArrayList<News.ResultBean.DataBean>) bean.getData();
                    Log.d("CESHICESHICESHICESHI", "onResponse: " + list.get(1).getTitle());
                    adapter = new NewMyAdapter(MainActivity.this, R.layout.news_layout, list);//注意这里！！！benlaisi  list
                    l.setAdapter(adapter);
                    l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            News.ResultBean.DataBean dataBean = list.get(position);
                            String url = dataBean.getUrl();
                            Intent intent = new Intent(MainActivity.this, Main21Activity.class);
                            intent.putExtra("url", url);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(MainActivity.this, "获取出了点问题哦，等会再试吧", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void initSwipe() {


        swipeRefreshLayout2 = (SwipeRefreshLayout) view1.findViewById(R.id.swipe_refresh2);
        swipeRefreshLayout3 = (SwipeRefreshLayout) view2.findViewById(R.id.swipe_refresh3);
        swipeRefreshLayout4 = (SwipeRefreshLayout) view3.findViewById(R.id.swipe_refresh4);
        swipeRefreshLayout5 = (SwipeRefreshLayout) view4.findViewById(R.id.swipe_refresh5);
        swipeRefreshLayout6 = (SwipeRefreshLayout) view5.findViewById(R.id.swipe_refresh6);
        swipeRefreshLayout7 = (SwipeRefreshLayout) view6.findViewById(R.id.swipe_refresh7);
        swipeRefreshLayout8 = (SwipeRefreshLayout) view7.findViewById(R.id.swipe_refresh8);
        swipeRefreshLayout9 = (SwipeRefreshLayout) view8.findViewById(R.id.swipe_refresh9);
        swipeRefreshLayout10 = (SwipeRefreshLayout) view9.findViewById(R.id.swipe_refresh10);
        swipeRefreshLayout11 = (SwipeRefreshLayout) view10.findViewById(R.id.swipe_refresh11);
    }

    public void ref() {
        swipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(list1, "top");
                Toast.makeText(MainActivity.this,"刷新中",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout2.setRefreshing(false);
            }
        });

        swipeRefreshLayout3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(list2, "shehui");
                Toast.makeText(MainActivity.this,"刷新中",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout3.setRefreshing(false);
            }
        });
        swipeRefreshLayout4.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(list3, "guonei");
                Toast.makeText(MainActivity.this,"刷新中",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout4.setRefreshing(false);
            }
        });
        swipeRefreshLayout5.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(list4, "guoji");
                Toast.makeText(MainActivity.this,"刷新中",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout5.setRefreshing(false);
            }
        });
        swipeRefreshLayout6.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(list5, "yule");
                Toast.makeText(MainActivity.this,"刷新中",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout6.setRefreshing(false);
            }
        });
        swipeRefreshLayout7.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(list6, "tiyu");
                Toast.makeText(MainActivity.this,"刷新中",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout7.setRefreshing(false);
            }
        });
        swipeRefreshLayout8.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(list7, "junshi");
                Toast.makeText(MainActivity.this,"刷新中",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout8.setRefreshing(false);
            }
        });
        swipeRefreshLayout9.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(list8, "keji");
                Toast.makeText(MainActivity.this,"刷新中",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout9.setRefreshing(false);
            }
        });
        swipeRefreshLayout10.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(list9, "caijing");
                Toast.makeText(MainActivity.this,"刷新中",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout10.setRefreshing(false);
            }
        });
        swipeRefreshLayout11.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(list10, "shishang");
                Toast.makeText(MainActivity.this,"刷新中",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout11.setRefreshing(false);
            }
        });
    }

    @Override
    public void onLoadData(List<News.ResultBean.DataBean> data) {

    }
}
