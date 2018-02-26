package opcv.test.com.firstpager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyk on 2017/12/8.
 */

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.FirstHolder> {
    List<Person> data;
    Context mContext;
    private int HEAD = 1;
    private int ITEM = 2;

    public FirstAdapter(Context context, List<Person> list) {
        data = list;
        mContext = context;
    }

    @Override
    public FirstHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (viewType == HEAD) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.recycle_head, parent, false);
        } else {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.recycle_item, parent, false);
        }
        return new FirstHolder(itemView);

    }

    @Override
    public void onBindViewHolder(FirstHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == ITEM) {
            Person person = data.get(position - 1);
            holder.mText.setText(person.getName());
        }

    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEAD;
        } else {
            return ITEM;
        }
    }

    class FirstHolder extends RecyclerView.ViewHolder {

        private final TextView mText;

        public FirstHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.recycler_item_text);
        }
    }
}
