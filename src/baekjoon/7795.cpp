#include <iostream>
#include <fstream>

/*
* 설계 4분 구현 10분 디버깅 3분
* 투포인터
* 두 배열을 정렬한 다음
* 가장 큰 숫자들부터 가리키면서
* A를 가리키는 숫자가 B를 가리키는 숫자보다 큰 경우
* A를 가리키는 숫자는 나머지 B에 있는 숫자들보다 크므로
* 현재 B가 가리키고 있는 인덱스를 이용하여 총합을 카운트 해줌
* 
* 정렬을 할 때는 직접 머지 소트 구현해서 풀어봄
* 
* 답이 안나오길래 삽질했는데 실수함
* i랑 j를 바꿔쓰는 실수
* 종료조건을 안쓰는 실수 등등
*/

using namespace std;

#define MAX_N 20000

int N;
int M;
int arr[MAX_N];	// A
int brr[MAX_N];	// B
int sorted[MAX_N];	// 머지 소트할 때 사용할 배열

// 머지
void merge(int* arr, int s, int m, int e) {
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

	// 원본 배열에 정렬 반영
	for (int i = s; i <= e; i++) {
		arr[i] = sorted[i];
	}
}

// 머지 소트
void mergeSort(int* arr, int s, int e) {
	// 종료 조건
	if (s >= e) return;

	int m = (s + e) / 2;

	mergeSort(arr, s, m);
	mergeSort(arr, m + 1, e);

	merge(arr, s, m, e);
}

// 개수 세기
int solve() {
	int i = N - 1;
	int j = M - 1;

	int cnt = 0;
	while (i >= 0 && j >= 0) {
		// A가 B보다 큰 경우 -> B에 있는 것들 다 A보다 작음
		if (arr[i] > brr[j]) {
			cnt += j + 1;
			i--;
		}
		// A가 B보다 크지 않은 경우
		else j--;
	}

	return cnt;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/7795.txt", "r", stdin);

	int T;
	cin >> T;
	for (int tc = 0; tc < T; tc++) {

		// 입력
		cin >> N >> M;

		for (int i = 0; i < N; i++) {
			cin >> arr[i];
		}

		for (int i = 0; i < M; i++) {
			cin >> brr[i];
		}

		// 정렬
		mergeSort(arr, 0, N - 1);
		mergeSort(brr, 0, M - 1);

		int ret = solve();

		cout << ret << "\n";
	}
}