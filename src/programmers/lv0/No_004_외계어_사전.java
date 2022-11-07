package programmers.lv0;

import java.util.*;

class No_004_외계어_사전 {

    int N;                      // spell의 길이
    int R;                      // N개중 R개를 뽑는다
    String[] arr;               // spell 배열
    String[] tr;                // 뽑은 배열
    boolean[] visited;          // 순열용 visited
    Map<String, Boolean> map;
    Boolean flag;

    void initMap(String[] dic) {
        for (int i = 0; i < dic.length; i++) {
            map.put(dic[i], true);
        }
    }

    void solve(int k) {
        if (k == R) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < R; i++) {
                sb.append(tr[i]);
            }

            String s = sb.toString();

            if (map.containsKey(s)) {
                flag = true;
            }
        } else {
            if (flag) return;

            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    tr[k] = arr[i];
                    solve(k + 1);
                    visited[i] = false;
                }
            }
        }
    }

    public int solution(String[] spell, String[] dic) {
        int answer = 0;

        N = spell.length;
        R = spell.length;
        arr = spell;
        tr = new String[R];
        visited = new boolean[N];
        map = new HashMap();
        flag = false;

        // dic에 있는 단어를 map에 담는다
        initMap(dic);

        // spell에서 순열 돌린다
        solve(0);

        answer = flag ? 1 : 2;
        return answer;
    }
}