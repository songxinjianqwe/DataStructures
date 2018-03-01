package com.sinjinsong.alogrithm.others;

public class Palindromes {
    public static boolean isPalindromes(String s, int index) {
        if (index == s.length() / 2) {
            return true;
        }
        return isPalindromes(s, index + 1) && (s.charAt(index) == s.charAt(s.length() - index - 1));
    }

    public static boolean isPalindromes(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Palindromes.isPalindromes("stts", 0));
        System.out.println(Palindromes.isPalindromes("stats", 0));
        System.out.println(Palindromes.isPalindromes("stats"));
        System.out.println(Palindromes.isPalindromes("Madam, I'm Adam"));
        System.out.println(Palindromes.isPalindromes("stts"));
    }
}
