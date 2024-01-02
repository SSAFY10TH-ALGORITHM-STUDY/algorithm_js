
import java.util.*;

class Solution {
	final int MAX_CNT = 8;
	List<HashSet<Integer>> numList;

	public int solution(int N, int number) {
		int answer = 0;
		numList = new ArrayList<>();
		for (int idx = 0; idx <= MAX_CNT; idx++) {
			numList.add(new HashSet<>());
		}
		// 사용 숫자 삽입
		int insert = N;
		int index = 1;
		// N번 사용해서 넣을 수 있는
		while (index <= MAX_CNT) {
			numList.get(index).add(insert);
			insert += (Math.pow(10, index++) * N);
		}
		//8번의 계산 수행
		for (int idx = 2; idx <= MAX_CNT; idx++) {
			for (int useNum = 1; useNum < idx; useNum++) {
				for (int num1 : numList.get(useNum)) {
					for (int num2 : numList.get(idx - useNum)) {
						numList.get(idx).add(num1 + num2);
						numList.get(idx).add(num1 - num2);
						numList.get(idx).add(num1 * num2);
						if (num1 != 0 && num2 != 0) {
							numList.get(idx).add(num1 / num2);
						}
					}
				}

			}
		}
		for (int idx = 1; idx <= MAX_CNT; idx++) {
			for (int num : numList.get(idx)) {
				if (num == number) {
					return idx;
				}
			}
		}
		return -1;
	}
}