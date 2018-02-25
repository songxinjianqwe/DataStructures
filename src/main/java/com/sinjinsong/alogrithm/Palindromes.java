package com.sinjinsong.alogrithm;

public class Palindromes {
	public static boolean isPalindromes(String s, int index) {
		if (index == s.length() / 2 ) {
			return true;
		}
		return isPalindromes(s, index + 1) && (s.charAt(index) == s.charAt(s.length() - index - 1));
	}
    
	public static boolean isPalindromes(String s){
		return isPalindromes((s.replaceAll("[\\p{Punct}\\s]+", "")).toLowerCase(),0);
	}
	
	public static void main(String[] args) {
		System.out.println(Palindromes.isPalindromes("stts", 0));
		System.out.println(Palindromes.isPalindromes("stats", 0));
		System.out.println(Palindromes.isPalindromes("Madam, I'm Adam"));
	}
}
