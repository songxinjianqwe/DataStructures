package com.sinjinsong.datastructure.stack;

public class NumberSystemConversion {

    /**
     * 十进制数字转其他进制
     * @param num
     * @param radix
     * @return
     */
    public static int convert(int num, int radix) {
        MyStack<Integer> stack = new MyStack<>();
        while (num != 0) {
            stack.push(num % radix);
            num /= radix;
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + "");
        }
        return Integer.parseInt(sb.toString());
    }

    public static void main(String[] args) {
        int num = 1348;
        int d = 8;
        System.out.println(NumberSystemConversion.convert(num, d));
    }
}
