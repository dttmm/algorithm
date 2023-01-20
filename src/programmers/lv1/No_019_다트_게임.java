package programmers.lv1;

import java.util.*;

/**
 단순 빡구현 문제
 입력을 어떻게 쪼개고
 데이터들을 어떻게 저장할지 고민했었음
 입력받은 문자열을 하나씩 돌면서
 어떤 문자인지 확인해서

 노드 클래스를 만들어서
 노드에 점수, 보너스, 옵션 정보 집어넣고
 노드 리스트에 저장함

 단, 문자열이 1인경우
 다음 문자열이 0이라면
 해당 점수는 10점으로 처리하는 과정 필요했음

 리스트 돌면서 보너스, 옵션에 따라 점수 계산 해주고
 마지막에 리스트 한번 더 돌면서 총합 계산함
 */
public class No_019_다트_게임 {


    class Solution {

        private static class Node {
            int score;
            char bonus;
            char option;

            public Node(int score) {
                this.score = score;
                this.option = '\0';
            }
        }

        public int solution(String dartResult) {
            int answer = 0;
            List<Node> list = new ArrayList();

            char[] cArr = dartResult.toCharArray();

            Node newNode = null;
            int index = -1;
            for (int i = 0; i < cArr.length; i++) {
                char c = cArr[i];

                if (c >= '0' && c <= '9') {

                    // 10점인 경우 처리
                    if (c == '1' && i + 1 < cArr.length && cArr[i + 1] == '0') {
                        newNode = new Node(10);
                        i++;
                    } else {
                        newNode = new Node(c - '0');
                    }
                    list.add(newNode);
                    index++;
                } else if (c == 'S' || c == 'D' || c == 'T') {
                    list.get(index).bonus = c;
                } else {
                    list.get(index).option = c;
                }
            }

            index = 0;
            for (Node node : list) {
                char bonus = node.bonus;
                char option = node.option;

                // 보너스 확인
                if (bonus == 'D') {
                    node.score = (int) Math.pow(node.score, 2);
                } else if (bonus == 'T') {
                    node.score = (int) Math.pow(node.score, 3);
                }

                // 옵션 확인
                if (option != '\0') {
                    // 스타상
                    if (option == '*') {
                        node.score *= 2;

                        if (index - 1 >= 0) {
                            list.get(index - 1).score *= 2;
                        }
                    }
                    // 아차상
                    else {
                        node.score *= -1;
                    }
                }

                index++;
            }

            for (Node node : list) {
                answer += node.score;
            }

            System.out.println(list.size());
            return answer;
        }
    }
}
