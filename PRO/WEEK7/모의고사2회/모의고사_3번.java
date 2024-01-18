import java.util.*;

class Solution {

    public int solution(int[] menu, int[] order, int k) {
        int answer = 0;
        int time = 0;
        int cnt = 0;
        int idx = 0;
        int makeIdx = 0;
        Queue<Integer> menuQueue = new ArrayDeque<>();
        while (true) {
            // 모든인원이 다 입장한 경우
            if (idx == order.length) {
                break;
            }
            // 손님 입장 현재 처리 음료 전까지의 시간까지 들어올 수 있는 인원확인
            while (idx * k < time + menu[order[makeIdx]]) {
                menuQueue.offer(order[idx++]);
                cnt++;
                if (idx == order.length) {
                    break;
                }
            }
            // 현재까지 손님이 최대인지 확인
            if (cnt > answer) {
                answer = cnt;
            }
            // 손님 처리
            time = time + menu[order[makeIdx++]];
            cnt--;
            // 현재 손님이 0이라면
            if (cnt == 0) {
                time = k * idx;
            }
        }
        return answer;
    }
}