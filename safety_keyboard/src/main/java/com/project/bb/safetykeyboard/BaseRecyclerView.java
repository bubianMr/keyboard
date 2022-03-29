package com.project.bb.safetykeyboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**RecyclerView.Adapter
 * des:自定义适配器
 * author:bubian
 * time:2022/3/23 14:06
 */
public class BaseRecyclerView extends RecyclerView.Adapter<BaseRecyclerView.BaseViewHolder> {

    private Context mContext;
    private int layout = -1;
    private List<?> mList;

    private KeyBoardOnItemClickListener mKeyBoardListener;

    public BaseRecyclerView(Context context, int layout, List<?> list) {
        this.mContext = context;
        this.layout = layout;
        this.mList = list;
    }

    public void onRefresh(List<?> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layout != -1) {
            View view = LayoutInflater.from(mContext).inflate(layout, parent, false);
            return new BaseViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v->{
            if (mKeyBoardListener!=null){
                mKeyBoardListener.onKeyBoardItemCLick(mList.get(position).toString(),position);
            }
        });
    }

    public void setOnKeyBoardClickListener(KeyBoardOnItemClickListener keyBoardClickListener) {
        this.mKeyBoardListener = keyBoardClickListener;
    }

    public List<?> getData(){
        return mList;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        private View itemView;

        public View getItemView(){
            return itemView;
        }

        public View findView(int id) {
            View textView = itemView.findViewById(id);
            return textView;
        }

        public TextView findTxt(int id) {
            TextView textView = itemView.findViewById(id);
            return textView;
        }

        public ImageView findImg(int id) {
            ImageView textView = itemView.findViewById(id);
            return textView;
        }

        public Button findButton(int id) {
            Button textView = itemView.findViewById(id);
            return textView;
        }

        public BaseViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }


}
