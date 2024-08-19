#include<iostream>
#include<queue>

using namespace std;

int T;
int N;
int mid;

int main() {

	cin >> T;
	for (int t = 0; t < T; t++) {
		cin >> N;

		priority_queue<int> leftPQ;	// �߾Ӱ����� ���� �� ����(ū ���� �켱����)
		priority_queue<int> rightPQ; // �߾Ӱ����� ū �� ����(���� ���� �켱����)
		for (int i = 1; i <= N; i++) {
			int n;
			cin >> n;

			// ù ������ ���
			if (i == 1) {
				mid = n;
				cout << mid << " ";
				continue;
			}

			// �߾Ӱ� ���� ũ�� ������ PQ��
			if (n >= mid) rightPQ.push(-1 * n);
			// ������ ���� PQ�� ����
			else leftPQ.push(n);

			// Ȧ����° ���� �ƴϸ� �о�
			if (i % 2 == 0) continue;

			// ������ PQ�� ���� �� ������
			// -> ���� �߾Ӱ��� ����PQ�� �ְ�
			// ������ PQ���� ������ �̾Ƽ� �߾Ӱ����� ����
			if (rightPQ.size() > leftPQ.size()) {
				leftPQ.push(mid);
				mid = -1 * rightPQ.top();
				rightPQ.pop();
			}
			// ���� PQ�� ���� �� ������
			// -> ���� �߾Ӱ��� ������PQ�� �ְ�
			// ���� PQ���� ū�� �̾Ƽ� �߾Ӱ����� ����
			else if (leftPQ.size() > rightPQ.size()) {
				rightPQ.push(-1 * mid);
				mid = leftPQ.top();
				leftPQ.pop();
			}

			cout << mid << " ";
		}
		cout << "\n";
	}
}