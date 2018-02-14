package com.sinjinsong.datastructure.graph;/*package graph;
enum GraphKind {
	DG, DN, UDG, UDN
}

class InfoType {
	String info;
	// 该弧相关信息的类
}
//弧结点
class ArcNode{
	int tailVex,headVex;//这条弧的弧头和弧尾在数组中的索引
	ArcNode headLink,tailLink;//指向下一个弧头相同的弧的指针，指向下一个弧尾相同的弧的指针
	InfoType type;
}
//顶点结点
class VexNode<E>{
	E data;
	ArcNode firstIn,firstOut;//指向以该结点为弧头的第一个弧结点（第一个进入），指向以该结点位弧尾的弧结点（第一个出去）
}
*//**
 * 十字链表表示的图
 * @author New Song
 *
 *//*
public class OrthListGraph<E> {
	public static final int MAX_VERTEX_NUM = 20;
	VexNode<E> [] xlist;
	int vexNum,arcNum;
	GraphKind kind;
}

*/