/**
 n개의 음이 아닌 정수들이 있다. 순서를 바꾸지 않고
 적절히 더하거나 뺀다.
 사용할 수 있는 숫자 배열 numbers
 타겟 넘버 (dfs로 풀기)
 **/
class Solution {
	static int selectIdx;
	static int sum, targetNum;
	static int successNum;
	static final int[] signs = {-1, 1};

	private void dfs(int[] numbers) {
		// 기저 조건
		// 모든 수에 대한 처리가 끝났으면
		if (selectIdx == numbers.length) {
			if (sum == targetNum) {
				successNum++;
			}
			return;
		}

		for (int idx = 0; idx < signs.length; idx++) {
			// 현재 부호 선택 후 값 연산
			int value = signs[idx] * numbers[selectIdx];
			sum += value;
			selectIdx++;
			dfs(numbers);
			selectIdx--;
			sum -= value;
		}
	}

	public int solution(int[] numbers, int target) {
		selectIdx = 0;
		sum = 0;
		successNum = 0;
		int answer = 0;
		targetNum = target;
		dfs(numbers);
		return successNum;
	}
}