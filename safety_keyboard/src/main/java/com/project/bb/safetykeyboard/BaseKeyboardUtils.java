package com.project.bb.safetykeyboard;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * des:自定义键盘工具类,该功能仿造中国建设银行APP安全键盘实现
 * author:bubian
 * time:2022/3/23 10:33
 */
public class BaseKeyboardUtils {

    private Context mContext;

    private PopupWindow mPopupWindow;

    private TextView mFunction1, mFunction2, mTitle, mDefine, mContent, mInputLine;
    private RecyclerView mRecyclerKey;
    private ConstraintLayout mConBgView;

    private static BaseKeyboardUtils mBaseKeyboardUtils;

    /**
     * 是否仅可输入数字模式
     */
    private boolean isOnlyNumber = false;
    /**
     * 默认最长输入
     */
    private int inputMaxLength = 20;

    /**
     * 自定义键盘输入的内容
     */
    private String inputMsg = "";

    /**
     * 是否为大写字母
     */
    private boolean isBig = false;

    private OnKeyBoardClickListener mOnClickListener;
    private OnDefineClickListener mOnDefineClickListener;

    /**
     * 键盘状态
     * 默认=0
     * 0=数字+字母键盘
     * 1=符号键盘
     * 2=纯数字键盘
     */
    private int keyBoardStatus = 0;

    public BaseKeyboardUtils(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_keyboard, null);
        mFunction1 = view.findViewById(R.id.tv_function1);
        mFunction2 = view.findViewById(R.id.tv_function2);
        mTitle = view.findViewById(R.id.tv_title);
        mContent = view.findViewById(R.id.tv_content);
        mDefine = view.findViewById(R.id.tv_define);
        mRecyclerKey = view.findViewById(R.id.recycler_key);
        mInputLine = view.findViewById(R.id.view_input_line);
        mConBgView = view.findViewById(R.id.con_bg_view);

        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        PopUnRecordProxy.instance().unRecord(mPopupWindow);

        changeKeyboard(0);

        mFunction1.setOnClickListener(v -> {
            switch (keyBoardStatus) {
                case 0:
                case 2:
                    changeKeyboard(1);
                    mFunction1.setText("123");
                    mFunction2.setText("Abc");
                    break;
                case 1:
                    changeKeyboard(2);
                    mFunction1.setText("符");
                    mFunction2.setText("Abc");
                    break;
            }
        });

        mFunction2.setOnClickListener(v -> {
            switch (keyBoardStatus) {
                case 0:
                    changeKeyboard(2);
                    mFunction1.setText("符");
                    mFunction2.setText("Abc");
                    break;
                case 1:
                case 2:
                    changeKeyboard(0);
                    mFunction1.setText("符");
                    mFunction2.setText("123");
                    break;
            }
        });

        mDefine.setOnClickListener(v -> {
            if (mOnDefineClickListener != null) {
                mOnDefineClickListener.onDefineClick(inputMsg);
            }
            mPopupWindow.dismiss();
        });

