package BOJ.WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1847_스택수열 {
	static BufferedReader br;
	static StringBuilder sb;
	static int numSeq, insertNum;
	static boolean isImpossible;
	static Stack<Integer> stack;

	public static void push() {
		sb.append("+").append("\n");
		stack.push(insertNum++);
	}

	public static void pop() {
		sb.append("-").append("\n");
		stack.pop();
	}

	public static void work(int number) {
		// 스택이 비어있는 경우
		if (stack.isEmpty()) {
			push();
		}
		// 불가능한 경우
		if (stack.peek() > number) {
			isImpossible = true;
			return;
		}
		while (stack.peek() < number) {
			push();
		}
		pop();
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		numSeq = Integer.parseInt(br.readLine().trim());
		stack = new Stack<>();
		insertNum = 1;
		for (int idx = 0; idx < numSeq; idx++) {
			if (isImpossible)
				break;
			int number = Integer.parseInt(br.readLine().trim());
			work(number);
		}
		if (isImpossible) {
			System.out.println("NO");
		} else {
			System.out.println(sb);
		}
	}
}
