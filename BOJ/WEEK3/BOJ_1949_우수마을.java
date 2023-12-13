package BOJ.WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 마을로 이루어진 나라가 있다.
 * N개의 마을은 트리 구조이며
 * 1. N개의 마을 중 몇 개의 마을을 우수 마을로 선정 하려고한다.
 * 2. 우수 마을에 선정된 주민 수의 총합을 최대로 해야한다.
 * 3. 인접해 있는 두 마을을 모두 '우수 마을'로 선정할 수 없다.
 * 4. 적어도 하나의 '우수 마을' 과는 인접해 있어야 한다.
 */
public class BOJ_1949_우수마을 {
	static BufferedReader br;
	static StringTokenizer st;
	static int numVillage;
	static int[] numPeople;
	static boolean[] visited;
	static int[][] dp;
	static Node[] adjList;

	static class Node {
		int vertex;
		Node next;

		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
	}

	public static void getInput() throws IOException {
		numVillage = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		numPeople = new int[numVillage + 1];
		for (int idx = 1; idx <= numVillage; idx++) {
			numPeople[idx] = Integer.parseInt(st.nextToken());
		}

		adjList = new Node[numVillage + 1];
		for (int idx = 0; idx < numVillage - 1; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			//양방향
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}
	}

	public static void dfs(int node) {
		visited[node] = true;
		for (Node tmp = adjList[node]; tmp != null; tmp = tmp.next) {
			// 부모 노드인 경우 넘긴다.
			if (visited[tmp.vertex])
				continue;
			// 자식 노드인 경우
			dfs(tmp.vertex);
			// 현재 노드가 우수마을이 아닌 경우 자식 노드가 우수노드일 수도 아닐 수도 있음
			dp[node][0] += Math.max(dp[tmp.vertex][0], dp[tmp.vertex][1]);
			dp[node][1] += dp[tmp.vertex][0];
		}
	}

	public static void solve() {
		// root 노드 설정
		int root = 1;
		// 0: 우수 마을이 아닌 경우, 1: 우수 마을인 경우
		dp = new int[numVillage + 1][2];
		for (int idx = 1; idx <= numVillage; idx++) {
			dp[idx][1] = numPeople[idx];
		}
		visited = new boolean[numVillage + 1];
		dfs(root);
		System.out.println(Math.max(dp[root][0], dp[root][1]));
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		getInput();
		solve();
	}
}
