package algorithm.day06queue;

public class Practice {
    static int[][] G;
    static int[] q = new int[10];
    static int front = -1;
    static int rear = -1;
    static boolean[] visited = new boolean[8];

    public static void BFS() {

        while (!(front == rear)) {
            int v = q[++front];
            for (int i = 1; i <= G[v][0]; i++) {
                int w = G[v][i];
                if (!visited[w]) {
                    visited[w] = true;
                    q[++rear] = w;
                    System.out.print(w + " ");
                }
            }
        }
    }


    public static void main(String[] args) {
        G = new int[8][8];

        char[] c = "1213242546566737".toCharArray();
        for (int i = 0; i < 8; i++) {
            int from = c[2 * i] - '0';
            int to = c[2 * i + 1] - '0';
            G[from][++G[from][0]] = to;
        }


        System.out.print(1 + " ");
        q[++rear] = 1;
        visited[1] = true;
        BFS();
    }
}
