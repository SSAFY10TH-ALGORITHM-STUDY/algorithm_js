import java.util.*;

class Solution {
	static class Truck {
		int weight;
		int time;

		public Truck(int weight, int time) {
			this.weight = weight;
			this.time = time;
		}
	}

	public int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		Queue<Truck> truckQueue = new ArrayDeque<>();
		// 1초부터 다리 오르기 가능
		int time = 1;
		// 다리 위에 존재하는 차의 무게
		int onBridgeWeight = 0;
		int idx = 0;
		while (true) {
			// 트럭 출발
			if (idx < truck_weights.length && onBridgeWeight + truck_weights[idx] <= weight) {
				truckQueue.offer(new Truck(truck_weights[idx], time));
				onBridgeWeight += truck_weights[idx++];
			}
			time++;
			// 완주 시키기
			if (time - truckQueue.peek().time == bridge_length) {
				onBridgeWeight -= truckQueue.poll().weight;
			}
			if (idx == truck_weights.length && truckQueue.isEmpty()) {
				break;
			}
		}
		answer = time;
		return answer;
	}
}