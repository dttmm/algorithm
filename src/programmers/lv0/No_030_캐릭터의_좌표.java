package programmers.lv0;

import java.util.*;

public class No_030_캐릭터의_좌표 {

    class Solution {

        // 상하좌우
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        Map<String, Integer> map;

        int x_limit;
        int y_limit;

        boolean isIn(int x, int y) {
            if (x >= (x_limit * -1) && x <= x_limit && y >= (y_limit * -1) && y <= y_limit) return true;
            return false;
        }

        public int[] solution(String[] keyinput, int[] board) {
            int[] answer = new int[2];
            x_limit = board[0] / 2;
            y_limit = board[1] / 2;
            map = new HashMap();
            map.put("up", 0);
            map.put("down", 1);
            map.put("left", 2);
            map.put("right", 3);

            int x = 0;
            int y = 0;
            for (int i = 0; i < keyinput.length; i++) {
                int dir = map.get(keyinput[i]);

                int newX = x + dx[dir];
                int newY = y + dy[dir];
                if (isIn(newX, newY)) {
                    x = newX;
                    y = newY;
                }
            }

            answer[0] = x;
            answer[1] = y;
            return answer;
        }
    }
}
