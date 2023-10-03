#include <fstream>
#include <iostream>

/*
* ���� 1�� ���� 2�� ����� 1��
* dp
* dp�� �Ǻ���ġó�� Ǯ��
* 
* Ʋ��
* �ڸ��� int�� �Ѿ�� Ʋ��
* long long���� ��ü
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