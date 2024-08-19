#include <iostream>
#include <string>

using namespace std;

#define MAX_N 100000

int N;
char arr[MAX_N + 2];
int L[MAX_N + 2];	// i까지 ((누적 개수

int main() {

	string s;
	cin >> s;

	N = s.length();
	for (int i = 1; i <= N; i++) {
		arr[i] = s[i - 1];
	}

	for (int i = 2; i <= N; i++) {
		L[i] = L[i - 1];

		if (arr[i] == '(' && arr[i - 1] == '(') L[i]++;
	}

	long long ans = 0;
	for (int i = N - 1; i >= 1; i--) {
		if (arr[i] == ')' && arr[i + 1] == ')') ans += L[i];
	}

	cout << ans;
}