import java.util.*;

class Solution {

    public int solution(int[] ability, int number) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int idx = 0; idx < ability.length; idx++) {
            pq.offer(ability[idx]);
        }

        // 교육 진행
        for (int idx = 0; idx < number; idx++) {
            int score1 = pq.poll();
            int score2 = pq.poll();
            int result = score1 + score2;
            pq.offer(result);
            pq.offer(result);
        }
        while (!pq.isEmpty()) {
            answer += pq.poll();
        }
        return answer;
    }
}