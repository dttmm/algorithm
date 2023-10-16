#include <fstream>
#include <iostream>

/*
* 구현 11분 디버깅 5분
* 퀵정렬
* 퀵 정렬 배운김에 적용해 보려고 했는데
* 문제에서 요구하는 퀵 정렬 방식과
* 기존 내가 구현하던 퀵 정렬 방식과
* 조금조금씩 다른 부분이 있어서
* 내 방식 그대로 구현했지만
* 문제에서 원하는 답이 안나와서
* 문제에서 요구하는 구현 방식을 맞추느라 고생좀함
* 
* swap(arr[low], arr[high])을 하기 위한 조건이라 든가
* swap(arr[high], arr[e])을 하기 위한 조건이 추가 되었다던가 등
*/

using namespace std;

#define MAX_N 10000

int N;
int K;
int arr[MAX_N];
int ans[2];
int cnt;

// 스왑
void swap(int& a, int& b) {
	int temp =a;
	a = b;
	b = temp;
	cnt++;

	if (cnt == K) {
		ans[0] = a < b ? a : b;
		ans[1] = a > b ? a : b;
	}
}

// 피벗 정하기
int selectPivot(int s, int e) {
	return arr[e];
}

// 피벗을 기준으로 나누기
int partition(int s, int e) {
	int pivot = selectPivot(s, e);

	int high = s;	// 피벗보다 큰 녀석 가리킬 때 사용
	// low: 피벗보다 작거나 같은 녀석 가리킬 떄 사용
	for (int low = s; low < e; low++) {
		if (arr[low] <= pivot) {
			swap(arr[low], arr[high]);
			high++;
		}
	}

	// 마지막에 피벗과 피벗보다 큰 숫자 바꿔줌
	if (high != e) swap(arr[high], arr[e]);

	// 피벗 위치 리턴
	return high;
}

// 퀵 정렬
void quickSort(int s, int e) {
	if (cnt >= K) return;
	if (s >= e) return;

	int index = partition(s, e);

	quickSort(s, index - 1);
	quickSort(index + 1, e);

}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/24090.txt", "r", stdin);

	// 입력
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// 정렬 수행
	quickSort(0, N - 1);

	if (cnt >= K) {
		cout << ans[0] << " " << ans[1];
	}
	else cout << -1;
}