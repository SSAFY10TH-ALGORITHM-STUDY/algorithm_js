// 해당 시점까지 들어온 요청 중에 가장 짧은 일 처리
// -> 요청 받기
// -> 반복

import java.util.*;

class Solution {
	static class Job implements Comparable<Job> {
		// 요청시간
		int request;
		// 소요시간
		int need;

		public Job(int request, int need) {
			this.request = request;
			this.need = need;
		}

		@Override
		public int compareTo(Job o) {
			return this.request - o.request;
		}

		@Override
		public String toString() {
			return "request = [" + this.request + "]" + "need= [" + this.need + "]";
		}
	}

	public int solution(int[][] jobs) {
		int answer = 0;
		List<Job> jobList = new ArrayList<>();

		for (int idx = 0; idx < jobs.length; idx++) {
			jobList.add(new Job(jobs[idx][0], jobs[idx][1]));
		}

		Collections.sort(jobList);
		// 우선순위 큐는 소요시간 순으로 삽입
		PriorityQueue<Job> jobQueue = new PriorityQueue<>((j1, j2) -> j1.need - j2.need);

		int time = 0;
		int idx = 0;

		while (!(idx >= jobList.size() && jobQueue.isEmpty())) {
			//삽입 수행
			while (idx < jobList.size() && jobList.get(idx).request <= time) {
				jobQueue.offer(jobList.get(idx++));
			}
			if (jobQueue.isEmpty()) {
				time++;
			} else {
				Job currentJob = jobQueue.poll();
				time += currentJob.need;
				answer += (time - currentJob.request);
			}
		}
		answer /= jobList.size();
		return answer;
	}
}