        mPopupWindow.setOnDismissListener(() -> {
            mContext = null;
            mBaseKeyboardUtils = null;
            inputMsg = "";
            isBig = false;
            isOnlyNumber = false;
            inputMaxLength = 20;
            mOnClickListener = null;
        });

    }


    public synchronized static BaseKeyboardUtils getInstance(Context context) {
        if (mBaseKeyboardUtils == null) {
            mBaseKeyboardUtils = new BaseKeyboardUtils(context);
        }
        return mBaseKeyboardUtils;
    }

    /**
     * 设置点击外部是否可隐藏键盘
     * true=点击外部隐藏
     * false=点击外部不隐藏
     *
     * @param focusable
     * @return
     */
    public BaseKeyboardUtils setFocusable(boolean focusable) {
        if (mPopupWindow != null) {
            mPopupWindow.setFocusable(focusable);
        }
        return mBaseKeyboardUtils;
    }

    /**
     * 设置只允许点击数字
     *
     * @return
     */
    public BaseKeyboardUtils setOnlyNumber() {
        isOnlyNumber = true;
        keyBoardStatus = 2;
        changeKeyboard(keyBoardStatus);
        mFunction1.setClickable(false);
        mFunction1.setEnabled(false);

        mFunction2.setClickable(false);
        mFunction2.setEnabled(false);
        return mBaseKeyboardUtils;
    }

    public BaseKeyboardUtils setInputMsg(String str) {
        if (str != null) {
            inputMsg = str;
        }
        return mBaseKeyboardUtils;
    }

    /**
     * 显示键盘
     *
     * @param view 依附控件
     */
    public void showKeyboard(View view) {
        if (mPopupWindow.isShowing()) {
//            mPopupWindow.dismiss();
            return;
        }
        //禁止截屏
        finActivity(mContext).getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 关闭键盘
     */
    public void dismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * 键盘title（例：XXXX安全键盘）
     *
     * @param title
     * @return
     */
    public BaseKeyboardUtils setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            mTitle.setText(title);
        }
        return mBaseKeyboardUtils;
    }

    /**
     * 输入框提示
     *
     * @param hint
     * @return
     */
    public BaseKeyboardUtils setInputHint(String hint) {
        if (hint != null && !hint.isEmpty()) {
            mContent.setHint(hint);
        }
        return mBaseKeyboardUtils;
    }

    /**
     * 可输入最长长度
     *
     * @param maxLength
     * @return
     */
    public BaseKeyboardUtils setInputMaxLength(int maxLength) {
        if (maxLength > 0) {
            inputMaxLength = maxLength;
        }
        return mBaseKeyboardUtils;
    }

    /**
     * 设置键盘监听事件
     *
     * @param onKeyBoardClickListener
     * @return
     */
    public BaseKeyboardUtils setKeyboardClickListener(OnKeyBoardClickListener onKeyBoardClickListener) {
        this.mOnClickListener = onKeyBoardClickListener;
        return mBaseKeyboardUtils;
    }

    /**
     * 设置确认监听事件
     *
     * @param onDefineClickListener
     * @return
     */
    public BaseKeyboardUtils setOnDefineClickListener(OnDefineClickListener onDefineClickListener) {
        this.mOnDefineClickListener = onDefineClickListener;
        return mBaseKeyboardUtils;
    }

    /**
     * 是否有输入框显示
     *
     * @param isHaveEdit
     * @return
     */
    public BaseKeyboardUtils setIsHaveInputBox(boolean isHaveEdit) {
        mInputLine.setVisibility(isHaveEdit ? View.VISIBLE : View.GONE);
        mContent.setVisibility(isHaveEdit ? View.VISIBLE : View.GONE);
        mConBgView.setBackgroundColor(Color.parseColor(isHaveEdit ? "#FFFFFF" : "#00000000"));
        return mBaseKeyboardUtils;
    }

    public void cleanInput() {
        inputMsg = "";
    }


    /**
     * 切换键盘样式
     *
     * @param status
     */
    private void changeKeyboard(int status) {
        keyBoardStatus = status;
        switch (status) {
            case 0:
                //数字+字母键盘
                LetterAdapter letterAdapter = new LetterAdapter(mContext, KeyDataUtils.getLetter());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 20);
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

                    @Override
                    public int getSpanSize(int position) {
                        if (position == 20) {
                            return 2;
                        } else if (position == 30) {
                            return 3;
                        } else if (position == KeyDataUtils.getLetter().size() - 1) {
                            return 3;
                        } else {
                            return 2;
                        }
                    }
                });
                mRecyclerKey.setLayoutManager(gridLayoutManager);
                mRecyclerKey.setAdapter(letterAdapter);

                letterAdapter.setOnKeyBoardClickListener((msg, position) -> {

                    if (position == 20) {
                        //大小写
                        if (!isBig) {
                            letterAdapter.onRefresh(KeyDataUtils.getLetterBig());

                            letterAdapter.setIsBig(true);
                        } else {
                            letterAdapter.onRefresh(KeyDataUtils.getLetter());
                            letterAdapter.setIsBig(false);
                        }
                        isBig = !isBig;
                    } else if (position == 30) {
                        //空格
                        if (inputMsg.length() < inputMaxLength) {
                            inputMsg += " ";

                            if (mOnClickListener != null) {
                                mOnClickListener.onKeyBoardClick(" ", inputMsg);
                            }
                        }
                    } else if (position == KeyDataUtils.getLetter().size() - 1) {
                        //删除
                        if (inputMsg.length() > 0) {
                            inputMsg = inputMsg.substring(0, inputMsg.length() - 1);
                        }

                        if (mOnClickListener != null) {
                            mOnClickListener.onDeleteClick();
                        }
                    } else {
                        if (inputMsg.length() < inputMaxLength) {
                            inputMsg += msg;

                            if (mOnClickListener != null) {
                                mOnClickListener.onKeyBoardClick(msg, inputMsg);
                            }
                        }


                    }
                    mContent.setText(KeyDataUtils.replaceStr(inputMsg));
                });
                break;
            case 1:
                //符号键盘
                CharacterAdapter characterAdapter = new CharacterAdapter(mContext, KeyDataUtils.getCharacterMsg());
                GridLayoutManager gridLayoutManager2 = new GridLayoutManager(mContext, 20);
                gridLayoutManager2.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

                    @Override
                    public int getSpanSize(int position) {
                        if (position == 32) {
                            return 13;
                        } else if (position == 33) {
                            return 3;
                        } else {
                            return 2;
                        }
                    }
                });
                mRecyclerKey.setLayoutManager(gridLayoutManager2);
                mRecyclerKey.setAdapter(characterAdapter);

                characterAdapter.setOnKeyBoardClickListener((msg, position) -> {
                    if (position == 32) {
                        //空格
                        if (inputMsg.length() < inputMaxLength) {
                            inputMsg += " ";

                            if (mOnClickListener != null) {
                                mOnClickListener.onKeyBoardClick(" ", inputMsg);
                            }
                        }


                    } else if (position == KeyDataUtils.getCharacterMsg().size() - 1) {
                        //删除
                        if (inputMsg.length() > 0) {
                            inputMsg = inputMsg.substring(0, inputMsg.length() - 1);
                        }

                        if (mOnClickListener != null) {
                            mOnClickListener.onDeleteClick();
                        }
                    } else {
                        if (inputMsg.length() < inputMaxLength) {
                            inputMsg += msg;

                            if (mOnClickListener != null) {
                                mOnClickListener.onKeyBoardClick(msg, inputMsg);
                            }
                        }


                    }
                    mContent.setText(KeyDataUtils.replaceStr(inputMsg));
                });
                break;
            case 2:
                //数字键盘
                NumberAdapter numberAdapter = new NumberAdapter(mContext, KeyDataUtils.getNumberMsg());
                GridLayoutManager gridLayoutManager3 = new GridLayoutManager(mContext, 3);
                mRecyclerKey.setLayoutManager(gridLayoutManager3);
                mRecyclerKey.setAdapter(numberAdapter);

                numberAdapter.setOnKeyBoardClickListener((msg, position) -> {
                    if (position == KeyDataUtils.getNumberMsg().size() - 1) {
                        //删除
                        if (inputMsg.length() > 0) {
                            inputMsg = inputMsg.substring(0, inputMsg.length() - 1);
                        }

                        if (mOnClickListener != null) {
                            mOnClickListener.onDeleteClick();
                        }
                    } else {
                        if (inputMsg.length() < inputMaxLength) {
                            inputMsg += msg;

                            if (mOnClickListener != null) {
                                mOnClickListener.onKeyBoardClick(msg, inputMsg);
                            }
                        }


                    }
                    mContent.setText(KeyDataUtils.replaceStr(inputMsg));
                });
                break;
        }
    }


    /**
     * 通过context获取到所属的Activity
     *
     * @param context
     * @return
     */
    private Activity finActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            ContextWrapper wrapper = (ContextWrapper) context;
            return finActivity(wrapper.getBaseContext());
        } else {
            return null;
        }
    }


}
