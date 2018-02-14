package com.sinjinsong.datastructure.list;

import java.util.Comparator;
import java.util.Iterator;

@SuppressWarnings("all")
public class MyArrayList<E> implements MyList<E> {
    private Object[] elementData;
    private int size; //有效元素个数
    private static final int DEFAULE_CAPACITY = 10; //默认容量

    public MyArrayList() {
        this(DEFAULE_CAPACITY);
    }

    public MyArrayList(int length) {
        elementData = new Object[length];
    }

    private void checkBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("现有元素共" + size + "个，欲访问元素下标为" + index);
        }
    }

    public void ensureCapacity(int minCapacity) {
        Object[] dest = new Object[minCapacity + (minCapacity >> 1)];
        System.arraycopy(elementData, 0, dest, 0, size);
        elementData = dest;
    }

    public boolean add(E e) {
        if (size >= elementData.length) {
            ensureCapacity(size + 1);
        }
        elementData[size] = e;
        size++;
        return true;
    }

    public void add(int index, E e) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("插入元素的位置不合法:" + index);
        }
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = e;
        size++;
    }

    public E get(int index) {
        checkBounds(index);
        return (E) elementData[index];
    }

    public boolean contains(E e) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(e))
                return i;
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E remove(int index) {
        checkBounds(index);
        Object oldValue = get(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        }
        elementData[--size] = null;
        return (E) oldValue;
    }

    public boolean remove(E e) {
        int index = -1;
        if ((index = indexOf(e)) != -1) {
            remove(index);
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        Object[] dest = new Object[size];
        System.arraycopy(elementData, 0, dest, 0, size);
        return dest;
    }

    public E set(int index, E e) {
        checkBounds(index);
        Object oldValue = elementData[index];
        elementData[index] = e;
        return (E) oldValue;
    }

    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--) {
            if (elementData[i].equals(e))
                return i;
        }
        return -1;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                return MyArrayList.this.get(cursor++);
            }
        };
    }

    public void traverse() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(elementData[i] + (i == size - 1 ? "]\n" : ","));
        }
    }

    public void sort(Comparator<E> comp) {
        int k = 0;
        for (int i = 0; i < elementData.length - 1; i++) {
            k = i;
            for (int j = i + 1; j < elementData.length; j++) {
                if (comp.compare((E) elementData[k], (E) elementData[j]) > 0) {
                    k = j;
                }
            }
            if (k != i) {
                Object temp = elementData[k];
                elementData[k] = elementData[i];
                elementData[i] = temp;
            }
        }
    }
}
