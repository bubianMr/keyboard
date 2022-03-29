package com.example.keyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.project.bb.safetykeyboard.BaseKeyboardUtils;
import com.project.bb.safetykeyboard.OnDefineClickListener;
import com.project.bb.safetykeyboard.OnKeyBoardClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView phone = findViewById(R.id.tv_phone);
        TextView keyboard = findViewById(R.id.tv_keyboard);
        TextView inputKeyboard = findViewById(R.id.tv_have_input);

        phone.setOnClickListener(v -> {
            BaseKeyboardUtils.getInstance(this).dismiss();
            BaseKeyboardUtils.getInstance(this)
                    .setTitle("手机号专属安全键盘")
                    .setInputMaxLength(11)
                    .setIsHaveInputBox(false)
                    .setOnlyNumber()
                    .setKeyboardClickListener(new OnKeyBoardClickListener() {
                        @Override
                        public void onKeyBoardClick(String value, String inputValue) {
                            phone.setText(inputValue);
                        }

                        @Override
                        public void onDeleteClick() {
                            String msg = phone.getText().toString();
                            if (msg.length() > 0) {
                                phone.setText(msg.substring(0, msg.length() - 1));
                            }
                        }
                    })
                    .showKeyboard(keyboard);
        });

        keyboard.setOnClickListener(v -> {
            BaseKeyboardUtils.getInstance(this).dismiss();
            BaseKeyboardUtils.getInstance(this)
                    .setTitle("XX专属安全键盘")
                    .setInputMaxLength(15)
                    .setIsHaveInputBox(false)
                    .setKeyboardClickListener(new OnKeyBoardClickListener() {
                        @Override
                        public void onKeyBoardClick(String value, String inputValue) {
                            keyboard.setText(inputValue);
                        }

                        @Override
                        public void onDeleteClick() {
                            String msg = keyboard.getText().toString();
                            if (msg.length() > 0) {
                                keyboard.setText(msg.substring(0, msg.length() - 1));
                            }
                        }
                    })
                    .showKeyboard(keyboard);
        });

        inputKeyboard.setOnClickListener(v->{
            BaseKeyboardUtils.getInstance(this).dismiss();
            BaseKeyboardUtils.getInstance(this)
                    .setTitle("XX专属安全键盘")
                    .setInputHint("这是输入提示")
                    .setInputMaxLength(15)
                    .setIsHaveInputBox(true)
                    .setOnDefineClickListener(value -> {
                        inputKeyboard.setText(value);
                    })
                    .showKeyboard(keyboard);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseKeyboardUtils.getInstance(this).dismiss();
    }
}