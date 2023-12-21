import java.util.*;

/**
 * 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 앨범을 출시한다.
 * 1. 속한 노래가 많이 재생된 장르 수록
 * 2. 장르 내에서 많이 재생된 노래
 * 3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래 수록
 * 장르의 종류 100개 미만, 장르에 속한 곡이 하나라면 한 곡만 선택
 * 모든 장르는 재생된 횟수가 다름
 * 장르 별 재생 횟수를 기록할 해쉬 맵 <String Integer>
 * 장르 별 음악을 담을 해쉬 맵 <String PrioirtyQueue>
 **/
class Solution {
	static class Genre implements Comparable<Genre> {
		String genre;
		int cnt;

		public Genre(String genre, int cnt) {
			this.genre = genre;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Genre o) {
			return o.cnt - this.cnt;
		}
	}

	static class Music implements Comparable<Music> {
		int number;
		int cnt;

		public Music(int number, int cnt) {
			this.number = number;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Music o) {
			if (o.cnt == this.cnt) {
				return this.number - o.number;
			}
			return o.cnt - this.cnt;
		}
	}

	public int[] solution(String[] genres, int[] plays) {
		ArrayList<Integer> ans = new ArrayList<>();
		// 장르 별 재생 횟수를 기록할 맵
		HashMap<String, Integer> genreMap = new HashMap<>();
		// 장르 별 노래를 저장할 맵
		HashMap<String, PriorityQueue<Music>> genrePQ = new HashMap<>();
		for (int idx = 0; idx < genres.length; idx++) {
			String genre = genres[idx];
			if (genreMap.containsKey(genre)) {
				genreMap.put(genre, genreMap.get(genre) + plays[idx]);
				genrePQ.get(genre).offer(new Music(idx, plays[idx]));
				continue;
			}
			genreMap.put(genre, plays[idx]);
			PriorityQueue<Music> pq = new PriorityQueue<>();
			pq.offer(new Music(idx, plays[idx]));
			genrePQ.put(genre, pq);
		}

		// genre를 내림차순으로 정렬
		ArrayList<Genre> genreList = new ArrayList<>();
		for (String key : genreMap.keySet()) {
			int cnt = genreMap.get(key);
			genreList.add(new Genre(key, cnt));
		}
		Collections.sort(genreList);

		for (int idx = 0; idx < genreList.size(); idx++) {
			Genre genre = genreList.get(idx);
			// 장르 별로 2개씩 담는다 이 때 한곡만 남았다면 담지 않는다.
			for (int iter = 0; iter < 2; iter++) {
				if (genrePQ.get(genre.genre).isEmpty())
					break;
				ans.add(genrePQ.get(genre.genre).poll().number);
			}
		}

		// arrayList 에서 배열로 옮긴다.
		int[] answer = new int[ans.size()];
		for (int idx = 0; idx < ans.size(); idx++) {
			answer[idx] = ans.get(idx);
		}
		return answer;
	}
}