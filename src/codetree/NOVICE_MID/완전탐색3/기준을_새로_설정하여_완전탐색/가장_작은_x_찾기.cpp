#include <iostream>
#include <algorithm>
#include <cmath>

using namespace std;

int N;

int main() {

	// 입력
	cin >> N;

	// 범위 a, b를 2배씩 나눠서 가능한 숫자 찾기
	int p = 2;
	int maxVal = 0;
	for (int i = 0; i < N; i++) {
		int a, b;
		cin >> a >> b;

		// p를 곱했을 때, a를 넘길 수 있는 가장 작은 수
		int n = ceil((double)a / p);

		maxVal = max(maxVal, n);

		p *= 2;
	}

	cout << maxVal;
}