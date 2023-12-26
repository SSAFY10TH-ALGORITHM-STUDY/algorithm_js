import java.util.*;

class Solution {
	public int solution(int[] citations) {
		int answer = 0;
		// 정렬
		Integer[] citationsInt = Arrays.stream(citations).boxed().toArray(Integer[]::new);
		Arrays.sort(citationsInt, Collections.reverseOrder());

		for (int idx = 0; idx < citations.length; idx++) {
			int citation = citationsInt[idx];
			// h-index 만족조건
			if (citation <= idx + 1) {
				// 이전까지의 개수 vs 현재 인용값 중 큰 값
				answer = Math.max(citation, idx);
				break;
			}
		}

		if (answer == 0) {
			if (citationsInt[citationsInt.length - 1] != 0) {
				answer = citationsInt.length;
			}
		}

		return answer;
	}
}