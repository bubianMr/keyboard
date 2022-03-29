package com.project.bb.safetykeyboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * des:字母键盘
 * author:bubian
 * time:2022/3/23 13:53
 */
public class LetterAdapter extends BaseRecyclerView {

    private boolean isBig = false;

    /**
     * 是否选中大写
     * @param value
     */
    public void setIsBig(boolean value) {
        this.isBig = value;
        notifyItemChanged(20);
    }

    public LetterAdapter(Context context, List<?> list) {
        super(context, R.layout.item_keyboard_layout, list);
    }

    @Override
    public void onBindViewHolder( BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.findTxt(R.id.tv_des).setText(getData().get(position).toString());
        if (position == 20) {
            //大小写
            ViewGroup.LayoutParams layoutParams = holder.findImg(R.id.img_icon).getLayoutParams();
            layoutParams.width = 85;
            layoutParams.height = 65;
            holder.findImg(R.id.img_icon).setLayoutParams(layoutParams);
            holder.findImg(R.id.img_icon).setVisibility(View.VISIBLE);
            holder.findTxt(R.id.tv_des).setVisibility(View.GONE);
            holder.findImg(R.id.img_icon).setImageResource(R.mipmap.icon_case);
            holder.getItemView().setBackgroundResource(isBig ? R.drawable.shape_select_bg : R.drawable.shape_un_select_bg);
        } else if (position == 30) {
            holder.findImg(R.id.img_icon).setVisibility(View.VISIBLE);
            holder.findTxt(R.id.tv_des).setVisibility(View.GONE);
            holder.findImg(R.id.img_icon).setImageResource(R.mipmap.icon_small_space);

            ViewGroup.LayoutParams layoutParams = holder.findImg(R.id.img_icon).getLayoutParams();
            layoutParams.width = 100;
            layoutParams.height = 50;
            holder.findImg(R.id.img_icon).setLayoutParams(layoutParams);
        } else if (position == getData().size() - 1) {
            holder.findImg(R.id.img_icon).setVisibility(View.VISIBLE);
            holder.findTxt(R.id.tv_des).setVisibility(View.GONE);
            holder.findImg(R.id.img_icon).setImageResource(R.mipmap.icon_delete);

            ViewGroup.LayoutParams layoutParams = holder.findImg(R.id.img_icon).getLayoutParams();
            layoutParams.width = 60;
            layoutParams.height = 60;
            holder.findImg(R.id.img_icon).setLayoutParams(layoutParams);
        } else {
            holder.findImg(R.id.img_icon).setVisibility(View.GONE);
            holder.findTxt(R.id.tv_des).setVisibility(View.VISIBLE);
        }
    }


}
