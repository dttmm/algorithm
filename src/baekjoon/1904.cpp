#include <iostream>
#include <fstream>

/*
* ���� 1�� ���� 1��
* dp
* ������ dp����
* �ٷ� ���� ����� 1�� �ٿ��� �� �ְ�
* ������ ����� 00�� �ٿ��� �� �����Ƿ�
* ��ȭ���� �ٿ�� ���� ����
* d[i] = d[i - 1] + d[i - 2]
*/

using namespace std;

#define MAX_N 1000000
#define MOD 15746

int N;
int d[MAX_N + 1];

void solve() {
	d[1] = 1;
	d[2] = 2;

	for (int i = 3; i <= N; i++) {
		d[i] = (d[i - 1] + d[i - 2]) % MOD;
	}
}

int main() {

	freopen("res/baekjoon/1904.txt", "r", stdin);

	cin >> N;

	solve();

	cout << d[N];
}