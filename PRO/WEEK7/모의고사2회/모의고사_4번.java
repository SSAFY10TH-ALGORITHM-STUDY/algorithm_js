import java.util.*;

class Solution {

    int[][] map;
    // 방문 체크 배열 0: 점프하지 않은 경우, 1: 점프한 경우
    boolean[][][] visited;
    int rowSize, colSize;
    final int HOLE = -1;
    // 상, 우, 하, 좌
    final int[] DELTA_ROW = {-1, 0, 1, 0};
    final int[] DELTA_COL = {0, 1, 0, -1};

    static class Pos {

        int row;
        int col;
        int time;
        int jumped;

        public Pos(int row, int col, int time, int jumped) {
            this.row = row;
            this.col = col;
            this.time = time;
            this.jumped = jumped;
        }
    }

    public boolean isMap(int row, int col) {
        return !(row < 0 || col < 0 || row >= rowSize || col >= colSize);
    }

    public int solution(int n, int m, int[][] hole) {
        int answer = 0;
        // row, col;
        int[] start = {m - 1, 0};
        int[] end = {0, n - 1};
        rowSize = m;
        colSize = n;
        map = new int[m][n];
        visited = new boolean[2][m][n];
        for (int idx = 0; idx < hole.length; idx++) {
            int[] info = hole[idx];
            map[m - info[1]][info[0] - 1] = HOLE;
        }

        Queue<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(start[0], start[1], 0, 0));
        // 방문 체크
        visited[0][start[0]][start[1]] = true;
        visited[1][start[0]][start[1]] = true;
        int selectIdx = 0;
        while (!queue.isEmpty()) {
            Pos current = queue.poll();
            int row = current.row;
            int col = current.col;
            int time = current.time;
            int jumped = current.jumped;
            if (row == end[0] && col == end[1]) {
                return time;
            }
            // 점프를 하지 않은 경우
            if (jumped == 0) {
                for (int idx = 0; idx < 4; idx++) {
                    int tmpRow = row + 2 * DELTA_ROW[idx];
                    int tmpCol = col + 2 * DELTA_COL[idx];
                    // 맵을 벗어난 경우 || 방문한 경우
                    if (!isMap(tmpRow, tmpCol) || visited[1][tmpRow][tmpCol]) {
                        continue;
                    }
                    if (map[tmpRow][tmpCol] == HOLE) {
                        continue;
                    }
                    queue.offer(new Pos(tmpRow, tmpCol, time + 1, 1));
                    visited[1][tmpRow][tmpCol] = true;
                }
            }
            for (int idx = 0; idx < 4; idx++) {
                int tmpRow = row + DELTA_ROW[idx];
                int tmpCol = col + DELTA_COL[idx];
                // 맵을 벗어난 경우 || 방문한 경우
                if (!isMap(tmpRow, tmpCol) || visited[jumped][tmpRow][tmpCol]) {
                    continue;
                }
                if (map[tmpRow][tmpCol] == HOLE) {
                    continue;
                }
                queue.offer(new Pos(tmpRow, tmpCol, time + 1, jumped));
                visited[jumped][tmpRow][tmpCol] = true;
            }
        }

        return -1;
    }
}