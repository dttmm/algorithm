package baekjoon;

import java.io.*;

public class Main1655 {

    final static int MAX_SIZE = 100000;
    static int size;
    static int mid;
    static LowPQ lowPQ;
    static HighPQ highPQ;
    static BufferedWriter bw;

    private static class LowPQ {
        int heap[] = new int[MAX_SIZE * 2];
        int heapSize = 0;

        void heapPush(int value) {
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

        int heapPop() {
            if (heapSize <= 0) {
                return -1;
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
    }

    private static class HighPQ {
        int heap[] = new int[MAX_SIZE * 2];
        int heapSize = 0;

        void heapPush(int value) {
            if (heapSize + 1 > MAX_SIZE) {
                return;
            }

            heap[heapSize] = value;

            int current = heapSize;
            while (current > 0 && heap[current] > heap[(current - 1) / 2]) {
                int temp = heap[(current - 1) / 2];
                heap[(current - 1) / 2] = heap[current];
                heap[current] = temp;
                current = (current - 1) / 2;
            }

            heapSize++;
        }

        int heapPop() {
            if (heapSize <= 0) {
                return -1;
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
                    child = heap[current * 2 + 1] > heap[current * 2 + 2] ? current * 2 + 1 : current * 2 + 2;
                }

                if (heap[current] > heap[child]) {
                    break;
                }

                int temp = heap[current];
                heap[current] = heap[child];
                heap[child] = temp;

                current = child;
            }
            return value;
        }
    }

    public static void insert(int data) {

        // 데이터 집어 넣기
        if (size == 1) {
            mid = data;
        } else {
            // 미드보다 작은 경우
            if (data < mid) {
                highPQ.heapPush(data);
            }
            // 미드보다 큰 경우
            else {
                lowPQ.heapPush(data);
            }

            // mid값 조정하기
            // 짝수일 때
            if (size % 2 == 0) {
                // mid보다 작을 때
                if (data < mid) {
                    lowPQ.heapPush(mid);
                    mid = highPQ.heapPop();
                }
                // mid보다 클 때는 변함없음
                else {
                }
            }
            // 홀수일 때
            else {
                // mid보다 작을 때는 변함없음
                if (data < mid) {
                }
                // mid보다 클 때
                else {
                    highPQ.heapPush(mid);
                    mid = lowPQ.heapPop();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1655.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        size = 0;
        lowPQ = new LowPQ();
        highPQ = new HighPQ();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            size++;
            insert(num);
            bw.write("" + mid);
            bw.newLine();
        }

        bw.close();
    }
}
