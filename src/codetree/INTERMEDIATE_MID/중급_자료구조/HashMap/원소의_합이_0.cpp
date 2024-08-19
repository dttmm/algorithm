#include <iostream>
#include <unordered_map>
using namespace std;

#define MAX_N 5000
int N;
int arr[4][MAX_N];
unordered_map<int, int> mapAB;

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	// A B 조합 만들기
	for (int i = 0; i < N; i++) {
		int n = arr[0][i];
		for (int j = 0; j < N; j++) {
			int m = arr[1][j];

			mapAB[n + m]++;
		}
	}

	// C D 조합에서 가능한 A B 조합 찾기
	int total = 0;
	for (int i = 0; i < N; i++) {
		int n = arr[2][i];
		for (int j = 0; j < N; j++) {
			int m = arr[3][j];

			int target = -n - m;
			total += mapAB[target];
		}
	}

	cout << total;
}