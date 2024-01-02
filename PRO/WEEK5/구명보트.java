/**
 * 보트에 최대한 많은 무게를 채워 보내면 됨
 **/
import java.util.*;
class Solution {
	public int solution(int[] people, int limit) {
		int answer = 0;
		Arrays.sort(people);
		int lIdx= 0;
		int weight = 0;
		for (int rIdx= people.length - 1; rIdx >= lIdx; rIdx--){
			weight += people[rIdx];
			if(weight <= limit - people[lIdx]){
				weight += people[lIdx++];
			}
			weight = 0;
			answer++;
		}
		return answer;
	}
}