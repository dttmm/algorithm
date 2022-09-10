package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main5373 {

    static ArrayList<Rotate> rotates;
    static NodeManager nodeManager;

    private static class NodeManager {
        Node UP;
        Node DOWN;
        Node FRONT;
        Node BACK;
        Node LEFT;
        Node RIGHT;

        public NodeManager() {
            UP = new Node('w');
            DOWN = new Node('y');
            FRONT = new Node('r');
            BACK = new Node('o');
            LEFT = new Node('g');
            RIGHT = new Node('b');

            UP.up = BACK;
            UP.down = FRONT;
            UP.left = LEFT;
            UP.right = RIGHT;
            UP.up_i = new int[]{2, 2, 2};
            UP.up_j = new int[]{0, 1, 2};
            UP.down_i = new int[]{0, 0, 0};
            UP.down_j = new int[]{0, 1, 2};
            UP.left_i = new int[]{0, 1, 2};
            UP.left_j = new int[]{2, 2, 2};
            UP.right_i = new int[]{0, 1, 2};
            UP.right_j = new int[]{0, 0, 0};

            DOWN.up = FRONT;
            DOWN.down = BACK;
            DOWN.left = LEFT;
            DOWN.right = RIGHT;
            DOWN.up_i = new int[]{2, 2, 2};
            DOWN.up_j = new int[]{0, 1, 2};
            DOWN.down_i = new int[]{0, 0, 0};
            DOWN.down_j = new int[]{0, 1, 2};
            DOWN.left_i = new int[]{2, 1, 0};
            DOWN.left_j = new int[]{0, 0, 0};
            DOWN.right_i = new int[]{2, 1, 0};
            DOWN.right_j = new int[]{2, 2, 2};

            FRONT.up = UP;
            FRONT.down = DOWN;
            FRONT.left = LEFT;
            FRONT.right = RIGHT;
            FRONT.up_i = new int[]{2, 2, 2};
            FRONT.up_j = new int[]{0, 1, 2};
            FRONT.down_i = new int[]{0, 0, 0};
            FRONT.down_j = new int[]{0, 1, 2};
            FRONT.left_i = new int[]{2, 2, 2};
            FRONT.left_j = new int[]{2, 1, 0};
            FRONT.right_i = new int[]{2, 2, 2};
            FRONT.right_j = new int[]{0, 1, 2};

            BACK.up = DOWN;
            BACK.down = UP;
            BACK.left = LEFT;
            BACK.right = RIGHT;
            BACK.up_i = new int[]{2, 2, 2};
            BACK.up_j = new int[]{0, 1, 2};
            BACK.down_i = new int[]{0, 0, 0};
            BACK.down_j = new int[]{0, 1, 2};
            BACK.left_i = new int[]{0, 0, 0};
            BACK.left_j = new int[]{0, 1, 2};
            BACK.right_i = new int[]{0, 0, 0};
            BACK.right_j = new int[]{2, 1, 0};

            LEFT.up = BACK;
            LEFT.down = FRONT;
            LEFT.left = DOWN;
            LEFT.right = UP;
            LEFT.up_i = new int[]{0, 1, 2};
            LEFT.up_j = new int[]{0, 0, 0};
            LEFT.down_i = new int[]{2, 1, 0};
            LEFT.down_j = new int[]{0, 0, 0};
            LEFT.left_i = new int[]{2,1,0};
            LEFT.left_j = new int[]{0,0,0};
            LEFT.right_i = new int[]{0, 1, 2};
            LEFT.right_j = new int[]{0, 0, 0};

            RIGHT.up = BACK;
            RIGHT.down = FRONT;
            RIGHT.left = UP;
            RIGHT.right = DOWN;
            RIGHT.up_i = new int[]{2, 1, 0};
            RIGHT.up_j = new int[]{2, 2, 2};
            RIGHT.down_i = new int[]{0, 1, 2};
            RIGHT.down_j = new int[]{2, 2, 2};
            RIGHT.left_i = new int[]{0, 1, 2};
            RIGHT.left_j = new int[]{2, 2, 2};
            RIGHT.right_i = new int[]{2,1,0};
            RIGHT.right_j = new int[]{2,2,2};
        }
    }

    private static class Node {
        Character[][] arr;
        Node up;
        Node down;
        Node left;
        Node right;
        int[] up_i;
        int[] up_j;
        int[] down_i;
        int[] down_j;
        int[] left_i;
        int[] left_j;
        int[] right_i;
        int[] right_j;

        public Node(Character type) {
            this.arr = new Character[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    arr[i][j] = type;
                }
            }
        }
    }

    private static class Rotate {
        Character side;
        boolean clock_d; // 시계방향(오른쪽)이냐

        public Rotate(String s) {
            this.side = s.charAt(0);
            if (s.charAt(1) == '+') this.clock_d = true;
        }
    }

    public static void run() {
        for (Rotate rotate : rotates) {

            // 위쪽면
            if (rotate.side == 'U') {
                Node node = nodeManager.UP;
                doRotate(node, rotate.clock_d);
            }
            // 아래쪽면
            else if (rotate.side == 'D') {
                Node node = nodeManager.DOWN;
                doRotate(node, rotate.clock_d);
            }
            // 앞면
            else if (rotate.side == 'F') {
                Node node = nodeManager.FRONT;
                doRotate(node, rotate.clock_d);
            }
            // 뒷면
            else if (rotate.side == 'B') {
                Node node = nodeManager.BACK;
                doRotate(node, rotate.clock_d);
            }
            // 왼쪽면
            else if (rotate.side == 'L') {
                Node node = nodeManager.LEFT;
                doRotate(node, rotate.clock_d);
            }
            // 오른쪽면
            else if (rotate.side == 'R') {
                Node node = nodeManager.RIGHT;
                doRotate(node, rotate.clock_d);
            }
        }
    }


    // 회전시키기
    public static void doRotate(Node node, boolean isClock_d) {
        // 각 블럭에서 3개를 가져온다
        Character[] up = new Character[3];
        up[0] = node.up.arr[node.up_i[0]][node.up_j[0]];
        up[1] = node.up.arr[node.up_i[1]][node.up_j[1]];
        up[2] = node.up.arr[node.up_i[2]][node.up_j[2]];

        Character[] left = new Character[3];
        left[0] = node.left.arr[node.left_i[0]][node.left_j[0]];
        left[1] = node.left.arr[node.left_i[1]][node.left_j[1]];
        left[2] = node.left.arr[node.left_i[2]][node.left_j[2]];

        Character[] down = new Character[3];
        down[0] = node.down.arr[node.down_i[0]][node.down_j[0]];
        down[1] = node.down.arr[node.down_i[1]][node.down_j[1]];
        down[2] = node.down.arr[node.down_i[2]][node.down_j[2]];

        Character[] right = new Character[3];
        right[0] = node.right.arr[node.right_i[0]][node.right_j[0]];
        right[1] = node.right.arr[node.right_i[1]][node.right_j[1]];
        right[2] = node.right.arr[node.right_i[2]][node.right_j[2]];


        // 오른쪽인 경우
        // 오른쪽으로 회전시키면서 저장한다
        if (isClock_d) {
            node.right.arr[node.right_i[0]][node.right_j[0]] = up[0];
            node.right.arr[node.right_i[1]][node.right_j[1]] = up[1];
            node.right.arr[node.right_i[2]][node.right_j[2]] = up[2];

            node.down.arr[node.down_i[0]][node.down_j[0]] = right[2];
            node.down.arr[node.down_i[1]][node.down_j[1]] = right[1];
            node.down.arr[node.down_i[2]][node.down_j[2]] = right[0];

            node.left.arr[node.left_i[0]][node.left_j[0]] = down[0];
            node.left.arr[node.left_i[1]][node.left_j[1]] = down[1];
            node.left.arr[node.left_i[2]][node.left_j[2]] = down[2];

            node.up.arr[node.up_i[0]][node.up_j[0]] = left[2];
            node.up.arr[node.up_i[1]][node.up_j[1]] = left[1];
            node.up.arr[node.up_i[2]][node.up_j[2]] = left[0];

            // 해당 노드 오른쪽으로 회전
            Character[][] temp = new Character[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[i][j] = node.arr[3 - 1 - j][i];
                }
            }
            node.arr = temp;
        }
        // 왼쪽인경우
        // 왼쪽으로 회전시키면서 저장한다
        else {
            node.left.arr[node.left_i[0]][node.left_j[0]] = up[2];
            node.left.arr[node.left_i[1]][node.left_j[1]] = up[1];
            node.left.arr[node.left_i[2]][node.left_j[2]] = up[0];

            node.down.arr[node.down_i[0]][node.down_j[0]] = left[0];
            node.down.arr[node.down_i[1]][node.down_j[1]] = left[1];
            node.down.arr[node.down_i[2]][node.down_j[2]] = left[2];

            node.right.arr[node.right_i[0]][node.right_j[0]] = down[2];
            node.right.arr[node.right_i[1]][node.right_j[1]] = down[1];
            node.right.arr[node.right_i[2]][node.right_j[2]] = down[0];

            node.up.arr[node.up_i[0]][node.up_j[0]] = right[0];
            node.up.arr[node.up_i[1]][node.up_j[1]] = right[1];
            node.up.arr[node.up_i[2]][node.up_j[2]] = right[2];

            // 해당 노드 왼쪽으로 회전
            Character[][] temp = new Character[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[i][j] = node.arr[j][3 - 1 - i];
                }
            }
            node.arr = temp;
        }
    }

    public static void printResult() {
        Node node = nodeManager.UP;
        for (int i = 0; i < 3; i++) {
            String s = "";
            for (int j = 0; j < 3; j++) {
                s += node.arr[i][j];
            }
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/5373.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine());

        for (int t = 1; t <= test_case; t++) {

            rotates = new ArrayList();
            nodeManager = new NodeManager();

            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Rotate rotate = new Rotate(st.nextToken());
                rotates.add(rotate);
            }

            run();
            printResult();
        }
    }
}
