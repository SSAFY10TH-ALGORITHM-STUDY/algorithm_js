import java.util.*;

class Solution {

    public String solution(String input_string) {
        HashSet<Character> charSet = new HashSet<>();
        HashSet<Character> soloSet = new HashSet<>();
        char[] sentence = input_string.toCharArray();
        char prev = ' ';
        for (int idx = 0; idx < sentence.length; idx++) {
            char cur = sentence[idx];
            // 이전 문자열과 다른경우
            if (cur != prev) {
                // 외톨이 알파벳인 경우
                if (charSet.contains(cur)) {
                    soloSet.add(cur);
                }
                charSet.add(cur);
            }
            // 이전 문자열로 기록
            prev = cur;
        }

        if (soloSet.isEmpty()) {
            return "N";
        }

        String answer = "";
        // 정렬을 위해 리스트에 담고
        List<Character> answerList = new ArrayList<>();
        for (char text : soloSet) {
            answerList.add(text);
        }
        // 정렬
        Collections.sort(answerList);
        for (char text : answerList) {
            answer += text;
        }
        return answer;
    }
}