package com.sinjinsong.datastructure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree<E> {
	private HuffmanNode<E> root;

	public HuffmanTree() {
	}

	public HuffmanTree(HuffmanNode<E> root) {
		this.root = root;
	}

	public static <E> HuffmanTree<E> createHuffmanTree(List<HuffmanNode<E>> list) {
		HuffmanNode<E> leftChild = null, rightChild = null, parent = null;
		while (list.size() > 1) {
			Collections.sort(list);// 对所有结点进行排序，得到从小到大的结点序列
			leftChild = list.remove(0);
			rightChild = list.remove(0);// 取得权值最低的两棵子树
			parent = new HuffmanNode<>(null, leftChild.weight + rightChild.weight, leftChild, rightChild);
			list.add(parent);
		}
		return new HuffmanTree<>(list.get(0));
	}

	// 先序遍历二叉树，只输出叶子结点
	public void preOrder(HuffmanNode<E> subTree) {
		if (subTree != null) {
			if (subTree.data != null) {
				System.out.print(subTree + "  ");
			}
			preOrder(subTree.leftChild);
			preOrder(subTree.rightChild);
		}
	}

	public HuffmanNode<E> root() {
		return root;
	}

	public static void main(String[] args) {
		char[] data = { 'A', 'B', 'C', 'D' };
		char[] weights = { 25, 10, 45, 20 };
		List<HuffmanNode<Character>> list = new ArrayList<>();
		for (int i = 0; i < weights.length; i++) {
			list.add(new HuffmanNode<>(data[i], weights[i]));
		}
		HuffmanTree<Character> tree = createHuffmanTree(list);
		tree.preOrder(tree.root());
	}
}

class HuffmanNode<E> implements Comparable<HuffmanNode<E>> {
	// 两个结点之间需要进行比较（不是结点的值需要比较）
	// 只有叶子结点才有data的值，其他结点没有data的值
	// 所有结点都有weight
	E data;
	int weight;// 权值
	HuffmanNode<E> leftChild;
	HuffmanNode<E> rightChild;

	
	public HuffmanNode(E data, int weight) {
		this.data = data;
		this.weight = weight;
	}

	public HuffmanNode(E data, int weight, HuffmanNode<E> leftChild, HuffmanNode<E> rightChild) {
		this(data, weight);
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	@Override
	public int compareTo(HuffmanNode<E> node) {
		return weight > node.weight ? 1 : (weight == node.weight ? 0 : -1);
	}

	// 正序，由小到大
	@Override
	public String toString() {
		return "val:" + data + ",weight:" + weight;
	}
}
