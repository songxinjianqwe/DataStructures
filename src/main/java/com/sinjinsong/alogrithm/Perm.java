package com.sinjinsong.alogrithm;

import java.util.Arrays;

public class Perm {
	private char[] arr;
	public Perm(char[] arr) {
		this.arr = arr;
	}
	private void swap(int i,int j){
		char t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	public void perm(){
		perm(0,arr.length-1);
	}
	private void perm(int begin,int end){
		if(begin == end){
			System.out.println(Arrays.toString(arr));
		}else{
			for(int i = begin ; i<= end;i++){
				swap(begin,i);
				perm(begin+1,end);
				swap(begin,i);
			}
		}
	}
	
	public static void main(String[] args) {
		char [] arr = {'a','b','c','d'};
		Perm perm =  new Perm(arr);
		perm.perm();
	}
}
/*	arr = {1,2,3} 
*  ��һ����ִ�� perm({1,2,3},0,2),begin=0,end=2; 
*      i=0,���ִ��perm({1,2,3},1,2),begin=1,end=2; 
*          i=1,swap(arr,0,0)-->arr={1,2,3},  perm({1,2,3},2,2),begin=2,end=2; 
*              ��Ϊbegin==end������������{1,2,3} 
*          swap(arr,1,1) --> arr={1,2,3}; 
*          i=2,swap(arr,1,2)-->arr={1,3,2},  perm({1,3,2},2,2),begin=2,end=2; 
*              ��Ϊbegin==end������������{1,3,2} 
*          swap(arr,2,1) --> arr={1,2,3}; 
*      i=1,swap(arr,0,1) --> arr={2,1,3},     perm({2,1,3},1,2),begin=1,end=2; 
*          i=1,swap(arr,1,1)-->arr={2,1,3}   perm({2,1,3},2,2),begin=2,end=2; 
*              ��Ϊbegin==end������������{2,1,3} 
*          swap(arr,1,1)--> arr={2,1,3}; 
*          i=2,swap(arr,1,2)�� arr={2,3,1},��ִ��perm({2,3,1},2,2),begin=2,end=2; 
*              ��Ϊbegin==end������������{2,3,1} 
*          swap(arr,2,1) --> arr={2,1,3}; 
*      swap(arr,1,0)  --> arr={1,2,3} 
*      i=2,swap(arr,2,0) --> arr={3,2,1},ִ��perm({3,2,1},1,2),begin=1,end=2; 
*          i=1,swap(arr,1,1) --> arr={3,2,1} , perm({3,2,1},2,2),begin=2,end=2; 
*              ��Ϊbegin==end������������{3,2,1} 
*          swap(arr,1,1) --> arr={3,2,1}; 
*          i=2,swap(arr,2,1) --> arr={3,1,2},��ִ��perm({2,3,1},2,2),begin=2,end=2; 
*              ��Ϊbegin==end������������{3,1,2} 
*          swap(arr,2,1) --> arr={3,2,1}; 
*      swap(arr,0,2) --> arr={1,2,3} 
*/
