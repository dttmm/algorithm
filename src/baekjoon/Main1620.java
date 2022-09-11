package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1620 {

    static int N;
    static int M;
    static MyHashTable ht;

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

        void insert(String key, String value) {
            int hash = getHash(key);
            int index = converToIndex(hash);

            Node newNode = new Node(key, value);
            if (heads[index] == null) {
                heads[index] = newNode;
                tails[index] = newNode;
            } else {
                tails[index].next = newNode;
                tails[index] = newNode;
            }
        }

        String find(String key) {
            int hash = getHash(key);
            int index = converToIndex(hash);

            Node node = heads[index];

            while (node != null) {
                if (node.key.equals(key)) return node.value;
                node = node.next;
            }

            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1620.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ht = new MyHashTable(100000);

        for (int i = 1; i < N + 1; i++) {
            String data1 = "" + i;
            String data2 = br.readLine();

            ht.insert(data1, data2);
            ht.insert(data2, data1);
        }

        for (int i = 0; i < M; i++) {
            String s = br.readLine();

            String result = ht.find(s);
            System.out.println(result);
        }
    }
}
