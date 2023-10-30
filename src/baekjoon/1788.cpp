#include <fstream>
#include <iostream>

/*
* ���� 16�� ���� 12��
* dp
* ������ �Ǻ���ġ ���� ����Ͽ� -2�� ���Ϸ���
* f(-2) = f(-3) + f(-4)����
* -2�� ���ϱ� ���ؼ��� -3�� -4�� �˾ƾߵ�
* �׷��� -3�� -4�� �˱� ���ؼ���
* -5, -6, -7 ... ������ �˾ƾ� �Ǵ� ��Ȳ �߻�
* ������ ���� �ٲ����
* ���� ����
* f(n) = f(n-1) + f(n-2) ���� f(n-1)�� �ѱ��
* f(n-2) = f(n) - f(n-1)�� �ǰ� �� �׿� 2�� ���ϸ�
* f(n) = f(n-2) - f(n-1)�� ��
* �׷� ���� -2�� ���ϱ� ���ؼ���
* ���� ��ȣ�� �����ϸ�
* ����f(2) = f(0) - ����f(1)�� ��
*
* ���� ����� ���� ������ ����
* �Ǻ���ġ ���� dp�迭�� ���� �������ָ� ��
*
* ��ͷ� �Ϸ��� �ߴµ� �ð��ʰ��� ����
* tabluation���� dp ����
*/

using namespace std;

#define MAX_N 1000000
#define MOD 1000000000

int N;
int d_plus[MAX_N + 1];	// ������� dp
int d_minus[MAX_N + 1];	// �������� dp

// ������� dp ����
void setDPlus() {
	d_plus[0] = 0;
	d_plus[1] = 1;
	for (int i = 2; i <= MAX_N; i++) {
		d_plus[i] = (d_plus[i - 1] + d_plus[i - 2]) % MOD;
	}
}

// �������� dp ����
void setDMinus() {
	d_minus[0] = 0;
	d_minus[1] = 1;
	for (int i = 2; i <= MAX_N; i++) {
		d_minus[i] = (d_minus[i - 2] - d_minus[i - 1]) % MOD;
	}
}


int main() {

	freopen("res/baekjoon/1788.txt", "r", stdin);

	// �Է�
	cin >> N;

	// ��� ���� ����
	int ans;
	if (N > 0) {
		setDPlus();
		ans = d_plus[N];
	}
	else {
		setDMinus();
		ans = d_minus[-1 * N];	// ���� ����
	}

	// ��� ���� ���� ���
	if (ans > 0)
		cout << 1 << "\n" << ans;
	else if (ans < 0)
		cout << -1 << "\n" << -1 * ans;
	else cout << 0 << "\n" << 0;
}