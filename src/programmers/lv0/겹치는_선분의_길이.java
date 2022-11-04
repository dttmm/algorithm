package programmers.lv0;

class 겹치는_선분의_길이 {

    int[] arr;

    public int solution(int[][] lines) {
        int answer = 0;

        arr = new int[201];

        // 모든 점들에 +100을 해주어 범위를 0~200으로 맞추어
        for (int i = 0; i < 3; i++) {
            int x = lines[i][0] + 100;
            int y = lines[i][1] + 100;

            // 배열에 각 점들을 count 한다
            // 0~1 이면 0인 인덱스를 ++
            for (int j = x; j < y; j++) {
                arr[j]++;
            }
        }

        // 배열을 전체 탐색하면서 count가 2 이상인 것들의 개수를 센다
        for (int i = 0; i < 200; i++) {
            if (arr[i] >= 2) answer++;
        }

        return answer;
    }
}