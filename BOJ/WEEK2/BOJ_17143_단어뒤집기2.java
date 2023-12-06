package BOJ.WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 문자열 S는 다음의 규칙을 지킨다.
 * 1. 알파벳 소문자, 숫자, 공백, 특수문자(<,,>)로만 이루어져 있다.
 * 2. 문자열 시작과 끝은 공백이 아니다.
 * 3. <와 >가 문자열에 있는 경우 번갈아서 등장한다.
 * 단어는 공백으로 구분 태그와 단어 사이에 공백은 없다.
 */
public class BOJ_17143_단어뒤집기2 {
	static BufferedReader br;
	static StringBuilder sb;
	static final char BLANK = ' ', OPEN = '<', CLOSE = '>';
	static boolean isOpen;
	static Stack<Character> stack;

	// 스택에 들어있는 단어를 출력
	public static void printStack() {
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		char[] sentneces = br.readLine().toCharArray();

		stack = new Stack<>();
		isOpen = false;

		for (int idx = 0; idx < sentneces.length; idx++) {
			// 공백인 경우
			if (sentneces[idx] == BLANK) {
				printStack();
				sb.append(" ");
				continue;
			} else if (sentneces[idx] == OPEN) {
				printStack();
				isOpen = true;
				sb.append(OPEN);
				continue;
			} else if (sentneces[idx] == CLOSE) {
				isOpen = false;
				sb.append(CLOSE);
				continue;
			}
			// 태그인 경우
			if (isOpen) {
				sb.append(sentneces[idx]);
			} else {
				stack.push(sentneces[idx]);
			}
		}
		printStack();
		System.out.println(sb);
	}
}
