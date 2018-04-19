package opcv.test.com.firstpager;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class AbsActivity extends Activity {
    private List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs);

        for (int i = 0; i <= 30; i++) {
            list.add("测试数据" + i);
        }

        ListView mListView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                AbsActivity.this, android.R.layout.simple_list_item_1, list);
        mListView.setAdapter(adapter);

        SildingFinishLayout mSildingFinishLayout = (SildingFinishLayout) findViewById(R.id.sildingFinishLayout);
        mSildingFinishLayout
                .setOnSildingFinishListener(new SildingFinishLayout.OnSildingFinishListener() {

                    @Override
                    public void onSildingFinish() {
                        AbsActivity.this.finish();
                    }
                });

        // touchView要设置到ListView上面
        mSildingFinishLayout.setTouchView(mListView);

        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

//                startActivity(new Intent(AbsActivity.this, NormalActivity.class));
               /* overridePendingTransition(R.anim.base_slide_right_in,
                        R.anim.base_slide_remain);*/
            }
        });
    }

    // Press the back button in mobile phone
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        overridePendingTransition(0, R.anim.base_slide_right_out);
    }

}
