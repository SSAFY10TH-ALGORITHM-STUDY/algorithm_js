package BOJ.WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * A, B, C, D 에서
 * A[a], B[b], C[c], D[d] 합이 0 인 쌍의 수를 구하여라
 *
 * 1. a,b 와 c,d를 묶는다.
 * 4byte * 4000 * 4000 * 2 = 128MB
 * 2. ab, cd 리스트를 정렬한다
 * O(NlogN) 24 * 160000 = 384,000,000 3초
 * 3.각 리스트에서 이분탐색 두번을 수행해서 0이되는 갯수를 찾는다.
 * O(2NlogN) 24 * 160000 * 2 = 384,000,000 6초
 * 10초에 딱 들어옴 아슬아슬 한데?
 * 4. 틀린 것
 * 총 경우의수 4000*4000*4000*4000 =? 2^48 => long
 */
public class BOJ_7453_합이0인네정수 {
	static BufferedReader br;
	static StringTokenizer st;
	static int seqLength;
	static long pairCnt;
	static int[] aList, bList, cList, dList, abList, cdList;

	public static void getInput() throws IOException {
		seqLength = Integer.parseInt(br.readLine().trim());
		aList = new int[seqLength];
		bList = new int[seqLength];
		cList = new int[seqLength];
		dList = new int[seqLength];

		for (int idx = 0; idx < seqLength; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			aList[idx] = Integer.parseInt(st.nextToken());
			bList[idx] = Integer.parseInt(st.nextToken());
			cList[idx] = Integer.parseInt(st.nextToken());
			dList[idx] = Integer.parseInt(st.nextToken());
		}
	}

	public static void combineArr() {
		abList = new int[seqLength * seqLength];
		cdList = new int[seqLength * seqLength];

		// 선 정렬
		// Arrays.sort(aList);
		// Arrays.sort(bList);
		// Arrays.sort(cList);
		// Arrays.sort(dList);

		for (int idx1 = 0; idx1 < seqLength; idx1++) {
			for (int idx2 = 0; idx2 < seqLength; idx2++) {
				abList[idx1 * seqLength + idx2] = aList[idx1] + bList[idx2];
			}
		}

		for (int idx1 = 0; idx1 < seqLength; idx1++) {
			for (int idx2 = 0; idx2 < seqLength; idx2++) {
				cdList[idx1 * seqLength + idx2] = cList[idx1] + dList[idx2];
			}
		}

		//정렬
		Arrays.sort(abList);
		Arrays.sort(cdList);
	}

	public static int lowerbound(int target) {
		int low = 0;
		int high = cdList.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (cdList[mid] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

	public static int upperbound(int target) {
		int low = 0;
		int high = cdList.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (cdList[mid] <= target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

	public static void calNumPair() {
		pairCnt = 0;
		// 두 개의 배열씩 묶고 정렬한다.
		combineArr();
		// abList에 대해 반복
		for (int idx = 0; idx < abList.length; idx++) {
			int val = abList[idx];
			int low = lowerbound(-val);
			int high = upperbound(-val);
			pairCnt += Math.abs(high - low);
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 입력처리
		getInput();
		// 쌍을 구하는 함수
		calNumPair();
		System.out.println(pairCnt);
	}
}
