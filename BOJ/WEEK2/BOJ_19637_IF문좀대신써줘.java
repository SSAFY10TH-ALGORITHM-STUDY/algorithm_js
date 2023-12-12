package BOJ.WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19637_IF문좀대신써줘 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static int numTitle, numPeople;
	static int[] titlesIdx;
	static String[] titles;

	// stat보다 크거나 같은 첫 번째 위치를 찾는 것
	public static void lowerBound(int stat) {
		int low = 0;
		int high = numTitle - 1;
		while (low <= high) {
			// int mid = (low + high) /2;
			int mid = low + (high - low) / 2;
			if (titlesIdx[mid] < stat) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		// 해당 칭호 출력
		sb.append(titles[low]).append("\n");
	}

	public static void getInput() throws IOException {
		st = new StringTokenizer(br.readLine().trim());

		numTitle = Integer.parseInt(st.nextToken());
		numPeople = Integer.parseInt(st.nextToken());

		titlesIdx = new int[numTitle];
		titles = new String[numTitle];

		for (int idx = 0; idx < numTitle; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			titles[idx] = st.nextToken();
			titlesIdx[idx] = Integer.parseInt(st.nextToken());
		}

		for (int idx = 0; idx < numPeople; idx++) {
			int stat = Integer.parseInt(br.readLine().trim());
			// stat에 맞는 칭호를 선택한다. (이분탐색)
			lowerBound(stat);
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		getInput();
		System.out.println(sb);
	}
}
