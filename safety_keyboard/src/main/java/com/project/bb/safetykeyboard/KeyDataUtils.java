package com.project.bb.safetykeyboard;

import java.util.ArrayList;
import java.util.List;

/**
 * des:键盘按键参数
 * author:bubian
 * time:2022/3/24 10:32
 */
public class KeyDataUtils {
    /**
     * 获取小写字母按键
     * @return
     */
    public static List<String> getLetter() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("0");
        list.add("q");
        list.add("w");
        list.add("e");
        list.add("r");
        list.add("t");
        list.add("y");
        list.add("u");
        list.add("i");
        list.add("o");
        list.add("p");
        list.add("大小写");
        list.add("a");
        list.add("s");
        list.add("d");
        list.add("f");
        list.add("g");
        list.add("h");
        list.add("j");
        list.add("k");
        list.add("l");
        list.add("空格");
        list.add("z");
        list.add("x");
        list.add("c");
        list.add("v");
        list.add("b");
        list.add("n");
        list.add("m");
        list.add("删除");
        return list;
    }

    /**
     * 获取大写字母按键
     * @return
     */
    public static List<String> getLetterBig() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("0");
        list.add("Q");
        list.add("W");
        list.add("E");
        list.add("R");
        list.add("T");
        list.add("Y");
        list.add("U");
        list.add("I");
        list.add("O");
        list.add("P");
        list.add("大小写");
        list.add("A");
        list.add("S");
        list.add("D");
        list.add("F");
        list.add("G");
        list.add("H");
        list.add("J");
        list.add("K");
        list.add("L");
        list.add("空格");
        list.add("Z");
        list.add("X");
        list.add("C");
        list.add("V");
        list.add("B");
        list.add("N");
        list.add("M");
        list.add("删除");
        return list;
    }

    /**
     * 获取字符按键
     * @return
     */
    public static List<String> getCharacterMsg() {
        List<String> list = new ArrayList<>();
        list.add("&");
        list.add("\"");
        list.add(";");
        list.add("^");
        list.add(",");
        list.add("|");
        list.add("$");
        list.add("*");
        list.add(":");
        list.add("'");
        list.add("?");
        list.add("{");
        list.add("[");
        list.add("~");
        list.add("#");
        list.add("}");
        list.add(".");
        list.add("]");
        list.add("\\");
        list.add("!");
        list.add("(");
        list.add("%");
        list.add("-");
        list.add("_");
        list.add("+");
        list.add("/");
        list.add(")");
        list.add("=");
        list.add("<");
        list.add("`");
        list.add(">");
        list.add("@");
        list.add("空格");
        list.add("删除");
        return list;
    }

    /**
     * 获取数字按键
     */
    public static List<String> getNumberMsg() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add(".");
        list.add("0");
        list.add("删除");
        return list;
    }


    /**
     * 把输入的值替换为加密形式
     * @param msg
     * @return
     */
    public static String replaceStr(String msg) {
//        String str = "";
//        for (int i = 0; i < msg.length(); i++) {
//            str += "●";
//        }
        return msg;
    }
}
