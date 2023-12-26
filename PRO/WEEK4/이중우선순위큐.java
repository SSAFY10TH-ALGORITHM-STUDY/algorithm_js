import java.util.*;

class Solution {
	public int[] solution(String[] operations) {
		int[] answer = new int[2];
		int maxCnt = 0;
		int minCnt = 0;
		int totalCnt = 0;

		PriorityQueue<Integer> minQueue = new PriorityQueue<>();
		PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

		for (int idx = 0; idx < operations.length; idx++) {
			String operation = operations[idx];
			StringTokenizer st = new StringTokenizer(operation);
			String action = st.nextToken();
			int value = Integer.parseInt(st.nextToken());
			if (action.equals("I")) {
				//삽입 수행
				minQueue.offer(value);
				maxQueue.offer(value);
				totalCnt++;
			} else if (action.equals("D")) {
				//삭제 수행
				if ((maxCnt + minCnt) >= totalCnt)
					continue;
				if (value == 1) {
					maxQueue.poll();
					maxCnt++;
				} else if (value == -1) {
					minQueue.poll();
					minCnt++;
				}
			}
			if ((maxCnt + minCnt) == totalCnt) {
				minQueue.clear();
				maxQueue.clear();
			}
			;
		}

		if ((maxCnt + minCnt) < totalCnt) {
			answer[0] = maxQueue.poll();
			answer[1] = minQueue.poll();
		}

		return answer;
	}
}