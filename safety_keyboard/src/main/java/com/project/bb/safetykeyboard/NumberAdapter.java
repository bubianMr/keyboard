package com.project.bb.safetykeyboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * des:数字键盘
 * author:bubian
 * time:2022/3/23 16:44
 */
public class NumberAdapter extends BaseRecyclerView{


    public NumberAdapter(Context context, List<?> list) {
        super(context, R.layout.item_keyboard_layout, list);
    }

    @Override
    public void onBindViewHolder( BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.findTxt(R.id.tv_des).setText(getData().get(position).toString());
        if (position == getData().size() - 1) {
            //大小写
            holder.findImg(R.id.img_icon).setVisibility(View.VISIBLE);
            holder.findTxt(R.id.tv_des).setVisibility(View.GONE);
            holder.findImg(R.id.img_icon).setImageResource(R.mipmap.icon_delete);

            ViewGroup.LayoutParams layoutParams = holder.findImg(R.id.img_icon).getLayoutParams();
            layoutParams.width = 60;
            layoutParams.height = 60;
            holder.findImg(R.id.img_icon).setLayoutParams(layoutParams);
        }else {
            holder.findImg(R.id.img_icon).setVisibility(View.GONE);
            holder.findTxt(R.id.tv_des).setVisibility(View.VISIBLE);
        }
    }
}
