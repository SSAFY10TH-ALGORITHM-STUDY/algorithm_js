package BOJ.WEEK1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 양의 정수 N 개 더하기 연산자 P개 곱하기 연산자 Q개가 주어졌을 때
 * 가능한 연산 결과 중 최댓값을 출력한다.
 * 곱하기를 두고 각
 */
public class BOJ_21943_연산최대로 {
	static BufferedReader br;
	static StringTokenizer st;
	static int numNumber, numAdd, numMul;
	static int[] numList;
	static int maxNum;

	public static void getInput() throws IOException {
		numNumber = Integer.parseInt(br.readLine().trim());
		numList = new int[numNumber];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < numNumber; idx++) {
			numList[idx] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine().trim());
		numAdd = Integer.parseInt(st.nextToken());
		numMul = Integer.parseInt(st.nextToken());
	}

	public static void calMaxNum() {
		// 나눠야 하는 덩어리 수
		int numDivided = numMul + 1;
		// 곱셉연산 횟수가 0번이면
		if (numDivided == 1){
			for (int idx = 0;  idx < numNumber; idx++){
				maxNum += numList[idx];
			}
			return;
		}
		// 그렇지 않은 경우


	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 입력 처리
		getInput();
		// 최댓값 구하기
		calMaxNum();
		System.out.println(maxNum);
	}
}
