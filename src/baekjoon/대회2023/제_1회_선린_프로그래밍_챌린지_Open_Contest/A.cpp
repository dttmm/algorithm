#include <iostream>
#include <fstream>

/*
* 설계 0분 구현 1분
* 기본 구현 문제
*/

using namespace std;

int N;

int solve(int a, int b, int x) {
	return a * (x - 1) + b;
}

int main() {

	freopen("src/baekjoon/대회2023/제_1회_선린_프로그래밍_챌린지_Open_Contest/A.txt", "r", stdin);
	cin >> N;

	for (int k = 0; k < N; k++) {
		int a;
		int b;
		int x;
		cin >> a >> b >> x;

		int ret = solve(a, b, x);
		cout << ret << endl;
	}
}