#include <iostream>
#include <fstream>

/*
* ���� 5��
* ����
* lcm ������ �� Ǯ�
*/

using namespace std;

// �ִ����� ���ϱ�
int getGCD(int a, int b) {
	if (a == 0) return b;
	return getGCD(b % a, a);
}

// �ּҰ���� ���ϱ�
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

	//�Է�
	int a;
	int b;
	cin >> a >> b;

	long long ret = getLCM(a, b);
	cout << ret;
}