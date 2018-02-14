package com.sinjinsong.datastructure.string;

public class Test {
	public static void main(String[] args) {
		MyString str = new MyString("I am the king of the World!");
		System.out.println(str.indexOf(new MyString("am")));
		System.out.println(str.indexOf(new MyString("wor")));
		System.out.println(str.indexOf(new MyString("the")));
		System.out.println(str.indexOf(new MyString("World!")));
		
	}
}
