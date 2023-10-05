#include <iostream>
#include <algorithm>
#include <cstdlib>

using namespace std;

#define MAX_A 1000
#define MAX_T 100

int T;
int a;
int b;
int ans;
int sMax;				// S�� �� ����
int nMax;				// N�� �� ����
int sArr[MAX_T + 1];	// S�� �ִ� ��ġ
int nArr[MAX_T + 1];	// N�� �ִ� ��ġ
int sR[MAX_A + 1];		// ���� �����ʿ��� ���� ����� S�� ��ġ
int sL[MAX_A + 1];		// ���� ���ʿ��� ���� ����� S�� ��ġ
int sD[MAX_A + 1];		// ���� ���ʿ��� ���� ����� S���� �Ÿ�
int nR[MAX_A + 1];		// ���� �����ʿ��� ���� ����� N�� ��ġ
int nL[MAX_A + 1];		// ���� ���ʿ��� ���� ����� N�� ��ġ
int nD[MAX_A + 1];		// ���� ���ʿ��� ���� ����� N���� �Ÿ�

// ���� �����ʿ��� ���� ����� �༮ ���ϱ�
void setR(int* arr, int* arrR, int max) {
	fill_n(arrR, MAX_A, MAX_A * 2);
	int prev = -1;
	for (int i = 0; i < max; i++) {
		int n = *(arr + i);

		for (int j = prev + 1; j <= n; j++) {
			*(arrR + j) = n;
		}

		prev = n;
	}
}

// ���� ���ʿ��� ���� ����� �༮ ���ϱ�
void setL(int* arr, int* arrL, int max) {
	fill_n(arrL, MAX_A, MAX_A * -2);
	int prev = MAX_A + 1;
	for (int i = 0; i < max; i++) {
		int n = *(arr + max - 1 - i);

		for (int j = prev - 1; j >= n; j--) {
			*(arrL + j) = n;
		}

		prev = n;
	}
}

// ���� ���ʿ��� ���� ����� �༮���� �Ÿ� ���ϱ�
void setD(int* arrR, int* arrL, int* arrD) {
	for (int i = a; i <= b; i++) {
		int diffR = abs(*(arrR + i) - i);
		int diffL = abs(*(arrL + i) - i);
		*(arrD + i) = min(diffR, diffL);
	}
}

// d1�� d2���� �۰ų� ���� ��� ���ϱ�
void solve() {
	for (int i = a; i <= b; i++) {
		if (sD[i] <= nD[i]) ans++;
	}
}

int main() {

	// �Է�
	cin >> T >> a >> b;

	for (int i = 0; i < T; i++) {
		char c;
		int x;
		cin >> c >> x;

		if (c == 'S') sArr[sMax++] = x;
		else  nArr[nMax++] = x;
	}

	// ����
	sort(sArr, sArr + sMax);
	sort(nArr, nArr + nMax);

	// �����ʿ��� ����� �༮ ���ϱ�
	setR(sArr, sR, sMax);
	setR(nArr, nR, nMax);

	// ���ʿ��� ����� �༮ ���ϱ�
	setL(sArr, sL, sMax);
	setL(nArr, nL, nMax);

	// ���ʿ��� ����� �༮���� �Ÿ� ���ϱ�
	setD(sR, sL, sD);
	setD(nR, nL, nD);

	// ���� ���ϱ�
	solve();

	cout << ans;
}