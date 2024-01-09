import java.util.Queue;
import java.util.ArrayDeque;

/**
 * 1번 노드에서 가장 멀리 떨어진 노드의 개수 bfs를 통해 모든 노드와의 거리를 탐색한다. 간선 정보를 통해 인접리스트 구현 이 때 bfs에 노드까지의 거리가 기록되도록
 * 한다. 현재 최대 거리를 기록하는 변수와 최대거리일 때 세는 변수 선언 해당 노드의 길이가 최대 거리라면 최대거리일 때 세는 변수 증가 최대거리보다 커졌다면 최대거리 갱신 후
 * 최대거리 카운트 1로 변경
 **/

class Solution {

    static boolean[] visited;
    static Node[] adjList;
    static int LongestDistance, LongCnt;

    static class Node {

        int vertex;
        Node next;

        public Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }

    public int solution(int n, int[][] edge) {
        // edge + 1 만큼 adjList 초기화
        adjList = new Node[n + 1];
        visited = new boolean[n + 1];
        // 간선 정보를 토대로 인접 리스트구현
        for (int idx = 0; idx < edge.length; idx++) {
            int[] edgeInfo = edge[idx];
            int from = edgeInfo[0];
            int to = edgeInfo[1];
            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
        }
        // bfs를 위한 큐 선언
        Queue<int[]> queue = new ArrayDeque<>();
        // 큐에 삽입, 방문 처리
        int[] info = {1, 0};
        queue.offer(info);
        visited[info[0]] = true;
        while (!queue.isEmpty()) {
            info = queue.poll();
            int vertexId = info[0];
            int currentDistance = info[1];
            // 가장 긴 거리일 경우
            if (currentDistance == LongestDistance) {
                LongCnt++;
            } else if (currentDistance > LongestDistance) {
                LongestDistance = currentDistance;
                LongCnt = 1;
            }

            for (Node vertex = adjList[vertexId]; vertex != null; vertex = vertex.next) {
                // 해당 노드에 이미 방문했다면 넘기기
                if (visited[vertex.vertex]) {
                    continue;
                }
                visited[vertex.vertex] = true;
                // 아닌 경우 증가된 거리로 큐에 삽입
                int distance = currentDistance + 1;
                int[] vertexInfo = {vertex.vertex, distance};
                queue.offer(vertexInfo);
            }
        }
        return LongCnt;
    }
}