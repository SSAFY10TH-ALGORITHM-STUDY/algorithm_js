package BOJ.WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2503_숫자야구 {
	static BufferedReader br;
	static StringTokenizer st;
	static final int MAX_LEN = 3, MAX_SIZE = 1000;
	static int numQuestion;
	static boolean[] isNotAnswer;


	private static void preprocessing() {
		for (int number = 123; number < MAX_SIZE; number++) {
			char[] candidate = Integer.toString(number).toCharArray();
			char prev = ' ';
			char pprev = ' ';
			for (int idx = 0; idx < MAX_LEN; idx++){
				char now = candidate[idx];
				if (now == '0'){
					isNotAnswer[number] = true;
					break;
				}
				if(prev == now || pprev == now){
					isNotAnswer[number] = true;
					break;
				}
				pprev = prev;
				prev = now;
			}
		}
	}
	private static void checking(char[] question, int strike, int ball) {
		for (int number = 123; number < MAX_SIZE; number++) {
			if (isNotAnswer[number])
				continue;

			int strCnt = 0;
			int ballCnt = 0;
			//	char[]로 변환
			char[] candidate = Integer.toString(number).toCharArray();

			for (int idx1 = 0; idx1 < MAX_LEN; idx1++) {
				char candChar = candidate[idx1];
				for (int idx2 = 0; idx2 < MAX_LEN; idx2++) {
					char questionChar = question[idx2];
					if (candChar == questionChar) {
						if (idx1 == idx2) {
							strCnt++;
						} else {
							ballCnt++;
						}
					}
				}
			}
			// 둘중 하나라도 같지 않으면 후보가 아님
			if(strike != strCnt || ball != ballCnt) {
				isNotAnswer[number] = true;
			}
		}
	}

	private static int calNumCandidate() {
		int cnt = 0;
		for (int number = 123; number < MAX_SIZE; number++){
			System.out.println(number + " " + isNotAnswer[number]);
			if (isNotAnswer[number]) continue;
			cnt++;
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		isNotAnswer = new boolean[MAX_SIZE];
		numQuestion = Integer.parseInt(br.readLine().trim());
		preprocessing();
		for (int idx = 0; idx < numQuestion; idx++) {
			st = new StringTokenizer(br.readLine().trim());

			char[] question = st.nextToken().toCharArray();
			int strike = Integer.parseInt(st.nextToken());
			int ball = Integer.parseInt(st.nextToken());
			checking(question, strike, ball);
		}

		int numCandidate = calNumCandidate();
		System.out.println(numCandidate);
	}


}
