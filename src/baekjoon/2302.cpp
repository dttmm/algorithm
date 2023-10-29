#include <fstream>
#include <iostream>

/*
* ���� 6�� ���� 3��
* dp
* N��° �¼��� ���µ�
* �̹� N-1��° ������ ���� ���Դ� �����ϰ� dp�� ������
* ���� N��° �¼��� VIP���
* N���� �տ� �ִ� �¼��� ���̻� �������� �����Ƿ�
* d[N] = d[N-1]�� ��
* ���� N��° �¼��� VIP�� �ƴ϶��
* N��° �¼��� N-1��° �¼��� �̵��� �����ϹǷ�
* N�� N-1��° �ڸ��� �ٲ۴ٸ� N-2��° �¼������� �������� ���Եǰ�
* �ڸ��� �ٲ��� �ʴ� �ٸ� N-1��° �¼������� �������� ���Ե�
* �׷��� ��ȭ���� ������ ���� ����
* d[N] = d[N-1] + d[N-2] <- �ڸ��� �ٲ��� �ʾ��� �� + �ڸ��� �ٲ��� ��
* 
* �ٵ� N�� N-1�� �ڸ��� �ٲٱ� ���ؼ���
* N�� VIP�� �ƴϾ�ߵ�����
* N-1�� VIP�� �ƴϾ�� ��
* �׷��� N�� N-1��� VIP�� �ƴ� ��쿡�� �ڸ��� �ٲ��� �� ����
*/

using namespace std;

#define MAX_N 40

int N;
int M;
int d[MAX_N + 1];
bool isfixed[MAX_N + 1];	// VIP ����

// dp
void solve() {
	d[0] = 1;	// �ǹ̻� �ʿ���
	d[1] = 1;
	for (int i = 2; i <= N; i++) {
		// i��° �¼��� i-1���� �¼��� VIP�� �ƴ� ���
		if (!isfixed[i] && !isfixed[i - 1]) {
			d[i] = d[i - 1] + d[i - 2];
		}
		else {
			d[i] = d[i - 1];
		}
	}
}

int main() {

	freopen("res/baekjoon/2302.txt", "r", stdin);

	// �Է�
	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		int n;
		cin >> n;
		isfixed[n] = true;
	}

	solve();

	cout << d[N];
}