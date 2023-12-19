package BOJ.WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 벨만포드를 통해 음의 순환이 발생하면 -1
 * 그렇지 않은 경우 1번 도시에서 출발 해서 가장 빠른 시간을 순서대로 출력하며 경로가 없다면 -1을 출력한다.
 */
public class BOJ_11657_타임머신 {
	static BufferedReader br;
	static StringTokenizer st;
	static int numVertex, numEdge;
	static final long INF = 1_000_000_000;
	static long[] distanceList;
	static Edge[] edgeList;

	static class Edge{
		int from;
		int to;
		long cost;
		public Edge(int from, int to, long cost){
			this.from = from;
			this.to = to;
			this.cost= cost;
		}
	}

	public static boolean bellmanFord(){
		int start = 1;
		distanceList[start] = 0;
		for (int idx = 0; idx < numVertex; idx++){
			for(int edgeIdx = 0; edgeIdx < numEdge; edgeIdx++){
				Edge curEdge = edgeList[edgeIdx];
				int from = curEdge.from;
				int to = curEdge.to;
				long cost = curEdge.cost;
				if(distanceList[from] != INF && distanceList[to] > distanceList[from] + cost){
					distanceList[to] = distanceList[from] + cost;
					if (idx == numVertex-1){
						return true;
					}
				}
			}
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		numVertex = Integer.parseInt(st.nextToken());
		numEdge = Integer.parseInt(st.nextToken());

		edgeList = new Edge[numEdge];
		for (int idx =0; idx < numEdge; idx++){
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long cost = Long.parseLong(st.nextToken());
			edgeList[idx] = new Edge(from, to, cost);
		}

		distanceList = new long[numVertex + 1];
		for (int idx = 1; idx <= numVertex; idx++){
			distanceList[idx] = INF;
		}

		if (bellmanFord()){
			System.out.println(-1);
		}else {
			for (int idx = 2; idx <= numVertex; idx++){
				if (distanceList[idx] == INF){
					System.out.println(-1);
				}else {
					System.out.println(distanceList[idx]);
				}
			}
		}

	}
}
