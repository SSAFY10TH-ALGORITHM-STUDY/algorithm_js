/**
 * 현재 연결된 노드 중에 연결안된 노드 중 가장 저렴한 비용을 골라서 연결 시키는 것
 * 크루스칼
 **/

import java.util.*;

class Solution {
	int[] parent;
	int[] rank;

	public class Edge implements Comparable<Edge> {
		int from;
		int to;
		int cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	public int find(int idx) {
		if (idx == parent[idx]) {
			return idx;
		}
		return parent[idx] = find(parent[idx]);

	}

	public boolean union(int val1, int val2) {
		int parent1 = find(val1);
		int parent2 = find(val2);
		if (parent1 == parent2) {
			return false;
		}
		if (rank[parent1] > rank[parent2]) {
			parent[parent2] = parent1;
		} else {
			parent[parent1] = parent2;
			if (rank[parent1] == rank[parent2]) {
				rank[parent2]++;
			}
		}
		return true;
	}

	public int solution(int n, int[][] costs) {
		// 두 노드가 연결되었는지 확인해야함 -> unionfind
		parent = new int[n];
		rank = new int[n];
		for (int idx = 0; idx < n; idx++) {
			parent[idx] = idx;
		}

		List<Edge> edgeList = new ArrayList<>();
		for (int idx = 0; idx < costs.length; idx++) {
			int[] info = costs[idx];
			edgeList.add(new Edge(info[0], info[1], info[2]));
		}
		// 비용이 낮은 순으로 정렬
		Collections.sort(edgeList);
		int answer = 0;
		for (int idx = 0; idx < edgeList.size(); idx++) {
			Edge edge = edgeList.get(idx);
			if (union(edge.from, edge.to)) {
				answer += edge.cost;
			}
		}
		return answer;
	}
}