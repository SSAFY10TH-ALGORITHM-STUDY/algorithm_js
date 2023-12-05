package BOJ.WEEK1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 특정 건물을 짓는데 걸리는 최소 시간을 구하여라
 * [입력]
 * 테스트 케이스 수
 * 건물의 개수, 건물 순서 규칙
 * 각 건물이 지어지는데 걸리는 시간
 * 순서
 * [풀이]
 * 위상정렬로 해당 건물이 지어지기 전에 지어져야할 모든 건물 체크
 * 매 단계별로 최대의 시간을 갖는 녀석만 저장 후 다 더한 것이 답
 */
public class BOJ_1005_ACMCraft {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int testCase;
	static int numBuilding, numRule;
	static int targetNum;
	static int[] timeList;
	static boolean[] isCalcuated;
	static Node[] adjList;

	// 노드에는 이전에 지어저야할 목록들이 기록된다.
	static class Node {
		int vertex;
		Node next;

		public Node(int vertex, Node next) {
			this.next = next;
			this.vertex = vertex;
		}
	}

	public static void getInput() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		numBuilding = Integer.parseInt(st.nextToken());
		numRule = Integer.parseInt(st.nextToken());

		timeList = new int[numBuilding + 1];
		isCalcuated = new boolean[numBuilding + 1];
		adjList = new Node[numBuilding + 1];

		// building 건설 시간
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 1; idx <= numBuilding; idx++) {
			timeList[idx] = Integer.parseInt(st.nextToken());
		}

		// 순서 별로 인접 리스트에 추가
		for (int idx = 0; idx < numRule; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int prev = Integer.parseInt(st.nextToken());
			int vertex = Integer.parseInt(st.nextToken());
			adjList[vertex] = new Node(prev, adjList[vertex]);
		}

		// 목표 건물 설정
		targetNum = Integer.parseInt(br.readLine().trim());
	}

	public static int calMinTime(int buildingNum) {
		// 이미 계산된적 있거나 해당 번호 이전에 지어져야할 값이 없으면 바로 반환
		if (isCalcuated[buildingNum]) {
			return timeList[buildingNum];
		}

		if (adjList[buildingNum] == null) {
			return timeList[buildingNum];
		}

		int maxTime = Integer.MIN_VALUE;
		for (Node building = adjList[buildingNum]; building != null; building = building.next) {
			int usingTime = calMinTime(building.vertex);
			maxTime = Math.max(maxTime, usingTime);
		}
		maxTime += timeList[buildingNum];

		// 다시 계산하지 않도록 계산된 값을 소요되는 시간에 넣어준다.
		timeList[buildingNum] = maxTime;
		isCalcuated[buildingNum] = true;
		return maxTime;
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 0; tc < testCase; tc++) {
			// 입력 처리
			getInput();
			// 최소시간 구하기
			sb.append(calMinTime(targetNum)).append("\n");
		}
		System.out.println(sb);
	}

}
