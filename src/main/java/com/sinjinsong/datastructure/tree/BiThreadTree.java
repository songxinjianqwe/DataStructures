package com.sinjinsong.datastructure.tree;

/**
 * 中序线索化二叉树 构造方式是按照先序遍历构造出来 然后中序线索化 最后可以采用中序遍历方式进行遍历
 * 
 * @author New Song
 *
 */
enum PointerTag {
	Link, Thread
}

public class BiThreadTree<E extends Comparable<E>> {
	private ThreadTreeNode<E> head;
	private ThreadTreeNode<E> root;
	private ThreadTreeNode<E> pre;
	private static int index = -1;

	public BiThreadTree(ThreadTreeNode<E> root) {
		this.root = root;
	}

	// 按照先序遍历方式构造一个普通的二叉树，没有线索化
	// 注意，index
	// 必须是全局变量/静态变量，如果是局部变量，那么在递归调用中，即使index值修改，也会在返回的时候变为原来的值（每次返回时所有局部变量出栈，恢复值）
	// pre也是这样，凡是需要永久保存变量的修改值的情况，都需要将该变量设置为全局变量
	public static BiThreadTree<Character> createThreadTree(String preStr) {
		char[] pre = preStr.toCharArray();
		BiThreadTree<Character> tree = new BiThreadTree<>(createSubTree(pre));
		index = -1;
		return tree;
	}

	private static ThreadTreeNode<Character> createSubTree(char[] pre) {
		index++;
		ThreadTreeNode<Character> subTree = null;
		if (pre[index] == '#') {
			subTree = null;
		} else {
			subTree = new ThreadTreeNode<>(pre[index]);
			subTree.leftChild = createSubTree(pre);
			subTree.rightChild = createSubTree(pre);
		}
		return subTree;
	}

	// 中序遍历
	public void inOrderThreadTraverse() {
		ThreadTreeNode<E> node = head.leftChild;
		while (node != head) {
			// node 指向根节点，根据中序遍历的规则，先移动到左子树的最底端
			while (node.LTag == PointerTag.Link) {
				node = node.leftChild;
			}
			System.out.print(node.data + " ");
			// 按照链表的方式进行遍历
			while (node.RTag == PointerTag.Thread && node.rightChild != head) {
				node = node.rightChild; // 相当于node = node.next
				System.out.print(node.data + " ");
				// 注意在这里要先移至右子树，再访问
				// 如果先访问再移至右子树，那么如果不能进入下次循环，就无法访问这个右子树结点了
			}
			// 此时要么已经遍历完毕，要么其右子树存在，需要继续移动到左子树的最底端
			node = node.rightChild;
		}
	}
	
	//这个算法更简洁一些
	ThreadTreeNode<E> first(ThreadTreeNode<E> node){
		while (node.LTag == PointerTag.Link) {
			node = node.leftChild;
		}
		return node;
	}
	 
	ThreadTreeNode<E> next(ThreadTreeNode<E> node){
		ThreadTreeNode<E> ptr = node.rightChild;
		if(ptr == head){
			return head;
		}
		if (node.RTag == PointerTag.Link ){
			while (ptr.LTag == PointerTag.Link) {
				ptr = ptr.leftChild;
			}
		}
		return ptr;
	}
	
	// 中序遍历
	public void inOrderThreadTraverse2() {
		ThreadTreeNode<E> node = null;
		for(node = first(head.leftChild); node != head; node = next(node)){
			System.out.print(node.data + " ");
		}
	}
	
	/**
	 * 设置头结点并线索化
	 */
	public void inOrderThreading() {
		head = new ThreadTreeNode<>();
		head.LTag = PointerTag.Link;
		head.RTag = PointerTag.Thread;
		head.rightChild = head;
		// 设置头结点，左指针域指向根节点，右指针域指向自身
		if (root == null) {
			head.leftChild = head;
		} else {
			// pre初始值为hefyyy
			// 线索化结束后pre指向最后一个结点
			pre.RTag = PointerTag.Thread;
			pre.rightChild = head;
			head.rightChild = pre;
			// 建立指针链
		}
	}

	// 线索化当前子树（中序遍历）
	public void inThreading(ThreadTreeNode<E> node) {
		if (node != null) {
			inThreading(node.leftChild);
			// 进入到左子树的最底端
			if (node.leftChild == null) {
				node.LTag = PointerTag.Thread;
				node.leftChild = pre;
			}
			// 左子树在当前调用中进行处理，右子树在下次调用中进行处理
			// head的Rchild要求一开始非空
			if (pre.rightChild == null) {
				pre.RTag = PointerTag.Thread;
				pre.rightChild = node;
			}
			pre = node;
			inThreading(node.rightChild);
		}
	}

	public static void main(String[] args) {
		String pre = "ABDH##I##EJ###CF##G##";
		BiThreadTree<Character> tree = createThreadTree(pre);
		tree.inOrderThreading();
		tree.inOrderThreadTraverse();
		System.out.println();
		tree.inOrderThreadTraverse2();
		
	}
}

class ThreadTreeNode<E extends Comparable<E>> {
	E data;
	ThreadTreeNode<E> leftChild;
	ThreadTreeNode<E> rightChild;
	PointerTag LTag = PointerTag.Link, RTag = PointerTag.Link;

	public ThreadTreeNode() {
	}

	public ThreadTreeNode(E data) {
		this.data = data;
	}
}
