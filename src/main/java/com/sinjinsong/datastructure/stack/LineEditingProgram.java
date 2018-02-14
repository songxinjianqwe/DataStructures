package com.sinjinsong.datastructure.stack;

import java.util.Scanner;

public class LineEditingProgram {
	public static String input(){
		MyStack<Character> stack = new MyStack<>();
		Scanner scan = new Scanner(System.in);
		String line = null;
		StringBuilder sb = new StringBuilder();
		//回车，用一个空行作为结束标志
		while(!(line = scan.nextLine()).equals("")){
			for (int i = 0; i < line.length(); i++) {
				switch(line.charAt(i)){
				case '#':
					stack.pop();
					break;
				case '@':
					stack.clear();
					break;
				default:
					stack.push(line.charAt(i));
					break;
				}
			}
			if(!stack.isEmpty()){
				sb.append(stack.traverse()+"\r\n");
			}
		}
		scan.close();
		return sb.toString();
	} 
	public static void main(String[] args) {
		String code = input();
		System.out.println(code);
	}
}
