package BOJ.WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 0으로 끝나는 경우/ 9로 끝나는 경우 /나머지
 */
public class BOJ_10844_쉬운계단수 {
	static BufferedReader br;
	static StringTokenizer st;
	static int digit;
	static long numStair;
	static long[][] dp;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		digit = Integer.parseInt(br.readLine().trim());
		dp = new long[digit + 1][10];
		for (int idx = 1; idx < 10; idx++) {
			dp[1][idx] = 1;
		}

		for (int idx = 2; idx <= digit; idx++) {
			dp[idx][0] += dp[idx - 1][1];
			dp[idx][9] += dp[idx - 1][8];
			for (int num = 1; num < 9; num++) {
				dp[idx][num] += (dp[idx - 1][num - 1] + dp[idx - 1][num + 1]);
			}
			for (int num = 0; num < 10; num++) {
				dp[idx][num] %= 1_000_000_000;
			}
		}

		numStair = 0L;
		for (int idx = 0; idx < 10; idx++) {
			numStair += dp[digit][idx];
		}
		System.out.println(numStair % 1_000_000_000);
	}
}
