/**
 * [선물 주고 받기]
 * 1. 두 사람 사이에 주고 받은 기록이 있다면 더 많은 선물을 "준 사람"이 선물을 하나 받는다.
 * 2. 기록이 없거나 받은 수가 같다면 "선물 지수가 큰 사람"이 받는다.
 *	 2-1. 선물 지수 = 이번달까지 자신이 친구들에게 준 선물 수 - 받은 선물 수
 * 	 2-2. 선물지수도 같으면 주고 받지 않는다.
 * 3. 가장 선물을 많이 받을 친구의 선물 수를 알고 싶다.
 *
 * [풀이]
 */
import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        HashMap<String,Integer> naming = new HashMap<>();
        for (int idx = 0; idx < friends.length; idx++){
            String name = friends[idx];
            naming.put(name, idx);
        }
        int[][] giftMap = new int[friends.length][friends.length];
        int[] giftPoint = new int[friends.length];

        for  (int idx = 0; idx < gifts.length; idx++){
            StringTokenizer st = new StringTokenizer(gifts[idx]);
            int giver = naming.get(st.nextToken());
            int taker = naming.get(st.nextToken());
            giftMap[giver][taker] += 1;
            giftPoint[giver] += 1;
            giftPoint[taker] -= 1;
        }


        int[] giftCnt = new int[friends.length];
        for (int idx1 = 0; idx1 < friends.length; idx1++){
            for (int idx2 = idx1 + 1; idx2 < friends.length; idx2++){
                //  준 사람과 받은 사람이 같은 경우
                if (giftMap[idx1][idx2] == giftMap[idx2][idx1]){
                    // 선물지수가 같으면 넘어간다.
                    if (giftPoint[idx1] == giftPoint[idx2]){
                        continue;
                        // 선물 지수가 높은 쪽이 선물을 받는다.
                    }else if(giftPoint[idx1] > giftPoint[idx2]){
                        giftCnt[idx1] += 1;
                    }else{
                        giftCnt[idx2] += 1;
                    }

                }else if (giftMap[idx1][idx2] > giftMap[idx2][idx1]){
                    giftCnt[idx1] += 1;
                }else{
                    giftCnt[idx2] += 1;
                }

            }
        }

        int answer = 0;
        for (int idx = 0; idx < giftCnt.length; idx++){
            int cnt = giftCnt[idx];
            if (cnt > answer){
                answer = cnt;
            }
        }

        return answer;
    }
}