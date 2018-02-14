package com.sinjinsong.alogrithm;

public class Hanoi {
	public void hanoi(int n, char from, char via, char to) {
		if (n == 1) {
			System.out.println("move " + n + " from " + from + " to " + to);
		} else {
			hanoi(n - 1, from, to, via);
			System.out.println("move " + n + " from " + from +  " to " + to);
			hanoi(n - 1, via, from, to);
		}
	}

	public static void main(String[] args) {
		Hanoi h = new Hanoi();
		h.hanoi(3, 'A', 'B', 'C');
	}
}

