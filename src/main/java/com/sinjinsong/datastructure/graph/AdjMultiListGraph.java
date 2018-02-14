package com.sinjinsong.datastructure.graph;/*package graph;
enum GraphKind {
	DG, DN, UDG, UDN
}
enum IfVisit{
	visited,unvisited
}
class InfoType {
	String info;
	// 该弧相关信息的类
}
//边结点
class EdgeNode{
	IfVisit mark;//标记该条边是否被访问过
	int iVex,jVex;//分别为这条边的两个顶点在数组中的索引
	EdgeNode iLink,jLink;//分别指向下一条依附于结点iVex和jVex的边
	InfoType info;
}
//顶点结点
class VexNode<E>{
	E data;
	EdgeNode firstEdge;//该顶点的第一条边
}
*//**
 * 邻接多重表表示的图
 * @author New Song
 *
 *//*
public class AdjMultiListGraph<E> {
	public static final int MAX_VERTEX_NUM = 20;
	VexNode<E> [] adjMulist;
	int vexNum,arcNum;
	GraphKind kind;
}
*/