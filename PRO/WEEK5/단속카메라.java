/**
 * PriorityQueue 두개 생성
 * 진입시점으로 정렬
 * 진출시점으로 정렬
 **/

import java.util.*;

class Solution {
	PriorityQueue<Info> inQueue;
	PriorityQueue<Info> outQueue;

	class Info implements Comparable<Info> {
		int in;
		int out;

		public Info(int in, int out) {
			this.in = in;
			this.out = out;
		}

		@Override
		public int compareTo(Info o) {
			return this.in - o.in;
		}
	}

	public int solution(int[][] routes) {
		inQueue = new PriorityQueue<>();
		outQueue = new PriorityQueue<>((info1, info2) -> info1.out - info2.out);
		for (int idx = 0; idx < routes.length; idx++) {
			inQueue.offer(new Info(routes[idx][0], routes[idx][1]));
		}
		int answer = 0;
		while (!inQueue.isEmpty()) {
			// 하나를 빼서 넣는다.
			outQueue.offer(inQueue.poll());
			if (inQueue.isEmpty()) {
				answer++;
				break;
			}
			while (inQueue.peek().in <= outQueue.peek().out) {
				outQueue.offer(inQueue.poll());
				if (inQueue.isEmpty()) {
					break;
				}
			}
			outQueue.clear();
			answer++;
		}
		return answer;
	}
}