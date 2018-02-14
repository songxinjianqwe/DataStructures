package com.sinjinsong.datastructure.tree;

public class TreeExpress {

}

@SuppressWarnings("all")
/**
 * 双亲表示法
 * @author New Song
 *
 * @param <E>
 */
class ParentTree<E extends Comparable<E>> {
	private ParentNode<E> [] nodes ;
	private int root;//根节点在数组中的索引
	private int size;//结点个数
	private static int maxTreeSize = 100;
}

class ParentNode<E extends Comparable<E>> {
	E data;
	int parent;// 双亲在数组中的索引
}

/**
 * 孩子链表表示法
 * 结点放在数组中
 * 每个结点的孩子放在链表中
 * @author New Song
 *
 * @param <E>
 */
@SuppressWarnings("all")
class ChildTree<E extends Comparable<E>>{
	private ChildBox<E> [] nodes ;
	private int root;//根节点在数组中的索引
	private int size;//结点个数
}

class ChildBox<E extends Comparable<E>>{
	E data;
	ChildNode firstChild;//第一个孩子指针
}

class ChildNode{ //孩子结点
	int childIndex;//孩子结点在数组中的位置
	ChildNode next; //下一个孩子结点的指针
}
/**
 * 孩子兄弟表示法
 * @author New Song
 *
 * @param <E>
 *//*
class ChildSiblingTree<E extends Comparable<E>>{
	private ChildSiblingNode<E> root;
}
class ChildSiblingNode<E extends Comparable<E>>{
	E data;
	ChildSiblingNode<E> firstChild;//第一个孩子
	ChildSiblingNode<E> nextSibling;//右侧的兄弟
}
*/
