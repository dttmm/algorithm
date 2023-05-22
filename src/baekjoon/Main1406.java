package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 24분 구현 23분
 직접 링크드 리스트 구현해서 풀음
 head노드, tail노드, 커서노드 만들어서
 문자 삭제하거나 추가할 때마다
 링크드리스트 연결관계 재설정 해줌
 */
public class Main1406 {

    static Node head;
    static Node tail;
    static Node cursor;

    private static class Node {
        char data;
        Node prev;
        Node next;

        public Node(char data) {
            this.data = data;
        }
    }

    // 커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
    static void moveLeft() {
        if (cursor.prev == null) return;

        cursor = cursor.prev;
    }

    // 커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
    static void moveRight() {
        if (cursor.next == null) return;

        cursor = cursor.next;
    }

    // 커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
    static void deleteLeft() {
        if (cursor.prev == null) return;

        // 커서 왼쪽이 head인 경우
        if (cursor.prev == head) {
            head = cursor;
            cursor.prev = null;
            return;
        }

        // 커서와 삭제된 문자 연결관계 재설정
        cursor.prev = cursor.prev.prev;
        cursor.prev.next = cursor;
    }

    // c라는 문자를 커서 왼쪽에 추가함
    static void addLeft(char c) {
        Node newNode = new Node(c);

        // 새로 추가된 문자와 커서의 연결 관계 설정
        newNode.next = cursor;
        newNode.prev = cursor.prev;
        cursor.prev = newNode;

        // 커서가 head인 경우
        if (cursor == head) {
            head = newNode;
            return;
        }

        newNode.prev.next = newNode;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1406.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String s = br.readLine();

        // 입력받은 문자열로 링크드 리스트 만듦
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            Node newNode = new Node(c);
            if (head == null) {
                head = newNode;
                tail = newNode;
                continue;
            }

            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        // 커서 노드 추가
        cursor = new Node('\0');
        tail.next = cursor;
        cursor.prev = tail;
        tail = cursor;

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();

            switch (type) {
                case "L":
                    moveLeft();
                    break;
                case "D":
                    moveRight();
                    break;
                case "B":
                    deleteLeft();
                    break;
                case "P":
                    char c = st.nextToken().charAt(0);
                    addLeft(c);
            }
        }

        // 데이터 출력
        StringBuilder sb = new StringBuilder();
        while (head.data != '\0') {
            sb.append(head.data);
            head = head.next;
        }

        System.out.println(sb);
    }
}
