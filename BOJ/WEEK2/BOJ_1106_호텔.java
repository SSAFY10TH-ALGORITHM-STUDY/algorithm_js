package BOJ.WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 도시별 홍보 비용, 몇명 늘어나는지 가 있다.
 * 최소 C명 늘이기 위해 투자해야 하는 최솟값을 구하는 프로그램을 구하여라
 * [입력]
 * C, N
 * 홍보비용, 고객 수
 * [unbounded knapsack]
 */
public class BOJ_1106_호텔 {
	static BufferedReader br;
	static StringTokenizer st;

	static final int MAX_PEOPLE = 1100;
	static int numCity, targetNum;
	static int[] dp;
	// 비용, 고객 수
	static int[] costList;
	static int[] profitList;

	public static void getInput() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		targetNum = Integer.parseInt(st.nextToken());
		numCity = Integer.parseInt(st.nextToken());

		costList = new int[numCity];
		profitList = new int[numCity];

		for (int idx = 0; idx < numCity; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			costList[idx] = Integer.parseInt(st.nextToken());
			profitList[idx] = Integer.parseInt(st.nextToken());
		}
	}

	public static void solve() {
		dp = new int[MAX_PEOPLE + 1];
		for (int idx = 1; idx <= MAX_PEOPLE; idx++) {
			dp[idx] = MAX_PEOPLE * 101;
		}
		for (int idx = 0; idx < numCity; idx++) {
			int cost = costList[idx];
			int people = profitList[idx];
			for (int pIdx = 0; pIdx <= MAX_PEOPLE; pIdx++) {
				int iter = 1;
				while ((iter * people + pIdx) <= MAX_PEOPLE) {
					dp[iter * people + pIdx] = Math.min(dp[iter * people + pIdx],
						dp[(iter - 1) * people + pIdx] + cost);
					iter++;
				}
			}
		}
		int minValue = Integer.MAX_VALUE;
		for (int idx = MAX_PEOPLE; idx >= targetNum; idx--) {
			if (minValue > dp[idx]) {
				minValue = dp[idx];
			}
		}
		System.out.println(minValue);
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		getInput();
		solve();

	}
}
