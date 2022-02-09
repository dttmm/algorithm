package algorithm.day07tree;

import java.io.FileInputStream;
import java.util.Scanner;

public class Practice {
    static int V;
    static int[][] arr;

    public static void preOrder(int v) {
        if (v != 0) {
            System.out.print(v + " ");
            preOrder(arr[0][v]);
            preOrder(arr[1][v]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        V = 13;
        arr = new int[2][V + 1];
        String[] s = "1 2 1 3 2 4 3 5 3 6 4 7 5 8 5 9 6 10 6 11 7 12 11 13".split(" ");

        int count = 0;
        for (int i = 1; i < V; i++) {
            int n = Integer.parseInt(s[count++]);
            if (arr[0][n] == 0) {
                arr[0][n] = Integer.parseInt(s[count++]);
            } else {
                arr[1][n] = Integer.parseInt(s[count++]);
            }
        }

        preOrder(1);
    }
}
