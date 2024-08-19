#include <iostream>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N + 1];
int L[3][MAX_N + 1];	// 0:바위 1:가위 2:보

int main() {

	cin >> N;
	for (int i = 1; i <= N; i++) {
		char c;
		cin >> c;

		if (c == 'H') arr[i] = 0;
		else if (c == 'S') arr[i] = 1;
		else if (c == 'P') arr[i] = 2;
	}

	// i번째까지 바위,가위,보로 이기는 경우 수 계산
	for (int x = 0; x < 3; x++) {
		int target = (x + 2) % 3;
		for (int i = 1; i <= N; i++) {
			L[x][i] = L[x][i - 1] + (arr[i] == target);
		}
	}

	int max = 0;
	// A가 낼꺼 두개 고르기
	for (int a = 0; a < 3; a++) {
		for (int b = 0; b < 3; b++) {
			if (a == b) continue;

			// 1~i까지는 a내고 i+1~N까지는 b내기
			for (int i = 1; i <= N; i++) {
				int ret = L[a][i] + (L[b][N] - L[b][i]);
				max = ret > max ? ret : max;
			}
		}
	}

	cout << max;
}