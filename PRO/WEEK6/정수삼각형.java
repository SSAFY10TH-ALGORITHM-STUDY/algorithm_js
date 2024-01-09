class Solution {

    public int solution(int[][] triangle) {
        int answer = 0;
        int depth = triangle.length;
        int[][] dp = new int[depth][2 * depth - 1];
        // 시작 조건
        dp[0][0] = triangle[0][0];
        for (int idx = 0; idx < depth - 1; idx++) {
            int[] layer = triangle[idx];
            for (int width = 0; width < layer.length; width++) {
                // DP 식 : dp[layer][width] =  triangle[layer][width] + dp[layer - 1][width]
                for (int opt = 0; opt < 2; opt++) {
                    dp[idx + 1][width + opt] = Math.max(dp[idx + 1][width + opt],
                        dp[idx][width] + triangle[idx + 1][width + opt]);
                }
            }
        }
        for (int num : dp[depth - 1]) {
            if (num > answer) {
                answer = num;
            }
        }
        return answer;
    }
}