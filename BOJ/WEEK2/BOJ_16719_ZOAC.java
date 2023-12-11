package BOJ.WEEK2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 문자열에서 사전순으로 가장 앞에 오도록 문자를 보여주어라
 * 1. 앞에서부터 해당 문자열보다 늦게오는 문자열이 확인 뒤에 나오는 문자열이 더 앞인 경우 해당 문제열젝거
 * 2. 재귀적으로 반복
 */
public class BOJ_16719_ZOAC {
	static BufferedReader br;
	static StringBuilder sb;

	private static void recursivePrint(String word) {
		char[] spells = word.toCharArray();
		if (spells.length == 1) {
			sb.append(word);
			return;
		}

		int removeIdx = -1;
		char prev = 'A';
		// 문자열 위치 검사
		for (int idx = 0; idx < spells.length; idx++) {
			char spell = spells[idx];
			if (prev - spell > 0) {
				removeIdx = idx - 1;
				break;
			}
			prev = spell;
		}
		// 만약 index 수정이 일어나지 않은 경우 오름차순 정렬상태
		if (removeIdx == -1)
			removeIdx = spells.length - 1;
		// 다음 문자열 재귀 호출
		String next = "";
		for (int idx = 0; idx < spells.length; idx++) {
			if (idx == removeIdx)
				continue;
			;
			next += spells[idx];
		}
		recursivePrint(next);
		// 문자열 출력
		sb.append("\n");
		sb.append(word);
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String word = br.readLine().trim();
		recursivePrint(word);
		System.out.println(sb);
	}

}
