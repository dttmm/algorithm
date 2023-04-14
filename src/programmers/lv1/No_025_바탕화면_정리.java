package programmers.lv1;

/**
 왼쪽 클릭 지점은 파일들의 위치 중에서 최소값들로 고르고
 오른쪽 클릭 지점은 초디값들을 고름
 클릭 위치를 answer배열에서 관리함
 마지막에 오른쪽 클릭 지점을 하나씩 늘려주면 됨
 */
public class No_025_바탕화면_정리 {

    class Solution {
        public int[] solution(String[] wallpaper) {
            int[] answer = new int[4];  // 0: lux, 1: luy, 2: rdx, 3: rdy
            answer[0] = 51;
            answer[1] = 51;

            for (int i = 0; i < wallpaper.length; i++) {
                String s = wallpaper[i];
                for (int j = 0; j < s.length(); j++) {
                    char c = s.charAt(j);
                    if (c == '.') continue;

                    answer[0] = Math.min(answer[0], i);
                    answer[1] = Math.min(answer[1], j);
                    answer[2] = Math.max(answer[2], i);
                    answer[3] = Math.max(answer[3], j);
                }
            }
            answer[2]++;
            answer[3]++;

            return answer;
        }
    }
}
