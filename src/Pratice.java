public class Pratice {
    public static void main(String[] args) {
        int[] arr = {-1,3,-9,6,7,-6,1,5,4,-2};
        int count =0;
        for (int i = 1; i < (1 << arr.length) ; i++) {
            int sum = 0;
            for (int j = 0; j < arr.length; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += arr[j];
                }
            }
            if (sum ==0){
                count++;
            }
        }
        System.out.println(count);
    }
}
