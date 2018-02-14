package com.sinjinsong.datastructure.graph;
import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * 经过修改的入队和出队操作和链式队列
 * 
 * @author New Song
 *
 * @param <E>
 */
public class LinkedQueue {
	private Node front;
	private Node prev;
	private Node rear;
	private int size;
	
	//front指向头结点
	//rear指向最后一个结点
	
	public LinkedQueue() {
		front = null;
		rear = front;
		size = 0;
	}

	private void checkEmpty() {
		if (front == null) {
			throw new NoSuchElementException("队列为空");
		}
	}

	// 入队时将新添加结点的prev指针域指向队列刚刚出队的结点（队列的prev指针所指向的结点）
	public boolean enQueue(int e) {
		if (isEmpty()) {
			front = new Node(e, prev,rear);
			rear = front;
			// 只有一个结点时它既是头又是尾
		} else {
			Node newNode = new Node(e,prev, null);
			rear.next = newNode;
			rear = newNode;
		}
		size++;
		return true;
	}
	
	//出队时只移动front指针，不删除元素
	public int deQueue() {
		checkEmpty();
		int e = front.data;
		prev = front;
		front = front.next;
		size--;
		return e;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		Node node = front;
		Node temp = null;
		while (node != null) {
			temp = node.next;
			node = null;
			node = temp;
		}
		front = null;
		rear = null;
		size = 0;
	}
	
	public LinkedList<Integer> path(){
		LinkedList<Integer> list = new LinkedList<>();
		Node node = rear;
		list.addFirst(node.data);
		while(node.prev != null){
			node = node.prev;
			list.addFirst(node.data);
		}
		return list;
	}
	
	public int peek() {
		checkEmpty();
		return front.data;
	}

	public int size() {
		return size;
	}
	
	class Node {
		int data;
		Node prev;
		Node next;

		public Node(int data, Node prev,Node next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
}
