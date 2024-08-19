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

		priority_queue<int> leftPQ;	// 중앙값보다 작은 수 저장(큰 수가 우선순위)
		priority_queue<int> rightPQ; // 중앙값보다 큰 수 저장(작은 수가 우선순위)
		for (int i = 1; i <= N; i++) {
			int n;
			cin >> n;

			// 첫 원소인 경우
			if (i == 1) {
				mid = n;
				cout << mid << " ";
				continue;
			}

			// 중앙값 보다 크면 오른쪽 PQ에
			if (n >= mid) rightPQ.push(-1 * n);
			// 작으면 왼쪽 PQ에 저장
			else leftPQ.push(n);

			// 홀수번째 수가 아니면 패쓰
			if (i % 2 == 0) continue;

			// 오른쪽 PQ에 수가 더 많으면
			// -> 기존 중앙값은 왼쪽PQ에 넣고
			// 오른쪽 PQ에서 작은수 뽑아서 중앙값으로 지정
			if (rightPQ.size() > leftPQ.size()) {
				leftPQ.push(mid);
				mid = -1 * rightPQ.top();
				rightPQ.pop();
			}
			// 왼쪽 PQ에 수가 더 많으면
			// -> 기존 중앙값은 오른쪽PQ에 넣고
			// 왼쪽 PQ에서 큰수 뽑아서 중앙값으로 지정
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