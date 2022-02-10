package baekjoon;

public class Main4673 {

    public static void main(String[] args) throws Exception {
        boolean[] arr = new boolean[10001];

        int i = 1;
        while (true) {
            int num = i;
            int d = num;
            d += num % 10;
            while (num != 0) {
                num /= 10;
                d += num % 10;
            }
            if (d < 10000) arr[d] = true;
            if (i > 10000) break;
            i++;
        }
        for (int j = 1; j < 10000; j++) {
            if (!arr[j]) {
                System.out.println(j);
            }
        }
    }
}
