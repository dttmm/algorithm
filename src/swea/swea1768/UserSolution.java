package swea.swea1768;

class UserSolution {
	static Node head;
	static Node tail;
	static int[] tr;
	static boolean[] visited;

	static class Node {
		int[] arr;
		Node next;

		public Node() {
			arr = new int[4];
		}
	}

	static void createList(int k) {
		if (k == 4) {
			Node newNode = new Node();
			for (int i = 0; i < 4; i++) {
				newNode.arr[i] = tr[i];
			}
			if (head == null) {
				head = newNode;
				tail = newNode;
			} else {
				tail.next = newNode;
				tail = newNode;
			}
		} else {
			for (int i = 0; i < 10; i++) {
				if (!visited[i]) {
					visited[i] = true;
					tr[k] = i;
					createList(k + 1);
					visited[i] = false;
				}
			}
		}
	}

	static Solution.Result myQuery(int[] targetArr, int[] guess) {
		Solution.Result result = new Solution.Result();

		result.strike = 0;
		result.ball = 0;
		int[] count = new int[10];

		for (int i = 0; i < 4; i++) {
			count[targetArr[i]]++;
		}

		for (int idx = 0; idx < 4; ++idx)
			if (guess[idx] == targetArr[idx])
				result.strike++;
			else if (count[guess[idx]] > 0)
				result.ball++;

		return result;
	}

	public void doUserImplementation(int guess[]) {
		// Implement a user's implementation function
		//
		// The array of guess[] is a return array that
		// is your guess for what digits[] would be.

		head = null;
		tail = null;
		tr = new int[4];
		visited = new boolean[10];

		createList(0);

		while (true) {
			int[] targetArr = head.arr;
			Solution.Result result = Solution.query(targetArr);

			if (result.strike == 4) {
				for (int i = 0; i < 4; i++) {
					guess[i] = targetArr[i];
				}
				return;
			}

			for (Node node = head; node.next != null;) {
				Solution.Result result2 = myQuery(targetArr, node.next.arr);

				if (result.strike == result2.strike && result.ball == result2.ball) {
					node = node.next;
				} else {
					node.next = node.next.next;
				}
			}

			head = head.next;
		}
	}
}