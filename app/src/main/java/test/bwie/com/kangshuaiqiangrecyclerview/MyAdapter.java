package test.bwie.com.kangshuaiqiangrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by 黑白 on 2017/9/7.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<String> arr;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public MyAdapter(Context context, ArrayList<String> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof MyHolder) {
            final MyHolder myHolder = (MyHolder) holder;
            myHolder.view.setText(arr.get(position));
            if (mOnItemClickLitener != null) {
                myHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickLitener.onItemClick(myHolder.view, position);
                    }
                });

                myHolder.view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        mOnItemClickLitener.onItemLongClick(myHolder.view, position);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView view;

        public MyHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.textview);
        }
    }

    public void addItem(int position) {
        arr.add(position, "添加新的数据");
        notifyItemInserted(position);
    }

    public void removo(int position) {

        notifyItemRemoved(position);
    }
}
