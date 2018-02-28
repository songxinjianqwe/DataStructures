package com.sinjinsong.alogrithm.linkedlist;

public class JosephusArrayImpl {
    private int[] data;
    private boolean[] isInline;

    public JosephusArrayImpl(int n) {
        data = new int[n];
        isInline = new boolean[n];
        for (int i = 0; i < n; i++) {
            data[i] = i + 1;
            isInline[i] = true;
        }
    }

    public void go(int gap) {
        int inline = data.length;
        int count = 0;
        int ptr = 0;
        while (true) {
            if (isInline[ptr]) {
                count++;
                if (count % gap == 0) {
                    isInline[ptr] = false;
                    inline--;
                    if (inline == 0) {
                        System.out.println(data[ptr]);
                        return;
                    }
                }
            }
            ptr = (ptr + 1) % data.length;
        }
    }

    public static void main(String[] args) {
        JosephusArrayImpl j = new JosephusArrayImpl(8);
        j.go(3);
    }
}
