package com.sinjinsong.leetcode.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LeetCode146 {
//    static class LRUCache {
//        private Map<Integer, Integer> cache;
//
//        public LRUCache(int capacity) {
//            cache = new LinkedHashMap<Integer, Integer>(capacity, 1, true) {
//                protected boolean removeEldestEntry(Map.Entry eldest) {
//                    return size() > capacity;
//                }
//            };
//        }
//
//        public int get(int key) {
//            return cache.getOrDefault(key, -1);
//        }
//
//        public void put(int key, int value) {
//            cache.put(key, value);
//        }
//    }

    static class LRUCache {
        int size;
        Node[] table;
        int capacity;
        Node head;
        Node tail;

        static class Node {
            int key;
            int val;
            Node next;
            Node before;
            Node after;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        public void print() {
            System.out.println("asc");
            Node curr = head;
            while (curr != null) {
                System.out.println(curr.key + " : " + curr.val);
                curr = curr.after;
            }
            System.out.println("desc");
            curr = tail;
            while (curr != null) {
                System.out.println(curr.key + " : " + curr.val);
                curr = curr.before;
            }
            System.out.println();
        }

        public LRUCache(int capacity) {
            this.table = new Node[capacity];
            this.capacity = capacity;
        }

        public int get(int key) {
            int bucket = key % capacity;
            if (table[bucket] == null) {
                return -1;
            }
            Node node = table[bucket];
            while (node != null) {
                if (node.key == key) {
                    afterNodeAccess(node);
                    return node.val;
                }
                node = node.next;
            }
            return -1;
        }

        private void afterNodeAccess(Node node) {
            if (node == tail) {
                // 不需要调整
                return;
            }
            // 1）node为链表头部 2）node处于链表中部
            Node before = node.before;
            Node after = node.after;
            if (before == null) {
                after.before = null;
                head = after;
            } else {
                before.after = after;
                after.before = before;
            }
            node.before = tail;
            node.after = null;
            tail.after = node;
            tail = node;
        }

        public void put(int key, int value) {
            int bucket = key % capacity;
            Node node = table[bucket];
            if (node == null) {
                table[bucket] = newNode(key, value);
            } else {
                Node prev = node;
                while (node != null) {
                    if (node.key == key) {
                        if (node.val == value) {
                            return;
                        } else {
                            node.val = value;
                            afterNodeAccess(node);
                            return;
                        }
                    }
                    prev = node;
                    node = node.next;
                }
                prev.next = newNode(key, value);
            }
            size++;
            afterNodeInsertion();
        }

        private Node newNode(int key, int value) {
            Node node = new Node(key, value);
            // link node last
            if (head == null) {
                head = tail = node;
            } else {
                node.before = tail;
                tail.after = node;
                tail = node;
            }
            return node;
        }

        private void afterNodeInsertion() {
            if (size > capacity) {
                // 删除双向链表的头节点
                Node first = head;
                head = head.after;
                remove(first.key);
            }
        }

        private void remove(int key) {
            int bucket = key % capacity;
            if (table[bucket] == null) {
                throw new IllegalStateException();
            }
            Node node = table[bucket];
            if (node == null) {
                return;
            }
            Node prev = null;
            while (node != null) {
                if (node.key == key) {
                    if (prev == null) {
                        table[bucket] = node.next;
                    } else {
                        prev.next = node.next;
                    }
                    size--;
                    afterNodeRemoval(node);
                    return;
                }
                prev = node;
                node = node.next;
            }
        }

        private void afterNodeRemoval(Node node) {
            Node before = node.before;
            Node after = node.after;
            node.before = node.after = null;
            if (before == null) {
                // head
                after.before = null;
                head = after;
            } else {
                before.after = after;
            }

            if (after == null) {
                // tail
                tail = before;
            } else {
                after.before = before;
            }
        }
    }

    private static void conv() {
        StringBuilder sb = new StringBuilder();
        int[][] res = {{2},{2,1},{1,1},{2,3},{4,1},{1},{2}};
        for (int[] re : res) {
            if (re.length == 1) {
                sb.append("cache.get(" + re[0] + ");\n");
            } else {
                sb.append("cache.put(" + re[0] + ", " + re[1] + ");\n");
            }
        }
        System.out.println(sb.toString());
    }

    static class LRUCacheBasedOnHashMap {
        static class Node {
            int key;
            int value;
            Node before;
            Node after;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private final Map<Integer, Node> map;
        private Node head;
        private Node tail;
        private final int capacity;
        private int size;

        public LRUCacheBasedOnHashMap(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) {
                return -1;
            }
            afterNodeAccess(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                if (value == node.value) {
                    return;
                }
                node.value = value;
                afterNodeAccess(node);
            } else {
                map.put(key, newNode(key, value));
                size++;
                afterNodeInsertion();
            }
        }

        private void afterNodeAccess(Node node) {
            if (node == tail) {
                // tail
                return;
            }
            Node before = node.before;
            Node after = node.after;
            if (before == null) {
                // head
                after.before = null;
                head = after;
            } else {
                // middle
                before.after = after;
                after.before = before;
            }
            node.before = tail;
            node.after = null;
            tail.after = node;
            tail = node;
        }

        private void afterNodeInsertion() {
            if (size > capacity) {
                map.remove(head.key);
                head.after.before = null;
                head = head.after;
                size--;
            }
        }

        private Node newNode(int key, int value) {
            Node node = new Node(key, value);
            if (head == null) {
                head = tail = node;
            } else {
                node.before = tail;
                node.after = null;
                tail.after = node;
                tail = node;
            }
            return node;
        }
    }

    public static void main(String[] args) {
        conv();
        LRUCacheBasedOnHashMap cache = new LRUCacheBasedOnHashMap(2);
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        cache.get(1);
        cache.get(2);
    }
}
