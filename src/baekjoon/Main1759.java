package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 먼저 모음 리스트(arr)와 자음 리스트(brr)를 나누어 저장
 먼저, 모음 리스트(arr)에서 모음 하나 꺼낸 다음
 자음 리스트(brr)에서 자음 두개 꺼냄
 그리고 나머지 선택되지 못한 알파벳 중에서 나머지만큼 꺼냄

 선택되지 못한 알파벳은 set으로 관리함

 아 뭐야
 그냥 조합으로 뽑아서
 다 뽑고난 다음에 조건검사해도 되자너
 내 방법이 더 효율적일 줄 알았는데
 그냥 조합으로 다 뽑는게 내꺼보다 두 배 빠르네..
 */
public class Main1759 {

    static int L;
    static int C;
    static List<Character> arr;
    static List<Character> brr;
    static Set<Character> set;
    static char[] tr;
    static TreeSet<String> answerSet;
    static StringBuilder sb;

    static void solve(int k, int start, int R, List<Character> list, int type) {
        if (k == R) {
            // arr에서 다 뽑았을 때
            if (type == 1) {
                // 이제 brr에서 뽑기 시작
                solve(k, 0, 3, brr, 2);
            }
            // brr에서 다 뽑았을 때
            else if (type == 2) {
                List<Character> setList = new ArrayList();
                for (Character c : set) {
                    setList.add(c);
                }
                // 이제 선택되지 못한 알파벳 중에서 뽑기 시작
                solve(k, 0, L, setList, 3);
            }
            // 다 뽑았을 때
            else {
                char[] crr = tr.clone();
                Arrays.sort(crr);
                String s = String.valueOf(crr);
                answerSet.add(s);
            }
        } else {
            for (int i = start; i < list.size(); i++) {
                char c = list.get(i);

                tr[k] = c;

                // arr, brr에서 뽑은 것은 선택되지 못한 알파벳 set에서 제거해줌
                if (type <= 2) set.remove(c);

                solve(k + 1, i + 1, R, list, type);

                // brr에서 뽑고 나온 경우, 다시 해다 알파벳 set에 넣어줌
                if (type == 2) set.add(c);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1759.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new ArrayList();
        brr = new ArrayList();
        set = new HashSet();
        tr = new char[L];
        answerSet = new TreeSet();
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            char c = st.nextToken().charAt(0);

            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') arr.add(c);
            else brr.add(c);
            set.add(c);
        }

        solve(0, 0, 1, arr, 1);

        for (String s : answerSet) {
            sb.append(s + "\n");
        }

        System.out.println(sb);
    }
}
