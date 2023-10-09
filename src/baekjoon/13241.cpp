#include <iostream>
#include <fstream>

/*
* 구현 5분
* 수학
* lcm 연습할 겸 풀어봄
*/

using namespace std;

// 최대공약수 구하기
int getGCD(int a, int b) {
	if (a == 0) return b;
	return getGCD(b % a, a);
}

// 최소공배수 구하기
long long getLCM(int a, int b) {
	int min = a < b ? a : b;
	int max = a > b ? a : b;
	int gcd = getGCD(min, max);
	return (long long)a * b / gcd;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/13241.txt", "r", stdin);

	//입력
	int a;
	int b;
	cin >> a >> b;

	long long ret = getLCM(a, b);
	cout << ret;
}