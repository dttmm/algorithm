#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N + 1];

int main() {

	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	int prev = arr[N];
	int index = 0;
	// 뒤에서부터 탐색하면서
	// 이전에 나왔던 숫자보다 큰 숫자가 나온다면
	// 해당 숫자(큰 숫자)까지는 무조건 순서가 와야하므로
	// 해당 인덱스까지는 앞에서부터 순서를 바꿔야됨
	// 그리고 바로 해당 인덱스가 바로 바꾸는 횟수가 됨
	for (int i = N; i > 0; i--) {
		int n = arr[i];
		if (n > prev) {
			index = i;
			break;
		}
		prev = n;
	}

	cout << index;
}