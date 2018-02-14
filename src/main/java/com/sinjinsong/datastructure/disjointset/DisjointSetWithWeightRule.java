package com.sinjinsong.datastructure.disjointset;

/**
 * 使用重量原则
 * 
 * @ClassName: DisjointSetWithWeightRule
 * @Description: TODO
 * @author NewSong
 * @date 2016年12月6日 上午11:29:03
 *
 */
public class DisjointSetWithWeightRule {
	private int[] parent;//对于根节点而言， 存放整棵树的结点数目（所有孩子+自己）；对于其他结点，存放根节点的下标
	private boolean[] isRoot;// 每个结点是否为根节点

	public DisjointSetWithWeightRule(int capacity) {
		parent = new int[capacity + 1];
		isRoot = new boolean[capacity + 1];
		for(int i = 0;i < parent.length;++i){
			parent[i] = 1;
			isRoot[i] = true;
		}
	}
	
	public int find(int e){
		while(!isRoot[e]){
			e = parent[e];
		}
		return e;
	}
	
	public void union(int i, int j){
		if(parent[i] < parent[j]){//i是j的子树
			parent[j] += parent[i];
			isRoot[i] = false;
			parent[i] = j;
		}else{
			parent[i] += parent[j];
			isRoot[j] = false;
			parent[j] = i;
		}
	}
}
