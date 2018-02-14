package com.sinjinsong.alogrithm;


class LinkedList<E> {
	Node<E> head;
	Node<E> tail;

	// 添加到尾
	public boolean add(E e) {
		if (head == null) {
			head = new Node(e, tail);
			tail = head;
		} else {
			Node newNode = new Node(e, null);
			tail.next = newNode;
			tail = newNode;
		}
		return true;
	}

	static class Node<E> {
		E data;
		Node next;

		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}
}

public class LengthOfList {
	public static <T> int length(LinkedList<T> list) {
		if (list.head == list.tail) {
			return 1;
		}
		list.head = list.head.next;
		return 1 + length(list);
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		for(int i =0 ; i < 20;i++){
			list.add(i);
		}
		System.out.println(LengthOfList.length(list));
	}
}
