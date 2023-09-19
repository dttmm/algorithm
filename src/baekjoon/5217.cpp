#include <iostream>
#include <fstream>

using namespace std;

int main() {

	freopen("res/baekjoon/5217.txt", "r", stdin);

	int T;

	cin >> T;

	int n;
	for (int i = 0; i < T; i++) {
		cin >> n;

		cout << "Pairs for " << n << ": ";
		for (int i = 1; i <= n / 2; i++) {
			if (i == n - i) continue;

			if (i != 1) cout << ", ";

			cout << i << " " << n - i;
		}
		cout << endl;
	}
}