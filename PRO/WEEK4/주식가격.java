import java.util.*;

/**
 * 스택에 차례대로 삽입
 * 현재 값보다 스택에 존재하는 값이 작은 경우 스택에서 값을 뽑고 존재했던 시간을 기록
 **/
class Solution {
	static class Stock {
		// 생성시간
		int time;
		int value;

		public Stock(int time, int value) {
			this.time = time;
			this.value = value;
		}

	}

	public int[] solution(int[] prices) {
		int[] answer = new int[prices.length];
		Stack<Stock> stocks = new Stack<>();
		for (int time = 0; time < prices.length; time++) {
			// 비어있는 경우
			if (stocks.isEmpty()) {
				stocks.push(new Stock(time, prices[time]));
				continue;
			}

			// 스택에 존재하는 값이 현재 값 보다 클때 동안 반복
			while (!stocks.isEmpty()) {
				if (stocks.peek().value <= prices[time]) {
					break;
				}
				int diff = time - stocks.peek().time;
				answer[stocks.pop().time] = diff;
			}
			// 현재 시간의 주식 삽입
			stocks.push(new Stock(time, prices[time]));
		}
		// 남아있는 스택들은 최대 시간에서 뺀값
		while (!stocks.isEmpty()) {
			int diff = prices.length - 1 - stocks.peek().time;
			answer[stocks.pop().time] = diff;
		}

		return answer;
	}
}