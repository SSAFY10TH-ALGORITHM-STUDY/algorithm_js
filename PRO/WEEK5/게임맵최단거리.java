/**
 * bfs를 통해 최단거리 접근
 **/

import java.util.*;

class Solution {
	// 상, 우, 하, 좌
	final int[] DELTA_ROW = {-1, 0, 1, 0};
	final int[] DELTA_COL = {0, 1, 0, -1};
	Queue<Pos> queue;
	boolean[][] visited;

	class Pos {
		int row;
		int col;
		int step;

		public Pos(int row, int col, int step) {
			this.row = row;
			this.col = col;
			this.step = step;
		}
	}

	public int bfs(int[][] maps) {
		int rowSize = maps.length;
		int colSize = maps[0].length;
		visited = new boolean[rowSize][colSize];
		queue = new ArrayDeque<>();
		Pos pos = new Pos(0, 0, 1);
		int step = 0;
		queue.offer(pos);
		visited[0][0] = true;
		while (!queue.isEmpty()) {
			// 큐에서 빼준다.
			Pos current = queue.poll();
			int curRow = current.row;
			int curCol = current.col;
			int curStep = current.step;

			if (curRow == rowSize - 1 && curCol == colSize - 1) {
				step = curStep;
				break;
			}
			for (int idx = 0; idx < DELTA_ROW.length; idx++) {
				int tmpRow = curRow + DELTA_ROW[idx];
				int tmpCol = curCol + DELTA_COL[idx];
				// 벗어나는 경우
				if (tmpRow < 0 || tmpCol < 0 || tmpRow >= rowSize || tmpCol >= colSize) {
					continue;
				}
				// 이미 방문한 경우
				if (visited[tmpRow][tmpCol]) {
					continue;
				}
				// 벽인 경우
				if (maps[tmpRow][tmpCol] == 0) {
					continue;
				}
				// 큐에 삽입 후 방문 처리
				queue.offer(new Pos(tmpRow, tmpCol, curStep + 1));
				visited[tmpRow][tmpCol] = true;
			}
		}
		return step;
	}

	public int solution(int[][] maps) {
		int answer = bfs(maps);
		// 종료조건을 만족하지 못한경우
		if (answer == 0) {
			return -1;
		}
		return answer;
	}
}