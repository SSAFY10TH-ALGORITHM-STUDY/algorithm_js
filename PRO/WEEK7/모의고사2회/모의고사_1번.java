class Solution { // 상, 우, 하, 좌

    final int[] DELTA_ROW = {1, 0, -1, 0};
    final int[] DELTA_COL = {0, 1, 0, -1};

    public int[] solution(String command) {
        int[] answer = new int[2];
        int direction = 0;
        char[] commandToChar = command.toCharArray();
        for (char com : commandToChar) {
            if (com == 'G') {
                answer[0] += DELTA_COL[direction];
                answer[1] += DELTA_ROW[direction];
            } else if (com == 'B') {
                answer[0] -= DELTA_COL[direction];
                answer[1] -= DELTA_ROW[direction];
            } else if (com == 'R') {
                direction = (direction + 1) % 4;
            } else {
                direction = (direction + 3) % 4;
            }
        }
        return answer;
    }
}