import java.util.*;

class Solution {
	public int solution(int[] scoville, int K) {
		int answer = 0;

		// 스코빌 지수가 낮은 순의 우선순위 큐
		PriorityQueue<Integer> scovillQueue = new PriorityQueue<>();
		for (int idx = 0; idx < scoville.length; idx++) {
			int scovill = scoville[idx];
			scovillQueue.offer(scovill);
		}

		while (true) {
			// 기준치를 넘겼다면 제조중단.
			if (scovillQueue.peek() >= K) {
				break;
			}

			int scv1 = scovillQueue.poll();

			// 하나만 남은경우 더 이상 만들 수 없음
			if (scovillQueue.isEmpty()) {
				answer = -1;
				break;
			}

			int scv2 = scovillQueue.poll();
			int scovill = scv1 + (2 * scv2);
			scovillQueue.offer(scovill);
			answer++;
		}

		return answer;
	}
}