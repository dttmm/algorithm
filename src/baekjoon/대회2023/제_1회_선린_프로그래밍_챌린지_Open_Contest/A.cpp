#include <iostream>
#include <fstream>

/*
* ���� 0�� ���� 1��
* �⺻ ���� ����
*/

using namespace std;

int N;

int solve(int a, int b, int x) {
	return a * (x - 1) + b;
}

int main() {

	freopen("src/baekjoon/��ȸ2023/��_1ȸ_����_���α׷���_ç����_Open_Contest/A.txt", "r", stdin);
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