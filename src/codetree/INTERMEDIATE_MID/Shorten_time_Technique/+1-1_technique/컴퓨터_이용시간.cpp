#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>
#include <set>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N];
vector<tuple<int, int, int> > list;	// x�ð�, v�������� ����, id
set<int> Set;	// ��� ������ ��ǻ��

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N;
	for (int i = 0; i < N; i++) {
		int a, b;
		cin >> a >> b;

		list.push_back(make_tuple(a, 1, i));	// ���� ����
		list.push_back(make_tuple(b, -1, i));	// ���� ����
	}

	// ����
	sort(list.begin(), list.end());

	// ��� ������ ��ǻ�� ����
	for (int i = 1; i <= N; i++) {
		Set.insert(i);
	}

	for (auto it : list) {
		int x, v, id;
		tie(x, v, id) = it;

		// ���������̸� ��밡���� ��ǻ�� ���
		if (v > 0) {
			int com = *Set.begin();
			Set.erase(com);

			arr[id] = com;
		}
		// ���������̸� ����� ��ǻ�� �ݳ�
		else {
			Set.insert(arr[id]);
		}
	}

	for (int i = 0; i < N; i++) {
		cout << arr[i] << " ";
	}
}