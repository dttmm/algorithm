package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 16분 구현 10분 디버깅 16분
 세그먼트트리
 주어진 구간을 일일이 색칠하게 될 경우
 최대 10만 * 10만으로 시간초과 발생
 색칠된 칸의 정보를 효율적으로 관리하기 위해 세그먼트트리 사용
 특정 구간을 색칠할 때
 자식들의 색을 상위 부모의 색으로 나타낼 수 있으면 나타냄

 각 칸에 무슨 색이 칠해져 있는지 출력할 때는
 부모 노드로 올라가면서 칠해진 색이 있는 경우
 해당 색을 리턴하도록 함

 틀림
 색을 칠할 때
 해당 칸에 색이 없는 경우에만 새로 색을 칠해주었는데
 해당 칸을 포함하고 있는 부모에도 색이 있는지 없는지 확인을 해주어야 되는 것을 간과함

 세그먼트트리 오랜만에 해서
 기억이 가물치했음
 */
public class Main29618 {

    static int N;
    static int Q;
    static int[] tree;
    static int treeSize;
    static int startTreeIndex;

    // 트리 만들기
    static void initTree() {
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        treeSize = (int) Math.pow(2, h + 1);
        startTreeIndex = treeSize / 2;
        tree = new int[treeSize];
    }

    // 색 칠하기
    static void query(int start, int end, int color) {
        if (start > end) return;

        if (start % 2 == 1) {
            // 해당 칸에 색이 칠해지지 않은 경우
            if (getColor(start) == 0) tree[start] = color;
        }
        if (end % 2 == 0) {
            // 해당 칸에 색이 칠해지지 않은 경우
            if (getColor(end) == 0) tree[end] = color;
        }

        start = (start + 1) / 2;
        end = (end - 1) / 2;

        query(start, end, color);
    }

    // 칸에 색칠된 색 알아내기
    static int getColor(int index) {
        if (index == 1) return tree[index];

        // 상위 부모 색 없는지 확인
        if (tree[index] == 0) return getColor(index / 2);

        return tree[index];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/29618.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        // 트리 만들기
        initTree();

        // 입력 받기
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());

            int treeStart = start - 1 + startTreeIndex;
            int treeEnd = end - 1 + startTreeIndex;
            // 색칠하기
            query(treeStart, treeEnd, color);
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int index = i + startTreeIndex;
            int color = getColor(index);
            sb.append(color + " ");
        }

        System.out.println(sb);
    }
}
