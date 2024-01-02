import java.util.*;

class Solution {
	List<String> pathList;
	boolean[] used;

	public void dfs(String[][] tickets, int selectIdx, String path, String airport) {
		// 기저조건
		if (selectIdx == tickets.length) {
			// 해당 경로는 올바른 경로
			pathList.add(path);
			return;
		}
		for (int idx = 0; idx < tickets.length; idx++) {
			if (used[idx]) {
				continue;
			}
			if (!tickets[idx][0].equals(airport)) {
				continue;
			}
			used[idx] = true;
			dfs(tickets, selectIdx + 1, path + " " + tickets[idx][1], tickets[idx][1]);
			used[idx] = false;
		}
	}

	public String[] solution(String[][] tickets) {
		String[] answer = new String[tickets.length + 1];
		pathList = new ArrayList<>();
		used = new boolean[tickets.length];
		// 시작 공항
		String start = "ICN";
		dfs(tickets, 0, start, start);
		Collections.sort(pathList);
		StringTokenizer st = new StringTokenizer(pathList.get(0));
		int idx = 0;
		while (idx <= tickets.length) {
			answer[idx++] = st.nextToken();
		}
		return answer;
	}
}