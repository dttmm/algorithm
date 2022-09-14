package swea.swea13469;

import java.util.HashMap;
import java.util.Map;

class UserSolution {

	static int H;
	static int W;
	static int[] countAll;
	static Node head;
	static Node tail;
	static int size;
	static int cursor;
	static Node cursorNode;

	class Node {
		char data;
		Node next;
		Node pre;
		int[] has;

		public Node(char data) {
			this.data = data;
			has = new int[26];
		}
	}

	void init(int H, int W, char mStr[]) {
		this.H = H;
		this.W = W;
		countAll = new int[26];
		cursor = 1;
		size = 0;

		int i = 0;
		char c = mStr[i];
		while (c != '\0') {
			Node newNode = new Node(c);

			if (head == null) {
				head = newNode;
				tail = newNode;
			} else {
				// tail의 next를 새로운 노드로한다
				tail.next = newNode;
				// 새로운 노드의 pre를 tail로 한다
				newNode.pre = tail;
				// tail을 새 노드로 바꾼다
				tail = newNode;

				// pre노드의 has배열을 deep copy한다
				Node preNode = newNode.pre;
				if (preNode != null) {
					for (int k = 0; k < 26; k++) {
						newNode.has[k] = preNode.has[k];
					}
				}
			}
			// 자신의 has배열에 자신의 값을 증가시키기
			newNode.has[c - 'a']++;
			// countMap에 자신 추가하기
			countAll[c - 'a']++;

			size++;

			c = mStr[++i];
		}

		cursorNode = head;
	}

	void insert(char mChar) {
		Node newNode = new Node(mChar);

		// 커서가 마지막에 있는 경우
		if (cursorNode == null) {
			// tail의 next를 새 노드로
			tail.next = newNode;
			// 새 노드의 pre를 tail로
			newNode.pre = tail;

			// tail 노드의 has배열을 deep copy한다
			if (tail != null) {
				for (int k = 0; k < 26; k++) {
					newNode.has[k] = tail.has[k];
				}
			}

			tail = newNode;

		}
		// 커서가 첫번쨰인 경우
		else if (cursor == 1) {
			newNode.next = head;
			head.pre = newNode;
			head = newNode;
		} else {
			Node preNode = cursorNode.pre;
			// cursorNode의 pre의 next를 새 노드로 한다
			preNode.next = newNode;
			// 새 노드의 pre를 cursorNode의 pre노드로 한다
			newNode.pre = preNode;
			// cursorNode의 pre를 새 노드로 한다
			cursorNode.pre = newNode;
			// 새 노드의 next를 cursorNode로 한다
			newNode.next = cursorNode;

			// pre노드의 has배열을 deep copy한다
			if (preNode != null) {
				for (int k = 0; k < 26; k++) {
					newNode.has[k] = preNode.has[k];
				}
			}
		}

		// 자신의 has배열에 자신의 값을 증가시키기
		newNode.has[mChar - 'a']++;

		// countMap에 자신 추가하기
		countAll[mChar - 'a']++;

		size++;
		cursor++;
	}

	char moveCursor(int mRow, int mCol) {
		int target = W * (mRow - 1) + mCol;

		if (target > size) {
			cursorNode = null;
			cursor = size + 1;
			return '$';
		}

		int diff = Math.abs(target - cursor);
		int diffHead = Math.abs(target - 1);
		int diffTail = Math.abs(target - size);

		int minType = 0; // 0: diif가 최소, 1: diffHead가 최소, 2: diffTail이 최소
		if (diffHead < diff) {
			if (diffTail < diffHead) {
				minType = 2;
			} else {
				minType = 1;
			}
		} else {
			if (diffTail <= diff) {
				minType = 2;
			}
		}

		switch (minType) {
		// diff가 최소
		case 0:
			// 오른쪽에 목표가 있는 경우
			if (target - cursor >= 0) {
				while (diff != 0) {
					cursorNode = cursorNode.next;
					cursor++;
					diff--;
				}
			} else {
				while (diff != 0) {
					cursorNode = cursorNode.pre;
					cursor--;
					diff--;
				}
			}
			break;

		// diffHead가 최소
		case 1:
			cursorNode = head;
			cursor = 1;
			while (diffHead != 0) {
				cursorNode = cursorNode.next;
				cursor++;
				diffHead--;
			}
			break;

		// diffTail이 최소
		case 2:
			cursorNode = tail;
			cursor = size;
			while (diffTail != 0) {
				cursorNode = cursorNode.pre;
				cursor--;
				diffTail--;
			}
			break;
		default:

		}

		return cursorNode.data;
	}

	int countCharacter(char mChar) {

		// 커서가 맨 마지막을 가리키는 경우
		if (cursorNode == null) {
			return 0;
		}

		int count = 0;
		int max = countAll[mChar - 'a'];
		Node preNode = cursorNode.pre;
		if (preNode != null) {
			max -= preNode.has[mChar - 'a'];
		}

		Node currentNode = cursorNode;
		while (currentNode != null) {
			if (currentNode.data == mChar) {
				count++;
			}

			if (count >= max)
				break;

			currentNode = currentNode.next;
		}

		return count;
	}
}
