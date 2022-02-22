package algorithm.day12graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// 50분
public class Solution1251 {
    static int N;
    static int[][] arr;
    static double E;
    static int[] parent;
    static int[] tr;
    static List<Node> list;

    private static class Node implements Comparable<Node> {
        int v1;
        int v2;
        double cost;

        Node(int v1, int v2, double cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            if (this.cost > o.cost) return 1;
            else if (this.cost < o.cost) return -1;
            else return 0;
        }
    }

    public static double calCost(int x1, int y1, int x2, int y2) {
        int diffx = x1 - x2;
        int diffy = y1 - y2;
        return E * (Math.pow(diffx, 2) + Math.pow(diffy, 2));
    }

    public static void c(int k, int start) {
        if (k == 2) {
            Node node = new Node(tr[0], tr[1], calCost(arr[0][tr[0]], arr[1][tr[0]], arr[0][tr[1]], arr[1][tr[1]]));
            list.add(node);
        } else {
            for (int i = start; i < N; i++) {
                tr[k] = i;
                c(k + 1, i + 1);
            }
        }
    }

    public static int getParent(int v) {
        if (parent[v] == v) return v;
        return parent[v] = getParent(parent[v]);
    }


    public static boolean isSameParent(Node node) {
        if (getParent(node.v1) != getParent(node.v2)) return false;
        return true;
    }

    public static void unionParent(Node node) {
        int a = getParent(node.v1);
        int b = getParent(node.v2);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day12_1251.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[2][N];
            tr = new int[2];
            parent = new int[N];
            list = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[0][i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[1][i] = Integer.parseInt(st.nextToken());
            }
            E = Double.parseDouble(br.readLine());

            c(0, 0);
            Collections.sort(list);

            int k = 0;
            double total = 0.0;
            for (int i = 0; k < N - 1; i++) {
                Node node = list.get(i);
                if (!isSameParent(node)) {
                    unionParent(node);
                    total += node.cost;
                    k++;
                }
            }

            System.out.println("#" + test_case + " " + Math.round(total));

        }
    }
}