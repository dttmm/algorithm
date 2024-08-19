#include<iostream>
#include<queue>

using namespace std;

int N;
priority_queue<long long> pq;

int main() {

	cin >> N;
	for (int i = 1; i <= N; i++) {
		int n;
		cin >> n;

		pq.push(-1 * n);

		// ���� 3�� �̸��� ���
		if (i < 3) {
			cout << "-1\n";
			continue;
		}

		// ���� ���� �� 3�� ����
		int n1 = pq.top() * -1;
		pq.pop();

		int n2 = pq.top() * -1;
		pq.pop();

		int n3 = pq.top() * -1;
		pq.pop();

		pq.push(-1 * n1);
		pq.push(-1 * n2);
		pq.push(-1 * n3);

		cout << (long long)n1 * n2 * n3 << "\n";
	}
}