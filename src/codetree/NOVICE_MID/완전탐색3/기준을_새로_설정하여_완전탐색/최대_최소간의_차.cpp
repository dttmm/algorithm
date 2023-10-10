#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_X 10000

int N;
int K;
int cnt[MAX_X + 2];		// ������ ����
int ans = MAX_X * MAX_N;

void solve() {
	// ������ �̸� ������
	int minVal = 0;				// �ּҰ�
	int maxVal = minVal + K;	// �ִ밪

	int cntL = 0;	// �ּҰ����� ���� ����� ��
	int cntR = 0;	// �ִ밪���� ū ����� ��

	// ���� �ȿ� ��� ���� ����� ��
	int sum = 0;
	// �ִ밪���� ū ����� �� ���ϱ�
	for (int i = maxVal + 1; i <= MAX_X; i++) {
		sum += cnt[i] * (i - maxVal);
		cntR += cnt[i];
	}
	ans = min(ans, sum);

	// ������ �̵����Ѱ��� ���� �ȿ� ��� ���� �� ���ϱ� (�����̵� ������)
	while (maxVal <= MAX_X) {
		// ���� �ּҰ��� 1Ŀ���� ���� ���� �ּҰ��� ����?��
		cntL += cnt[minVal];

		// ��� ������Ʈ
		sum += cntL;
		sum -= cntR;

		// �ִ밪�� 1Ŀ���� ���� �ִ밪���� 1ū �༮�� ���� �ȿ� ���Ե�
		cntR -= cnt[maxVal + 1];

		// ���� �̵�
		minVal++;
		maxVal++;

		ans = min(ans, sum);
	}

}

int main() {

	// �Է�
	cin >> N >> K;

	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;
		cnt[n]++;
	}

	solve();

	cout << ans;
}