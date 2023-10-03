#include <fstream>
#include <iostream>

/*
* 설계 1분 구현 2분 디버깅 1분
* dp
* dp로 피보나치처럼 풀음
* 
* 틀림
* 자릿수 int형 넘어가서 틀림
* long long으로 교체
*/

using namespace std;

#define MAX_N 116

int N;
long long d[MAX_N + 1];

long long f(int n) {
	if (n <= 3) return 1;

	if (d[n] != 0) return d[n];

	return d[n] = f(n - 1) + f(n - 3);
}

int main() {

	freopen("res/baekjoon/14495.txt", "r", stdin);

	cin >> N;

	long long ret = f(N);

	cout << ret;
}