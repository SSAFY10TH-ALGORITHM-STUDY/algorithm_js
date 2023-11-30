package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 효성이는 길이가 N인 수열 A에서 X와 서로소인 수들을 골라 평균을 구해보려고 한다.
 * GCD가 1인 경우 서로소
 */
public class BOJ_21920_서로소평균 {
	static BufferedReader br;
	static StringTokenizer st;

	static int length, targetNum;

	static double sumVal, sumCnt;
	static int[] seq;

	// Greatest Common Division
	public static int gcd(int val1, int val2) {
		// val1에 큰 값이 오도록 한다.
		if (val1 < val2) {
			int tmp = val1;
			val1 = val2;
			val2 = tmp;
		}

		// val2가 0이 될 때까지 반복 0인 순간의 val1이 gcd
		while (val2 > 0) {
			int remain = val1 % val2;
			val1 = val2;
			val2 = remain;
		}
		return val1;
	}

	public static void setValue() throws IOException {
		length = Integer.parseInt(br.readLine().trim());
		seq = new int[length];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < length; idx++) {
			int value = Integer.parseInt(st.nextToken());
			seq[idx] = value;
		}
		targetNum = Integer.parseInt(br.readLine().trim());
	}

	public static void compare() {
		for (int idx = 0; idx < length; idx++) {
			int remain = gcd(targetNum, seq[idx]);
			// 최대 공약수가 1이면 서로소
			if (remain == 1) {
				sumVal += seq[idx];
				sumCnt++;
			}
		}
	}

	public static void solution() {
		System.out.println(sumVal / sumCnt);
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 입력을 받는다.
		setValue();
		// 목표값과 비교를 수행한다.
		compare();
		// 출력
		solution();
	}
}
