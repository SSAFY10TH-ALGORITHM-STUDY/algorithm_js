package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 연구실은 격자이며 에어켄에서 바람이 상,하,좌,우 4방향으로 분다.
 * 이 때 에어컨 바람이 지나가는 곳 중 하나를 선택하여 앉으려한다. 이 때 원하는 자리가 몇개인지 고르시오
 * 물건들은 모양에 따라 반사됨
 * 물건 1: 우->좌, 좌->우
 * 물건 2: 하->상, 상-> 하
 * 물건 3: 상-> 우, 우 -> 상,하->좌, 좌->하
 * 물건 4: 상->좌, 우->하, 하->우, 좌 ->상
 */
public class BOJ_21922_학부연구생민상 {
	static BufferedReader br;
	static StringTokenizer st;

	// 상, 우, 하, 좌
	static final int[] DELTA_ROW = {-1, 0, 1, 0};
	static final int[] DELTA_COL = {0, 1, 0, -1};
	static final int NUM_DIRECTION = 4;
	static final int BLANK = 0;
	static final int AIRCONDITIONER = 9;
	static final int THING1 = 1, THING2 = 2, THING3 = 3, THING4 = 4;
	static int rowSize, colSize;
	// 연구실 크기 * 바람의 방향 수만큼 배열선언
	static int[][] lab;
	static boolean[][][] visited;
	static boolean[][] availableSeat;

	static List<AirConditioner> airconList;

	static class AirConditioner {
		// 에어컨의 위치
		int row, col;

		public AirConditioner(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void setting() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		// 연구실 값 초기화
		lab = new int[rowSize][colSize];
		// 연구실에서 앉을 수 있는 자리를 체크
		availableSeat = new boolean[rowSize][colSize];
		// 해당 방향으로 바람이 지나갔는지 체크할 visted 배열
		visited = new boolean[rowSize][colSize][NUM_DIRECTION];
		// 에어컨이 들어갈 배열 초기화
		airconList = new ArrayList<>();
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; col++) {
				int val = Integer.parseInt(st.nextToken());
				if (val == BLANK) {
					continue;
				} else if (val == AIRCONDITIONER) {
					// availableSeat[row][col] = true;
					AirConditioner airConditioner = new AirConditioner(row, col);
					airconList.add(airConditioner);
					continue;
				}
				// 에어컨이 아닌 다른 물건이 있는 경우 해당 위치를 기록한다.
				lab[row][col] = val;
			}
		}
	}

	public static int changeDirection(int numThing, int direction) {
		if (numThing == THING1) {
			// 좌우인 경우에만 방향 전환 수행
			if ((direction & 1) == 1) {
				return (direction + 2) % NUM_DIRECTION;
			}
			return direction;
		} else if (numThing == THING2) {
			// 상하인 경우
			if ((direction & 1) == 0) {
				return (direction + 2) % NUM_DIRECTION;
			}
			return direction;
		} else if (numThing == THING3) {
			if ((direction & 1) == 0) { // 상 -> 우, 하 -> 좌
				return (direction + 1);
			} else { // 우 -> 상, 좌 -> 하
				return (direction - 1);
			}
		} else if (numThing == THING4) {
			// 상으로 들어온 경우 좌로 반환
			if (direction == 0) {
				return 3;
			}
			if ((direction & 1) == 0) {
				return (direction - 1);
			} else {
				return (direction + 1) % NUM_DIRECTION;
			}
		}
		return -1;
	}

	public static void run(int row, int col, int direction) {
		// 해당 위치에 같은 방향으로 바람이 들어온적 있으면 종료
		if (visited[row][col][direction]) {
			return;
		}
		// 방문 처리
		visited[row][col][direction] = true;
		availableSeat[row][col] = true;
		// 새로 갈 방향 정의
		int newDirection = direction;
		// 만약 현재 위치에 물건이 있다면 방향 변경
		if (lab[row][col] != BLANK) {
			newDirection = changeDirection(lab[row][col], direction);
		}
		// 이동
		int newRow = row + DELTA_ROW[newDirection];
		int newCol = col + DELTA_COL[newDirection];
		if (newRow < 0 || newCol < 0 || newRow >= rowSize || newCol >= colSize) {
			return;
		}
		// 다음 작동 수행
		run(newRow, newCol, newDirection);
	}

	public static void checkSeat() {
		// 에어컨 수 만큼 작동 반복
		for (int idx = 0; idx < airconList.size(); idx++) {
			AirConditioner airCon = airconList.get(idx);
			int startRow = airCon.row;
			int startCol = airCon.col;
			// 상, 하, 좌, 우 탐방 수행
			for (int dirIdx = 0; dirIdx < NUM_DIRECTION; dirIdx++) {
				// 시작 위치, 방향을 받아 탐색을 수행
				run(startRow, startCol, dirIdx);
			}
		}
	}

	public static void count() {
		int availableCnt = 0;
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (availableSeat[row][col]) {
					availableCnt++;
				}
			}
		}
		System.out.println(availableCnt);
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 입력처리
		setting();
		// 에어컨 가동
		checkSeat();
		// 배열로 부터 사용가능한 개수 세고 출력
		count();
	}
}

