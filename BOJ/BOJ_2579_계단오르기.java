package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 계단에는 일정한 점수가 있고 오르는 규칙은 다음과 같다.
 * 1. 한번에 한 계단 또는 두계단 오를 수 있다.
 * 2. 연속된 세 개의 계단 모두 밟아서는 안된다. 단, 시작점은 포함되지 않는다.
 * 3. 마지막 도착 계단은 반드시 밟아야한다.
 * 이 때 이 게임에서 얻을 수 있는 총 점수의 최댓값을 구하는 프로그램을 구하여라
 * => DP
 */
public class BOJ_2579_계단오르기 {
	static BufferedReader br;
	static int numStair;
	static int[] scores;
	static int[][] dp;

	public static void getInput() throws IOException {
		numStair = Integer.parseInt(br.readLine().trim());
		scores = new int[numStair + 1];
		for (int idx = 1; idx <= numStair; idx++) {
			scores[idx] = Integer.parseInt(br.readLine().trim());
		}
		dp = new int[numStair + 1][2];
	}

	public static void dp() {
		// 이전에 선택하지 않았으면 0, 했으면 1번에 값을 저장한다.
		// dp[0][0] = score1 dp[0][1] = 0
		// dp[1][0] = score2 dp[1][1] = dp[0][0] + score2
		// dp[2][0] = dp[0][0] + scpre 3, dp[2][1] = dp[1][0] + score2
		dp[1][0] = scores[1];
		for (int idx = 2; idx <= numStair; idx++) {
			dp[idx][0] = Math.max(dp[idx - 2][0], dp[idx - 2][1]) + scores[idx];
			dp[idx][1] = dp[idx - 1][0] + scores[idx];
		}
	}

	public static void solution() {
		int ans = Math.max(dp[numStair][0], dp[numStair][1]);
		System.out.println(ans);
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 입력처리
		getInput();
		// DP 수행
		dp();
		// 출력
		solution();
	}
}
