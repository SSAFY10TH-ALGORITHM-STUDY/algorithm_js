import java.util.*;

class Solution {
	final int MAX_SIZE = 50;
	// 상, 우, 하, 좌
	final int[] DELTA_ROW = {-1, 0, 1, 0};
	final int[] DELTA_COL = {0, 1, 0, -1};
	int[][] map;
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

	public void drawLine(int[][] rectangle) {
		for (int idx = 0; idx < rectangle.length; idx++) {
			int[] info = rectangle[idx];
			int leftX = 2 * info[0];
			int leftY = 2 * (MAX_SIZE - info[1]);
			int rightX = 2 * info[2];
			int rightY = 2 * (MAX_SIZE - info[3]);
			for (int row = rightY; row <= leftY; row++) {
				for (int col = leftX; col <= rightX; col++) {
					if (row == rightY || row == leftY || col == leftX || col == rightX) {
						if (map[row][col] == 1) {
							continue;
						}
						map[row][col] += 1;
					} else {
						map[row][col] += 2;
					}
				}
			}
		}
	}

	public int findTarget(int characterX, int characterY, int itemX, int itemY) {
		int ans = 0;
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(characterY, characterX, 0));
		visited[characterY][characterX] = true;
		while (!queue.isEmpty()) {
			Pos pos = queue.poll();
			int row = pos.row;
			int col = pos.col;
			int step = pos.step;
			if (row == itemY && col == itemX) {
				ans = step;
				break;
			}
			for (int idx = 0; idx < DELTA_ROW.length; idx++) {
				int tmpRow = row + DELTA_ROW[idx];
				int tmpCol = col + DELTA_COL[idx];
				if (tmpRow < 0 || tmpCol < 0 || tmpRow > 2 * MAX_SIZE || tmpCol > 2 * MAX_SIZE) {
					continue;
				}
				if (visited[tmpRow][tmpCol]) {
					continue;
				}
				if (map[tmpRow][tmpCol] == 1) {
					queue.offer(new Pos(tmpRow, tmpCol, step + 1));
					visited[tmpRow][tmpCol] = true;
				}

			}
		}
		return ans / 2;
	}

	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		map = new int[2 * MAX_SIZE + 1][2 * MAX_SIZE + 1];
		visited = new boolean[2 * MAX_SIZE + 1][2 * MAX_SIZE + 1];
		// 사각형을 그린다.
		drawLine(rectangle);
		// 경로이동을 수행한다.
		int answer = findTarget(2 * characterX, 2 * (MAX_SIZE - characterY),
			2 * itemX, 2 * (MAX_SIZE - itemY));
		return answer;
	}
}