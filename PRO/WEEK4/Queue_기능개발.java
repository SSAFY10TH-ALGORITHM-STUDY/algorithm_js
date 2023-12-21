import java.util.*;

/**
 * Queue에 넣고 Day를 1씩 증가시키면서 반복 수행
 **/
class Solution {
	static final int DONE = 100;

	public class Process {
		int progress;
		int speed;

		public Process(int progress, int speed) {
			this.progress = progress;
			this.speed = speed;
		}
	}

	public int[] solution(int[] progresses, int[] speeds) {
		List<Integer> ansList = new ArrayList<>();

		Queue<Process> processQueue = new ArrayDeque<>();
		for (int idx = 0; idx < progresses.length; idx++) {
			processQueue.offer(new Process(progresses[idx], speeds[idx]));
		}

		int day = 1;
		while (!processQueue.isEmpty()) {
			int doneCnt = 0;
			while (true) {
				if (processQueue.peek() == null) {
					break;
				}
				int progress = processQueue.peek().progress + day * processQueue.peek().speed;
				if (progress < DONE) {
					break;
				}
				processQueue.poll();
				doneCnt++;
			}
			if (doneCnt != 0) {
				ansList.add(doneCnt);
			}
			day++;
		}

		int[] answer = new int[ansList.size()];
		for (int idx = 0; idx < ansList.size(); idx++) {
			answer[idx] = ansList.get(idx);
		}
		return answer;
	}
}