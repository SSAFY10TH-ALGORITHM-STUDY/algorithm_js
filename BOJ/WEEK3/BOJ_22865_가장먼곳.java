package BOJ.WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 인접리스트 생성 후 friendList에서 부터 BFS 수행하면서 배열 값 업데이트
 * 큐에 거리가 짧은 것을 우선순위로 한다. 전체 배열 중 가잔 큰 index가 가장 먼 곳
 */
public class BOJ_22865_가장먼곳 {
	static BufferedReader br;
	static StringTokenizer st;
	static final int NUM_FRIENDS = 3;
	static int numCandidate, numPath, maxLand;
	static int[] frinedList, distanceList;
	static Node[] adjList;
	static PriorityQueue<Path> priorityQueue;

	static class Path implements Comparable<Path> {
		int destination;
		int distance;

		public Path(int destination, int distance) {
			this.destination = destination;
			this.distance = distance;
		}

		@Override
		public int compareTo(Path o) {
			if (this.distance == o.distance) {
				return this.destination - o.destination;
			}
			return this.distance - o.distance;
		}

		@Override
		public String toString() {
			return "[distance] = " + this.distance + ", [destination] = " + this.destination;
		}
	}

	static class Node {
		int distance, vertex;
		Node next;

		public Node(int vertex, int distance, Node next) {
			this.distance = distance;
			this.vertex = vertex;
			this.next = next;
		}
	}

	public static void bfs() {
		priorityQueue = new PriorityQueue<>();
		// 친구와 이어진 노드들을 모두 삽입한다.
		for (int idx = 0; idx < NUM_FRIENDS; idx++) {
			int friend = frinedList[idx];
			distanceList[friend] = 0;
			for (Node connection = adjList[friend]; connection != null; connection = connection.next) {
				priorityQueue.offer(new Path(connection.vertex, connection.distance));
			}
		}

		while (!priorityQueue.isEmpty()) {
			Path path = priorityQueue.poll();
			int dest = path.destination;
			int dist = path.distance;
			if (distanceList[dest] < dist) {
				continue;
			}
			distanceList[dest] = dist;
			for (Node conn = adjList[dest]; conn != null; conn = conn.next) {
				int tmpDest = conn.vertex;
				int tmpDist = dist + conn.distance;
				if (distanceList[tmpDest] > tmpDist) {
					distanceList[tmpDest] = tmpDist;
					priorityQueue.offer(new Path(tmpDest, tmpDist));
				}
			}
		}
	}

	public static void findMaxDistance() {
		int maxDist = Integer.MIN_VALUE;
		for (int idx = 1; idx <= numCandidate; idx++) {
			int dist = distanceList[idx];
			if (dist > maxDist) {
				maxDist = dist;
				maxLand = idx;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		numCandidate = Integer.parseInt(br.readLine().trim());
		frinedList = new int[NUM_FRIENDS];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < NUM_FRIENDS; idx++) {
			frinedList[idx] = Integer.parseInt(st.nextToken());
		}
		// 인접행렬 생성
		numPath = Integer.parseInt(br.readLine().trim());
		adjList = new Node[numCandidate + 1];
		for (int idx = 0; idx < numPath; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, distance, adjList[from]);
			adjList[to] = new Node(from, distance, adjList[to]);
		}
		distanceList = new int[numCandidate + 1];
		for (int idx = 1; idx <= numCandidate; idx++) {
			distanceList[idx] = Integer.MAX_VALUE;
		}
		// 친구노드로 부터 bfs 수행
		bfs();
		// 가장 먼 노드 탐색
		findMaxDistance();
		System.out.println(maxLand);
	}
}
