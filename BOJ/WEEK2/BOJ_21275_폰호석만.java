package BOJ.WEEK2;

import java.util.*;
import java.io.*;

/**
 * 어떤 진법의 수가 주어져도 다른 진법으로 변환이 가능하다.
 * 1. 수 3개 X, A,B를 결정하며 X는 10진법이다. X를 A진법으로 표현한 수와 B진법으로 표한한 수를 종이 써 놓는다.
 * 2. 좋이에 써져 있는 수를 보여준다. 주어진 2개의 숫자를 통해 원래 숫자인 X, A,B를 계산한다.
 * 조건을 만족하는 (X,A,B)가 여러개라면 Multiple, 없다면 impossible을 출력한다.
 * A랑 B랑 같은 경우도 패스
 */
public class BOJ_21275_폰호석만 {

	static final int MAX_DECIMAL = 36;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int ansCnt;
	static long target;
	static int targetA, targetB;
	static long[] ansList1;
	static long[] ansList2;

	private static long convertByDecimal(int decimal, String a) {
		char[] number = a.toCharArray();
		long ans = 0L;
		for (int idx = number.length - 1; idx >= 0; idx--){
			char tmp = number[idx];
			// a~z인 경우
			if (tmp - '0' > 10){
				int val = tmp - 'a' + 10;
				if (val >= decimal){
					return -1;
				}
				ans += (long) Math.pow(decimal, (number.length - idx - 1)) * val;
			}else{
				// 0 ~ 9인 경우
				int val = tmp - '0';
				if (val >= decimal){
					return -1;
				}
				ans += (long) Math.pow(decimal, (number.length - idx - 1)) * val;
			}
		}
		return ans;
	}
	public static void convertNumber(String a, String b){
		// 각 숫자를 진수에 넣는다.
		for (int idx = 2; idx <= MAX_DECIMAL; idx++){
			long ans1 = convertByDecimal(idx, a);
			long ans2 = convertByDecimal(idx, b);
			ansList1[idx] = ans1;
			ansList2[idx] = ans2;
		}
	}

	public static void countAns(){
		for (int idx1 = 2; idx1 <= MAX_DECIMAL; idx1++){
			long ans1 = ansList1[idx1];
			for (int idx2 = 2; idx2 <= MAX_DECIMAL; idx2++){
				// 같은 진수면 무효
				if (idx1 == idx2) continue;
				long ans2 = ansList2[idx2];
				if (ans1 >= 0 && ans2 >= 0){
					// 두 답이 같지 않으면 답이 아님
					if (ans1 != ans2) continue;
					ansCnt++;
					target = ans1;
					targetA = idx1;
					targetB = idx2;
				}
			}
		}

	}
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		String num1 = st.nextToken();
		String num2 = st.nextToken();

		ansList1 = new long[MAX_DECIMAL + 1];
		ansList2 = new long[MAX_DECIMAL + 1];
		convertNumber(num1, num2);
		countAns();
		if(ansCnt == 0){
			System.out.println("Impossible");
		}else if (ansCnt == 1){
			System.out.println(target +" " + targetA + " " + targetB);
		}else{
			System.out.println("Multiple");
		}

	}
}
