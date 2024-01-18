/**
 * 생성점: 들어오는 간선 0, 나가는 간선 2 이상인 정점
 * 막대 그래프: 들어오는 간선 1, 나가는 간선 0인 정점
 * 8자 그래프: 들어오는 간선 2, 나가는 간선 2인 정점
 * 도넛 그래프: 생성점의 나가는 간선 수 - 막대 그래프 수, 8자 그래프 수
 * 생성점에 의해 들어올 수 있으므로 들어오는 개수는 이상으로 취급해야함
 */
class Solution {
    static final int MAX_LENGTH = 1_000_000;
    static class Node{
        // 들어온 수
        int inCnt;
        // 나간 수
        int outCnt;
    }

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Node[] nodeList = new Node[MAX_LENGTH + 1];
        for (int idx = 0; idx < edges.length; idx++){
            int from = edges[idx][0];
            int to = edges[idx][1];

            if (nodeList[from] == null){
                nodeList[from] = new Node();
            }

            if (nodeList[to] == null){
                nodeList[to] = new Node();
            }

            nodeList[from].outCnt += 1;
            nodeList[to].inCnt += 1;
        }

        for (int idx = 1; idx <= nodeList.length; idx++){
            if (nodeList[idx] == null){
                break;
            }
            Node node = nodeList[idx];
            // 해당 노드가 시작점인 경우
            if (node.inCnt == 0 && node.outCnt >= 2) {
                answer[0] = idx;
            }else if (node.inCnt >= 1 && node.outCnt == 0) { // 막대 그래프 수
                answer[2] += 1;
            }else if (node.inCnt >= 2 && node.outCnt == 2) {
                answer[3] += 1;
            }
        }
        answer[1] = nodeList[answer[0]].outCnt - answer[2] - answer[3];
        return answer;
    }
}