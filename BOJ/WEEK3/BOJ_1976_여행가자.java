package BOJ.WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Union find로 갈 수 있는 경로의 경우 하나의 서로소 집합으로 다 같은 부모를 가지고 있으면 여행가능
 */
public class BOJ_1976_여행가자 {
	static BufferedReader br;
	static StringTokenizer st;
	static int numCity, numPlan;
	static int[] parents, ranks, plans;
	static int[][] adjMatrix;

	public static int find(int val) {
		if (parents[val] == val) {
			return val;
		}
		// return parents[val];
		return parents[val] = find(parents[val]);
	}

	public static void union(int val1, int val2) {
		int parent1 = find(val1);
		int parent2 = find(val2);
		// 이미 같은 집합인 경우
		if (parent1 == parent2) {
			return;
		}
		if (ranks[parent1] > ranks[parent2]) {
			parents[parent2] = parent1;
		} else {
			parents[parent1] = parent2;
			if (ranks[parent1] == ranks[parent2]) {
				ranks[parent2]++;
			}
		}
	}

	public static boolean isPossible() {
		// 계획의 모든 곳에 대해 경로 압축 수행
		for (int idx = 0; idx < numPlan; idx++) {
			find(plans[idx]);
		}
		// 모든 부모가 같은지 확인
		int parent = parents[plans[0]];
		for (int idx = 1; idx < numPlan; idx++) {
			if (parent != parents[plans[idx]]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		numCity = Integer.parseInt(br.readLine().trim());
		parents = new int[numCity + 1];
		// 초기 부모는 자기 자신
		for (int idx = 1; idx <= numCity; idx++) {
			parents[idx] = idx;
		}
		ranks = new int[numCity + 1];
		adjMatrix = new int[numCity + 1][numCity + 1];

		numPlan = Integer.parseInt(br.readLine().trim());
		plans = new int[numPlan];

		for (int row = 1; row <= numCity; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 1; col <= numCity; col++) {
				int connection = Integer.parseInt(st.nextToken());
				adjMatrix[row][col] = connection;
				if (connection == 1) {
					union(row, col);
				}
			}
		}

		// plans에 있는 모든 부모가 같은지 확인
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < numPlan; idx++) {
			plans[idx] = Integer.parseInt(st.nextToken());
		}

		if (isPossible()) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
}
