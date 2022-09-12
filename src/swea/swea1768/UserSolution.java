package swea.swea1768;

class UserSolution {
	public final static int N = 4;
	static int[] arr1 = { 0, 1, 2, 3 };
	static int[] arr2 = { 4, 5, 6, 7 };
	static int[] arr3 = { 8, 9 };
	static int sum1;
	static int sum2;
	static int sum3;
	static int[] tr;
	static int[] answer;
	static boolean flag;
	static boolean[] visited;

	// 배열중에서 S나B의 개수만큼 선택
	// type : arr1, arr2, arr3인지
	public void findElement(int type, int[] arr, int sum, int k, int start) {
		if (k == sum) {
			if (type == 1) {
				findElement(2, arr2, sum1 + sum2, k, 0);
			} else if (type == 2) {
				findElement(3, arr3, sum1 + sum2 + sum3, k, 0);
			} else {
				Solution.Result result = Solution.query(tr);
				sum = result.strike + result.ball;
				if (sum == 4) {
					flag = true;
				}
			}
		} else {
			for (int i = start; i < arr.length; i++) {
				tr[k] = arr[i];
				findElement(type, arr, sum, k + 1, start + 1);
				if (flag)
					return;
			}
		}
	}

	public void findAnswer(int k) {
		if (k == 4) {
			Solution.Result result = Solution.query(answer);
			if (result.strike == 4) {
				flag = true;
			}
		} else {
			for (int i = 0; i < 4; i++) {
				if (!visited[i]) {
					visited[i] = true;
					answer[k] = tr[i];
					findAnswer(k + 1);
					visited[i] = false;
				}
				if (flag)
					return;
			}
		}
	}

	public void doUserImplementation(int guess[]) {
		// Implement a user's implementation function
		//
		// The array of guess[] is a return array that
		// is your guess for what digits[] would be.

		tr = new int[4];
		flag = false;

		Solution.Result result = Solution.query(arr1);
		sum1 = result.strike + result.ball;

		result = Solution.query(arr2);
		sum2 = result.strike + result.ball;

		sum3 = 4 - sum1 - sum2;

		findElement(1, arr1, sum1, 0, 0);

		answer = new int[4];
		visited = new boolean[4];
		flag = false;
		findAnswer(0);

		for (int i = 0; i < 4; i++) {
			guess[i] = answer[i];
		}
	}
}