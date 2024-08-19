#include <iostream>

using namespace std;

/*
* 설계 8분 구현 4분
*/

#define MAX_N 100000
#define INF 1000000000
#define MIN(a, b) ((a) < (b) ? (a) : (b))

int N;
int arr[MAX_N + 1];		// i에서 에너지를 채우는데 필요한 비용
int needs[MAX_N + 1];	// i에서 다음으로 넘어가기 위한 에너지
int L[MAX_N + 1];		// i에서 에너지를 채우는데 필요한 비용의 최소값

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	// 입력
	cin >> N;
	for (int i = 1; i < N; i++) {
		cin >> needs[i];
	}
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	// L배열 초기화
	L[0] = INF;
	for (int i = 1; i < N; i++) {
		L[i] = MIN(L[i - 1], arr[i]);
	}

	// i에서 다음으로 넘어가기 위해 필요한 에너지를 최소값으로 싸게 채우면 됨
	long long ans = 0;
	for (int i = 1; i < N; i++) {
		ans += (long long)L[i] * needs[i];
	}

	cout << ans;
}