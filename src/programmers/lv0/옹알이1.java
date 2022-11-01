package programmers.lv0;

import java.util.*;

class 옹알이1 {

    String[] arr = {"aya", "ye", "woo", "ma"};
    Map<String, Boolean> map;
    String[] tr;
    boolean[] visited;

    void solve(int k, int R) {
        if (k == R) {
            String s = "";
            for (int i = 0; i < R; i++) {
                s += tr[i];
            }
            map.put(s, true);
        } else {
            for (int i = 0; i < 4; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    tr[k] = arr[i];
                    solve(k + 1, R);
                    visited[i] = false;
                }
            }
        }
    }

    public int solution(String[] babbling) {
        int answer = 0;
        map = new HashMap();

        // map init
        for (int i = 1; i <= 4; i++) {
            tr = new String[i];
            visited = new boolean[4];
            solve(0, i);
        }

        for (int i = 0; i < babbling.length; i++) {
            if (map.containsKey(babbling[i])) answer++;
        }

        return answer;
    }
}