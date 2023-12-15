package BOJ.WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 0과 1로 이루어진 무한한 문자열 X가 있다.
 * 1. X는 맨 처음 0으로 시작한다.
 * 2. x에서 0을 1로, 1을 0으로 뒤바꾼 X를 만든다.
 * 3. X의 뒤에 X를 붙인 문자열을 x로 정의한다.
 * 4. 2,3의 과정을 무한히 반복한다.
 * 10^18 < 2^ 64
 */
public class BOJ_18222_투에모스문자열 {
	static BufferedReader br;
	static long input;
	static long[] pow;

	public static int thuemorse(long number){
		if (number == 1){
			return 0;
		}
		for (int idx = 1; idx <64; idx++){
			if (pow[idx] >= number){
				return 1 - thuemorse(number - pow[idx-1]);
			}
		}
		return 0;
	}
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		input = Long.parseLong(br.readLine().trim());
		pow = new long[64];
		pow[0] = 1;
		for (int idx = 1; idx < 64; idx++){
			pow[idx] = pow[idx-1] * 2;
		}
		System.out.println(thuemorse(input));
	}
}
