package programmers.lv0;

class No_003_삼각형의_완성조건2 {
    public int solution(int[] sides) {
        int answer = 0;

        //가능한 범위: (두수의 합 -1) ~ (두수의 차 +1) +1
        int sum = sides[0] + sides[1];
        int diff = Math.abs(sides[0] - sides[1]);

        answer = (sum - 1) - (diff + 1) + 1;

        return answer;
    }
}
