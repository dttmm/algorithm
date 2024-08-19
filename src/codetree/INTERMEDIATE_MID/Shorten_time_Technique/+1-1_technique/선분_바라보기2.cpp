#include <iostream>
#include <set>
#include <unordered_set>
#include <vector>
#include <tuple>
#include <algorithm>

using namespace std;

#define MAX_N 50000

struct Node {
	int id;
	int s;	// ���� ����
	int e;	// ���� ����
	int y;	// y��

	Node() {}
	Node(int id_, int s_, int e_, int y_) {
		id = id_;
		s = s_;
		e = e_;
		y = y_;
	}

	// y ���� �� �켱
	bool operator<(Node o) const {
		return y < o.y;
	}

}nodes[MAX_N];

int N;
set<Node> Set;
vector<tuple<int, int, int> > lists;	// x����, v����������, id
unordered_set<int> colorSet;	// ���� �� ����

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N;
	for (int i = 0; i < N; i++) {
		int a, b, y;
		cin >> y >> a >> b;

		nodes[i] = Node(i, a, b, y);
		lists.push_back(make_tuple(a, 1, i));
		lists.push_back(make_tuple(b, -1, i));
	}

	// x��ǥ �� ����
	sort(lists.begin(), lists.end());

	for (auto it : lists) {
		int x, v, id;
		tie(x, v, id) = it;

		// ���������� ��� -> �ش� ��� �߰�
		if (v > 0) {
			Set.emplace(nodes[id]);
		}
		// ���������� ��� -> �ش� ��� ����
		else {
			Set.erase(nodes[id]);
		}

		// y���� ���� ���� ����
		if (Set.begin() != Set.end()) colorSet.emplace(Set.begin()->id);
	}

	cout << colorSet.size();
}