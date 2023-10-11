#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

int N;
int ans;

int main() {

	cin >> N;

	int scoreA = 0;	// A ����
	int scoreB = 0;	// B ����
	int rank = 0;
	for (int i = 0; i < N; i++) {
		char c;
		int n;
		cin >> c >> n;

		if (c == 'A')scoreA += n;
		else scoreB += n;

		// A�� ���� �� ���� ���
		if (scoreA > scoreB) {
			// ���� �ٲ� ���
			if (rank != 1) {
				rank = 1;
				ans++;
			}
		}
		// B�� ���� �� ���� ���
		else if (scoreB > scoreA) {
			// ���� �ٲ� ���
			if (rank != 2) {
				rank = 2;
				ans++;
			}
		}
		// ���� ���� ���
		else {
			// ���� �ٲ� ���
			if (rank != 0) {
				rank = 0;
				ans++;
			}
		}
	}

	cout << ans;
}