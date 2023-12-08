package BOJ.WEEK2;

import java.util.*;
import java.io.*;

/**
 * Queue넣고 동작 1과 2를 반복시킨다.
 * 동작 1 수행 후 남은 숫자가 1일 때 중지
 */
public class BOJ_2164_카드2 {
	static BufferedReader br;
	static final int TERMINATE = 1;
	static int numCard;
	static Queue<Integer> cardQueue;

	public static void remove() {
		cardQueue.poll();
		numCard--;
	}

	public static void change() {
		cardQueue.offer(cardQueue.poll());
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		numCard = Integer.parseInt(br.readLine().trim());

		cardQueue = new ArrayDeque<>();
		// 카드 크기 순으로 삽입
		for (int idx = 1; idx <= numCard; idx++) {
			cardQueue.offer(idx);
		}

		while (true) {
			if (numCard == TERMINATE) {
				break;
			}
			// 동작 1
			remove();
			// 동작 2
			change();
		}
		System.out.println(cardQueue.poll());
	}
}
