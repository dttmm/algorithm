package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 설계 1시간 1분 구현 18분 디버깅 7분
 첫번째 난관
 가장 긴 증가하는 부분 수열을 어떻게 구할 것인가
 보통 나보다 큰 녀석을 찾기 위해 스택을 사용하는데
 스택을 어떻게 활용해야할까 고민쓰
 이전에 풀었던
 가장 긴 증가하는 부분 수열 3 문제 풀었었던 코드 좀 참고함

 어떤 n이 있을때
 이분 탐색을 이용하여
 스택에 n보다 작거나 같은 수 중에서 제일 큰 수를 찾아서
 해당 숫자 위치에 n을 끼워 넣고
 만약 스택에 n보다 작거나 같은 수가 없다면
 스택에 n을 새로 추가해주면
 가장 긴 증가하는 부분 수열의 길이만큼은 구할 수가 있음

 두번째 난관
 정답 경로를 어떻게 저장할 것인가
 스택에 새로운 값 n을 넣을 때
 n이 들어갈 인덱스 바로 앞에 있는 숫자를
 n이 가리키게 하면
 스택의 top에 있는 숫자부터
 자신 앞에 있는 숫자를 타고타고 가면
 경로를 구할 수 있음

 틀림
 시간초과?
 분명 이분탐색 로직은 문제가 없는데
 시간 초과가 어디서 날끄아..?
 아무리봐도 수상한 부분은
 스트링빌더 부분
 경로 출력을 반대로 해주기 위해
 스트링빌더 append말고 insert사용해봤는데 이부분이 의심스러워서
 경로를 임시 스택에 담아놓고
 스택에서 값을 하나씩 꺼내가며 경로를 반대로 출력했더니
 시간안에 잘 되었음
 출력 많을 때
 insert 사용ㄴㄴ해
 */
public class Main14003 {

    static int N;
    static Node[] stack;
    static int top;

    private static class Node {
        int n;      // 자신의 숫자
        Node prev;  // 스택에서 자신보다 앞에 있는 녀석 가리킴

        public Node(int n) {
            this.n = n;
        }
    }

    // 이분 탐색으로 n이 들어갈 인덱스 구하기
    static int solve(int start, int end, int n) {
        if (start > end) return start;  // n이 들어갈 인덱스 반환

        int mid = (start + end) / 2;

        // n이 더 큰 경우
        if (n > stack[mid].n) return solve(mid + 1, end, n);
        // n이 작거나 같은 경우
        else return solve(start, mid - 1, n);
    }

    // 스택에 값 삽입
    static void insert(Node node) {
        // 스택이 비어있는 경우
        if (top == -1) {
            stack[++top] = node;
            return;
        }

        // 새로운 노드가 들어갈 인덱스 구하기
        int index = solve(0, top, node.n);
        stack[index] = node;
        top = Math.max(top, index);

        if (index < 1) return;
        // 자신 보다 앞에 있는 노드 가리킴
        node.prev = stack[index - 1];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/14003.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stack = new Node[1000001];
        top = -1;

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            Node newNode = new Node(n);

            // 스택에 값 추가
            insert(newNode);
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(top + 1 + "\n");

        // 경로 구하기
        Stack<Integer> answer = new Stack<>();
        Node node = stack[top];
        answer.add(node.n);
        while (node.prev != null) {
            node = node.prev;
            answer.add(node.n);
        }

        // 경로 출력
        while (!answer.isEmpty()) sb.append(answer.pop() + " ");

        System.out.println(sb);
    }
}
