package com.sinjinsong.datastructure.list;
/**
 * 一元多项式
 * @author New Song
 *
 */
public class Polynomial {
	private Node head;
	private Node tail;
	private int size;
	public Polynomial() {
		head = tail = null;
		size = 0;
	}
	public Polynomial add(double coef,int exp){
		if(head == null){
			head = new Node(coef,exp,tail);
			tail = head;
		}else{
			Node newNode = new Node(coef, exp, null);
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		return this;
	}
	
	
	public void add(int index,double coef,int exp){
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("插入元素的位置不合法:" + index);
		} else if (index == 0) {
			head = new Node(coef,exp, tail);
			tail = head;
			size++;
		} else if (index == size) {
			add(coef,exp);
		} else {
			Node node = head;
			int i = 0;
			while (node != null && i < index - 1) {
				node = node.next;
				i++;
			}
			// 此时i == index-1，node指向该位置的前一个元素
			Node newNode = new Node(coef,exp, node.next);
			node.next = newNode;
			size++;
		}
	}
	
	private void checkBounds(int index){
		if(index >= size||index < 0 ){
			throw new IndexOutOfBoundsException("链表元素共" + size + "个，所要访问的元素下标为" + index);
		}
	}
	public Node get(int index){
		checkBounds(index);
		Node node = head;
		int i = 0;
		while(node != null && i < index){
			node = node.next;
			i++;
		}
		return node;
	}
	public void print(){
		Node node = head;
		StringBuilder sb = new StringBuilder();
		sb.append("P(x) = ");
		while(node != null){
			sb.append("("+node.coef+"X^"+node.exp+")");
			if(node.next != null){
				sb.append(" + ");
			}
			node = node.next;
		}
		System.out.println(sb.toString());
	}
	public int size(){
		return size;
	}
	//约定 指数小的在前，指数大的在后
	public void addPolyn(Polynomial poly){
		int len = poly.size();
		//len是参数链表的长度，size是this的长度
		for(int i = 0; i < len; i++){
			Node node = poly.get(i);
			for(int j = 0; j < size;j++){
				Node temp = this.get(j);
				if(temp.exp == node.exp){
					temp.coef += node.coef;
					break;
				}
				if(temp.exp > node.exp){
					add(j,node.coef,node.exp);
					break;
				}
				if((j == size-1) && temp.exp < node.exp){
					add(size,node.coef,node.exp);
					break;
				}
			}
		}
	}
	public void substractPolyn(Polynomial poly){
		int len = poly.size();
		//len是参数链表的长度，size是this的长度
		for(int i = 0; i < len; i++){
			Node node = poly.get(i);
			for(int j = 0; j < size;j++){
				Node temp = this.get(j);
				if(temp.exp == node.exp){
					temp.coef -= node.coef;
					break;
				}
				if(temp.exp > node.exp){
					add(j,-node.coef,node.exp);
					break;
				}
				if((j == size-1) && temp.exp < node.exp){
					add(size,-node.coef,node.exp);
					break;
				}
			}
		}
	}
	class Node{
		double coef ;//系数
		int exp;//指数
		Node next;
		public Node(double coef,int exp,Node next) {
			this.coef = coef;
			this.exp = exp;
			this.next = next;
		}
	}
	
	public static void main(String[] args) {
		Polynomial p = new Polynomial();
		p.add(1, 0).add(3, 3).add(5, 12).add(-32, 22);
		//P(x) = 1 + 3x^3 + 5x^12 - 32x^22
		p.print();
		
		Polynomial q = new Polynomial();
		q.add(7, 3).add(5,5).add(-2, 12).add(-8, 999);
		//P(x) = 7x^3 -2x^12 -8x^999
		q.print();
		
		p.addPolyn(q);
		System.out.println("---------------");
		p.print();
		/*p.substractPolyn(q);
		System.out.println("---------------");
		p.print();*/
	}
}
