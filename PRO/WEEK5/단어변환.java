import java.util.*;

class Solution {
	boolean[] visited;
	Queue<Word> queue;

	class Word {
		String word;
		int cnt;

		// 초기화
		public Word(String word, int cnt) {
			this.word = word;
			this.cnt = cnt;
		}

	}

	// String 두 개를 받아서 단어 개수 차이를 계산해주는 변수
	public int differCnt(String str1, String str2) {
		char[] char1 = str1.toCharArray();
		char[] char2 = str2.toCharArray();
		int diffCnt = 0;
		for (int idx = 0; idx < char1.length; idx++) {
			if (char1[idx] == char2[idx]) {
				continue;
			}
			diffCnt++;
		}
		return diffCnt;
	}

	public int solution(String begin, String target, String[] words) {
		int answer = 0;
		visited = new boolean[words.length];
		queue = new ArrayDeque<>();
		queue.offer(new Word(begin, 0));
		while (!queue.isEmpty()) {
			Word current = queue.poll();
			String word = current.word;
			int cnt = current.cnt;
			if (word.equals(target)) {
				answer = cnt;
			}
			for (int idx = 0; idx < words.length; idx++) {
				if (visited[idx]) {
					continue;
				}
				String compare = words[idx];
				int diffCnt = differCnt(word, compare);
				// 두개이상 바꿔야 하는 경우
				if (diffCnt > 1) {
					continue;
				}
				queue.offer(new Word(compare, cnt + 1));
				visited[idx] = true;
			}
		}
		return answer;
	}
}