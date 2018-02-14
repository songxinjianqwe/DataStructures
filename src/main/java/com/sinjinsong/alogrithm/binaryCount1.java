package com.sinjinsong.alogrithm;

public class binaryCount1 {
	public static int calculate(int n){
		if(n == 0){
			return 0;
		}
		if(n % 2 == 1){
			return calculate(n/2)+1;
		}else{
			return calculate(n/2);
		}
	}
	public static void main(String[] args) {
		System.out.println(calculate(6));
	}
}
