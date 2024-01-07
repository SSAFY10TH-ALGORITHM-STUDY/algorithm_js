import java.util.*;

class Solution {

    class Program implements Comparable<Program> {

        // 점수
        int score;
        // 호출 시간
        int callTime;
        // 실행 시간
        int runningTime;

        public Program(int score, int callTime, int runningTime) {
            this.score = score;
            this.callTime = callTime;
            this.runningTime = runningTime;
        }

        @Override
        public int compareTo(Program o) {
            if (this.score == o.score) {
                // 점수가 같은 경우 먼저 호출된 것이 올라감
                return this.callTime - o.callTime;
            }
            // 점수가 낮을 수록 우선순위
            return this.score - o.score;
        }
    }

    public long[] solution(int[][] program) {
        long[] answer = new long[11];
        // 호출 시간에 따른 정렬
        PriorityQueue<Program> watingQueue = new PriorityQueue<>(
            (o1, o2) -> o1.callTime - o2.callTime);
        long time = 0;
        // 작업 큐
        PriorityQueue<Program> workQueue = new PriorityQueue<>();

        //대기 큐에 모든 작업을 넣는다.
        for (int idx = 0; idx < program.length; idx++) {
            watingQueue.offer(new Program(program[idx][0], program[idx][1], program[idx][2]));
        }

        while (true) {
            // 현재 시각까지 호출된 모든 작업을 작업큐에 넣는다.
            while (!watingQueue.isEmpty() && (long) watingQueue.peek().callTime <= time) {
                workQueue.offer(watingQueue.poll());
            }

            if (workQueue.isEmpty()) {
                // 대기 큐도 비었으면 완전 끝
                if (watingQueue.isEmpty()) {
                    answer[0] = time;
                    break;
                }
                // 다음 대기시간까지 이동
                time = watingQueue.peek().callTime;
                continue;
            }
            // 실행하던 작업을 완료한다.
            Program pro = workQueue.poll();
            // 대기시간을 더해준다.
            answer[pro.score] += (time - pro.callTime);
            // 작업시간만큼 소요한다.
            time += pro.runningTime;
        }
        return answer;
    }
}

public class 유전법칙_3번 {

}
