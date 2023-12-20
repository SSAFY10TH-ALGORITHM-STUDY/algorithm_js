import java.util.*;

class Solution {
	public boolean solution(String[] phone_book) {
		Arrays.sort(phone_book);
		HashSet<String> hashSet = new HashSet<>();
		boolean answer = true;
		for (int idx = 0; idx < phone_book.length; idx++){
			char[] phoneNumber = phone_book[idx].toCharArray();
			String number = "";
			for (int cIdx = 0; cIdx < phoneNumber.length; cIdx++){
				number += phoneNumber[cIdx];
				if (hashSet.contains(number)){
					answer = false;
				}
			}
			hashSet.add(number);
			// 이미 접두사가 존재하는 경우 탈출
			if (!answer){
				break;
			}
		}
		return answer;
	}
}