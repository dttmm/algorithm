#include <iostream>
#include <unordered_map>
using namespace std;

int N;
int M;
unordered_map<int, int> m;

int main() {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		if (m.find(n) == m.end()) {
			m[n] = 1;
		}
		else m[n]++;
	}

	for (int i = 0; i < M; i++) {
		int n;
		cin >> n;

		if (m.find(n) == m.end()) {
			cout << "0 ";
		}
		else cout << m[n] << " ";
	}
}