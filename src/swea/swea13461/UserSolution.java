package swea.swea13461;

class UserSolution {
    final static int MAX_N = 10000;
    final int MAX_M = 10;
    static int N;
    static int M;
    static Node tree;

    static int answer_id;
    static int answer_limit;

    private static class Node {
        char c;
        Node[] next = new Node[2];
        boolean isEnd = false;
        int id = MAX_N + 1;
    }

    void init(int N, int M, char mImageList[][][]) {
        this.N = N;
        this.M = M;
        tree = new Node();

        for (int k = 0; k < N; k++) {
            Node currentNode = tree;

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    char c = mImageList[k][i][j];

                    Node newNode = new Node();
                    if (c == 0) {
                        newNode.c = 0;
                    } else {
                        newNode.c = 1;
                    }

                    if (currentNode.next[c] == null) {
                        currentNode.next[c] = newNode;
                    }

                    currentNode = currentNode.next[c];
                }
            }
            currentNode.isEnd = true;
            if (k + 1 < currentNode.id) {
                currentNode.id = k + 1;
            }
        }
    }

    int findImage(char mImage[][]) {
        answer_id = MAX_N + 1;
        answer_limit = 2;

        Node currentNode = tree;

        String s = "";

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                char c = mImage[i][j];

                s += c;
            }
        }

        solve(currentNode, 0, s, 0);

        return answer_id;
    }

    static void solve(Node currentNode, int current_limit, String s, int index) {
        if (currentNode == null)
            return;

        if (current_limit < answer_limit) {
            if (currentNode.isEnd) {
                answer_limit = current_limit;
                answer_id = MAX_N + 1;
                if (currentNode.id < answer_id)
                    answer_id = currentNode.id;
            } else {
                char c = s.charAt(index);
                Node node = currentNode.next[c];
                Node otherNode = currentNode.next[1 - c];

                solve(node, current_limit, s, index + 1);
                solve(otherNode, current_limit + 1, s, index + 1);
            }
        } else if (current_limit == answer_limit) {
            if (currentNode.isEnd) {
                if (currentNode.id < answer_id)
                    answer_id = currentNode.id;
            } else {
                char c = s.charAt(index);
                Node node = currentNode.next[c];
                solve(node, current_limit, s, index + 1);
            }
        }
    }
}