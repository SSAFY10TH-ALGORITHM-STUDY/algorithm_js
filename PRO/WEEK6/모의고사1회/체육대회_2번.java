class Solution {

    int maxAbility;
    int numSports;
    int[] abilityList;
    boolean[] isSelected;

    public void permutation(int selectedIdx, int[][] ability) {
        // 기저 조건 모두 선택된 경우
        if (selectedIdx == numSports) {
            int total = 0;
            for (int idx = 0; idx < numSports; idx++) {
                total += abilityList[idx];
            }
            if (total > maxAbility) {
                maxAbility = total;
            }
            return;
        }

        for (int idx = 0; idx < ability.length; idx++) {
            // 이미 선택된 경우
            if (isSelected[idx]) {
                continue;
            }
            // 선택한 경우
            isSelected[idx] = true;
            abilityList[selectedIdx] = ability[idx][selectedIdx];
            permutation(selectedIdx + 1, ability);
            isSelected[idx] = false;
        }

    }

    public int solution(int[][] ability) {
        // 학생 수, 종목 수 설정
        int numStudent = ability.length;
        numSports = ability[0].length;
        // 종목 별로 선택된 능력치와 선택됐는지 여부
        abilityList = new int[numSports];
        isSelected = new boolean[numStudent];
        maxAbility = 0;
        // 조합 수행
        permutation(0, ability);
        return maxAbility;
    }
}