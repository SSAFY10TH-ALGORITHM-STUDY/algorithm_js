package BOJ.WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470_두용액 {
	static BufferedReader br;
	static StringTokenizer st;
	static int numLiquid;
	static int[] liquidList;
	static int[] ans;

	public static int lowerbound(int target) {
		int low = 0;
		int high = numLiquid - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (liquidList[mid] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		// 범위를 벗어나는 경우
		if (low == numLiquid) {
			return low - 1;
		}
		return low;
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		numLiquid = Integer.parseInt(br.readLine().trim());
		liquidList = new int[numLiquid];
		ans = new int[2];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < numLiquid; idx++) {
			liquidList[idx] = Integer.parseInt(st.nextToken());
		}
		// 이분탐색을 위한 정렬
		Arrays.sort(liquidList);
		int minDiff = Integer.MAX_VALUE;
		for (int idx = 0; idx < numLiquid; idx++) {
			int val = liquidList[idx];
			// - value 보다 크거나 같은 첫번재 인자와 비교
			int minIdx = lowerbound(-val);
			//1. 큰값일 경우
			// -> 작은값을 더한 값이 더 합이 작을 수 있다.
			int diff = Math.abs(val + liquidList[minIdx]);
			// 같은 index인 경우 무효
			if (idx == minIdx) {
				diff = Integer.MAX_VALUE;
			}
			// 하나 작은 index에 대해 비교 수행
			if (minIdx != 0 && minIdx - 1 != idx) {
				int compare = Math.abs(val + liquidList[minIdx - 1]);
				if (diff > compare) {
					diff = compare;
					minIdx--;
				}
			}
			if (minDiff > diff) {
				ans[0] = idx;
				ans[1] = minIdx;
				minDiff = diff;
			}
		}
		Arrays.sort(ans);
		System.out.println(liquidList[ans[0]] + " " + liquidList[ans[1]]);
	}
}
