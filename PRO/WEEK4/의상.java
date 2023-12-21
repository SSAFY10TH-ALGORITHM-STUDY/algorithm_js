
import java.util.*;

/**
 * HashMap String, Integer
 * 모든 경우의 수 각 List의 개수 + 1을 모두 곱한다.
 **/
class Solution {
	public int solution(String[][] clothes) {
		int answer = 1;
		HashMap<String, Integer> clothMap = new HashMap<>();
		for (int idx = 0; idx < clothes.length; idx++){
			String type = clothes[idx][1];
			if (clothMap.containsKey(type)){
				clothMap.put(type, (clothMap.get(type) + 1));
				continue;
			}
			// 선택하지 않은 경우와 한개 선택한 경우
			clothMap.put(type, 2);
		}

		for (String key: clothMap.keySet()){
			answer = answer * clothMap.get(key);
		}
		// 모두 선택하지 않은경우는 제거
		answer -= 1;
		return answer;
	}
}
