#include <fstream>
#include <iostream>
#include <algorithm>

/*
* ���� 3�� ���� 3��
* dp
* ������ ���ڸ��� N^2���� Ǯ��� �ڴٴ� ������ �����
* ���� dp�迭�� i��° ���ڱ��� �������� �� �κм��� ���� �ִ밪�� ����
* �׸��� i������ ���ڵ��� ���鼭 �ڽź��� ���� ���ڸ� �������
* ���� ���ڵ��� dp���� �߿��� �ִ밪�� ���
* �ڽ��� ���Ѵٸ�
* i��° ���ڱ��� �������� ���� �ִ밪�� ����
*/

using namespace std;

#define MAX_N 1000

int N;
int arr[MAX_N];
int d[MAX_N];

// dp
void solve() {
	// ���� i�� ���ϰ�
	for (int i = 0; i < N; i++) {
		int n = arr[i];
		int maxVal = 0;

		// ���غ��� ���� ���� Ž��
		for (int j = i - 1; j >= 0; j--) {
			int m = arr[j];

			// �ڽź��� ���� ���ڸ� ���� ���
			if (m < n) {
				// �ش� ���ڵ� �߿��� dp�ִ밪 ã��
				maxVal = max(maxVal, d[j]);
			}
		}

		// �ִ밪�� �ڽ��� ���Խ��� dp�� ������Ʈ
		d[i] = maxVal + n;
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/11055.txt", "r", stdin);

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	// dp�迭���� �ִ밪 ã��
	int ans = 0;
	for (int i = 0; i < N; i++) {
		ans = max(ans, d[i]);
	}

	cout << ans;
}