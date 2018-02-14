package com.sinjinsong.datastructure.disjointset;

public class DisjointSet {
    private int[] parent;

    //	parent[i] = j  i是自己的值（同时也是下标），j是父节点的值（同时也是下标）
    //数组中第0号位置不存储元素，parent[根节点下标] = 0
    public DisjointSet(int capacity) {
        parent = new int[capacity + 1];
        for (int i = 1; i < parent.length; ++i) {
            parent[i] = 0;//默认都是根节点
        }
    }
	
    //查找该元素的根节点（属于哪一堆等价类）
    public int find(int e) {
        while (parent[e] != 0) {
            e = parent[e];
        }
        return e;
    }

    //两个等价类要合并，i和j分别是等价类的根节点下标
    public void union(int i, int j) {
        parent[i] = j;
    }

    public void print() {
        for (int i = 1; i < parent.length; ++i) {
            System.out.print(parent[i] + " ");
        }
        System.out.println();
    }
}
