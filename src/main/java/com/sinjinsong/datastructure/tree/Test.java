package com.sinjinsong.datastructure.tree;


public class Test {
	public static void main(String[] args) {
//		int [] datas = {8,3,1,6,4,7,10,14,13};
//		BinarySearchTree<Integer> tree=  new BinarySearchTree<>();
//		for (int i = 0; i < datas.length; i++) {
////			tree.insert(datas[i]);
//			tree.createBiTree(tree.root(), datas[i]);
//		}
//		tree.preOrder(tree.root());
//		tree.levelOrder();
/*		System.out.println("递归先序遍历：");
		tree.preOrder(tree.root());
		System.out.println("\r\n递归中序遍历：");
		tree.inOrder(tree.root());
		System.out.println("\r\n递归后序遍历：");
		tree.postOrder(tree.root());
		System.out.println("\r\n--------------------");
		
		System.out.println("非递归先序遍历：");
		tree.noRecPreOrder(tree.root());
		System.out.println("\r\n非递归中序遍历：");
		tree.noRecInOrder(tree.root());
		System.out.println("\r\n非递归后序遍历：");
		tree.noRecPostOrder(tree.root());
		System.out.println("\r\n--------------------");
		
		System.out.println(tree.find(20));
		System.out.println(tree.find(3).data);
		tree.delete(8);
		tree.preOrder(tree.root());
		
		System.out.println(tree.depth());
		System.out.println(tree.size());
		
		System.out.println(tree.parent(tree.find(13)).data);
		System.out.println(tree.leftChild(tree.find(6)).data);
		tree.destroy();
		System.out.println(tree.root());
		tree.preOrder(tree.root());
		System.out.println();
		tree.clear(tree.find(6));
		tree.preOrder(tree.root());
		System.out.println(tree.leafSize());
		BinarySearchTree<Integer> tree2 = tree.clone();
		tree2.preOrder(tree2.root());
		System.out.println();
		tree2.insert(32);
		tree2.preOrder(tree2.root());
		System.out.println();
		tree.preOrder(tree.root());
		
		String pre = "ABCDEFGH";
		String in = "BDCEAFHG";
		BinarySearchTree<Character> tree3 = BinarySearchTree.preInCreateBiTree(pre, in);
		tree3.preOrder(tree3.root());
		*/
		BinarySearchTree<Character> tree = BinarySearchTree.createBiTree("ABE##CFG#H###DI#JK#####");
		tree.preOrder(tree.root());
        System.out.println();
		tree.levelOrder();
		System.out.println();
        System.out.println();
//		tree.printAllBiTreePaths();
        tree.postOrder(tree.root());
        System.out.println();
        tree.noRecPostOrder(tree.root());
	}
}
