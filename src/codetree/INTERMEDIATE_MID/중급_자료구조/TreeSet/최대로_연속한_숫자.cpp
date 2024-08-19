#include <iostream>
#include <set>
#include <map>

using namespace std;

int N;
int M;
set<int> Set;	// �ɰ� ��ġ ǥ��
map<int, int> Map; // ������ ���� ����


int main() {

	// �Է�
	cin >> N >> M;

	// �ִ� ���� ����
	int s = -1;
	int e = N + 1;

	Map[e - s - 1]++;

	for (int i = 0; i < M; i++) {
		int n;
		cin >> n;

		// ������ ū�� ���ϱ�
		int right;
		auto it = Set.upper_bound(n);
		if (it == Set.end()) right = e;
		else right = *it;

		// ������ ������ ���ϱ�
		int left;
		it = Set.lower_bound(n);
		if (it != Set.begin()) it--;
		else it = Set.end();

		if (it == Set.end()) left = s;
		else left = *it;

		// ���� ���� �ѷ� �ɰ�
		int total = right - left - 1;
		Map[total]--;
		if (Map[total] == 0)Map.erase(total);

		Map[right - n - 1]++;
		Map[n - left - 1]++;

		// �ɰ� ���� ǥ��
		Set.insert(n);

		int max = Map.rbegin()->first;
		cout << max << "\n";
	}

}