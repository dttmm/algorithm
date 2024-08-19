#include <iostream>
#include <unordered_map>

using namespace std;

#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
unordered_map<int, int> Map;
int ans;

int main() {

	cin >> N;

	int sum = 0;
	Map[0] = 0;	// �ƹ��͵� �������� �ʾ����� ������ 0ó�� ����ߵ�
	for (int i = 1; i <= N; i++) {
		int n;
		cin >> n;

		// ������
		sum += n;

		// ������
		int r = sum % 7;
		sum = r;

		// ������ ������ �������� ������ �������� �ִ� ���
		if (Map.find(r) != Map.end()) {
			// ���� �����տ��� �ش� ������ ����
			// ������ 0�̹Ƿ� �ش��ϴ� ������ ���ԵǴ� ������ ���� 7�� ��� ��
			int diff = i - Map[r];
			ans = MAX(ans, diff);
		}
		else {
			Map[r] = i;
		}
	}
	cout << ans;
}