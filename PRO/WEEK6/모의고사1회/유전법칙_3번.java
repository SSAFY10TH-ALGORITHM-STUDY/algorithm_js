class Solution {

    String[] pattern = {"RR", "Rr", "Rr", "rr"};

    public String find(int generation, int idx) {
        if (generation == 2) {
            return pattern[idx];
        }
        int div = idx / 4;
        int remainder = idx % 4;
        String parent = find(generation - 1, div);
        if (parent == "RR") {
            return "RR";
        } else if (parent == "rr") {
            return "rr";
        } else {
            if (remainder == 0) {
                return "RR";
            } else if (remainder == 3) {
                return "rr";
            } else {
                return "Rr";
            }
        }
    }

    public String[] solution(int[][] queries) {
        String[] answer = new String[queries.length];
        ;
        for (int idx = 0; idx < queries.length; idx++) {
            int[] query = queries[idx];
            int generation = query[0];
            int index = query[1];
            if (generation == 1) {
                answer[idx] = "Rr";
                continue;
            }
            answer[idx] = find(generation, index - 1);
        }
        return answer;
    }
}