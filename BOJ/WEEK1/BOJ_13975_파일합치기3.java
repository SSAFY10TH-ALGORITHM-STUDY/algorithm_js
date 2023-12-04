package BOJ.WEEK1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 챕터별로 저장된 파일을 하나로 합칠 때 두 파일씩 합쳐 하나의 파일로 만들 때 최소 비용을 구하여라
 * 합칠 때 사용 된 파일의 크기가 매번 최소가 되도록 반복
 * PQ 생성 -> 두개 뽑아서 합치고 다시 삽입 -> 하나 뽑고 다음 것을 못뽑으면 하나로 합쳐진것
 * 10,000 * 1,000,000 => 100억이네
 */
public class BOJ_13975_파일합치기3 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int testCase;
	static long minCost;
	static PriorityQueue<Long> fileQueue;

	public static void getInput() throws IOException {
		int numFile = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		fileQueue = new PriorityQueue<>();
		for (int idx = 0; idx < numFile; idx++){
			long file = Long.parseLong(st.nextToken());
			fileQueue.offer(file);
		}
	}

	public static void mergeFile(){
		minCost = 0;
		while (true){
			long tmpFile = fileQueue.poll();
			if (fileQueue.isEmpty()){
				break;
			}
			tmpFile += fileQueue.poll();
			minCost += tmpFile;
			fileQueue.offer(tmpFile);
		}
		sb.append(minCost).append("\n");
	}
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		testCase = Integer.parseInt(br.readLine().trim());
		for (int tc= 0; tc< testCase; tc++){
			// 입력을 받는 함수
			getInput();
			// 파일을 하나로 합치는 함수
			mergeFile();
		}
		System.out.println(sb);
	}
}
