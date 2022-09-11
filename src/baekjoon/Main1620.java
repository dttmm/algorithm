package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1620 {

    static int N;
    static int M;
    static Node[] nodes_index;  // index를 키값으로 가짐
    static MyHashTable ht_data;   // data를 키값으로 가짐

    private static class Node {
        int index;
        String data;
        Node next;

        public Node(int index, String data) {
            this.index = index;
            this.data = data;
        }
    }

    private static class MyHashTable {
        int capacity;
        Node[] heads;
        Node[] tails;

        public MyHashTable(int capacity) {
            this.capacity = capacity;
            heads = new Node[capacity];
            tails = new Node[capacity];
        }

        int getHash(String str) {
            int hash = 5381;
            for (int i = 0; i < str.length(); i++) {
                int c = (int) str.charAt(i);
                hash = ((hash << 5) + hash) + c;
            }
            if (hash < 0) hash *= -1;
            return hash;
        }

        int converToIndex(int hash) {
            return hash % capacity;
        }

        void insert(String key, int data) {
            int hash = getHash(key);
            int index = converToIndex(hash);

            Node newNode = new Node(data, key);
            if (heads[index] == null) {
                heads[index] = newNode;
                tails[index] = newNode;
            } else {
                tails[index].next = newNode;
                tails[index] = newNode;
            }
        }

        int find(String key) {
            int hash = getHash(key);
            int index = converToIndex(hash);

            Node node = heads[index];

            while (node != null) {
                if (node.data.equals(key)) return node.index;
                node = node.next;
            }

            return -1;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1620.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes_index = new Node[N + 1];
        ht_data = new MyHashTable(100000);

        for (int i = 1; i < N + 1; i++) {
            String data = br.readLine();
            Node newNode = new Node(i, data);

            nodes_index[i] = newNode;
            ht_data.insert(data, i);
        }

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            int n = s.charAt(0) - '0';

            // 숫자인 경우
            if (n >= 0 && n < 10) {
                String result = nodes_index[Integer.parseInt(s)].data;
                System.out.println(result);
            } else {
                int result = ht_data.find(s);
                System.out.println(result);
            }
        }
    }
}
