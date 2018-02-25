package com.sinjinsong.datastructure.search;

import java.util.Objects;

/**
 * @author sinjinsong
 * @date 2018/1/25
 */
public class MyHashMap<K, V> {


    private static final int DEFAULT_CAPACITY = 16;
    private Node<K, V>[] table;
    private int size;
    
    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashMap(int size) {
        this.table = new Node[size];
    }

    public V put(K key, V value) {
        int index = key.hashCode() & (table.length - 1);
        Node<K, V> node = table[index];
        if (node == null) {
            table[index] = new Node(key, value, null);
        } else {
            Node<K, V> prev = node;
            // 链表不为空
            while (node != null) {
                // 如果是同一个key，那么会在本次循环返回
                if (node.key.equals(key)) {
                    // value相同，那么不插入
                    if (node.value.equals(value)) {
                        return null;
                    } else {
                        // value不同，则替换
                        V oldValue = node.value;
                        node.value = value;
                        return oldValue;
                    }
                }
                prev = node;
                node = node.next;
            }
            // 此时链表中没有key相同的，并且当前节点是空，那么直接将新节点链到前一个节点后面
            prev.next = new Node(key, value, null);
        }
        size++;
        return null;
    }

    

    public V get(K key) {
        int index = key.hashCode() & (table.length - 1);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = key.hashCode() & (table.length - 1);
        Node<K, V> node = table[index];
        if (node == null) {
            return null;
        } else {
            while (node != null) {
                if (node.key.equals(key)) {
                    V value = node.value;
                    // 链表上节点不唯一
                    if (node.next != null) {
                        node.key = node.next.key;
                        node.value = node.next.value;
                        node.next = node.next.next;
                    } else {
                        table[index] = null;
                    }
                    size--;
                    return value;
                }
                node = node.next;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void traverse() {
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            while (node != null) {
                System.out.print(node + " ");
                node = node.next;
            }
        }
        System.out.println();
    }


    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key) ^ Objects.hash(value);
        }

        @Override
        public String toString() {
            return "[" + key + "=" + value + "]";
        }
    }

    public static void main(String[] args) {
        MyHashMap<StringWrapper, Integer> map = new MyHashMap<>();
        map.put(new StringWrapper("a"), 1);
        map.put(new StringWrapper("a"), 2);
        map.put(new StringWrapper("b"), 3);
        map.put(new StringWrapper("a"), 2);
        map.traverse();
        System.out.println(map.get(new StringWrapper("a")));
        System.out.println(map.size());
        System.out.println(map.remove(new StringWrapper("a")));
        map.traverse();
        System.out.println(map.remove(new StringWrapper("c")));
        map.traverse();
        System.out.println(map.size());
    }


    private static class StringWrapper {
        String value;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StringWrapper that = (StringWrapper) o;
            return Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return 1;
        }

        public StringWrapper(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
