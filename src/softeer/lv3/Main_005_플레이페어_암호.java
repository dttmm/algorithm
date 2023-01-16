package softeer.lv3;

import java.util.*;
import java.io.*;

/**
 알고리즘이 필요없는 빡구현 문제
 데이터를 표를 어떻게 저장할지 자료구조만 잘 짜면 끝
 nodes에 알파벳 위치 정보 담고
 arr에 알파벳 담음
 */
public class Main_005_플레이페어_암호 {

    public class Main {

        static Node[] nodes;
        static char[][] arr;
        static StringBuilder sb;

        private static class Node {
            char c;
            int i;
            int j;

            public Node(char c, int i, int j) {
                this.c = c;
                this.i = i;
                this.j = j;
            }
        }

        // 암호문으로 변환
        static void trans(char[] chars) {
            Node n1 = nodes[chars[0] - 'A'];
            Node n2 = nodes[chars[1] - 'A'];

            if (n1.i == n2.i) {
                int newJ1 = (n1.j + 1) % 5;
                int newJ2 = (n2.j + 1) % 5;
                sb.append(arr[n1.i][newJ1]);
                sb.append(arr[n2.i][newJ2]);
            } else if (n1.j == n2.j) {
                int newI1 = (n1.i + 1) % 5;
                int newI2 = (n2.i + 1) % 5;
                sb.append(arr[newI1][n1.j]);
                sb.append(arr[newI2][n2.j]);
            } else {
                sb.append(arr[n1.i][n2.j]);
                sb.append(arr[n2.i][n1.j]);
            }
        }

        public static void main(String args[]) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String msg = br.readLine();
            String key = br.readLine();
            nodes = new Node[26];
            arr = new char[5][5];
            sb = new StringBuilder();

            // 입력 받기
            int index = 0;
            for (int k = 0; k < key.length(); k++) {
                char c = key.charAt(k);

                if (nodes[c - 'A'] != null) continue;

                int i = index / 5;
                int j = index % 5;
                Node newNode = new Node(c, i, j);
                nodes[c - 'A'] = newNode;
                arr[i][j] = c;
                index++;
            }

            for (int c = 'A'; c <= 'Z'; c++) {
                if (c == 'J') continue;
                if (nodes[c - 'A'] != null) continue;

                int i = index / 5;
                int j = index % 5;
                Node newNode = new Node((char) c, i, j);
                nodes[c - 'A'] = newNode;
                arr[i][j] = (char) c;
                index++;
            }

            // 쌍지어서 변환하기
            char preC = '\0';
            int count = 0;
            char[] chars = new char[2];
            for (int k = 0; k < msg.length(); k++) {
                char c = msg.charAt(k);

                // 첫번째 문자
                if (count == 0) {
                    chars[count] = c;
                    preC = c;
                    count = (count + 1) % 2;
                }
                // 두번째 문자
                else {
                    // 중복된 경우
                    if (preC == c) {
                        // X인 경우
                        if (c == 'X') {
                            chars[count] = 'Q';
                        } else {
                            chars[count] = 'X';
                        }
                        trans(chars);
                    } else {
                        chars[count] = c;
                        trans(chars);
                        preC = '\0';
                        count = (count + 1) % 2;
                    }
                }
            }

            // 마지막 글자 남은 경우
            if (count == 1) {
                chars[count] = 'X';
                trans(chars);
            }

            System.out.println(sb);
        }
    }
}
