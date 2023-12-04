package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정수를 저장하는 스택을 구현한 다음 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오
 *	push, pop, size, empty, top
 */
public class BOJ_10828_스택 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static Stack stack;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		//명령 수
		int numInstruction = Integer.parseInt(br.readLine().trim());
		//스택 생성
		stack = new Stack();
		//명령 수 만큼 처리
		for (int idx = 0; idx < numInstruction; idx++) {
			// 5개의 명령에 따른 동작 수행
			action();
		}
		System.out.println(sb);
	}

	public static void action() throws IOException {
		// 명령을 받고 명령어에 따라 동작 수행
		st = new StringTokenizer(br.readLine().trim());
		String instruction = st.nextToken();
		if (instruction.equals("push")) {
			int number = Integer.parseInt(st.nextToken());
			stack.push(number);
		} else if (instruction.equals("pop")) {
			sb.append(stack.pop()).append("\n");
		} else if (instruction.equals("size")) {
			sb.append(stack.size()).append("\n");
		} else if (instruction.equals("empty")) {
			sb.append(stack.empty()).append("\n");
		} else if (instruction.equals("top")) {
			sb.append(stack.top()).append("\n");
		}
	}
}

// First In Last Out
class Stack {
	int[] arr;
	// 현재 기록될 위치를 갖는 idx
	int curIdx;
	int size;

	public Stack() {
		this.arr = new int[1000];
		this.size = 1000;
	}

	public Stack(int size) {
		this.arr = new int[size];
		this.size = size;
	}

	public void extend() {
		int newSize = size * 9 / 8;
		int[] newArr = new int[newSize];

		// 이동
		for (int idx = 0; idx < size; idx++) {
			newArr[idx] = arr[idx];
		}

		this.arr = newArr;
		this.size = newSize;
	}

	// 정수 x를 arr에 넣는다.
	public void push(int val) {
		// 꽉 찬 경우 배열을 늘리고 이동한다.
		if (this.size == this.curIdx) {
			extend();
		}
		this.arr[this.curIdx++] = val;
	}

	// 스택의 가장위에 있는 정수를 빼고 그 수를 반환
	// 없는 경우 -1 반환
	public int pop() {
		if (this.curIdx == 0) {
			return -1;
		}
		return this.arr[--this.curIdx];
	}

	public int size() {
		return this.curIdx;
	}

	// 비어 있으면 1 아니면 0을 출력
	public int empty() {
		if (this.curIdx == 0) {
			return 1;
		}
		return 0;
	}

	public int top() {
		if (this.curIdx == 0) {
			return -1;
		}
		return this.arr[this.curIdx - 1];
	}

}

