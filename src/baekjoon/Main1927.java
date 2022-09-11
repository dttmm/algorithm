package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// 최소 힙 직접 구현해서 풀기
public class Main1927 {

    static int N;

    static int MAX_SIZE;
    static int heap[];
    static int heapSize = 0;

    static void heapPush(int value) {
        if (heapSize + 1 > MAX_SIZE) {
            return;
        }

        heap[heapSize] = value;

        int current = heapSize;
        while (current > 0 && heap[current] < heap[(current - 1) / 2]) {
            int temp = heap[(current - 1) / 2];
            heap[(current - 1) / 2] = heap[current];
            heap[current] = temp;
            current = (current - 1) / 2;
        }

        heapSize++;
    }

    static int heapPop() {
        if (heapSize <= 0) {
            return 0;
        }

        int value = heap[0];
        heapSize--;

        heap[0] = heap[heapSize];

        int current = 0;
        while (current < heapSize && current * 2 + 1 < heapSize) {
            int child;
            if (current * 2 + 2 >= heapSize) {
                child = current * 2 + 1;
            } else {
                child = heap[current * 2 + 1] < heap[current * 2 + 2] ? current * 2 + 1 : current * 2 + 2;
            }

            if (heap[current] < heap[child]) {
                break;
            }

            int temp = heap[current];
            heap[current] = heap[child];
            heap[child] = temp;

            current = child;
        }
        return value;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1927.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        MAX_SIZE = N;
        heap = new int[MAX_SIZE];
        heapSize = 0;

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) {
                System.out.println(heapPop());
            } else {
                heapPush(n);
            }
        }
    }
}
