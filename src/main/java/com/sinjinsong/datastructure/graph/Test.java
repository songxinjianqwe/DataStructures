package com.sinjinsong.datastructure.graph;

public class Test {
	public static void main(String[] args) {

		// 有向图测试
		String[] data = { "v1", "v2", "v3", "v4" };
		String[] tail = { "v1", "v1", "v3", "v4" };
		String[] head = { "v2", "v3", "v4", "v1" };
		MatrixGraph<String> graph = MatrixGraph.createGraph(GraphKind.DG, data, tail, head, null, null);
		graph.display();
		System.out.println("--------------------");

		graph.DFSTraverse();

		graph.deleteVex(new Vertex<>("v3"));
		graph.display();
		graph.insertVex(new Vertex<>("v5"));
		graph.insertArc(null, new Vertex<>("v4"), 0, null);

		// graph.destroy(); graph.display();

		System.out.println(graph.firstAdjVex(new Vertex<>("v1")));
		System.out.println(graph.firstAdjVex(new Vertex<>("v2")));
		System.out.println(graph.firstAdjVex(new Vertex<>("v4")));
		System.out.println("------------------");
		System.out.println(graph.nextAdjVex(new Vertex<>("v1"), new Vertex<>("v3")));
		System.out.println(graph.nextAdjVex(new Vertex<>("v1"), new Vertex<>("v2")));
		System.out.println(graph.nextAdjVex(new Vertex<>("v1"), new Vertex<>("v4")));

		graph.putVertex(new Vertex<>("v3"), "v5");
		graph.display();
		System.out.println(graph.getVertex(new Vertex<>("v3")));
		graph.deleteArc(new Vertex<>("v1"), new Vertex<>("v3"));
		graph.display();
		graph.deleteArc(new Vertex<>("v2"), new Vertex<>("v4"));
		graph.display();
//**************************************************************************************
		// 有向网测试
		String[] data2 = { "v1", "v2", "v3", "v4" };
		String[] tail2 = { "v1", "v1", "v3", "v4" };
		String[] head2 = { "v2", "v3", "v4", "v1" };
		int[] weight = { 3, 5, 6, 9 };
		MatrixGraph<String> graph2 = MatrixGraph.createGraph(GraphKind.DN, data2, tail2, head2, weight, null);
		graph2.display();
		System.out.println("--------------------");
		graph2.deleteVex(new Vertex<>("v3"));
		graph2.display();
		graph2.insertVex(new Vertex<>("v5"));
		graph2.insertArc(new Vertex<>("v5"), new Vertex<>("v4"), 23, null);
		graph2.insertArc(new Vertex<>("v4"), new Vertex<>("v5"), 12, null); // graph.destroy();
		graph2.display();

		System.out.println(graph2.firstAdjVex(new Vertex<>("v1")));
		System.out.println(graph2.firstAdjVex(new Vertex<>("v2")));
		System.out.println(graph2.firstAdjVex(new Vertex<>("v5")));
		System.out.println("------------------");
		System.out.println(graph2.nextAdjVex(new Vertex<>("v1"), new Vertex<>("v3")));
		System.out.println(graph2.nextAdjVex(new Vertex<>("v4"), new Vertex<>("v1")));
		System.out.println(graph2.nextAdjVex(new Vertex<>("v1"), new Vertex<>("v4")));
		System.out.println("-------------------");
		graph2.putVertex(new Vertex<>("v5"), "v7");
		graph2.display();
		System.out.println(graph2.getVertex(new Vertex<>("v7")));
		graph2.deleteArc(new Vertex<>("v1"), new Vertex<>("v3"));
		graph2.display();
		graph2.deleteArc(new Vertex<>("v2"), new Vertex<>("v4"));
		graph2.display();
//******************************************************************************************************
		// 无向图测试

		String[] data3 = { "v1", "v2", "v3", "v4", "v5" };
		String[] tail3 = { "v1", "v1", "v2", "v2", "v3", "v3" };
		String[] head3 = { "v2", "v4", "v3", "v5", "v4", "v5" };
		MatrixGraph<String> graph3 = MatrixGraph.createGraph(GraphKind.UDG, data3, tail3, head3, null, null);
		graph3.display();
		System.out.println("--------------------");

		graph3.deleteVex(new Vertex<>("v3"));
		graph3.display();
		graph3.insertVex(new Vertex<>("v6"));
		graph3.insertArc(new Vertex<>("v6"), new Vertex<>("v4"), 0, null);
		graph3.display();
		graph3.destroy();
		graph3.display();

		System.out.println(graph3.firstAdjVex(new Vertex<>("v1")));
		System.out.println(graph3.firstAdjVex(new Vertex<>("v2")));
		System.out.println(graph3.firstAdjVex(new Vertex<>("v4")));
		System.out.println("------------------");
		System.out.println(graph3.nextAdjVex(new Vertex<>("v1"), new Vertex<>("v3")));
		System.out.println(graph3.nextAdjVex(new Vertex<>("v1"), new Vertex<>("v2")));
		System.out.println(graph3.nextAdjVex(new Vertex<>("v1"), new Vertex<>("v4")));

		graph3.insertVex(new Vertex<>("v3"));
		System.out.println(graph3.putVertex(new Vertex<>("v3"), "v5"));
		graph3.display();

		System.out.println(graph3.getVertex(new Vertex<>("v3")));
		graph3.deleteArc(new Vertex<>("v1"), new Vertex<>("v4"));
		graph3.display();
		graph3.deleteArc(new Vertex<>("v3"), new Vertex<>("v5"));
		graph3.display();

		graph3.DFSTraverse();
//*********************************************************************************************************
		// 课本示例，无向图
		String[] data4 = { "A", "B", "C", "D", "E", "F", "G" };
		String[] tail4 = { "A", "A", "A", "A", "A", "A", "B", "D", "F" };
		String[] head4 = { "B", "C", "D", "E", "F", "G", "C", "E", "G" };
		MatrixGraph<String> graph4 = MatrixGraph.createGraph(GraphKind.UDG, data4, tail4, head4, null, null);
		graph4.display();
		System.out.println("--------------------");
		graph4.BFSTraverse();
		graph4.searchPath(new Vertex<>("F"), new Vertex<>("C"));
//*********************************************************************************************************
		// 测试求最短路径
		String [] data5 = {"1","2","3","4","5","6","7","8","9"};
		String[] tail5 = { "1", "1", "1", "1", "2", "4", "4", "5", "6", "7", "7", "8" };
		String[] head5 = { "2", "3", "4", "7", "3", "5", "6", "6", "8", "8", "9", "9" };
		MatrixGraph<String> graph5 = MatrixGraph.createGraph(GraphKind.UDG, data5, tail5, head5, null, null);
		graph5.display();
		graph5.searchMinPath(new Vertex<>("9"), new Vertex<>("1"));
//*********************************************************************************************************
		// 测试最小生成树 
		String[] data6 = { "A", "B", "C", "D", "E", "F", "G", "H"};
		String[] tail6 = { "A", "A", "B", "B", "B", "C", "C", "D", "D", "D", "D", "E", "F", "G" };
		String[] head6 = { "B", "C", "C", "D", "E", "D", "H", "E", "F", "G", "H", "F", "G", "H" };
		int[] weight6 = { 4, 2, 3, 5, 9, 5, 5, 7, 6, 5, 4, 3, 2, 6 };
		MatrixGraph<String> graph6 = MatrixGraph.createGraph(GraphKind.UDN, data6, tail6, head6, weight6,null);
		System.out.println("--------------------");
		graph6.miniSpanTreeOfPrim();System.out.println("--------------------");
		graph6.miniSpanTreeOfKruskal();
//*********************************************************************************************************
		// 测试求关节点
		String[] data7 = { "A", "B", "C", "D", "E", "F", "G", "H" };
		String[] tail7 = { "A", "A", "B", "C", "C", "C", "A", "A", "G" };
		String[] head7 = { "B", "F", "C", "D", "E", "F", "G", "H", "H" };
		MatrixGraph<String> graph7 = MatrixGraph.createGraph(GraphKind.UDG, data7, tail7, head7, null,null);
		graph7.findArticul();
//*********************************************************************************************************
		// 测试Dijkstra算法求最短路径 
		String[] data8 = { "A", "B", "C", "D", "E", "F","G",};
		String[] tail8 = { "A", "A", "A", "B", "C", "C", "D", "E", "F", "F", "G" };
		String[] head8 = { "B", "C", "D", "E", "E", "F", "G", "G", "D", "G", "B" };
		int[] weight8 = { 15, 2, 10, 6, 7, 4, 4, 9, 5, 10, 4 };
		MatrixGraph<String> graph8 = MatrixGraph.createGraph(GraphKind.DN, data8, tail8, head8, weight8, null);
		String[] path =	graph8.shortestPathOfDijkstra(new Vertex<>("A"));
		for(String step:path){
			System.out.println(step);
		}
//*********************************************************************************************************
		// 测试Floyd算法求最短路径
		String[] data9 = { "A", "B", "C", "D" };
		String[] tail9 = { "A", "A", "B", "C", "C", "D" };
		String[] head9 = { "B", "D", "C", "D", "A", "B" };
		int[] weight9 = { 1, 3, 1, 2, 5, 4 };
		MatrixGraph<String> graph9 = MatrixGraph.createGraph(GraphKind.DN, data9, tail9, head9, weight9, null);
		String[][] path9 = graph9.shortestPathOfFloyd();
		for(String[] steps:path9){
			for (String step : steps) {
				System.out.println(step);
			}
		}
//*********************************************************************************************************	
		// 测试拓扑排序
		String[] data10 = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
		String[] tail10 = { "A", "B", "B", "C", "D", "D", "G", "E", "F", "G", "H" };
		String[] head10 = { "D", "D", "E", "E", "F", "G", "E", "H", "I", "I", "I" };
		MatrixGraph<String> graph10 = MatrixGraph.createGraph(GraphKind.DG, data10, tail10, head10, null,null);
		graph10.topologicalSort();	// B-E 没有回路 E-B有回路
//*********************************************************************************************************
		// 测试关键路径
		String[] data11 = { "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8", "v9" };
		String[] tail11 = { "v1", "v1", "v1", "v2", "v3", "v4", "v5", "v5", "v6", "v7", "v8" };
		String[] head11 = { "v2", "v3", "v4", "v5", "v5", "v6", "v7", "v8", "v8", "v9", "v9" };
		int []weight11 = { 6, 4, 5, 1, 1, 2, 8, 7, 4, 2, 4 };
		MatrixGraph<String> graph11 = MatrixGraph.createGraph(GraphKind.DN, data11, tail11, head11, weight11,null);
		graph11.criticalPath();
	}
}
