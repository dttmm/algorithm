#include <iostream>
#include <map>
#include <climits>
using namespace std;

int N;
int T;
map<int, long long> Map;	// key: ó�� ��ġ, value: ���� ��ġ

int main() {

	// �Է�
	cin >> N >> T;
	for (int i = 0; i < N; i++) {
		long long p, v;
		cin >> p >> v;

		Map[-1 * p] = p + v * T;
	}

	int ans = 0;
	long long min = LLONG_MAX;	// ���� ��ġ �߿��� �ּҰ�
	// ó�� ��ġ�� ���� ������� ������ġ���� ����
	for (auto item : Map) {
		long long value = item.second;	// ������ġ

		// ������ġ �ּҰ� ����
		// -> ������ �ּҰ����� ū ������ �ּҰ��� ���� �׷����� �ٳ����
		// �ּҰ����� ���� ������ ���ο� �׷����� ����
		if (value < min) {
			min = value;
			ans++;	// �׷� �� ����
		}
	}

	cout << ans;
}