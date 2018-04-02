package com.sinjinsong.datastructure.search;

import java.util.Objects;

/**
 * @author sinjinsong
 * @date 2018/1/25
 */
public class LRUCache<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private Node<K, V>[] table;
    private Node<K, V> head;
    private Node<K, V> tail;
    private int size;

    public LRUCache() {
        this(DEFAULT_CAPACITY);
    }

    public LRUCache(int size) {
        this.table = new Node[size];
    }

    /**
     * 如果是更新值，那么将该节点放到链表尾部（相当于访问）
     * 如果是新增值，那么也将该节点放在链表尾部，并触发LRU淘汰
     *
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {
        int index = key.hashCode() & (table.length - 1);
        Node<K, V> node = table[index];
        if (node == null) {
            table[index] = newNode(key, value);
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
                        afterNodeAccess(node);
                        return oldValue;
                    }
                }
                prev = node;
                node = node.next;
            }
            // 此时链表中没有key相同的，并且当前节点是空，那么直接将新节点链到前一个节点后面
            prev.next = newNode(key, value);
        }
        size++;

        afterNodeInsertion();
        return null;
    }

    /**
     * 创建新节点时链到链表尾部
     * @param key
     * @param value
     * @return
     */
    private Node<K, V> newNode(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        linkNodeLast(node);
        return node;
    }

    private void linkNodeLast(Node<K, V> node) {
        if (head == null) {
            head = tail = node;
        } else {
            node.before = tail;
            tail.after = node;
            tail = node;
        }
    }

    /**
     * 超过阈值时，移除链表头节点
     */
    private void afterNodeInsertion() {
        if (size > table.length) {
            // 删除双向链表的头节点
            Node<K, V> first = head;
            head = head.after;
            remove(first.key);
        }
    }

    /**
     * 将访问到的节点链到尾部
     *
     * @param key
     * @return
     */
    public V get(K key) {
        int index = key.hashCode() & (table.length - 1);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                afterNodeAccess(node);
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * move node to last
     *
     * @param node
     */
    private void afterNodeAccess(Node<K, V> node) {
        if (tail != node) {
            // 1）node处于链表中部 2）node为链表头部
            Node<K, V> before = node.before;
            Node<K, V> after = node.after;

            // 1）node为链表头部
            if (before == null) {
                after.before = null;
                head = after;
            } else {
                // 2）node处于链表中部
                // 调整前驱指针
                before.after = after;
                // 调整后继指针
                after.before = before;
            }
            // 将node插入到tail之后
            node.before = tail;
            node.after = null;
            tail.after = node;
            tail = node;
        }
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
                    afterNodeRemoval(node);
                    return value;
                }
                node = node.next;
            }
        }
        return null;
    }

    /**
     * 调整被删除结点的前驱节点的after指针和后继节点的before指针
     * @param node
     */
    private void afterNodeRemoval(Node<K, V> node) {
        Node<K, V> before = node.before;
        Node<K, V> after = node.after;
        node.before = node.after = null;
        if (before == null) {
            // node 为head
            after.before = null;
            head = after;
        } else {
            before.after = after;
        }

        if (after == null) {
            // node 为tail
            tail = before;
        } else {
            after.before = before;
        }
    }

    public int size() {
        return size;
    }

    public void traverse() {
        Node<K, V> curr = head;
        while (curr != null) {
            System.out.print(curr + "   ");
            curr = curr.after;
        }
        System.out.println();
    }


    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> before;
        Node<K, V> after;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
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
//        LRUCache<StringWrapper, Integer> map = new LRUCache<>();
//        map.put(new StringWrapper("a"), 1);
//        map.put(new StringWrapper("a"), 2);
//        map.put(new StringWrapper("b"), 3);
//        map.put(new StringWrapper("a"), 2);
//        map.traverse();
//        System.out.println(map.get(new StringWrapper("a")));
//        System.out.println(map.size());
//        System.out.println(map.remove(new StringWrapper("a")));
//        map.traverse();
//        System.out.println(map.remove(new StringWrapper("c")));
//        map.traverse();
//        System.out.println(map.size());


//        LRUCache<String, Integer> cache = new LRUCache<>();
//        cache.put("a", 1);
//        cache.put("b", 1);
//        cache.put("c", 1);
//        cache.put("d", 1);
//        cache.put("e", 1);
//
//        cache.traverse();
//        System.out.println(cache.get("c"));
//        System.out.println(cache.get("a"));
//          System.out.println(cache.get("e"));
//          System.out.println(cache.get("b"));
//          System.out.println(cache.get("d"));
//        cache.remove("a"); 
//        cache.remove("b");
//        cache.remove("c");
//        cache.remove("d");
//        cache.remove("e");
//
//        for (int i = 0; i < 16; i++) {
//            cache.put(String.valueOf((char) ('a' + i)), i);
//        }
//        cache.get("a");
//        cache.put("b",12);
//        cache.put("q",16);
//        cache.traverse();
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
