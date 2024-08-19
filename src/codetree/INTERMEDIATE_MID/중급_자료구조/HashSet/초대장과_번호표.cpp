#include <iostream>
#include <unordered_set>
using namespace std;
#define MAX_N 100000
#define MAX_G 250000

int N;
int G;
unordered_set<int> SetN[MAX_N + 1];	// i��° ����� �� �׷� ����
unordered_set<int> SetG[MAX_G + 1];	// i��° �׷쿡 �ִ� ����� ����
unordered_set<int> SetAns;

// �ʴ��� �ֱ�
void solve(int n) {
	SetAns.insert(n);

	// n����� ���� �׷�g ���鼭
	for (int g : SetN[n]) {
		// �ش� �׷쿡�� n ����
		SetG[g].erase(n);

		// �׷쿡 �Ѹ� �����ִ� ���
		if (SetG[g].size() == 1) {
			// �� �Ѹ��� �ʴ��� ��
			int m = *(SetG[g].begin());
			solve(m);
		}
	}
}

int main() {

	// �Է�
	cin >> N;
	cin >> G;
	for (int i = 1; i <= G; i++) {
		int k;
		cin >> k;

		for (int j = 0; j < k; j++) {
			int n;
			cin >> n;

			SetG[i].insert(n);
			SetN[n].insert(i);
		}
	}

	solve(1);

	cout << SetAns.size();
}