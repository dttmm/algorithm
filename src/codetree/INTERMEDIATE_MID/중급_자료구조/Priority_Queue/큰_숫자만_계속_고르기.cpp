#include<iostream>
#include<queue>

using namespace std;

int N;
int M;
priority_queue<int> pq;

int main() {

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		pq.push(n);
	}

	for (int i = 0; i < M; i++) {
		int n = pq.top();
		pq.pop();

		pq.push(n - 1);
	}

	cout << pq.top();
}