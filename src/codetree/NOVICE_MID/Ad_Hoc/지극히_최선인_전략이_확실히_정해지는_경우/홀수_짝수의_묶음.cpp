#include <iostream>

using namespace std;

int N;
int cnt1;	// 홀수의 개수
int cnt2;	// 짝수의 개수

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		if (n % 2 == 0) cnt2++;
		else cnt1++;
	}

	// 짝으로 시작하기 때문에 최종 결과는
	// 짝이 홀보다 하나 더 많거나
	// 짝과 홀의 개수가 같아야 함

	// 홀수가 짝수보다 2개이상 많은 경우
	// 홀수 2개를 합쳐 짝수로 만들어 줘야함
	while (cnt1 > cnt2 + 1) {
		cnt1 -= 2;
		cnt2 += 1;
	}

	// 홀수가 짝수보다 많은 경우
	// 홀수 3개를 합쳐 홀수로 만들어 줘야함
	while (cnt1 > cnt2) {
		cnt1 -= 2;
	}

	// 짝수의 개수가 많은 경우
	// 짝수 2개를 합쳐 짝수호 만들어 줘야함
	while (cnt2 > cnt1 + 1) {
		cnt2 -= 1;
	}

	cout << cnt1 + cnt2;
}