import java.util.*;

class Solution {

    public long solution(int n, int[] times) {
        // 시간 배열 정렬
        Arrays.sort(times);
        // 최대로 걸릴 수 있는 시간은 최소 시간 x 명수 n
        long high = (long) n * (long) times[0];
        long answer = 0;
        long low = 0;
        while (low < high) {
            long mid = low + (high - low) / 2;
            long cnt = 0;
            for (int time : times) {
                // 각 직원이 해당 시간에 처리할 수 있는 최대 수
                cnt += (mid / time);
            }
            if (cnt < n) {
                low = mid + 1;
            } else {
                high = mid;
            }
            answer = low;
        }
        return answer;
    }
}