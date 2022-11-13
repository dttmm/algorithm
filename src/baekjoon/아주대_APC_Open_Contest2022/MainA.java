package baekjoon.아주대_APC_Open_Contest2022;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MainA {


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/아주대_APC_Open_Contest2022/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        Map<Character, Integer> map = new HashMap();
        map.put('B', 0);
        map.put('S', 1);
        map.put('G', 2);
        map.put('P', 3);
        map.put('D', 4);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = st.nextToken();
        }

        String first = "";
        String second = arr[N - 1];
        int flag = 0;   // 한번 찾았는지 검사할 플래그
        int currentRank = 0;
        int currentPoint = 1001;
        for (int i = 0; i < N; i++) {
            String s = arr[i];
            char c = s.charAt(0);
            int rank = map.get(c);
            int point = Integer.parseInt(s.substring(1));

            if (rank < currentRank) {
                if (flag == 1) {
                    second = arr[i];
                } else {
                    first = arr[i - 1];
                    second = arr[i];
                }
                flag++;
            } else if (rank == currentRank && point > currentPoint) {
                if (flag == 1) {
                    second = arr[i];
                } else {
                    first = arr[i - 1];
                    second = arr[i];
                }
                flag++;
            }

            currentRank = rank;
            currentPoint = point;

            if (flag == 2) break;
        }

        if (flag == 0) {
            System.out.println("OK");
        } else {
            // 등급 낮은 순으로 정렬
            int rank1 = map.get(first.charAt(0));
            int rank2 = map.get(second.charAt(0));
            int point1 = Integer.parseInt(first.substring(1));
            int point2 = Integer.parseInt(second.substring(1));

            if (rank1 > rank2) {
                String temp = first;
                first = second;
                second = temp;
            } else if (rank1 == rank2 && point1 < point2) {
                String temp = first;
                first = second;
                second = temp;
            }

            System.out.println("KO");
            System.out.println(first + " " + second);
        }
    }
}
