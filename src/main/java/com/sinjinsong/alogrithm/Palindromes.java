package com.sinjinsong.alogrithm;

public class Palindromes {
	public static boolean isPalin(String s, int index) {
		if (index == s.length() / 2 ) {
			return true;
		}
		return isPalin(s, index + 1) && (s.charAt(index) == s.charAt(s.length() - index - 1));
	}

	public static boolean isPalin(String s){
		return isPalin((s.replaceAll("[\\p{Punct}\\s]+", "")).toLowerCase(),0);
	}
	
	public static void main(String[] args) {
		System.out.println(Palindromes.isPalin("stts", 0));
		System.out.println(Palindromes.isPalin("stats", 0));
		System.out.println(Palindromes.isPalin("Madam, I'm Adam"));
	}
}
