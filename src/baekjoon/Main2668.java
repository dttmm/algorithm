package baekjoon;

import java.io.*;
import java.util.*;

/**
 일단 첫번쨰 줄과 두번째 줄이 서로에 대한 정보를 가지고 있어야함
 그리고 서로가 서로에게 가지고 있지 않은 정보를 반복적으로 지워주면 됨

 첫번째 줄에 있는 각 인덱스가 두번쨰 줄의 어떤 숫자를 가지고 있는지에 대한 정보를 map에 담고
 두번쨰 줄에 있는 숫자들을 가지고 있는 첫번째 줄의 인덱스 정보를 set[]에 담음

 그리고 첫번째 줄에는 있지만 두번째 줄에는 없는 숫자 정보를 isExist에 담고
 두번째 줄에 없는 숫자를 첫번째 줄에서 지원줌. 즉, map에서 해당 숫자를 지워줌
 그리고 map이 사라졌으니까 set에서도 해당 map 정보를 지워줘야됨
 즉, 두번째 숫자를 가지고 있는 첫번째 숫자가 없어졌으니까
 해당 첫번째 숫자에 대한 정보를 지워주는 거임
 그것이 바로
 해당 map이 가지고 있는 value로 set[]에 접근하여
 set에서 해당 숫자를 지워주는 과정임

 만약 set의 크기가 0이 된다면
 두번째 줄에 있는 숫자를 가지고 있는 녀석이 첫번쨰 줄에 없다는 뜻이므로
 해당 두번쨰 줄에 있는 숫자를 key로 하여 위 과정을 반복하게 되면
 결국, 첫번째 줄과 두번째 줄이 서로 동시에 갖고 있는 녀석들만 남게됨
 */
public class Main2668 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2668.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 첫번째 줄이 가지고 있는 두번째 줄의 정보
        Map<Integer, Integer> map = new HashMap();

        // 두번째 줄의 숫자를 가지고 있는 첫째줄들의 정보
        Set<Integer>[] sets = new Set[N + 1];

        // 두번째 줄에 없는 숫자 저옵를 담을 배열
        boolean[] isExist = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            sets[i] = new HashSet();
        }

        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(br.readLine());

            isExist[n] = true;
            map.put(i, n);
            sets[n].add(i);
        }

        for (int i = 1; i <= N; i++) {
            if (isExist[i]) continue;

            // 두번째 줄에 없는 놈들을 이용할거임
            int key = i;
            Set<Integer> set;
            do {
                // 첫번째줄에 해당하는 value값 미리 가져와줌
                int value = map.get(key);
                // 두번째 줄에서 없는 놈들 map에서 해당 key 지움
                map.remove(key);

                // map에서 지워졌으니까 두번째 줄이 가지고있는
                // 첫번째 줄 정보에서도 해당 key 지워줌
                set = sets[value];
                set.remove(key);

                // 만약 두번째 줄이 가지고 있는 첫번째 줄의 정보가 없다면
                // 해당 두번째 줄의 숫자를 key로 하여 다시 반복
                // 결국 서로가 가지고 있지 않은 녀석을 계속 지워주는 거임
                key = value;
            } while (set.size() == 0);
        }

        // map에서 살아남은 놈들이 정답임
        List<Integer> list = new ArrayList();
        for (int i : map.keySet()) {
            list.add(i);
        }

        int[] arr = new int[list.size()];
        int index = 0;
        for (int i : list) {
            arr[index++] = i;
        }

        bw.write(arr.length + "\n");
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            bw.write(arr[i] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
