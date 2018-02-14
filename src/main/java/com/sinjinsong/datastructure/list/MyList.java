package com.sinjinsong.datastructure.list;

public interface MyList<E> {
	void add(int index, E e);
	boolean add(E e);
	E get(int index);
	boolean contains(E e);
	int indexOf(E e);
	void traverse();
}
