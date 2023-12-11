package BOJ.WEEK2;

import java.io.*;
import java.util.*;

/**
 * 1. 모음을 포함 해야 한다.
 * 2. 모음이 3개 혹은 자음이 3개 연속으로 오면 안된다.
 * 3. 같은 글짜가 연속적으로 두번오면 안되나. ee와 oo를 허용 한다.
 */
public class BOJ_4659_비밀번호발음하기 {
	static BufferedReader br;
	static StringBuilder sb;
	static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};
	static final int LIMIT = 3;

	public static void safe(String str){
		sb.append("<").append(str).append("> is acceptable.").append("\n");
	}
	public static void nonSafe(String str){
		sb.append("<").append(str).append("> is not acceptable.").append("\n");
	}

	public static void testing(String password){
		// 1. 모음이 한 개 이상 있는지 체크한다.
		// 2. 모음이 3개 혹은 자음이 3개 연속으로 오면 안된다.
		// 3. 같은 글자가 연속적으로 두번 오면 안되나 ee와 oo는 허용한다.
		char[] charList = password.toCharArray();
		int vowelCnt = 0;
		int totalVowelCnt = 0;
		int nonCnt = 0;
		char prevChar = ' ';

		for (int idx =0 ; idx < charList.length; idx++){
			char character = charList[idx];
			// 비밀번호에 모음이 있는지 확인
			boolean isVowel = false;
			for (int cIdx = 0; cIdx < VOWELS.length; cIdx++){
				if (isVowel) continue;
				if (character == VOWELS[cIdx]){
					isVowel = true;
					vowelCnt++;
					totalVowelCnt++;
					nonCnt = 0;
				}
			}
			if (!isVowel){
				vowelCnt = 0;
				nonCnt++;
			}
			// 2. 연속 카운트가 3이상인 경우
			if (vowelCnt >= LIMIT || nonCnt >= LIMIT){
				nonSafe(password);
				return;
			}
			// 3.
			if (prevChar == character){
				if(prevChar == 'o' || prevChar == 'e') continue;
				nonSafe(password);
				return;
			}
			// 이전 문자열 기록
			prevChar = character;
		}

		if (totalVowelCnt == 0){
			nonSafe(password);
			return;
		}
		safe(password);
	}
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		while(true){
			String password = br.readLine().trim();
			if (password.equals("end")){
				break;
			}
			testing(password);
		}
		System.out.println(sb);
	}
}
