package programmers.lv1;

import java.util.*;

/**
 날짜 정보 담을 MyTime클래스 만들어서 관리함
 문자열로 주어지는 날짜를 총 일수로 변경하고
 총 일수를 비교하여 파기 날짜 지났는지 판별함

 문자열 split할 때 .은 \\.이걸로 분리해야되네ㅔ에
 */
public class No_023_개인정보_수집_유효기간 {

    class Solution {

        static Map<Character, Integer> map = new HashMap();

        private static class MyTime {
            int time;
            int expiredTime;    // 파기 날짜

            // 문자열 시간 -> 총 일수로 변경
            public MyTime(String s) {
                String[] arr = s.split("\\.");
                int num = Integer.parseInt(arr[0]) * 12 * 28;
                num += Integer.parseInt(arr[1]) * 28;
                num += Integer.parseInt(arr[2]);
                this.time = num;
            }

            // 파기 날짜 구함
            public void addTime(char type) {
                int num = map.get(type);
                this.expiredTime = this.time + num;
            }

            // 파기 날짜 지났는지 판별
            public boolean isExpired(MyTime o) {
                if (this.time >= o.expiredTime) return true;
                return false;
            }
        }

        public int[] solution(String today, String[] terms, String[] privacies) {
            int[] answer = {};
            MyTime todayTime = new MyTime(today);
            List<Integer> list = new ArrayList();

            // 약관별 유효기간 저장
            for (int i = 0; i < terms.length; i++) {
                StringTokenizer st = new StringTokenizer(terms[i]);
                char key = st.nextToken().charAt(0);
                int value = Integer.parseInt(st.nextToken());
                map.put(key, value * 28);
            }

            // 각각의 파기 기간 넘는지 검사
            for (int i = 0; i < privacies.length; i++) {
                StringTokenizer st = new StringTokenizer(privacies[i]);
                String time = st.nextToken();
                char term = st.nextToken().charAt(0);

                MyTime newTime = new MyTime(time);
                newTime.addTime(term);
                if (todayTime.isExpired(newTime)) list.add(i + 1);
            }

            // 정답
            answer = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }

            return answer;
        }
    }
}
