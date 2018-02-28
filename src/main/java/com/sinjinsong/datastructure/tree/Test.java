package com.sinjinsong.datastructure.tree;


public class Test {
    public static void main(String[] args) {
//		int [] datas = {8,3,1,6,4,7,10,14,13};
//		BinarySearchTree<Integer> tree=  new BinarySearchTree<>();
//		for (int i = 0; i < datas.length; i++) {
////			tree.insert(datas[i]);
//			tree.createCharBiTree(tree.root(), datas[i]);
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
		tree.noRecPostOrderByOneStack(tree.root());
		System.out.println("\r\n--------------------");
		
		System.out.println(tree.find(20));
		System.out.println(tree.find(3).val);
		tree.delete(8);
		tree.preOrder(tree.root());
		
		System.out.println(tree.depth());
		System.out.println(tree.size());
		
		System.out.println(tree.parent(tree.find(13)).val);
		System.out.println(tree.left(tree.find(6)).val);
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
//		BinarySearchTree<Character> tree = BinarySearchTree.createCharBiTree("013478910");
//		tree.levelOrder();
//        System.out.println();
////		tree.levelOrderWithLine();
//        BinarySearchTree<String> tree = BinarySearchTree.createStringBiTree("0!1!3!7!15!#!#!#!8!#!#!4!9!#!#!10!#!#!2!5!11!#!#!12!#!#!6!13!#!#!14!#!#!");
////        BinarySearchTree<String> tree = BinarySearchTree.createStringBiTree("0!1!2!#!#!#!#!");
//        tree.preOrder(tree.root());
//        System.out.println();
//        tree.invert(tree.root());
//        tree.preOrder(tree.root());

//        System.out.println();
//		tree.levelOrderWithLine();
//        System.out.println();
//        List<List<String>> list = tree.levelOrderToListWithLine();

//        for(List<String> li : list) {
//            System.out.println(li);
//        }
//		tree.preOrder(tree.root());
//        System.out.println();
//		tree.levelOrder();
//		System.out.println();
//        System.out.println();
//		tree.printAllBiTreePaths();
//        tree.postOrder(tree.root());
//        System.out.println();
//        tree.noRecPostOrderByOneStack(tree.root());


//        BinarySearchTree<Character> a = BinarySearchTree.createCharBiTree("124##5##3##");
//        BinarySearchTree<Character> b = BinarySearchTree.createCharBiTree("24##5##");

//        System.out.println(a.isSubTreeBySerialization(b));
//        a.preOrder();
//        a.noRecPostOrder();

//        BinarySearchTree<String> a = BinarySearchTree.createStringBiTree("1!2!4!8!#!#!9!#!#!5!10!#!#!11!#!#!3!6!12!#!#!13!#!#!7!14!#!#!15!#!#!");
        BinarySearchTree<Character> a = BinarySearchTree.createCharBiTree("421##3##5##");
        System.out.println(a.sizeOfLevel(1));
        System.out.println(a.sizeOfLevel(0));
        System.out.println(a.sizeOfLevel(2));
        System.out.println(a.sizeOfLevel(3));
        
//        TreeNode<Character> head = a.BST2DoubleLinkedList(a.root());
//        while(head != null) {
//            System.out.println(head.val);
//            head = head.right;
//        }
        
//        a.levelOrder();
//        a.inOrder();
//        System.out.println(a.isBST());
//        System.out.println(a.isBSTByPassingMaxMin(a.root(), Integer.MIN_VALUE,Integer.MAX_VALUE));
//        System.out.println(a.getMaxSubBSTInfo(a.root()));
//        List<List<String>> lists = a.printZ();
//        a.printAllBiTreePaths();
        
        
        
    }
}
