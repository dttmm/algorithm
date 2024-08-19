#include <iostream>
#include<unordered_map>

using namespace std;

#define MAX_X 1000000

int N;
int Q;
int arr[MAX_X + 1];	// 1: i인덱스에 숫자가 있다는 표시
int sum[MAX_X + 1]; // i 인덱스까지 있는 숫자의 개수

// a이상 b이하 구간 숫자 개수 리턴
int getSum(int a, int b) {
	return sum[b] - sum[a] + arr[a];
}

int main() {

	cin >> N >> Q;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		arr[n] = 1;
	}

	sum[0] = arr[0];
	for (int i = 1; i <= MAX_X; i++) {
		sum[i] = sum[i - 1] + arr[i];
	}

	for (int i = 0; i < Q; i++) {
		int a, b;
		cin >> a >> b;

		int ret = getSum(a, b);
		cout << ret << "\n";
	}
}