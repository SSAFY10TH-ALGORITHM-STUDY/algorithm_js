package BOJ.WEEK2;

import java.util.*;
import java.io.*;

/**
 * 전화 번호 목록이 주어진다. 이 때 이 모록에 대한 일관성이 있는지 없는지
 * 일관성은 한 번호가 다른 번호의 접두어인 경우가 없어야 한다.
 * prefix가 있으면 전화가 걸려버리기 때문에 일관성 X
 * [풀이 ]
 * String으로 정렬 후 HashSet 사용
 */
public class BOJ_5052_전화번호목록 {

	static BufferedReader br;
	static StringBuilder sb;
	static int testCase;
	static int numPhone;
	static String[] phoneNumbers;
	static HashSet<String> bookSet;

	public static void solve() throws Exception{
		int numPhone = Integer.parseInt(br.readLine().trim());
		phoneNumbers = new String[numPhone];
		for (int idx = 0; idx < numPhone; idx++){
			phoneNumbers[idx] = br.readLine().trim();
		}
		Arrays.sort(phoneNumbers);
		bookSet = new HashSet<>();
		for (int idx = 0; idx < numPhone; idx++){
			char[] numbers = phoneNumbers[idx].toCharArray();
			String phoneNumber = "";
			for (int cIdx = 0; cIdx < numbers.length; cIdx++){
				phoneNumber += numbers[cIdx];
				if (bookSet.contains(phoneNumber)){
					sb.append("NO").append("\n");
					return;
				};
			}
			bookSet.add(phoneNumber);
		}
		sb.append("YES").append("\n");

	}
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		testCase = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= testCase; tc++){
			solve();
		}
		System.out.println(sb);
	}
}
