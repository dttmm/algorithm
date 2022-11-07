package programmers.lv0;

class No_002_안전지대 {

    int N;
    boolean[][] visited;
    int[] di = {-1, -1, 0, 1, 1, 1, 0, -1, 0};
    int[] dj = {0, 1, 1, 1, 0, -1, -1, -1, 0};

    boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < N) return true;
        return false;
    }

    public int solution(int[][] board) {
        N = board[0].length;
        visited = new boolean[N][N];
        int answer = N * N;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    for (int dir = 0; dir < 9; dir++) {
                        int newI = i + di[dir];
                        int newJ = j + dj[dir];
                        if (isIn(newI, newJ) && !visited[newI][newJ]) {
                            visited[newI][newJ] = true;
                            answer--;
                        }
                    }
                }
            }
        }
        return answer;
    }
}