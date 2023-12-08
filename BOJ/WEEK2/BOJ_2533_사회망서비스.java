package BOJ.WEEK2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SNS에서 친구관계는 그래프로 표현할 수 있는데 사람은 정점으로 엣지는 친구관계를 나타낸다.
 * 어떤 새로운 아이디어를 먼저 받아들인 사람을 얼리 어답터라고 하는데
 * SNS의 모든 사람들은 얼리어답터이거나 아닌데, 얼리 어답터가 아닌 사람들은 자신의 모든 친구들이 얼리 어답터일 때만 아이디어를 받아들인다.
 * 친구 관계 트리가 주어 졌을 때 모든 개인이 새로운 아이디어를 수용하기 위한 최소 얼리 어답터의 수를 구하는 프로그램을 작성하라
 * [입력 ]s
 * 트리의 정점 개수 2<= N <= 1,000,000
 * N-1개의 정점간의 연결을 알려줌
 * [풀이 ]
 * 모든 유저가 얼리어답터인지 아닌지에 대한 부분집합 => 2^1,000,000 => 시간초과
 *
 */
public class BOJ_2533_사회망서비스 {
	static BufferedReader br;
	static StringTokenizer st;
	static int numNode;
	static int[][] dp;
	static Node[] adjList;
	static boolean[] visited;

	static class Node {
		int vertex;
		Node next;

		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
	}

	public static void getInput() throws Exception {
		numNode = Integer.parseInt(br.readLine().trim());
		adjList = new Node[numNode + 1];
		// 0: 얼리어답터가 아닌 경우, 1: 얼리어답터
		dp = new int[numNode + 1][2];
		// 모든 노드에 대해서 얼리어답터일 경우 최소 자기 자신은 얼리어답터이기에 1로 초기화
		for (int idx = 1; idx <= numNode; idx++) {
			dp[idx][1] = 1;
		}
		visited = new boolean[numNode + 1];
		for (int idx = 0; idx < numNode - 1; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			// 양방향 그래프
			adjList[start] = new Node(end, adjList[start]);
			adjList[end] = new Node(start, adjList[end]);
		}
	}

	public static void dfs(int node) {
		// 방문 처리
		visited[node] = true;
		for (Node curNode = adjList[node]; curNode != null; curNode = curNode.next) {
			// 이미 방문한 경우 자식 노드가 아님
			if (visited[curNode.vertex])
				continue;
			// 자식 노드로 타고 들어간다.
			dfs(curNode.vertex);
			// 해당 노드가 얼리어답터가 아닌 경우 자식 노드가 얼리어답터여야 한다.
			dp[node][0] += dp[curNode.vertex][1];
			// 해당 노드가 얼리어답터인 경우 자식 노드는 얼리어답터일 수도 있고 아닐 수도 있기에 그 중 작은 값을 더한다.
			dp[node][1] += Math.min(dp[curNode.vertex][0], dp[curNode.vertex][1]);
		}
	}

	public static void solve() {
		// root Node 번호 설정
		// 트리는 방향성이 없기 대문에 어느 꼭지 점을 루트로 하든 상관없음
		int root = 1;
		dfs(root);
		System.out.println(Math.min(dp[root][0], dp[root][1]));
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 입력 처리
		getInput();
		// DP
		solve();
	}
}
