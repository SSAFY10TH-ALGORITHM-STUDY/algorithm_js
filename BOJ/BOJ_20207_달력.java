package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 달력에 일정이 있는 곳에 코팅지를 붙이려고하며 룰은 다음과 같다.
 * 1. 연속된 두 일자에 각각 일정이 1개 이상 있다면 이를 일정이 연속되었다고 표현한다.
 * 2. 연속된 모든 일정은 하나의 직사각형에 포함되어야 한다.
 * 3. 연속된 일정을 모두 감싸는 가장 작은 직사각형의 크기 만큼 코팅지를 오린다.
 * 달력의 규칙
 * 1. 일정은 시작날짜와 종료날짜를 포함한다.
 * 2. 시작일이 가장 앞선 일정부터 차례로 채워진다.
 * 3. 시작일이 같을 경우 일정의 기간이 긴 것 먼저 채워진다.
 * 4. 일정은 가능한 최 상단에 배치된다.
 * 5. 일정 하나의 세로 길이는 1이다.
 * 6. 하루의 폭은 1이다.
 * 일정의 개수와 각 일정의 시작날짜, 종료날짜가 주어질 때 수현이가 자르는 코팅지의 면적을 구하시오
 * [입력 ]
 * 일정 개수 N
 * 시작날짜 S, 종료날짜 E
 * [ 풀이 ]
 * 배열에 일정에 해당하는 값을 채운다.
 * 첫 날부터 순회하면서 연속된 날짜가 있는지 체크하고 연속된다면 가로길이를 추가 아니면 현재 가로길이 x 지금까지 구간의 최대높이를 더한다.
 *
 */
public class BOJ_20207_달력 {
	static BufferedReader br;
	static StringTokenizer st;

	static final int YEAR = 365;
	static final int EMPTY = 0;
	static int[] calendar = new int[YEAR + 1];

	static int numSchedule;
	static int area;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		numSchedule = Integer.parseInt(br.readLine().trim());
		// 일정 채우기 
		scheduling();
		// 면적 계산하기 
		calculateArea();
		System.out.println(area);
	}

	private static void scheduling() throws IOException {
		for (int idx = 0; idx < numSchedule; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			for (int day = start; day <= end; day++) {
				calendar[day]++;
			}
		}
	}

	private static void calculateArea() {
		int width = 0;
		int height = 0;
		for (int day = 1; day <= YEAR; day++) {
			// 더이상 연속되지 않을 때
			if (calendar[day] == EMPTY) {
				area += width * height;
				height = 0;
				width = 0;
				continue;
			}
			width++;
			height = (height > calendar[day]) ? height : calendar[day];
	 	}
		// 마지막까지 일정이 이어지는 경우
		if (width != 0 ){
			area += width * height;
		}
	}

}