#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

#define MAX_N 1000

string a;
string b;
int d[MAX_N + 1][MAX_N + 1];	// 문자열A의 i번째, 문자열B의 j번째 글자까지 고려했을 때 최대값

void solve() {
	for (int i = 1; i <= a.length(); i++) {
		int c1 = a[i - 1];
		for (int j = 1; j <= b.length(); j++) {
			int c2 = b[j - 1];

			// 두 문자가 같은 경우
			if (c1 == c2) d[i][j] = d[i - 1][j - 1] + 1;
			// 두 문자가 다른 경우
			else d[i][j] = max(d[i - 1][j], d[i][j - 1]);
		}
	}
}

int main() {

	// 입력
	cin >> a >> b;

	solve();

	cout << d[a.length()][b.length()];
}