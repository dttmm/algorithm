#include <iostream>

using namespace std;

#define MAX_N 26

int N;
int M;
int P;
bool d[MAX_N][MAX_N];	// 안 읽은 사람 수가 i일때, j가 채팅방에 있었는지 여부
bool read[MAX_N];		// i가 P메시지를 읽었는지 여부

int main() {

	// d배열 세팅
	fill(d[0], d[0] + MAX_N, true);

	cin >> N >> M >> P;
	for (int i = 1; i <= M; i++) {
		char c;
		int u;
		cin >> c >> u;

		d[u][c - 'A'] = true;

		// P번째 메시지 이전인 경우
		if (i < P) continue;
		// P번째 메시지인 경우
		if (i == P) {
			// 안 읽은 사람 수가 u일때, 채팅방에 있었던 사람들은 모두 P를 읽게됨
			for (int j = 0; j < MAX_N; j++) {
				if (d[u][j]) read[j] = true;
			}
		}

		// P이후에 채팅남긴 사람은 무조건 읽음
		read[c - 'A'] = true;
	}

	// 안읽은 사람 세기
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		if (!read[i]) cout << (char)('A' + i) << " ";
	}
}