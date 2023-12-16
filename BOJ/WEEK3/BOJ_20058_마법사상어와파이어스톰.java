package BOJ.WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2^n x 2^n 격자에서 파이어스톰을 시전하려고 한다.
 * L단계 설정 -> 2^L x 2^L
 */
public class BOJ_20058_마법사상어와파이어스톰 {
	static BufferedReader br;
	static StringTokenizer st;
	static final int[] DELTA_ROW = {-1, 0, 1, 0};
	static final int[] DELTA_COL = {0, 1, 0, -1};
	static int mapSize, numAction;
	static int maxSize;
	static boolean[][] visited;
	static int[][] map;

	public static void rotate90(int row, int col, int interval) {
		int[][] tmpMatrix = new int[interval][interval];
		for (int rIdx = row; rIdx < row + interval; rIdx++) {
			for (int cIdx = col; cIdx < col + interval; cIdx++) {
				tmpMatrix[rIdx - row][cIdx - col] = map[(row + col + interval - 1) - cIdx][col - row + rIdx];
			}
		}

		for (int rIdx = row; rIdx < row + interval; rIdx++) {
			for (int cIdx = col; cIdx < col + interval; cIdx++) {
				map[rIdx][cIdx] = tmpMatrix[rIdx - row][cIdx - col];
			}
		}

	}

	public static void melting() {
		int[][] tmpMatrix = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				// 이미 다 녹은 경우 통과
				if (map[row][col] == 0)
					continue;
				// 주변 얼음 개수
				int cnt = 0;
				for (int idx = 0; idx < DELTA_ROW.length; idx++) {
					int tmpRow = row + DELTA_ROW[idx];
					int tmpCol = col + DELTA_COL[idx];
					if (tmpRow < 0 || tmpCol < 0 || tmpRow >= mapSize || tmpCol >= mapSize)
						continue;
					if (map[tmpRow][tmpCol] == 0)
						continue;
					cnt++;
				}
				// 3개 보다 적으면 1 줄어든다.
				if (cnt < 3) {
					tmpMatrix[row][col] = 1;
				}
			}
		}

		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				map[row][col] -= tmpMatrix[row][col];
			}
		}
	}

	// 격자의 크기를 받아서 파이어볼 동작 수행
	public static void action(int pow) {
		int interval = (int)Math.pow(2, pow);
		for (int row = 0; row < mapSize; row += interval) {
			for (int col = 0; col < mapSize; col += interval) {
				// 회전 수행
				rotate90(row, col, interval);
			}
		}
		melting();
	}

	public static void remainIce() {
		int remainder = 0;
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				remainder += map[row][col];
			}
		}
		System.out.println(remainder);
	}

	public static int bfs(int row, int col) {
		int size = 0;
		Queue<int[]> queue = new ArrayDeque<>();
		visited[row][col] = true;
		if (map[row][col] == 0) {
			return 0;
		}
		queue.offer(new int[] {row, col});
		size++;
		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			int curRow = pos[0];
			int curCol = pos[1];
			// System.out.println(curRow +" " + curCol);
			for (int idx = 0; idx < DELTA_ROW.length; idx++) {
				int tmpRow = curRow + DELTA_ROW[idx];
				int tmpCol = curCol + DELTA_COL[idx];
				// 맵에서 벗어나는 경우 넘어간다.
				if (tmpRow < 0 || tmpCol < 0 || tmpRow >= mapSize || tmpCol >= mapSize)
					continue;
				// 이미 방문한 경우 넘어간다.
				if (visited[tmpRow][tmpCol])
					continue;
				visited[tmpRow][tmpCol] = true;
				if (map[tmpRow][tmpCol] == 0)
					continue;
				size++;
				queue.offer(new int[] {tmpRow, tmpCol});
			}
		}
		return size;
	}

	public static void findMaxSize() {
		visited = new boolean[mapSize][mapSize];
		maxSize = 0;
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				if (!visited[row][col]) {
					int size = bfs(row, col);
					maxSize = Math.max(maxSize, size);
				}
			}
		}
		System.out.println(maxSize);
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		mapSize = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
		numAction = Integer.parseInt(st.nextToken());

		map = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < numAction; idx++) {
			int pow = Integer.parseInt(st.nextToken());
			action(pow);
		}

		// 남은 얼음 수 출력
		remainIce();
		// 가장 큰 얼음 덩어리 출력
		findMaxSize();
	}

}
