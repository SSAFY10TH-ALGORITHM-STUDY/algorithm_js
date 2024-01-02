/**
 * 플로이드워셜
 **/
class Solution {
	int[][] adjMatrix;
	// 최대 100
	final int INF = 1000;

	public int solution(int n, int[][] results) {
		adjMatrix = new int[n + 1][n + 1];
		// 인접행렬 초기화
		for (int from = 1; from <= n; from++) {
			for (int to = 1; to <= n; to++) {
				adjMatrix[from][to] = INF;
			}
		}

		// 승부 결과 적용
		for (int idx = 0; idx < results.length; idx++) {
			int[] result = results[idx];
			int winner = result[0];
			int loser = result[1];
			adjMatrix[loser][winner] = 1;
		}

		// 플로이드 워셜
		for (int mid = 1; mid <= n; mid++) {
			for (int start = 1; start <= n; start++) {
				for (int end = 1; end <= n; end++) {
					// 업데이트할 수 있는 경로가 있다면 업데이트
					if (adjMatrix[start][end] > adjMatrix[start][mid] + adjMatrix[mid][end]) {
						adjMatrix[start][end] = adjMatrix[start][mid] + adjMatrix[mid][end];
					}

				}
			}
		}

		int answer = 0;
		for (int from = 1; from <= n; from++) {
			boolean isExact = true;
			for (int to = 1; to <= n; to++) {
				if (from == to) {
					continue;
				}
				// 접근할 수 없는 노드가 있다면 정확하게 알 수 없는 것
				if ((adjMatrix[from][to] == INF) && (adjMatrix[to][from] == INF)) {
					isExact = false;
					break;
				}
			}
			if (isExact) {
				answer++;
			}
		}

		return answer;
	}
}