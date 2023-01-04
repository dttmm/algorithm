package programmers.lv1;

import java.util.*;

/**
 처음에는 pre를 플래그겸으로 이용하면서 햄버거를 쌓아갔다
 1이 쌓여있을 때 2가 쌓이면 pre = 2
 2가 쌓여있을 때 3이 쌓이면 pre = 3
 pre = 3일 때 1이 쌓이면 햄버거 완성하는 방식으로 했었는데
 햄버거가 완성될 때, stack이 비어있지 않다면 pre값은 peek으로 했었는데
 만약 stack에 3만 달랑 남아있을 경우
 다음에 1이 오게 되는 순간 햄버거가 완성 되어버린다는 오류 발생

 할 수 없이
 햄버거를 쌓아가면서 검사하지 말고
 1이 쌓였을 때, stack을 하나씩 꺼내보면서 조건 검사를 함함 */
public class No_016_햄버거_만들기 {

    class Solution {

        Stack<Integer> stack = new Stack();

        public int solution(int[] ingredient) {
            int answer = 0;

            for (int i = 0; i < ingredient.length; i++) {
                int n = ingredient[i];

                if (n != 1) {
                    stack.push(n);
                    continue;
                }

                if (stack.isEmpty() || stack.peek() != 3) {
                    stack.push(n);
                    continue;
                }

                stack.pop();
                if (stack.isEmpty() || stack.peek() != 2) {
                    stack.push(3);
                    stack.push(n);
                    continue;
                }

                stack.pop();
                if (stack.isEmpty() || stack.peek() != 1) {
                    stack.push(2);
                    stack.push(3);
                    stack.push(n);
                    continue;
                }

                stack.pop();
                answer++;
            }
            return answer;
        }
    }
}
