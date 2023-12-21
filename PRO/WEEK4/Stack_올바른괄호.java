import java.util.*;

class Solution {
	boolean solution(String s) {
		boolean answer = true;
		Stack<Character> bracketStack = new Stack<>();
		char[] sList = s.toCharArray();
		for (int idx = 0; idx < sList.length; idx++) {
			char bracket = sList[idx];
			if (bracket == '(') {
				bracketStack.push(bracket);
			} else {
				// 오른쪽 괄호가 나왔는데 비어있는 경우 올바르지 않음
				if (bracketStack.isEmpty()) {
					answer = false;
					break;
				}
				// 오른쪽 괄호가 나온 경우 스택에 왼쪽 괄호가 있는지 확인
				if (bracketStack.pop() == '(') {
					continue;
				}
				answer = false;
				break;
			}
		}
		// 처리 후 남은 괄호가 있다면 유효하지 않음
		if (!bracketStack.isEmpty()) {
			answer = false;
		}
		return answer;
	}
}