import java.util.*;

class Solution {
	boolean[] visited;
	Queue<Integer> queue;

	public void bfs(int start, int[][] computers) {
		queue = new ArrayDeque<>();
		// 출발 지점 칠하기
		visited[start] = true;
		queue.offer(start);
		while (!queue.isEmpty()) {
			int from = queue.poll();
			for (int to = 0; to < computers.length; to++) {
				// 이미 방문한 경우 패스
				if (visited[to]) {
					continue;
				}
				// 이어져 있지 않은 경우 패스
				if (computers[from][to] == 0) {
					continue;
				}
				queue.offer(to);
				visited[to] = true;
			}

		}

	}

	public int solution(int n, int[][] computers) {
		int answer = 0;
		visited = new boolean[computers.length];
		for (int start = 0; start < computers.length; start++) {
			// 해당 노드를 이미 방분한 경우 pass
			if (visited[start]) {
				continue;
			}
			// bfs 수행
			bfs(start, computers);
			// network 수 증가
			answer += 1;
		}
		return answer;
	}
}