package com.sinjinsong.datastructure.tree;

import java.util.Stack;


public class ExpressionTree {
	public static final char[][] prior = {
			{'>','>','<','<','>','>','>'},
			{'>','>','<','<','>','>','>'},
			{'>','>','>','>','>','>','>'},
			{'>','>','>','>','>','>','>'}, 
			{'<','<','<','<','>',' ','>'},
			{' ',' ',' ',' ','=',' ',' '},
			{' ',' ',' ',' ',' ',' ','='}
	};
	public static final char[] operands = {'+','-','*','/','(',')','#'};
	//检测该字符是否为运算符，如果是，返回在operands数组中的索引，否则返回-1
	private static int indexOf(char op){
		for (int i = 0; i < operands.length; i++) {
			if(op == operands[i]){
				return i;
			}
		}
		return -1;
	}
	
	//根据两个操作符的位置找到优先级列表中的二者的优先级
	private static char precede(char opA,char opB){
		return prior[indexOf(opA)][indexOf(opB)];
	}
	
	public static BinarySearchTree<Character> createExpressionTree(String exp){
		
		Stack<TreeNode<Character>> nodes = new Stack<>();
		Stack<Character> opnd = new Stack<>();
		opnd.push('#');
		//作为操作数栈开始和结束时的标志
		char op = 0;
		TreeNode<Character> Lchild = null;
		TreeNode<Character> Rchild = null;
		TreeNode<Character> root = null;
		char [] exps = exp.toCharArray();
		for (int i = 0; i < exps.length; i++) {
			if(indexOf(exps[i]) != -1){
				switch(precede(exps[i], opnd.peek())){
				case '>':
					opnd.push(exps[i]);
					break;
				//如果当前的运算符优先级高于栈顶运算符，则入栈
				case '<':
				//如果当前的运算符优先级低于栈顶运算符，则先出栈后入栈
				//将出栈的运算符与结点栈中的前两个结点建树，建完后压入结点栈
					op = opnd.pop();
					Lchild = nodes.pop();
					Rchild = nodes.pop();
					root = new TreeNode<>(op, Lchild, Rchild);
					nodes.push(root);
					opnd.push(exps[i]);
					break;
				case ' ':
					while(precede(exps[i], (op = opnd.pop())) != '='){
						Rchild = nodes.pop();
						Lchild = nodes.pop();
						root = new TreeNode<>(op, Lchild, Rchild);
						nodes.push(root);
					}
					//出现空白符的有两种情况，一种是右括号遇到非左括号的情况，此时会不断出栈直至左括号出栈，出栈后就进行类似于<的情况的操作
					//另一种是遇到#，表示表达式结束，此时不断出栈直至遇到运算符栈栈底的#，将栈清空
					break;
				}
			}else{
				//操作数就建立结点，并压入结点栈，作为左右子树
				Lchild = new TreeNode<>(exps[i]);
				nodes.push(Lchild);
			}
		}
		root = nodes.pop();
		//结束后正常情况下运算符栈为空，结点栈只有一个元素，就是根节点
		return new BinarySearchTree<>(root);
	}
	public static void main(String[] args) {
		String exp = "(a+b)*c-d/e#";
		//所有的表达式要求以#结束
		BinarySearchTree<Character> tree = ExpressionTree.createExpressionTree(exp);
		tree.preOrder();
		System.out.println();
		tree.inOrder();
	}
}
