#include <iostream>
#include <fstream>

/*
* 구현 8분
* 머지 소트
* 머지소트 직접 구현해서 풀음
*/

using namespace std;

#define MAX_N 500000

int N;
int K;
int arr[MAX_N];
int sorted[MAX_N];
int cnt;		// 저장 횟수 카운트
int ans = -1;	// 정답 저장할 변수

// s~m, m+1~e 두 구간 머지하기
void merge(int s, int m, int e) {
	int i = s;
	int j = m + 1;

	int k = s;
	while (i <= m && j <= e) {
		if (arr[i] < arr[j]) sorted[k++] = arr[i++];
		else sorted[k++] = arr[j++];
	}

	while (i <= m) {
		sorted[k++] = arr[i++];
	}

	while (j <= e) {
		sorted[k++] = arr[j++];
	}

	for (int x = s; x <= e; x++) {
		arr[x] = sorted[x];
		cnt++;	// 저장 횟수 세기

		if (cnt == K) {
			ans = arr[x];
			return;
		}
	}
}

// s~e구간 머지소트하기
void mergeSort(int s, int e) {
	if (cnt == K) return;
	if (s >= e) return;
	int m = (s + e) / 2;

	mergeSort(s, m);
	mergeSort(m + 1, e);

	merge(s, m, e);
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/24060.txt", "r", stdin);

	// 입력
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// 머지 소트 수행
	mergeSort(0, N - 1);

	cout << ans;
}