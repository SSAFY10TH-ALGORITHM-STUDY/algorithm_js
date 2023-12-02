package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N명이 서 있고 한 팀에는 최소 2명이 있어야 한다.
 * 팀 능력치는 A,B 사이에 존재하는 다른 사람의 수 x min(A 능력치, B 능력치)
 * 팀 빌딩에서 나올 수 있는 능력치 중 최대값을 구해보자.
 * [풀이 ]
 *	초기에 양 끝에 값을 두고
 *	왼쪽 값, 오른쪽 값을 각각 저장
 *	더 작은 쪽 포인터를 이동한다.
 */
public class BOJ_22945_팀빌딩 {
	static BufferedReader br;
	static StringTokenizer st;
	static int maxAbility;

	static int numPeople;
	static int[] abilityList;

	public static void getInput() throws IOException {
		numPeople = Integer.parseInt(br.readLine().trim());
		abilityList = new int[numPeople];

		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < numPeople; idx++) {
			int ability = Integer.parseInt(st.nextToken());
			abilityList[idx] = ability;
		}
	}

	public static int calculateTeamAbility(int leftIdx, int rightIdx, int leftVal, int rightVal) {
		return Math.min(leftVal, rightVal) * Math.max((rightIdx - leftIdx - 1), 0);
	}

	public static void abilityOptimization() {
		int leftIdx = 0;
		int rightIdx = numPeople - 1;
		// 초기 능력치 계산
		maxAbility = calculateTeamAbility(leftIdx, rightIdx, abilityList[leftIdx], abilityList[rightIdx]);
		while (rightIdx > leftIdx + 1) {
			if (abilityList[leftIdx] > abilityList[rightIdx]) {
				rightIdx--;
			} else {
				leftIdx++;
			}
			maxAbility = Math.max(maxAbility,
				calculateTeamAbility(leftIdx, rightIdx, abilityList[leftIdx], abilityList[rightIdx]));
		}

	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 입력처리
		getInput();
		// 최대 값 찾기 수행
		abilityOptimization();
		// 출력
		System.out.println(maxAbility);
	}
}
