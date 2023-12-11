package BOJ.WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N x M 행렬을 그린 다음 각 칸에 -10,000 ~ 10,000 사이의 정수를 쓴다 그 다음 부분 행렬의 합이 최대가 되는 부분 행렬을 구하라.
 * 200 X 200
 */
public class BOJ_1749_점수따먹기 {
	static BufferedReader br;
	static StringTokenizer st;
	static int rowSize, colSize;
	static int[][] map;
	static int[][] prefixMap;
	static int[][] dp;

	public static void getInput() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		map = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}

	public static void getPrefixArr() {
		prefixMap = new int[rowSize + 1][colSize + 1];
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				prefixMap[row + 1][col + 1] = prefixMap[row + 1][col] + prefixMap[row][col + 1]
					- prefixMap[row][col] + map[row][col];
			}
		}
	}

	// 구간합을 구해주는 함수
	public static int prefixSum(int row1, int col1, int row2, int col2) {
		return prefixMap[row2 + 1][col2 + 1] - prefixMap[row1][col2 + 1] - prefixMap[row2 + 1][col1]
			+ prefixMap[row1][col1];
	}

	public static void calMaxVal(int rSize, int cSize) {
		for (int row = 0; row <= rSize; row++) {
			for (int col = 0; col <= cSize; col++) {
				dp[rSize][cSize] = Math.max(dp[rSize][cSize], prefixSum(row, col, rSize, cSize));
			}
		}

	}

	public static void solve() {
		dp = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				dp[row][col] = Integer.MIN_VALUE;
				calMaxVal(row, col);
			}
		}

		int maxVal = Integer.MIN_VALUE;

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (dp[row][col] > maxVal) {
					maxVal = dp[row][col];
				}
			}
		}
		System.out.println(maxVal);
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 입력을 받는다.
		getInput();
		// 누적합 배열을 생성한다.
		getPrefixArr();

		solve();
	}
}
