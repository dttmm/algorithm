#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000

int N;
int arr[MAX_N];
int d1[MAX_N];	// ���ʿ������� ���� �κ� ���� ���� �ִ밪
int d2[MAX_N];	// �����ʿ������� ���� �κ� ���� ���� �ִ밪

// dp
void solve() {
	// �ʱ�ȭ
	for (int i = 0; i < N; i++) {
		d1[i] = 1;
		d2[i] = 1;
	}

	// ���ʿ������� ���� �κ� ���� ���� �ִ밪 ���ϱ�
	for (int i = 1; i < N; i++) {
		for (int j = 0; j < i; j++) {
			if (arr[j] >= arr[i]) continue;
			d1[i] = max(d1[i], d1[j] + 1);
		}
	}

	// �����ʿ������� ���� �κ� ���� ���� �ִ밪 ���ϱ�
	for (int i = N - 1; i >= 0; i--) {
		for (int j = N - 1; j > i; j--) {
			if (arr[j] >= arr[i]) continue;
			d2[i] = max(d2[i], d2[j] + 1);
		}
	}
}

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	// ���� i�� �߽�����
	// ���ʿ����� i������ ���� �κ� ������ ����
	// �����ʿ����� i���� �����ϴ� ���� �κ� ������ ����
	// ���� �� �� �ִ밪 ����
	int ans = 0;
	for (int i = 0; i < N; i++) {
		ans = max(ans, d1[i] + d2[i]);
	}

	// ���� i�� �ߺ����� ī��Ʈ�ż� �ϳ� ����
	cout << ans - 1;
}