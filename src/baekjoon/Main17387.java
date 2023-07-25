package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 14분 구현 7분 디버깅 23분
 문제를 똭 보자마자 수학 문제라는 것을 느꼈음
 그래서 어떤 방법으로 접근해야할까
 아이디어만 살까 얻어봄
 CCW라..
 CCW로 어떻게 풀어야할지 생각함
 일단 한 선분애 대해서 다른 선분에 있는 두 점에 대해 각각 CCW를 구한 뒤
 그 결과가 둘다 같다면 서로 교차하지 않고
 그 결과가 다르면(외적 방향이 다르면) 교차한다고 판별하면 된다고 생각함

 그런데 한 선분을 기준으로만 CCW를 수행하면
 CCW 결과가 다를지라도 교차하지 않는 경우 발생
 그래서 두 선분을 각각 기준으로 잡고 각각에 대해 CCW를 수행하여
 각각의 결과가 모두 외적 방향이 다르다고 나오거나
 외적 결과가 0이 나온 경우 교차한다고 판단

 틀림
 두 선분이 모두 한 직선 안에 있을 때 예외가 발생함
 한 직선안에 있지만 두 선분이 만나지 않는 경우를 고려해줘야함
 ex) 1 1 2 2, 3 3 4 4
 그래서 외적 결과가 둘 다 0인 경우
 x좌표 기준으로 한 선분의 x좌표가
 다른 선분의 두 x좌표 사이에 있는지 판별하여
 사이에 있을 경우 교차한다고 판단함

 틀림
 두 선분이 모두 한 직선 안에 있을 때
 x좌표 뿐만 아니라 y좌표도 다른 선분의 사이에 있는지 체크해야 하는 예외 발생
 ex) 0 1 0 2, 0 3 0 4
 그리고
 두 선분이 모두 한 직선 안에 있을 때,
 한 선분에 다른 선분이 포함 되어 있을 때는
 a선분에 대해서 b선분이 교차한다고 나오지만
 b선분에 대해서 a선분이 교차하지 않는다고 나오기 때문에
 둘 중 하나에 대해서만 교차해도 최종 정답으로는 교차한다고 나오도록
 solve함수에서 2를 리턴하도록 리턴값을 조정하고
 최종 리턴값이 2이상을 때 교차한다고 판별함
 */
public class Main17387 {

    static Node[] L1;   // 선분1
    static Node[] L2;   // 선분2

    // 선분 좌표 정보
    private static class Node {
        long x;
        long y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 한 선분(n1, n2)안에 다른 선분(n3)의 좌표가 포함되어 있는지 판별
    static boolean isIn(Node n1, Node n2, Node n3) {
        long xMin = Math.min(n1.x, n2.x);
        long xMax = Math.max(n1.x, n2.x);

        long yMin = Math.min(n1.y, n2.y);
        long yMax = Math.max(n1.y, n2.y);

        return (n3.x >= xMin && n3.x <= xMax) && (n3.y >= yMin && n3.y <= yMax);
    }

    // ccw 외적
    static int ccw(Node n1, Node n2, Node n3) {
        long result = (n1.x * n2.y + n2.x * n3.y + n3.x * n1.y) - (n2.x * n1.y + n3.x * n2.y + n1.x * n3.y);

        // 외적 방향 리턴
        if (result > 0) return 1;
        if (result < 0) return -1;
        else return 0;
    }

    // a선분에 대해 b선분이 교차하는지
    static int solve(Node[] a, Node[] b) {
        int result1 = ccw(a[0], a[1], b[0]);
        int result2 = ccw(a[0], a[1], b[1]);

        // 두 선분이 한 직선안에 있는 경우
        if (result1 == 0 && result2 == 0) {
            // a선분안에 b선분의 한 점이 포함되어 있는지
            boolean ret1 = isIn(a[0], a[1], b[0]);
            // a선분안에 b선분의 다른 한 점이 포함되어 있는지
            boolean ret2 = isIn(a[0], a[1], b[1]);

            // a선분 안에 b선분이 포함되어 있는 경우
            if (ret1 || ret2) return 2;
            else return 0;
        }

        // 두 선분이 교차하는 경우
        if (result1 + result2 == 0 || result1 == 0 || result2 == 0) return 1;
        return 0;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/17387.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L1 = new Node[2];
        L2 = new Node[2];

        // 선분1 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        L1[0] = new Node(x, y);
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        L1[1] = new Node(x, y);

        // 선분2 입력 받기
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        L2[0] = new Node(x, y);
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        L2[1] = new Node(x, y);

        // 선분1기준에서 선분2가 교차하는지 체크
        int result1 = solve(L1, L2);
        // 선분2기준에서 선분1이 교차하는지 체크
        int result2 = solve(L2, L1);

        if (result1 + result2 >= 2) System.out.println(1);
        else System.out.println(0);
    }
}
