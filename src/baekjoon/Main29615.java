package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 설계 6분 구현 6분 디버깅 10분
 투포인터

 처음에는 완탐으로 접근함
 앞에서 부터 순회하면서
 친구가 아닌 번호를 만나면
 해당 위치에서부터 이중 순회하면서
 친구번호를 찾고
 번호를 바꿨다고 처리를 해줌

 틀림
 번호가 1 2 3 4 5 이렇게 있고
 친구 번호가 2 3 4 5인 경우
 1과 5를 한번만 바꿔주면 되지만
 완탐을 하게 되면 1과 2를 바꾸고
 또다시 1과 3을 바꾸고..
 총 4번 바꾸게 되서 비효율적으로 동작하게 됨
 효율적으로 동작하기 위해
 앞에서부터 순회하면서 친구가 아닌 번호를 만나면
 맨 뒤에서부터 친구를 찾아서 번호를 교환해줌
 */
public class Main29615 {

    static int N;
    static int M;
    static int[] arr;
    static Set<Integer> friend; // 친구 번호 정보
    static int answer;

    // 투포인터
    static void solve() {
        int left = 0;
        int right = N - 1;

        // 투포인터 조건과 친구를 다 검사할 때까지 반복
        while (left < right && M > 0) {
            // left가 친구 번호가 아닐 때
            if (!friend.contains(arr[left])) {
                // 오른쪽에서부터 탐색하면서 친구 번호를 찾음
                while (!friend.contains(arr[right])) right--;

                // 투포인터 조건 갱신
                right--;
                // 바뀐 횟수 갱신
                answer++;
            }

            // 투포인터 조건 갱신
            left++;
            // 검사한 친구 수 갱신
            M--;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/29615.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        friend = new HashSet();
        answer = 0;

        // 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        // 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(st.nextToken());
            friend.add(n);
        }

        // 투포인터
        solve();

        System.out.println(answer);
    }
}
