#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

#define MAX_N 26

int N;
int arr[MAX_N];	// �� ��ġ�� ���ĺ� ���� ����

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		char c;
		cin >> c;

		arr[i] = c;
	}

	// �� �տ� ������ ū ���ĺ� ������ �׸�ŭ �ڸ� �ٲ���ߵ�
	int ans = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < i; j++) {
			if (arr[i] < arr[j]) ans++;
		}
	}

	cout << ans;
}