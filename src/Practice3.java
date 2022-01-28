public class Practice3 {

    public static int findMin(int[][] arr) {

        int m = 100;
        int i_idx = 0;
        int j_idx = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (arr[i][j] < m) {
                    m = arr[i][j];
                    i_idx = i;
                    j_idx = j;
                }
            }
        }
        arr[i_idx][j_idx] = 100;
        return m;
    }

    public static void main(String[] args) {
        int[][] arr = {{9, 20, 2, 18, 11}, {19, 1, 25, 3, 21}, {8, 24, 10, 17, 7}, {15, 4, 16, 5, 6}, {12, 13, 22, 23, 14}};
        int[][] sorted = new int[5][5];

        int len = 5;
        int pos = 1;
        int i = 0;
        int j = -1;
        for (int k = 0; k < 25; k++) {

            for (int x = 0; x < len; x++) {
                j += pos;
                sorted[i][j] = findMin(arr);
            }

            len--;

            for (int x = 0; x < len; x++) {
                i += pos;
                sorted[i][j] = findMin(arr);
            }
            pos *= -1;
        }

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                System.out.printf("%3d ", sorted[x][y]);
            }
            System.out.println();
        }
    }
}
