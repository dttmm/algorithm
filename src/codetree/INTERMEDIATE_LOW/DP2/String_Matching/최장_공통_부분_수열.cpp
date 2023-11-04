#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

#define MAX_N 1000

string a;
string b;
int d[MAX_N + 1][MAX_N + 1];	// ���ڿ�A�� i��°, ���ڿ�B�� j��° ���ڱ��� ������� �� �ִ밪

void solve() {
	for (int i = 1; i <= a.length(); i++) {
		int c1 = a[i - 1];
		for (int j = 1; j <= b.length(); j++) {
			int c2 = b[j - 1];

			// �� ���ڰ� ���� ���
			if (c1 == c2) d[i][j] = d[i - 1][j - 1] + 1;
			// �� ���ڰ� �ٸ� ���
			else d[i][j] = max(d[i - 1][j], d[i][j - 1]);
		}
	}
}

int main() {

	// �Է�
	cin >> a >> b;

	solve();

	cout << d[a.length()][b.length()];
}