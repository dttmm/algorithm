package programmers.lv1;

import java.util.*;

/**
 크레인 안에 있는 인형들의 정보를 어떻게 가지고 있을지 고민함
 그대로 board 배열로 가지고 있으면 편하긴 한데
 해당 열에 인형이 있는지 없는지 검사하기 위해서는
 매번 크레인의 높이만큼 선형 탐색을 해야함
 그래서 처음에 인형들의 정보를 Queue에 담았음
 Queue를 이용하면 인형 검사를 빨리 할 수 있을 거라 판단

 뽑은 인형들을 Stack에 담아야 하는데
 헷갈려서 Queue담아서 문제 푸느라 헤멤;;
 */

public class No_011_1차_크레인_인형뽑기_게임 {

    class Solution {

        Queue<Integer>[] queues;    // 인형들 정보 담을 큐
        Stack<Integer> stack;       // 뽑은 인형들 담을 큐

        public int solution(int[][] board, int[] moves) {
            int answer = 0;
            int row = board.length;
            int col = board[0].length;

            queues = new LinkedList[col + 1];
            stack = new Stack();

            for (int i = 1; i <= col; i++) {
                queues[i] = new LinkedList();
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == 0) continue;
                    queues[j + 1].add(board[i][j]);
                }
            }

            // moves대로 해당 열에서 인형을 뽑는다
            for (int i = 0; i < moves.length; i++) {
                int index = moves[i];

                // 해당 열에 인형이 없는 경우 패쓰
                if (queues[index].isEmpty()) continue;

                int n = queues[index].poll();
                if (!stack.isEmpty()) {
                    // 이미 뽑은 인형이랑 지금 뽑은 인형이랑 비교
                    if (stack.peek() == n) {
                        stack.pop();
                        answer++;
                    } else {
                        stack.push(n);
                    }
                } else {
                    stack.push(n);
                }
                System.out.println();
            }

            return answer * 2;
        }
    }
}
