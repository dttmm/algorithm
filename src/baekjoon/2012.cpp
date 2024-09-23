#include <fstream>
#include <iostream>
#include <cstdlib>

/* 설계 3분 구현 26분
* 그리디
* 딱 떠오르는 방법은
* 예상 등수를 낮은 순으로 정렬하고
* 예상 등수 앞에서 부터 1등을 주면서
* 불만도 차이를 계산함
* 뭔가 이방법에는 예외가 있지 않을까해서 찾아봤지만
* 별다른 케이스를 찾지 못하여 바로 구현해봄
* 
* 직접 퀵소트, 머지소트 구현해서 풀어보고
* 내장 소트도 사용해봄
* 
* 결과는
* 내장 소트가 80ms 가장 빠르고
* 그다음은 머지소트가 104ms
* 퀵소트는 시간초과가 나버림
* 뭔가 퀵소트 저격 데이터가 있었나봄
* 더러운 사람들
* 
* 아
* 정렬 직접 구현하면서 자잘한 실수들 많이 나온다
* 큇 소트 스왑할 때 arr[i] 이런 값을 인자로 넘겨줘야 하는데
* 자꾸 i 이런 인덱스만 넘겨주는 실수
* 머지 소트 분할하여 소트하고
* 마지막에 머지를 안해주는 실수
* 실수 조심쓰
* 
*/

using namespace std;

#define MAX_N 500000

int N;
int arr[MAX_N];
int sorted[MAX_N];

void swap(int& a, int& b) {
	int temp = a;
	a = b;
	b = temp;
}

int selectPivot(int s, int e) {
	int mid = (s + e) / 2;
	swap(arr[mid], arr[e]);
	return arr[e];
}

int partition(int s, int e) {
	int pivot = selectPivot(s, e);

	int j = s;
	for (int i = s; i < e; i++) {
		if (arr[i] < pivot) {
			swap(arr[i], arr[j]);
			j++;
		}
	}

	swap(arr[j], arr[e]);
	return j;
}

void quickSort(int s, int e) {
	if (s >= e) return;
	int p = partition(s, e);

	quickSort(s, p - 1);
	quickSort(p + 1, e);
}

void merge(int s, int m, int e) {
	int i = s;
	int j = m + 1;
	int k = s;

	while (i <= m && j <= e) {
		if (arr[i] < arr[j]) sorted[k++] = arr[i++];
		else sorted[k++] = arr[j++];
	}

	while (i <= m) sorted[k++] = arr[i++];
	while (j <= e) sorted[k++] = arr[j++];

	for (int x = s; x <= e; x++) {
		arr[x] = sorted[x];
	}
}

void mergeSort(int s, int e) {
	if (s >= e)return;

	int m = (s + e) / 2;

	mergeSort(s, m);
	mergeSort(m + 1, e);

	merge(s, m, e);
}

long long solve() {
	long long sum = 0;
	for (int i = 0; i < N; i++) {
		int rank = i + 1;
		int diff = abs(arr[i] - rank);
		sum += diff;
	}

	return sum;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/2012.txt", "r", stdin);

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	mergeSort(0, N - 1);

	long long ret = solve();

	cout << ret;
}