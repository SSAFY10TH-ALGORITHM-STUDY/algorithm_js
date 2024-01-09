class Solution {

    final int PUDDLE = -1;
    final int DIV = 1_000_000_007;

    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n + 1][m + 1];
        for (int idx = 0; idx < puddles.length; idx++) {
            int[] pos = puddles[idx];
            dp[pos[1]][pos[0]] = PUDDLE;
        }

        // dp 배열 초기화
        for (int idx = 1; idx <= m; idx++) {
            // 이미 우물인 경우
            if (dp[1][idx] == PUDDLE) {
                break;
            }
            dp[1][idx] = 1;
        }

        for (int idx = 1; idx <= n; idx++) {
            if (dp[idx][1] == PUDDLE) {
                break;
            }
            dp[idx][1] = 1;
        }

        for (int row = 2; row <= n; row++) {
            for (int col = 2; col <= m; col++) {
                if (dp[row][col] == PUDDLE) {
                    continue;
                }
                dp[row][col] =
                    (Math.max(dp[row - 1][col], 0) + Math.max(dp[row][col - 1], 0)) % DIV;
            }
        }

        int answer = dp[n][m];
        return answer;
    }
}