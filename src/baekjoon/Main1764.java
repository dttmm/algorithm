package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 해시테이블 + 머지소트 직접 구현해서 풀어보기
public class Main1764 {

    static String[] arr;
    static String[] sorted;
    static int e;

    private static class Node {
        String key;
        String value;
        Node next;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class MyHashTable {
        Node[] heads;
        Node[] tails;
        int capacity;

        public MyHashTable(int capacity) {
            this.capacity = capacity;
            heads = new Node[capacity + 1];
            tails = new Node[capacity + 1];
        }

        private int hash(String str) {
            int hash = 5381;

            for (int i = 0; i < str.length(); i++) {
                int c = (int) str.charAt(i);
                hash = ((hash << 5) + hash) + c;
            }
            if (hash < 0) hash *= -1;
            return hash;
        }

        private int converToIndex(int hash) {
            return hash % capacity;
        }

        void add(String key, String value) {
            int h = hash(key);
            int index = converToIndex(h);

            Node newNode = new Node(key, value);
            if (heads[index] == null) {
                heads[index] = newNode;
                tails[index] = newNode;
            } else {
                tails[index].next = newNode;
                tails[index] = newNode;
            }
        }

        public String find(String key) {
            int h = hash(key);
            int index = converToIndex(h);

            Node node = heads[index];

            while (node != null) {
                if (node.key.equals(key)) return node.value;
                node = node.next;
            }
            return null;
        }
    }

    public static void merge(int s, int mid, int e) {
        int i = s;
        int j = mid + 1;
        int k = i;
        while (i <= mid && j <= e) {
            if (arr[i].compareTo(arr[j]) < 0) {
                sorted[k] = arr[i++];
            } else {
                sorted[k] = arr[j++];
            }
            k++;
        }

        while (i <= mid) {
            sorted[k] = arr[i++];
            k++;
        }

        while (j <= e) {
            sorted[k] = arr[j++];
            k++;
        }

        while (s <= e) {
            arr[s] = sorted[s];
            s++;
        }
    }


    public static void mergerSort(int s, int e) {
        if (s < e) {
            int mid = (s + e) / 2;
            mergerSort(s, mid);
            mergerSort(mid + 1, e);
            merge(s, mid, e);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1764.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new String[500001];
        sorted = new String[500001];
        e = -1;

        MyHashTable ht = new MyHashTable(100000);

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            ht.add(s, s);
        }

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            if (ht.find(s) != null) {
                arr[++e] = s;
            }
        }

        mergerSort(0, e);

        System.out.println(e + 1);
        for (int i = 0; i <= e; i++) {
            System.out.println(arr[i]);
        }
    }
}
