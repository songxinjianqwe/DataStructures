package com.sinjinsong.datastructure.search;

public class StaicSearchTable<E extends Comparable<E>> {
	private Object[] value;
	private int elements; //有效元素个数
	private static int DEFAULT_CAPACITY = 10; //默认容量
	public StaicSearchTable() {
		this(DEFAULT_CAPACITY);
	}

	public StaicSearchTable(int length) {
		value = new Object[length];
		elements = 0;
	}
	
	public boolean add(E e) {
		value[elements] = e;
		elements++;
		return true;
	}
	
	public int indexOf(E e) {
		for (int i = 0; i < elements; i++) {
			if (value[i].equals(e))
				return i;
		}
		return -1;
	}
	//注意两种查找要求数组的结构是不一样的
	public int indexOfWithGuard(E e){
		value[0] = e;
		int i = elements;
		while(!value[i].equals(e)){
			i--;
		}
		return i;
	}
}
