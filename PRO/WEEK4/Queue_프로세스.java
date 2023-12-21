import java.util.*;
/**
 * 우선순위 List 정렬 => Idx 순회하면서
 * 해당 우선순위인 경우 큐에서 제거
 **/

class Solution {
	static class Process{
		int location;
		int priority;

		public Process(int location, int priority){
			this.location = location;
			this.priority = priority;
		}
	}

	public int solution(int[] priorities, int location) {
		int answer = 0;
		Queue<Process> watingQueue = new ArrayDeque<>();
		for (int idx = 0; idx < priorities.length; idx++){
			watingQueue.offer(new Process(idx, priorities[idx]));
		}
		// 우선순위 오름차순 정렬
		Arrays.sort(priorities);
		Run:
		for (int runIdx = 1; runIdx <= priorities.length; runIdx++){
			while(true){
				Process currentProcess = watingQueue.poll();
				// 가장 높은 우선순위와 같은 경우 탈출
				if (priorities[priorities.length - runIdx] == currentProcess.priority){
					if(currentProcess.location == location){
						answer = runIdx;
						break Run;
					}
					break;
				}
				watingQueue.offer(currentProcess);
			}
		}
		return answer;
	}
}