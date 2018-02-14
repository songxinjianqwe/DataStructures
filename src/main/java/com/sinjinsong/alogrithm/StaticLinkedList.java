package com.sinjinsong.alogrithm;

/**
 * 使用数组来实现链表
 * 
 * @author NewSong
 *
 */
public class StaticLinkedList {
	public static final int MAX_SIZE = 100;
	private Node[] cursorSpace = new Node[MAX_SIZE];
	private int head = 0;
	private int curr = 0;

	public StaticLinkedList() {
		for (int i = 0; i < MAX_SIZE; i++) {
			cursorSpace[i] = new Node(0, i + 1);
		}
		cursorSpace[MAX_SIZE - 1].next = -1;// 结束标识
	}

	public void add(int data) {
		cursorSpace[curr].data = data;
		curr = cursorSpace[curr].next;// 相当于++
	}

	public int get(int index) {
		return cursorSpace[index].data;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int ptr = head;
		while (ptr != -1) {
			sb.append(cursorSpace[ptr].data);
			if (cursorSpace[ptr].next != 0) {
				sb.append("\t");
			}
			ptr = cursorSpace[ptr].next;
		}
		return sb.toString();
	}

	private static class Node {
		int data;
		int next;

		public Node(int data, int next) {
			this.data = data;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		StaticLinkedList list = new StaticLinkedList();
		for (int i = 0; i < 20; i++) {
			list.add(i);
		}
		System.out.println(list.toString());
	}
}
