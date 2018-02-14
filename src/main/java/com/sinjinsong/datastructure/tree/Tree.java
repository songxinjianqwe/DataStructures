package com.sinjinsong.datastructure.tree;

import java.util.LinkedList;

/**
 * 孩子兄弟表示法
 * 
 * @author New Song
 *
 * @param <E>
 */
public class Tree<E> {
	private ChildSiblingNode<E> root;
	private LinkedList<ChildSiblingNode<E>> pathList;
	// 用于输出从根节点到各个叶子结点的所有路径

	public Tree() {
	}

	public Tree(ChildSiblingNode<E> root) {
		this.root = root;
	}

	// 求深度
	public int depth() {
		return depth(root);
	}

	private int depth(ChildSiblingNode<E> node) {
		if (node == null) {
			return 0;
		} else {
			int depthOfChild = depth(node.firstChild);
			int depthOfSibling = depth(node.nextSibling);
			return (depthOfChild + 1) > depthOfSibling ? depthOfChild + 1 : depthOfSibling;
		}
	}
	
	// 输出树/森林（转为了二叉树之后）从根结点到每个叶子结点的路径
	// 区别在于两点
	// 第一，当前结点为叶子结点只需要其左指针域为空即可，不需要右指针域为空，因为右指针域表示的是其兄弟
	// 第二，不应该输出访问第二棵子树时经过的第一棵子树，访问完其子树后应从链表中删掉当前结点
	public void printAllTreePaths() {
		if (pathList == null) {
			pathList = new LinkedList<>();
		}
		printTreePath(root);
		pathList.clear();
	}

	// 递归+循环
	private void printTreePath(ChildSiblingNode<E> node) {
		// 条件变为循环，每次循环处理一棵子树
		// 如果node值没有改变的话，使用while就会进入死循环
		// 递归调用并没有改变参数的值
		// 递归实现输出一条路径，循环实现尝试所有路径
		while (node != null) {
			pathList.addLast(node);
			if (node.firstChild == null) {
				// 左指针域为空表示其为叶子结点，就输出路径
				for (ChildSiblingNode<E> treeNode : pathList) {
					System.out.print(treeNode.data + " ");
				}
				System.out.println();
				// 输出链表从根节点到叶子的数据域

			} else {
				printTreePath(node.firstChild);
				// 到这里就已经输出了第一条路径了
			}
			// 以上为处理其子树，现在进入第二棵子树
			pathList.removeLast();
			node = node.nextSibling;
			// node变为第二棵子树的根
		}
		// 递归来求一条路径，循环来转向兄弟
	}
	
	// 建立树的存储结构，输入一个层序遍历得到的有序对的字符串，每一个有序对用空格隔开
	public static Tree<Character> createTree(String pairStr) {
		LinkedList<ChildSiblingNode<Character>> queue = new LinkedList<>();
		ChildSiblingNode<Character> root = null;
		ChildSiblingNode<Character> node = null;
		ChildSiblingNode<Character> parentNode = null;
		ChildSiblingNode<Character> lastChild = null;
		char parent = 0;
		char child = 0;
		for (int i = 0; i < pairStr.length()/3+1; i++) {
			parent = pairStr.charAt(i*3);
			child = pairStr.charAt(i*3+1);
			if(child == '#'){
				break;
			}
			node = new ChildSiblingNode<>(child);
			queue.addLast(node);
			if(parent == '#'){
				root = node;
			}else{
				//创建结点并入队，只有当当前结点不为根节点时，查找其双亲结点
				while((parentNode = queue.getFirst()).data != parent){
					queue.removeFirst();
				}
				//结束后parentNode指向当前结点的双亲结点
				if(parentNode.firstChild == null){
					parentNode.firstChild = node;
					lastChild = node;
				}else{
					lastChild.nextSibling = node;
					lastChild = node;
				}
				//lastChild总是指向双亲结点刚刚建立联系的孩子结点
				//如果双亲结点的firstChild为空，那么设其为当前结点，并且lastChild也指向当前结点（第一个孩子）
				//如果不为空，表示已经有孩子了，那么由lastChild可以找到其孩子，并让其兄弟指针指向当前结点
			}
		}
		return new Tree<>(root);
	}
	
	public ChildSiblingNode<E> root(){
		return root;
	}
	
	//树的先根遍历就等价于二叉树的先序遍历
	//先访问根节点，在按照从左至右的顺序先根遍历各个子树（子树森林）
	public void preRoot(ChildSiblingNode<E> subTree) {
		if (subTree != null) {
			System.out.print(subTree.data + " ");
			preRoot(subTree.firstChild);
			preRoot(subTree.nextSibling);
		}
	}

	//树的后根遍历就等价于二叉树的中序遍历
	//先后根遍历第一棵子树，再访问根节点，然后从左至右后根遍历其他子树
	public void postRoot(ChildSiblingNode<E> subTree) {
		if (subTree != null) {
			postRoot(subTree.firstChild);
			System.out.print(subTree.data + " ");
			postRoot(subTree.nextSibling);
		}
	}
	
	public static void main(String[] args) {
		String str = "#A AB AC AD CE CF FG ##";
		Tree<Character> tree = createTree(str);
		System.out.print("树的先根遍历：");
		tree.preRoot(tree.root());
		System.out.print("\r\n树的后根遍历：");
		tree.postRoot(tree.root());
		System.out.println("\r\n树的输出根节点到所有叶子结点的路径：");
		tree.printAllTreePaths();
	}
}


class ChildSiblingNode<E> {
	E data;
	ChildSiblingNode<E> firstChild;// 第一个孩子
	ChildSiblingNode<E> nextSibling;// 右侧的兄弟
	
	public ChildSiblingNode(E data) {
		this.data = data;
	}
}
