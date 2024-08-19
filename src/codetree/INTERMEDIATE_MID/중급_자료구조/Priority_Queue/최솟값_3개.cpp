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

		// 아직 3개 미만인 경우
		if (i < 3) {
			cout << "-1\n";
			continue;
		}

		// 제일 작은 수 3개 뽑음
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