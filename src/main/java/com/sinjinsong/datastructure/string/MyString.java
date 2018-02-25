package com.sinjinsong.datastructure.string;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MyString {

    private char[] value;

    public MyString(MyString str) {
        value = str.toCharArray();
    }

    public MyString(String str) {
        value = str.toCharArray();
    }

    public MyString(char[] chars) {
        value = new char[chars.length];
        System.arraycopy(chars, 0, value, 0, chars.length);
    }

    public MyString(char[] chars, int offset, int count) {
        value = new char[count];
        System.arraycopy(chars, offset, value, 0, count);
    }

    public int length() {
        return value.length;
    }

    public MyString substring(int startIndex) {
        char[] subArray = new char[value.length - startIndex];
        System.arraycopy(value, startIndex, subArray, 0, value.length - startIndex);
        return new MyString(subArray);
    }

    public MyString substring(int startIndex, int endIndex) {
        char[] subArray = new char[endIndex - startIndex];
        System.arraycopy(value, startIndex, subArray, 0, endIndex - startIndex);
        return new MyString(subArray);
    }

    public MyString concat(MyString str) {
        char[] chars = str.toCharArray();
        char[] res = new char[value.length + chars.length];
        System.arraycopy(value, 0, res, 0, value.length);
        System.arraycopy(chars, 0, res, value.length, chars.length);
        return new MyString(res);
    }

    public int compareTo(MyString str) {
        char[] chars = str.toCharArray();
        int i = 0;
        while (i < value.length && i < chars.length) {
            if ((value[i] - chars[i]) != 0) {
                return value[i] - chars[i];
            }
            i++;
        }
        return (value.length == chars.length) ? 0 : (i == value.length ? chars[i] : value[i]);
    }

    public char[] toCharArray() {
        char[] res = new char[value.length];
        System.arraycopy(value, 0, res, 0, value.length);
        return res;
    }

    public char charAt(int index) {
        return value[index];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MyString) {
            MyString str = (MyString) obj;
            char[] chars = str.toCharArray();
            if (chars.length == value.length) {
                for (int i = 0; i < value.length; i++) {
                    if (chars[i] != value[i]) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return new String(value);
    }

    public boolean startsWith(MyString str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != value[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean endsWith(MyString str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != value[i + value.length - chars.length]) {
                return false;
            }
        }
        return true;
    }

    public int indexOf(char ch) {
        for (int i = 0; i < value.length; i++) {
            if (ch == value[i]) {
                return i;
            }
        }
        return -1;
    }

    /*
     * public int simpleMatch(MyString str) { int lenOfShortestSubArray = str.length(); for (int i =
     * 0; i < value.length - lenOfShortestSubArray + 1; i++) { MyString sub = this.substring(i, i
     * + lenOfShortestSubArray); if (sub.equals(str)) { return i; } } return -1; }
     */

    public MyString replace(char oldChar, char newChar) {
        char[] chars = this.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == oldChar) {
                chars[i] = newChar;
            }
        }
        return new MyString(chars);
    }

    public MyString replace(MyString target, MyString replacement) {
        if (target.length() == 0) {
            return null;
        }
        char[] chars = this.toCharArray();
        MyString res = new MyString(chars);
        int index = 0;
        while ((index = res.indexOf(target)) != -1) {
            res.delete(index, target.length());
            res.insert(replacement, index);
        }
        return res;
    }

    public MyString toLowerCase() {
        char[] chars = this.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isUpperCase(chars[i])) {
                chars[i] = Character.toLowerCase(chars[i]);
            }
        }
        return new MyString(chars);
    }

    public MyString toUpperCase() {
        char[] chars = this.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLowerCase(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }
        return new MyString(chars);
    }

    // 用来改变自身，其他方法均不改变自身
    public void insert(MyString str, int startIndex) {
        char[] chars = str.toCharArray();
        char[] newChars = new char[value.length + chars.length];
        System.arraycopy(value, 0, newChars, 0, startIndex);
        System.arraycopy(chars, 0, newChars, startIndex, chars.length);
        System.arraycopy(value, startIndex, newChars, startIndex + chars.length, value.length - startIndex);
        value = newChars;
    }

    // 用来改变自己
    public void delete(int startIndex, int count) {
        char[] chars = new char[value.length - count];
        System.arraycopy(value, 0, chars, 0, startIndex);
        System.arraycopy(value, startIndex + count, chars, startIndex, value.length - startIndex - count);
        value = chars;
    }


    public int indexOf(MyString str) {
        char[] chars = str.toCharArray();
        int i = 0, j = 0;
        int[] next = getNext(str);
        while (i < value.length && j < chars.length) {
            if (j == -1 || value[i] == chars[j]) {
                //next数组中存在-1，为了避免越界，增加j == -1这个条件
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == chars.length) {
            return i - chars.length;
        } else {
            return -1;
        }
    }

    private static int[] getNext(MyString str) {
        int[] next = new int[str.length()];
        int i = -1, j = 0;
        char[] chars = str.toCharArray();
        next[0] = -1;
        while (j < chars.length - 1) {
            if (i == -1 || chars[i] == chars[j]) { //相当于chars[next[j]] == chars[j]
                i++;
                j++;
                next[j] = i;
                //通过j指向的字符得到j+1指向的字符的next值 
                //相当于next[j+1] = next[j]+1`
            } else {
                i = next[i];
            }
        }
        //j 是 指向当前字符，每次增1或不变，用来控制何时结束
        //i 会不断变化，向前进行跳转
        //也可以视j为主串指针，i为子串指针（虽然主串和子串都是模式串）
        return next;
    }

    /**
     * 参数字符串是否是当前字符串的旋转词
     * 旋转词指的是各个字符的相对顺序不变，但是位置不同的字符串
     * 比如123,231,312
     * 最优解算法为O(n)
     * 算法：
     * 1）比较长度是否相同
     * 2）如果相同，生成str1+str1的大字符串
     * 3）用KMP算法判断大字符串中是否包含str2
     *
     * @param str
     * @return
     */
    public boolean isRotationWord(MyString str) {
        if (value.length != str.length()) {
            return false;
        }
        MyString newStr = this.concat(this);
        return newStr.indexOf(str) != -1;
    }

    /**
     * 变形词：两个字符串，如果出现的字符是一样的并且每个字符出现次数一样，则返回true；否则返回false。
     * 比如"abc" "bca"
     * 哈希表，字符统计
     * 可以使用定长数组来取代哈希表，时间复杂度为O(n)，空间复杂度为O(n)。
     * char -> 使用长度为256的数组
     *
     * @param str
     * @return
     */
    public boolean isTransferWord(MyString str) {
        Map<Character, Integer> s1 = new HashMap<>();
        for (char c : this.value) {
            if (s1.get(c) == null) {
                s1.put(c, 1);
            } else {
                s1.put(c, s1.get(c) + 1);
            }
        }
        Map<Character, Integer> s2 = new HashMap<>();
        for (char c : str.value) {
            if (s2.get(c) == null) {
                s2.put(c, 1);
            } else {
                s2.put(c, s2.get(c) + 1);
            }
        }
        if (s1.size() != s2.size()) {
            return false;
        }

        for (Map.Entry<Character, Integer> entry : s1.entrySet()) {
            if (!entry.getValue().equals(s2.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 在单词间做一个逆序，但单词内不做逆序
     * "dog loves pig" -> "pig loves dog"
     * 算法：
     * 1）将整个字符串逆序
     * 2）对每个单词再做逆序
     *
     * @return
     */
    public MyString reverseBetweenWords() {
        // 拷贝一份
        char[] chars = new char[this.value.length];
        System.arraycopy(value, 0, chars, 0, value.length);
        reverse(chars, 0, chars.length - 1);
        int low = 0;
        for (int i = 0; i < chars.length; i++) {
            if (Character.isWhitespace(chars[i])) {
                reverse(chars, low, i - 1);
                low = i + 1;
            } else if (i == chars.length - 1) {
                reverse(chars, low, chars.length - 1);
            }
        }
        return new MyString(chars);
    }

    private static void reverse(char[] chars, int low, int high) {
        assert low <= high;
        int length = high - low + 1;
        char temp;
        for (int i = 0; i < length / 2; i++) {
            temp = chars[i + low];
            chars[i + low] = chars[high - i];
            chars[high - i] = temp;
        }
    }

    /**
     * 比如"ABCDE",n=3 -> "DEABC"
     * 时间复杂度为O(n)，空间复杂度为O(1)
     * 做三次逆序：
     * 1)[0,len -1]逆序
     * 2)[len,N-1]逆序
     * 3)[0,N-1]逆序
     *
     * @param len
     * @return
     */
    public MyString leftShift(int len) {
        char[] chars = new char[this.value.length];
        System.arraycopy(value, 0, chars, 0, value.length);
        reverse(chars, 0, len - 1);
        reverse(chars, len, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        return new MyString(chars);
    }

    /**
     * 找到一种拼接顺序，使得拼接后的整个字符串的字典序最小
     * 最优复杂度为O(nlogn)
     * 实质上是一个排序
     * 注意不能直接按字典序排序，比如["ba","b"] -> ["b","ba"] "bab" < "bba"
     * 要这样排序：
     * 如果str1+str2 < str2+str1，那么str1 < str2
     *
     * @param arr
     * @return
     */
    public static MyString concatArrayToMinString(MyString[] arr) {
        Comparator<MyString> comp = new Comparator<MyString>() {
            @Override
            public int compare(MyString o1, MyString o2) {
                return o1.concat(o2).compareTo(o2.concat(o1));
            }
        };
        Arrays.sort(arr, comp);
        StringBuilder sb = new StringBuilder();
        for (MyString s : arr) {
            sb.append(s.value);
        }
        return new MyString(sb.toString());
    }

    /**
     * 字符串中只有左右小括号，判断是否匹配。
     * 可以用栈，也可以不用。
     * 设置一个变量num：、
     * 遍历字符串
     * 1）遇到左括号，num++
     * 2）遇到右括号，num--，如果num<0，返回false
     * 3）遍历完后，num为0，返回true，否则返回false
     * 时间复杂度为O(n)
     *
     * @return
     */
    public boolean isBracketMatch() {
        int num = 0;
        for (char c : value) {
            if (c == '(') {
                num++;
            } else if (c == ')') {
                num--;
                if (num < 0) {
                    return false;
                }
            }
        }
        return num == 0;
    }

    /**
     * 最长无重复子串长度
     * 比如”abcb”，其最长无重复子串为”abc”，长度为3
     * 比如”abcd”，长度为4
     * 时间复杂度为O(n)，空间复杂度为O(n)
     * 
     * @return
     */
    public int longestLengthOfUniqueSubstring() {
        // 记录了i-1位置上的最长无重复子串的开始下标
        int preStart = 0;
        int currentLen = 0;
        int maxLen = 0;
        Map<Character, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < value.length; i++) {
            if (lastIndex.get(value[i]) != null) {
                preStart = Math.max(lastIndex.get(value[i]) + 1, preStart);
            }
            // 此时currentStart指向了如果以i位置为结尾，最长的子串的开始位置
            // currentLen是当前最长子串长度
            currentLen = i - preStart + 1;
            // 取较长字符长度
            maxLen = Math.max(maxLen, currentLen);
            // 一定要更新当前字符，可以舍弃前面的重复字符，但绝不能舍弃当前的重复字符
            // 比如rfrabc，遇到第二个r时一定要舍弃前一个r（pre前进），而不能舍弃第二个r
            lastIndex.put(value[i], i);
        }
        return maxLen;
    }

    public static void main(String[] args) {
//        System.out.println(new MyString("123").isRotationWord(new MyString("231")));
//        System.out.println(new MyString("123").isRotationWord(new MyString("321")));

//        System.out.println(Arrays.toString(getNext(new MyString("abcdabd"))));
//        System.out.println(new MyString("dog loves pig").reverseBetweenWords());
//        System.out.println(new MyString("ABCDE").leftShift(2));
//        MyString string = concatArrayToMinString(new MyString[]{new MyString("b"), new MyString("ba")});
//        System.out.println(string);
//        System.out.println(new MyString("abc").isTransferWord(new MyString("bca")));
//        System.out.println(new MyString("ABCDE").leftShift(3));
        System.out.println(new MyString("abcb").longestLengthOfUniqueSubstring());
        System.out.println(new MyString("abcd").longestLengthOfUniqueSubstring());
        System.out.println(new MyString("kojypxtmcheuvhpkqapldlkxkdbbouclkqwpnivxquwimbexyuqlipqpnlaklsqjnhcqspegqswpwinmvaokwbzylyrctfiscjuweakmmiaqsuanrfxhaffeuaauafqkeksjxgdpspkzzzsawuyvrdbgdqhrnkspldkkqfmlsofadojwfdikwpzsafacxoktpxkzmzzihljiqlsnbygkstydeflbgjrzrtxryfcntduaadqyzxmsrrxqbgldcvmtmqwyqgjtda").longestLengthOfUniqueSubstring());
    }
}
