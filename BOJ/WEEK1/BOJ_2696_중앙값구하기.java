package BOJ.WEEK1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 수열을 읽고 홀수 번째 마다, 지금까지 입력 받은 값의 중앙값을 출력하는 프로그램을 작성하시오
 * 1. 이분 탐색으로 바로 정렬된 위치에 삽입 하고 삽입되는 인덱스에 중간값을 사용
 * 2. 우선순위 큐 앞의 절반이 들어갈 Queue와 뒤의 절반이 들어갈 Queue
 */
public class BOJ_2696_중앙값구하기 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static final int NEW_LINE = 10;
	static int testCase;
	static int totalNum;
	static PriorityQueue<Integer> frontQueue;
	static PriorityQueue<Integer> backQueue;

	public static void solve() throws IOException {
		int totalNum = Integer.parseInt(br.readLine().trim());
		sb.append((totalNum + 1) / 2).append("\n");

		//상위 절반이 들어갈 큐와 하위 절반이 들어갈 큐
		frontQueue = new PriorityQueue<>();
		backQueue = new PriorityQueue<>(Collections.reverseOrder());

		st = new StringTokenizer(br.readLine().trim());
		int val = Integer.parseInt(st.nextToken());
		frontQueue.offer(val);
		sb.append(frontQueue.peek()).append(" ");
		for (int idx = 2; idx <= totalNum; idx++) {
			val = Integer.parseInt(st.nextToken());
			// 짝수일 때
			if ((idx & 1) == 0) {
				if (val > frontQueue.peek()) {
					frontQueue.offer(val);
					backQueue.offer(frontQueue.poll());
				} else {
					backQueue.offer(val);
				}
			} else { //홀수일 때
				// 중앙 값을 항상 frontQueue에 마지막에 저장되게 한다.
				if (val > backQueue.peek()) {
					frontQueue.offer(val);
				} else {
					frontQueue.offer(backQueue.poll());
					backQueue.offer(val);
				}
				// 중앙 값은 frontQueue에 저장된 가장 낮은 값
				sb.append(frontQueue.peek()).append(" ");
			}

			// 10개마다 새로운 입력
			if ((idx % NEW_LINE) == 0) {
				st = new StringTokenizer(br.readLine().trim());
			}
			// 20개마다 개행
			if ((idx % (2 * NEW_LINE)) == 0) {
				sb.append("\n");
			}
		}
		sb.append("\n");
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 0; tc < testCase; tc++) {
			solve();
		}
		System.out.println(sb);
	}

}
