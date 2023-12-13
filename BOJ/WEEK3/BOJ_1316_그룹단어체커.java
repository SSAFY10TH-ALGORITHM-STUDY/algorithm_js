package BOJ.WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1316_그룹단어체커 {
	static BufferedReader br;
	static int numWords;
	static int groupCnt;

	public static void isGroupWord(char[] spelling){

		boolean[] isSeen = new boolean[26];
		char prev = ' ';
		for (int idx = 0; idx < spelling.length; idx++){
			char spell = spelling[idx];
			// 이전 문자열과 달라진경우
			if (prev != spell){
				if(!isSeen[spell - 'a']){
					isSeen[spell - 'a'] = true;
				}else{ // 그룹 단어가 아님
					return;
				}
			}
			prev = spell;
		}
		groupCnt++;
	}
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		numWords = Integer.parseInt(br.readLine().trim());
		for (int idx =0; idx <numWords; idx++){
			char[] spelling = br.readLine().trim().toCharArray();
			isGroupWord(spelling);
		}
		System.out.println(groupCnt);
	}
}
