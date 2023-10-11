#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int arr[4][4];	// ù��° �����ڰ� i�� ���� ��, �ι�° �����ڰ� j�� �� Ƚ��

int main() {

	cin >> N;
	for (int x = 0; x < N; x++) {
		int i, j;
		cin >> i >> j;
		arr[i][j]++;
	}

	// 1>2>3>1 �϶�
	int case1 = arr[1][2] + arr[2][3] + arr[3][1];

	// 1>3>2>1 �϶�
	int case2 = arr[1][3] + arr[3][2] + arr[2][1];

	// �� ����� �ִ밪
	int ans = max(case1, case2);

	cout << ans;
}