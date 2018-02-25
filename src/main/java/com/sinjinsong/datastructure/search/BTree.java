package com.sinjinsong.datastructure.search;
/**
 * B-树
 * @author New Song
 *
 */
public class BTree {
	public static final int order = 3;//阶数
}

class BTreeNode<E extends Comparable<E>>{
	int keyNum;//关键字个数
	BTreeNode<E> parent;//指向双亲的指针
	E []keys;//关键字数组
	BTreeNode<E>[] ptrs;//指针域
	Record[] recordPtrs;//指向记录的指针域
}

//记录类
class Record {
	
}