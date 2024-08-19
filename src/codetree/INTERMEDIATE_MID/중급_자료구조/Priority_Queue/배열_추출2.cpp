#include<iostream>
#include<queue>
#include<cstdlib>
#include<vector>

using namespace std;

struct cmp {
	bool operator()(int a, int b) {
		if (abs(a) != abs(b)) return !(abs(a) < abs(b));
		return !(a < b);
	}
};


int N;
priority_queue<int, vector<int>, cmp> pq;


int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		if (n == 0) {
			if (pq.empty()) {
				cout << "0\n";
			}
			else {
				cout << pq.top() << "\n";
				pq.pop();
			}
		}
		else {
			pq.push(n);
		}
	}
}