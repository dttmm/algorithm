package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// 47분+
public class Main1107 {

    static char[] arr;
    static List<Integer> numberList;
    static int target;
    static char[] tr;
    static int count;

    // 중복 순열 만드는 함수
    static void p(int k, int R) {
        if (k == R) {
            String s = String.valueOf(tr);
            numberList.add(Integer.parseInt(s));
        } else {
            for (int i = 0; i < arr.length; i++) {
                // 0으로 시작하는 숫자는 제외. 단 0을 1개 뽑을때는 예외
                if (i == 0 && arr[i] == '0' && k == 0 && R != 1) continue;

                tr[k] = arr[i];
                p(k + 1, R);
            }
        }
    }

    // 이분 탐색 하는 함수
    static void solve(int startIdx, int endIdx) {
        if (startIdx > endIdx) return;

        int midIdx = (startIdx + endIdx) / 2;
        int mid = numberList.get(midIdx);

        if (mid == target) {
            count = Math.abs(target - mid);
            count += String.valueOf(mid).length();
            return;
        } else if (mid > target) {
            int newCount = Math.abs(target - mid);
            newCount += String.valueOf(mid).length();
            if (newCount < count) count = newCount;
            solve(startIdx, midIdx - 1);
        } else {
            int newCount = Math.abs(target - mid);
            newCount += String.valueOf(mid).length();
            if (newCount < count) count = newCount;
            solve(midIdx + 1, endIdx);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1107.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        arr = new char[10 - M];
        numberList = new ArrayList();
        count = 10000000;

        Map<Integer, Boolean> map = new HashMap();

        if (M != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < M; i++) {
                int n = Integer.parseInt(st.nextToken());
                map.put(n, true);
            }
        }

        int index = 0;
        for (int i = 0; i < 10; i++) {
            if (!map.containsKey(i)) arr[index++] = (char) ('0' + i);
        }

        // 숫자를 1개..2개...target의 길이+1개 만큼 뽑으면서 가능한 숫자 리스트에 담음
        for (int i = 1; i <= String.valueOf(target).length() + 1; i++) {
            tr = new char[i];
            p(0, i);
        }

        Collections.sort(numberList);

        solve(0, numberList.size() - 1);

        // 100에서 이동했을 때랑 뭐가더 최소인지 확인
        count = Math.min(Math.abs(target - 100), count);

        System.out.println(count);
    }
}
