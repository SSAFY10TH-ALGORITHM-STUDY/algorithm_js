package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 얼음의 동서남북에 0으로 접한 수 만큼 얼음칸의 숫자가 감소한다.
 * 2. 빙산의 덩어리가 두 덩어리 이상으로 분리되는 최초의 시간을 구하여라.
 * 3. 전부 녹을 때 까지 두 덩어리 이상으로 분리되지 않으면 0을 출력한다.
 */
public class BOJ_2573_빙산 {
	static BufferedReader br;
	static StringTokenizer st;
	static final int WATER = 0;
	// 상 우 하 좌
	static final int[] DELTA_ROW = {-1, 0, 1, 0};
	static final int[] DELTA_COL = {0, 1, 0, -1};
	static int[][] map;
	static boolean[][] visited;
	static boolean isDivided;
	static int rowSize, colSize;
	static List<ICE> iceList;

	static class ICE {
		int row, col;
		int height;
		int waterCnt;

		public ICE(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}

		// 주위에 있는 물의 개수를 세는 함수
		public void calWaterCnt() {
			this.waterCnt = 0;
			for (int idx = 0; idx < DELTA_ROW.length; idx++) {
				int dRow = this.row + DELTA_ROW[idx];
				int dCOL = this.col + DELTA_COL[idx];
				if (isValid(dRow, dCOL) && map[dRow][dCOL] == WATER) {
					waterCnt++;
				}
			}
		}

		//녹기 진행
		public int melting() {
			this.height -= this.waterCnt;
			return Math.max(this.height, 0);
		}
	}

	static class Pos {
		int row, col;

		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static boolean isValid(int row, int col) {
		return !(row < 0 || col < 0 || row >= rowSize || col >= colSize);
	}

	public static void getInput() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		map = new int[rowSize][colSize];

		iceList = new ArrayList<>();
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; col++) {
				int val = Integer.parseInt(st.nextToken());
				if (val != WATER) {
					map[row][col] = val;
					ICE ice = new ICE(row, col, val);
					iceList.add(ice);
				}
			}
		}
	}

	public static void melting() {
		for (int idx = iceList.size() - 1; idx >= 0; idx--) {
			ICE ice = iceList.get(idx);
			// 각 얼음별 녹는 정도 확인
			ice.calWaterCnt();
		}

		for (int idx = iceList.size() - 1; idx >= 0; idx--) {
			ICE ice = iceList.get(idx);
			// 녹기를 수행하고 해당 높이가 0 이면
			int height = ice.melting();
			map[ice.row][ice.col] = height;
			if (height == WATER) {
				iceList.remove(idx);
			}
		}
	}

	public static void bfs(int row, int col) {
		Pos pos = new Pos(row, col);
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(pos);
		visited[row][col] = true;

		while (!queue.isEmpty()) {
			Pos curPos = queue.poll();
			int curRow = curPos.row;
			int curCol = curPos.col;
			for (int idx = 0; idx < DELTA_ROW.length; idx++) {
				int tmpRow = curRow + DELTA_ROW[idx];
				int tmpCol = curCol + DELTA_COL[idx];
				if (isValid(tmpRow, tmpCol) && !visited[tmpRow][tmpCol] && map[tmpRow][tmpCol] != WATER) {
					visited[tmpRow][tmpCol] = true;
					queue.offer(new Pos(tmpRow, tmpCol));
				}
			}
		}
	}

	// 빙산이 나눠진지 확인하는 메서드
	public static void checkDivide() {
		visited = new boolean[rowSize][colSize];
		int icebergCnt = 0;
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (map[row][col] != WATER && !visited[row][col]) {
					bfs(row, col);
					icebergCnt++;
				}
			}
		}

		if (icebergCnt > 1) {
			isDivided = true;
		}
	}

	private static void out(int time) {
		if (isDivided) {
			System.out.println(time);
		} else {
			System.out.println(0);
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 입력 처리
		getInput();

		isDivided = false;
		int time = 0;

		while (iceList.size() > 0) {
			// 확인
			checkDivide();

			if (isDivided) {
				break;
			}
			// 녹기
			melting();

			time++;
		}

		out(time);
	}

}
