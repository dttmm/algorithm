package baekjoon;

import java.io.*;

public class Main5985 {
    static int N;
    static int total;

    public static int[] solve(int[] arr) {
        if (arr.length == 2) {
            int[] newArr = new int[2];
            if (arr[0] <= arr[1]) {
                newArr[0] = arr[0];
                newArr[1] = arr[1];
            } else {
                newArr[0] = arr[1];
                newArr[1] = arr[0];
                total += 2;
            }
            return newArr;
        } else {
            int mid = arr.length / 2;

            int[] arr1 = new int[mid];
            for (int i = 0; i < mid; i++) {
                arr1[i] = arr[i];
            }
            arr1 = solve(arr1);

            int[] arr2 = new int[mid];
            for (int i = 0; i < mid; i++) {
                arr2[i] = arr[mid + i];
            }
            arr2 = solve(arr2);

            if (arr1[0] <= arr2[0]) {
                for (int i = 0; i < mid; i++) {
                    arr[i] = arr1[i];
                }
                for (int i = 0; i < mid; i++) {
                    arr[mid + i] = arr2[i];
                }
            } else {
                for (int i = 0; i < mid; i++) {
                    arr[i] = arr2[i];
                }
                for (int i = 0; i < mid; i++) {
                    arr[mid + i] = arr1[i];
                }
                total += arr.length * mid;
            }
            return arr;
        }
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/5985.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = (int) Math.pow(2, Integer.parseInt(br.readLine()));
        total = 0;
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        arr = solve(arr);

        bw.write(total + "");
        bw.newLine();
        for (int i = 0; i < N; i++) {
            bw.write(arr[i] + "");
            bw.newLine();
        }
        bw.close();
    }
}
