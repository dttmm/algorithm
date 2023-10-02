#include <iostream>
#include <fstream>
#include <algorithm>

/*
* ���� 7�� ���� 9�� ����� 2��
* �����̵� ������
* ���� �������� ���������� K��ŭ ������ �Ÿ�����
* ���� �� �ִ� ������ ������
* �������� K��ŭ ������ �Ÿ�����
* ���� �� �ִ� ������ ������ ���� ���ؼ�
* ���������� ���� �� �ִ� ���� + �������� ���� �� �ִ� ������ �ִ밪�� ����
* 
* �������� ���� ���
* ���� ��ġ�� �����ؼ� ���������� K��ŭ ������ �Ÿ����� ������ ������
* �迭���� ������ġ���� K+1�� ��ŭ ���������� ������ ���̹Ƿ�
* �迭�� �� ��ġ���� ���������� K+1�� ��ŭ �������� ���� ������ ������ totalR�� ������
* ������ totalL�� ������
* 
* totalR, totalL��� �ڽ� ��ġ�� ���� ������ �ߺ����� �����Ƿ�
* ��ü ��ǥ�� ��ȸ�ϸ鼭
* totalR + totalL - �ڽ� ��ġ�� ���� ���� �߿��� �ִ밪�� ���� ��
* 
* Ʋ��
* OutOfBounds��
* arr�� ũ�⸦ ��ü ��ǥ ������ MAX_X�� ����� �Ǵµ�
* �Է� ����ŭ MAX_N���־ Ʋ��
*/

using namespace std;

#define MAX_N 100000
#define MAX_X 1000000

int N;
int K;
int arr[MAX_X + 1];
long long totalR[MAX_X + 1];
long long totalL[MAX_X + 1];

void solve() {
	long long total = 0;
	for (int x = 0; x <= MAX_X; x++) {
		int n = arr[x];

		if (x < K + 1) {
			total += n;
		}
		else {
			total += n;
			total -= arr[x - (K + 1)];
		}

		totalR[x] = total;
	}

	total = 0;
	for (int x = 0; x <= MAX_X; x++) {
		int n = arr[MAX_X - x];

		if (x < K + 1) {
			total += n;
		}
		else {
			total += n;
			total -= arr[MAX_X - x + (K + 1)];
		}

		totalL[MAX_X - x] = total;
	}
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/10025.txt", "r", stdin);

	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		int g;
		int x;
		cin >> g >> x;

		arr[x] = g;
	}

	solve();

	long long maxVal = 0;
	for (int x = 0; x <= MAX_X; x++) {
		maxVal = max(maxVal, totalR[x] + totalL[x] - arr[x]);
	}

	cout << maxVal;
}