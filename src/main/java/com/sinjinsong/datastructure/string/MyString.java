package com.sinjinsong.datastructure.string;

public class MyString {

	private char[] value;

	public MyString(MyString str) {
		value = str.toCharArray();
	}

	public MyString(String str) {
		value = str.toCharArray();
	}

	public MyString(char[] chars) {
		value = new char[chars.length];
		System.arraycopy(chars, 0, value, 0, chars.length);
	}

	public MyString(char[] chars, int offset, int count) {
		value = new char[count];
		System.arraycopy(chars, offset, value, 0, count);
	}

	public int length() {
		return value.length;
	}

	public MyString substring(int startIndex) {
		char[] subArray = new char[value.length - startIndex];
		System.arraycopy(value, startIndex, subArray, 0, value.length - startIndex);
		return new MyString(subArray);
	}

	public MyString substring(int startIndex, int endIndex) {
		char[] subArray = new char[endIndex - startIndex];
		System.arraycopy(value, startIndex, subArray, 0, endIndex - startIndex);
		return new MyString(subArray);
	}

	public MyString concat(MyString str) {
		char[] chars = str.toCharArray();
		char[] res = new char[value.length + chars.length];
		System.arraycopy(value, 0, res, 0, value.length);
		System.arraycopy(chars, 0, res, value.length, chars.length);
		return new MyString(res);
	}

	public int compareTo(MyString str) {
		char[] chars = str.toCharArray();
		int i = 0;
		while (i < value.length && i < chars.length) {
			if ((value[i] - chars[i]) != 0) {
				return value[i] - chars[i];
			}
			i++;
		}
		return (value.length == chars.length) ? 0 : (i == value.length ? chars[i] : value[i]);
	}

	public char[] toCharArray() {
		char[] res = new char[value.length];
		System.arraycopy(value, 0, res, 0, value.length);
		return res;
	}

	public char charAt(int index) {
		return value[index];
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof MyString) {
			MyString str = (MyString) obj;
			char[] chars = str.toCharArray();
			if (chars.length == value.length) {
				for (int i = 0; i < value.length; i++) {
					if (chars[i] != value[i]) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return new String(value);
	}

	public boolean startsWith(MyString str) {
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != value[i]) {
				return false;
			}
		}
		return true;
	}

	public boolean endsWith(MyString str) {
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != value[i + value.length - chars.length]) {
				return false;
			}
		}
		return true;
	}

	public int indexOf(char ch) {
		for (int i = 0; i < value.length; i++) {
			if (ch == value[i]) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * public int indexOf(MyString str) { int len = str.length(); for (int i =
	 * 0; i < value.length - len + 1; i++) { MyString sub = this.substring(i, i
	 * + len); if (sub.equals(str)) { return i; } } return -1; }
	 */

	public MyString replace(char oldChar, char newChar) {
		char[] chars = this.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == oldChar) {
				chars[i] = newChar;
			}
		}
		return new MyString(chars);
	}

	public MyString replace(MyString target, MyString replacement) {
		if (target.length() == 0) {
			return null;
		}
		char[] chars = this.toCharArray();
		MyString res = new MyString(chars);
		int index = 0;
		while ((index = res.indexOf(target)) != -1) {
			res.delete(index, target.length());
			res.insert(replacement, index);
		}
		return res;
	}

	public MyString toLowerCase() {
		char[] chars = this.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (Character.isUpperCase(chars[i])) {
				chars[i] = Character.toLowerCase(chars[i]);
			}
		}
		return new MyString(chars);
	}

	public MyString toUpperCase() {
		char[] chars = this.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (Character.isLowerCase(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
			}
		}
		return new MyString(chars);
	}

	// 用来改变自身，其他方法均不改变自身
	public void insert(MyString str, int startIndex) {
		char[] chars = str.toCharArray();
		char[] newChars = new char[value.length + chars.length];
		System.arraycopy(value, 0, newChars, 0, startIndex);
		System.arraycopy(chars, 0, newChars, startIndex, chars.length);
		System.arraycopy(value, startIndex, newChars, startIndex + chars.length, value.length - startIndex);
		value = newChars;
	}

	// 用来改变自己
	public void delete(int startIndex, int count) {
		char[] chars = new char[value.length - count];
		System.arraycopy(value, 0, chars, 0, startIndex);
		System.arraycopy(value, startIndex + count, chars, startIndex, value.length - startIndex - count);
		value = chars;
	}
	public int indexOf(MyString str) {
		char[] chars = str.toCharArray();
		int i = 0, j = 0;
		int [] next = getNext(str);
		while (i < value.length && j < chars.length) {
			if (j == -1 ||value[i] == chars[j]) {
			//next数组中存在-1，为了避免越界，增加j == -1这个条件
				i++;
				j++;
			} else {
				j = next[j];
			}
		}
		if(j == chars.length){
			return i- chars.length;
		}else{
			return -1;
		}
	}
	private int[] getNext(MyString str){
		int []next = new int[str.length()];
		int i = -1,j = 0;
		char []chars = str.toCharArray();
		next[0] = -1;
		while(j < chars.length -1){
			if(i == -1 || chars[i] == chars[j]){ //相当于chars[next[j]] == chars[j]
				i++;
				j++;
				next[j] = i;
				//通过j指向的字符得到j+1指向的字符的next值 
				//相当于next[j+1] = next[j]+1
			}else{
				i = next[i];
			}
		}
		//j 是 指向当前字符，每次增1或不变，用来控制何时结束
		//i 会不断变化，向前进行跳转
		//也可以视j为主串指针，i为子串指针（虽然主串和子串都是模式串）
		return next;
	}
}
