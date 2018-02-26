package opcv.test.com.firstpager;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.TRANSLATION_Y;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private SwipeRefreshLayout swipRefresh;
    private FirstAdapter mAdapter;
    private LinearLayout ll_title;
    private int mHeight;
    private int mHeight1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView contacts = (ImageView) findViewById(R.id.contacts);
        ImageView add = (ImageView) findViewById(R.id.add);
        ll_title = (LinearLayout) findViewById(R.id.ll_title);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        swipRefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        personList.clear();
        initData();
        ll_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "标题栏点击事件", Toast.LENGTH_SHORT).show();
            }
        });
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, Contact.contacts, Toast.LENGTH_SHORT).show();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, Contact.add, Toast.LENGTH_SHORT).show();
            }
        });

        final LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        mRecycler.setLayoutManager(manager);
        mAdapter = new FirstAdapter(MainActivity.this, personList);
        mRecycler.setAdapter(mAdapter);

        swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                personList.clear();
                p = 0;
                initData();
                swipRefresh.setRefreshing(false);
                mAdapter.notifyDataSetChanged();
            }
        });

        mRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) mRecycler.getLayoutManager();
                int count = layoutManager.getItemCount();
                int position = layoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && position ==count -1){
                    initData();
                    mAdapter.notifyDataSetChanged();
                }
            }

            //滚动监听RecyclerView的事件，进行标题的显示和隐藏
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mHeight1 = ll_title.getHeight();
                LinearLayoutManager layoutManager = (LinearLayoutManager) mRecycler.getLayoutManager();
                int position = layoutManager.findFirstVisibleItemPosition();
                //界面刚显示的时候显示 标题栏
                if (position == 0) {
                    if (!isShow) {
                        isShow = true;
                        showTitle();
                    }
                } else {
                    if (disy > 25 && isShow) {
                        isShow = false;
                        hideTitle();
                        disy = 0;
                    }
                    if (disy < -25 && !isShow) {
                        isShow = true;
                        showTitle();
                        disy = 0;
                    }
                }
                if ((isShow && dy > 0) || (!isShow && dy < 0)) {
                    disy += dy;
                }
            }
        });

    }

    float disy = 0;
    boolean isShow = false;

    private void showTitle() {
        ObjectAnimator transAnimator = ObjectAnimator.ofFloat(ll_title, TRANSLATION_Y, -mHeight1, 0);
        transAnimator.setDuration(1000);
        transAnimator.start();
//        if ()
    }

    private void hideTitle() {
        ObjectAnimator transAnimator = ObjectAnimator.ofFloat(ll_title, TRANSLATION_Y, 0, -mHeight1);
        transAnimator.setDuration(1000);
        transAnimator.start();
    }

    int p = 0;

    private void initData() {
        for (int i = p; i < p + 10; i++) {
            Person person = new Person("", "");
            person.setId(i + "");
            person.setName("我的名字是" + i);
            personList.add(person);

        }
        p = p + 10;
    }

    List<Person> personList = new ArrayList<>();
}
