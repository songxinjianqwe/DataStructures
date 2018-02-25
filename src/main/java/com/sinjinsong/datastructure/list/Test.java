package com.sinjinsong.datastructure.list;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		MyDoubleLinkedList<Integer> list = new MyDoubleLinkedList<>();
		Random r = new Random();
		for (int i = 0; i < 20; i++) {
			list.add(r.nextInt(20));
		}
		list.traverse();
		list.add(19,2000);
		
		list.traverse();
        System.out.println(list.size());
	} 
}
/*class MyOrderedArrayList<E  extends Comparable<E>> {
	private Object[] value;
	private int elements;
	private static int defaultIncrement = 5;
	public <E extends Comparable<E>> boolean add(E  e) {
		if (elements + 1 > value.length) {
			Object [] dest = new Object [value.length + defaultIncrement];
			System.arraycopy(value, 0, dest, 0, value.length);
			free();
			value = dest;
		}
		//如果容量不足，则扩充
		int end = elements;
		//end值为现有元素的个数
		while(end > 0 && get(end-1).compareTo(e) > 0){
			value[end] = value[end-1];
			end--;
		}
		//将所有比e大的元素都移到e的右边
		value[end] = e;
		elements++;
		return true;
	}
	public int simpleMatch(E e) {
		int start = 0;
		int end = elements - 1;
		int mid = 0;
		while (start < end) {
			mid = (start + end) / 2;
			if (e.compareTo(get(mid)) > 0) {
				start = mid;
				//如果e比中间值大，那么取右半部分
			} else if (e.compareTo(get(mid)) < 0) {
				end = mid;
				//如果e比中间值小，那么取左半部分
			} else {
				return mid;
			}
		}
		//当start==end 且 该索引处的值不为e，则表示没有找到
		return -1;
	} 
}*/

