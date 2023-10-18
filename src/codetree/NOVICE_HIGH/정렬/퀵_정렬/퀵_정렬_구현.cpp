#include <iostream>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N];

// 스왑
void swap(int& a, int& b) {
	int temp = a;
	a = b;
	b = temp;
}

// 피벗을 고르고 피벗값을 반환
int selectPivot(int s, int e) {
	// 가운데 값으로 피벗 고르고
	int index = (s + e) / 2;

	// 피벗을 오른쪽 끝으로 스왑함
	swap(arr[index], arr[e]);

	return arr[e];
}

// 피벗을 기준으로 숫자를 나누고
// 피벗의 인덱스를 반환
int parition(int s, int e) {
	int pivot = selectPivot(s, e);

	// 피벗보다 같거나 큰 값의 인덱스 가리킴
	int high = s;

	// low: 피벗보다 작은 값의 인덱스 가리킴
	for (int low = s; low < e; low++) {
		if (arr[low] < pivot) {
			swap(arr[low], arr[high]);
			high++;
		}
	}

	// 마지막에 피벗이랑 피벗보다 큰거나 같은 값하고 스왑
	swap(arr[e], arr[high]);

	// 피벗 인덱스 리턴
	return high;
}

// 퀵 정렬
void quickSort(int s, int e) {
	// 종료 조건
	if (s >= e) return;

	// 피벗을 기준으로 나누고 피벗의 인덱스를 받음
	int index = parition(s, e);

	// 분할정복
	quickSort(s, index - 1);
	quickSort(index + 1, e);
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// 정렬
	quickSort(0, N - 1);

	// 출력
	for (int i = 0; i < N; i++) {
		cout << arr[i] << " ";
	}
}