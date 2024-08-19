#include <iostream>
#include <string>
#include <unordered_set>

using namespace std;

#define MAX_N 500
#define MAX_M 50
#define R 3

int N;
int M;
char arr[MAX_N][MAX_M];
char brr[MAX_N][MAX_M];
unordered_set<string> Set;
int ans;
int tr[3];

void solve(int k, int start) {
	if (k == R) {
		Set.clear();

		// A���� ���� ����
		for (int i = 0; i < N; i++) {
			string s = "";
			for (int j = 0; j < R; j++) {
				int index = tr[j];
				s += arr[i][index];
			}
			Set.insert(s);

		}

		// B���� ���� ����
		for (int i = 0; i < N; i++) {
			string s = "";
			for (int j = 0; j < R; j++) {
				int index = tr[j];
				s += brr[i][index];
			}

			// �ߺ��� �ִ� ���
			if (Set.find(s) != Set.end()) return;

		}
		// �ߺ��� ���� ���
		ans++;
	}
	else {
		for (int i = start; i < M; i++) {
			tr[k] = i;
			solve(k + 1, i + 1);
		}
	}
}

int main() {

	// �Է�
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		for (int j = 0; j < M; j++) {
			char c = s[j];
			arr[i][j] = c;
		}
	}

	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		for (int j = 0; j < M; j++) {
			char c = s[j];
			brr[i][j] = c;
		}
	}

	solve(0, 0);

	cout << ans;
}