package algorithm.day04stack1;

import java.util.Stack;

public class Practice3 {
    static boolean[] visited;
    static Stack<Integer> st;

    // 재귀
    static public void dfsR(int[][] arr, int node) {
        if (!visited[node]) {
            visited[node] = true;
            System.out.println(node + 1);
            for (int i = 0; i < 7; i++) {
                if (arr[node][i] == 1) {
                    if (!visited[i]) {
                        dfsR(arr, i);
                    }
                }
            }
        }
    }

    // 반복
    static public void dfsI(int[][] arr, int node) {
        st.push(node);
        while(!st.isEmpty()){
            int v = st.pop();
            if (!visited[v]){
                visited[v] = true;
                System.out.println(v+1);
                for (int i = 0; i < 7; i++) {
                    if (arr[v][i] == 1) {
                        if (!visited[i]) {
                            st.push(i);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = {
                {0, 1, 1, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 1},
                {0, 0, 1, 0, 0, 1, 0}
        };
        st = new Stack<>();
        visited = new boolean[7];

//        dfsR(arr, 0);
        dfsI(arr,0);
    }
}
