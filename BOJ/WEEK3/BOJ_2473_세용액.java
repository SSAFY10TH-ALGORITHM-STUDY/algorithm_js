package BOJ.WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2473_세용액 {
	static BufferedReader br;
	static StringTokenizer st;
	static int numLiquid;
	static long[] liquidList;

	public static int lowerbound(int idx, long target) {
		int low = idx + 1;
		int high = numLiquid - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (liquidList[mid] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		if (low == numLiquid) {
			return low - 1;
		}
		return low;
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		numLiquid = Integer.parseInt(br.readLine().trim());

		liquidList = new long[numLiquid];
		int[] ans = new int[3];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < numLiquid; idx++) {
			liquidList[idx] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(liquidList);
		long minDiff = Long.MAX_VALUE;
		for (int idx1 = 0; idx1 < numLiquid - 2; idx1++) {
			long val = liquidList[idx1];
			for (int idx2 = idx1 + 1; idx2 < numLiquid - 1; idx2++) {
				long val2 = val + liquidList[idx2];
				int idx3 = lowerbound(idx2, -val2);
				long diff = Math.abs(val2 + liquidList[idx3]);
				if (idx3 > idx2 + 1) {
					long compare = Math.abs(val2 + liquidList[idx3 - 1]);
					if (diff > compare) {
						diff = compare;
						idx3 -= 1;
					}
				}
				if (minDiff > diff) {
					minDiff = diff;
					ans[0] = idx1;
					ans[1] = idx2;
					ans[2] = idx3;
				}
			}
		}
		System.out.println(liquidList[ans[0]] + " " + liquidList[ans[1]] + " " + liquidList[ans[2]]);
	}
}
