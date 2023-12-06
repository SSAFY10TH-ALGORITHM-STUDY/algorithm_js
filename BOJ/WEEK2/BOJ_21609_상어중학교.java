package BOJ.WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * N x N 격자에서 진행
 * 블록: 검은색: -1, 무지개: 0, 일반 블록 M가지: M이하의 자연수
 * 블록 그룹은 연결된 블록집합 일반블록이 최소 한개 있어야 하며 모두 같아야 한다. 검은 블록은 안되며 무지개 블록은 얼마든 가능하다.
 * 블록 그룹에서 기준 블록은 무지개 블록이 아닌 블록 중에서 행의 번호가 가장 작은 블록 그러한 블록이 여러개면 열의 번호가 가장 작은 블록이다.
 * 진행방식
 * 1. 가장 큰 블록 그룹을 찾는다. 그러한 그룹이 여러개라면 포함된 무지개 블록의 수가 가장 많은 블록, 기준 블록이 행이 가장 큰, 열이 가장 큰 것을 찾는다.
 * 2. 1에서 찾은 블록 그룹의 모든 블록을 제거한다. 블록 그룹에 포함된 블록 수를 B라 할 대 B^2 점수를 획득한다.
 * 3. 중력 작용 (검은 블록은 이동 x)
 * 4. 격자가 90도로 반시계 방향으로 회전한다.
 * 5. 다시 격자에 중력이 작용한다.
 * 1~5의 과정을 블록 그룹이 존재하는 동안 반복한다.
 */
public class BOJ_21609_상어중학교 {
	static BufferedReader br;
	static StringTokenizer st;

	static final int BLANK = 0, BLACK = -1, RAINBOW = -2, MIN_SIZE = 2;
	// 상 우 하 좌
	static final int[] DELTA_ROW = {-1, 0, 1, 0};
	static final int[] DELTA_COL = {0, 1, 0, -1};
	static int mapSize, numColor;
	static int score;
	static int[][] map;
	static boolean[][] visited;
	static boolean[][] grouped;
	static Queue<Block> blockQueue;
	static List<BlockGroup> groupList;

	static class Block {
		int row, col;
		int color;

		public Block(int row, int col, int color) {
			this.row = row;
			this.col = col;
			this.color = color;
		}
	}

	static class BlockGroup implements Comparable<BlockGroup> {
		int numRainbow;
		int baseRow, baseCol;
		List<Block> blockList;

		public BlockGroup(int baseRow, int baseCol) {
			this.baseRow = baseRow;
			this.baseCol = baseCol;
			this.blockList = new ArrayList<>();
		}

		@Override
		public int compareTo(BlockGroup o) {
			if (o.blockList.size() == this.blockList.size()) {
				if (this.numRainbow == o.numRainbow) {
					if (this.baseRow == o.baseRow) {
						return o.baseCol - this.baseCol;
					}
					return o.baseRow - this.baseRow;
				}
				return o.numRainbow - this.numRainbow;
			}
			return o.blockList.size() - this.blockList.size();
		}
	}

	public static void getInuput() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		numColor = Integer.parseInt(st.nextToken());

		map = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < mapSize; col++) {
				int val = Integer.parseInt(st.nextToken());
				if (val == 0) {
					val = RAINBOW;
				}
				map[row][col] = val;
			}
		}

	}

	public static boolean isValid(int row, int col) {
		return !(row < 0 || col < 0 || row >= mapSize || col >= mapSize);
	}

	// bfs를 통해 그룹핑 수행
	public static void grouping(Block startBlock) {
		int groupColor = startBlock.color;
		visited = new boolean[mapSize][mapSize];
		BlockGroup bg = new BlockGroup(startBlock.row, startBlock.col);
		bg.blockList.add(startBlock);

		blockQueue = new ArrayDeque<>();
		blockQueue.offer(startBlock);
		visited[startBlock.row][startBlock.col] = true;

		int numRainbow = 0;
		while (!blockQueue.isEmpty()) {
			Block curBlock = blockQueue.poll();
			int curRow = curBlock.row;
			int curCol = curBlock.col;
			for (int dirIdx = 0; dirIdx < DELTA_ROW.length; dirIdx++) {
				int tmpRow = curRow + DELTA_ROW[dirIdx];
				int tmpCol = curCol + DELTA_COL[dirIdx];
				// 유효하지 않은 위치나 이미 방문한 경우 패스
				if (!isValid(tmpRow, tmpCol) || visited[tmpRow][tmpCol]) {
					continue;
				}
				visited[tmpRow][tmpCol] = true;
				int color = map[tmpRow][tmpCol];
				// 무지개 블럭이거나 원래 색일 경우 추가한다.
				if (color == RAINBOW || color == groupColor) {
					Block block = new Block(tmpRow, tmpCol, color);
					bg.blockList.add(block);
					blockQueue.offer(block);
					if (color == groupColor) {
						// 원래 그룹색이면 다시 그룹핑하지 않게 방문체크
						grouped[tmpRow][tmpCol] = true;
					} else {
						// 무지개블록이면 무지개 블록 수 증가
						numRainbow++;
					}
				}
			}
		}

		if (bg.blockList.size() >= MIN_SIZE) {
			bg.numRainbow = numRainbow;
			groupList.add(bg);
		}

	}

	public static void searchBlockGroups() {
		grouped = new boolean[mapSize][mapSize];
		groupList = new ArrayList<>();
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				int color = map[row][col];
				// 일반 블럭일 때만 그룹핑 수행
				if (color > 0 && !grouped[row][col]) {
					grouping(new Block(row, col, color));
				}
			}
		}
	}

	public static void removeLargest() {
		// group의 정룔조건에 따라 정렬
		Collections.sort(groupList);
		// 가장 큰 그룹을 제거한다.
		BlockGroup bg = groupList.get(0);
		int size = bg.blockList.size();
		score += (size * size);
		// 해당 블록을 제거한다
		for (int idx = 0; idx < size; idx++) {
			Block block = bg.blockList.get(idx);
			map[block.row][block.col] = BLANK;
		}
	}

	public static void applyGravity() {
		// 가장 아래 행부터 1행까지
		for (int row = mapSize - 1; row > 0; row--) {
			for (int col = 0; col < mapSize; col++) {
				// 현재 블록이 비어있다면 위에 블록을 내린다.
				if (map[row][col] == BLANK) {
					// 빈칸이 아닐 때까지
					for (int tmpRow = row; tmpRow >= 0; tmpRow--) {
						if (map[tmpRow][col] == BLANK) {
							continue;
						} else if (map[tmpRow][col] == BLACK) {
							break;
						}
						map[row][col] = map[tmpRow][col];
						map[tmpRow][col] = BLANK;
						break;
					}
				}
			}
		}
	}

	public static void rotateCounter90() {
		// 반시계로 90도 회전
		// 1 2 3       		3 6 9
		// 4 5 6     => 	2 5 8
		// 7 8 9 			1 4 7
		int[][] copyMap = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				copyMap[mapSize - 1 - col][row] = map[row][col];
			}
		}
		map = copyMap;
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 입력을 받는다.
		getInuput();

		// 블록 그룹이 없을 때 까지 autoPlay
		while (true) {
			// 모든 블록 그룹 찾기
			searchBlockGroups();

			if (groupList.isEmpty()) {
				break;
			}
			// 가장 큰 블록 제거
			removeLargest();

			//중력 작용
			applyGravity();

			//반시계 90도 회전
			rotateCounter90();

			//다시 중력 작용
			applyGravity();

		}
		System.out.println(score);
	}
}
