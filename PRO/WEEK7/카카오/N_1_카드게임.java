/**
 * 1 ~ n 사이의 수가 적힌 카드 뭉치와 동전을 이용한 게임.
 * <p>
 * 1. 처음에 n/3 장을 뽑아 모두 가진다. 2. 1라운드 부터 각 라운드 마다 카드 2장을 뽑는다. - 카드 뭉치에 남은 카드가 없다면 게임을 종료한다. - 뽑은 카드는 한 장당 동전 하나를 소모해 가지거나 버린다. 3. 적힌 수의 합이 n + 1이 되도록 카드 두 장을 내고 다음 라운드를 진행한다. - 두 장을 낼 수 없다면 종료한다.
 */

import java.util.*;

class Solution {

    List<Integer> handList;
    List<Integer> candList;

    public int solution(int coin, int[] cards) {
        int answer = 1;
        handList = new ArrayList<>();
        candList = new ArrayList<>();
        int initCardNum = cards.length / 3;
        // 손패 보충
        for (int idx = 0; idx < initCardNum; idx++) {
            handList.add(cards[idx]);
        }

        int cardIdx = initCardNum;
        while (cardIdx < cards.length) {
            // 턴이 시작하면 후보에 2개를 넣는다.
            for (int idx = 0; idx < 2; idx++) {
                candList.add(cards[cardIdx++]);
            }

            // 카드 선택이 완료되었는지 확인하기 위한 flag변수 선언
            boolean isEnd = false;

            // 선택된 카드 숫자와 인덱스 기록
            int num1 = 0;
            int num2 = 0;

            // 우선 손패에서 가능한 조합이 있는지 확인
            for (int idx1 = 0; idx1 < handList.size(); idx1++) {
                num1 = handList.get(idx1);
                for (int idx2 = idx1 + 1; idx2 < handList.size(); idx2++) {
                    num2 = handList.get(idx2);
                    if ((num1 + num2) == cards.length + 1) {
                        isEnd = true;
                        handList.remove(idx2);
                        handList.remove(idx1);
                        break;
                    }
                }
                if (isEnd) {
                    break;
                }
            }
            // 가능한 경우
            if (isEnd) {
                answer++;
                continue;
            }
            // 코인이 다 떨어졌다면
            if (coin == 0) {
                break;
            }
            coin--;
            // 손패 1개, 후보 1개
            for (int idx1 = 0; idx1 < handList.size(); idx1++) {
                num1 = handList.get(idx1);
                for (int idx2 = 0; idx2 < candList.size(); idx2++) {
                    num2 = candList.get(idx2);
                    if ((num1 + num2) == cards.length + 1) {
                        isEnd = true;
                        candList.remove(idx2);
                        handList.remove(idx1);
                        break;
                    }
                }
                if (isEnd) {
                    break;
                }
            }
            // 가능한 경우
            if (isEnd) {
                answer++;
                continue;
            }
            // 코인이 다 떨어졌다면
            if (coin == 0) {
                break;
            }
            coin--;
            // 손패 1개, 후보 2개
            for (int idx1 = 0; idx1 < candList.size(); idx1++) {
                num1 = candList.get(idx1);
                for (int idx2 = idx1 + 1; idx2 < candList.size(); idx2++) {
                    num2 = candList.get(idx2);
                    if ((num1 + num2) == cards.length + 1) {
                        isEnd = true;
                        candList.remove(idx2);
                        candList.remove(idx1);
                        break;
                    }
                }
                if (isEnd) {
                    break;
                }
            }

            // 가능한 경우
            if (isEnd) {
                answer++;
                continue;
            }
            break;
        }
        return answer;
    }
}