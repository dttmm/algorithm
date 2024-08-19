#include <iostream>
#include<unordered_map>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N + 2];
long long L[MAX_N + 2];	// 1~i���� ������
long long R[MAX_N + 2];	// N~i���� ������
long long diff[MAX_N + 2]; // L[i] - R[i+1]
unordered_map<long long, int> RMAP;	// ���ؿ��� �����ʿ� �ִ� ���ҵ� ����
unordered_map<long long, int> LMAP;	// ���ؿ��� ���ʿ� �ִ� ���ҵ� ����

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	// 1~i���� ������ ���ϱ�
	for (int i = 1; i <= N; i++) {
		L[i] = L[i - 1] + arr[i];
	}

	// N~i���� ������ ���ϱ�
	for (int i = N; i >= 1; i--) {
		R[i] = R[i + 1] + arr[i];
	}

	// L[i] - R[i+1] ���ϱ�
	for (int i = 0; i < N; i++) {
		diff[i] = L[i] - R[i + 1];
	}

	// ������ 1���� ��Ƽ� �����ҰŶ�
	// 1���� �����ʿ� �ִ� ���ҵ��� RAMP�� �ְ�
	for (int i = 2; i < N; i++) {
		int n = diff[i];
		RMAP[n]++;
	}
	// 1�� �ش��ϴ� LMAP�� ����
	LMAP[diff[1]]++;

	// ���� i�� �ϳ��� ������Ű��
	long long cnt = 0;
	for (int i = 2; i < N - 1; i++) {
		int n = diff[i];

		// ������ �߽����� ������ ���ʿ� �ش��ϴ� ���� ������Ʈ
		RMAP[n]--;

		// 2���� �������� ���� �� �ִ� ���� ã���� ��
		// 2���� ������ �� 2���� �������� ������� �ϱ� ������ 2�� ������� Ȯ��
		if (n == 0 && L[i] % 2 == 0) {
			int target = L[i];

			// 2���� ������ �� 2���� �������� ���������� ��� ���
			cnt += LMAP[-1 * target] * RMAP[target];
		}

		LMAP[n]++;
	}

	cout << cnt;
}