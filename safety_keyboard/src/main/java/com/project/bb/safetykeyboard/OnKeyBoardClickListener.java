package com.project.bb.safetykeyboard;

/**
 * des:整个键盘的监听事件
 * author:bubian
 * time:2022/3/24 15:29
 */
public interface OnKeyBoardClickListener {
    /**
     * 普通按键点击事件
     * @param value 单次点击的值
     * @param inputValue 已输入的值
     */
    void onKeyBoardClick(String value,String inputValue);

    /**
     * 删除按钮点击事件
     */
    void onDeleteClick();
}
