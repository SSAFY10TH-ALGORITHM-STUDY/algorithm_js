import java.util.*;

class Solution {
	final int MAX_SIZE = 50;
	// 상, 우, 하, 좌
	final int[] DELTA_ROW = {-1, 0, 1, 0};
	final int[] DELTA_COL = {0, 1, 0, -1};
	int[][] map;
	boolean[][] visited;

	public void drawLine(int[][] rectangle){
		for (int idx = 0; idx < rectangle.length; idx++){
			int[] info = rectangle[idx];
			// 좌표 값 할당
			int leftX = 2 * info[0];
			int leftY = 2 * (MAX_SIZE - info[1]);
			int rightX = 2 * info[2];
			int rightY = 2 * (MAX_SIZE - info[3]);

			for (int row = rightY; row <= leftY; row++){
				for (int col = leftX; col <= rightX; col++){
					// 테두리인지 판별하기 위한 조건
					if (row == rightY || row == leftY || col == rightX || col == leftY){
						// 테두리가 겹칠 수 있기 때문에
						if (map[row][col] == 1){
							continue;
						}
						map[row][col] +=1;
					}else{
						map[row][col] += 2;
					}
				}
			}
		}
	}

	class Pos{
		int row;
		int col;
		int step;
		public Pos(int row, int col, int step){
			this.row = row;
			this.col = col;
			this.step = step;
		}
	}

	// bfs
	public int findItem(int characterX, int characterY, int itemX, int itemY){
		int ans = 0;
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(characterY, characterX, 0));
		visited[characterY][characterX] = true;
		while(!queue.isEmpty()){
			Pos pos = queue.poll();
			int row = pos.row;
			int col = pos.col;
			int step = pos.step;
			// 아이템을 찾은 경우 종료
			if (row == itemY && col == itemX){
				ans =step;
				break;
			}
			// 탐색을 수행한다.
			for (int idx = 0; idx < DELTA_ROW.length; idx++){
				int tmpRow = row + DELTA_ROW[idx];
				int tmpCol = col + DELTA_COL[idx];
				// 맵을 벗어났는지 확인
				if(tmpRow < 0 || tmpCol < 0 || tmpRow > 2 * MAX_SIZE || tmpCol > 2* MAX_SIZE){
					continue;
				}
				// 방문했던 경우
				if (visited[tmpRow][tmpCol]){
					continue;
				}
				// 테두리만 탐색
				if(map[tmpRow][tmpCol] == 1){
					queue.offer(new Pos(tmpRow, tmpCol, step + 1));
					visited[tmpRow][tmpCol] = true;
				}
			}
		}
		return ans;
	}
	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		map = new int[2 * MAX_SIZE + 1][2 * MAX_SIZE + 1];
		visited = new boolean[2 * MAX_SIZE + 1][2 * MAX_SIZE + 1];
		// 주어진 좌표 사각형 그리기
		// 테두리만 따라갈 수 있게 해야함
		drawLine(rectangle);
		// 캐릭터의 위치로부터 아이템 줍기
		int answer = findItem(2 * characterX, 2 * (MAX_SIZE -characterX), 2* itemX, 2*(MAX_SIZE - itemY));
		return answer;
	}
}