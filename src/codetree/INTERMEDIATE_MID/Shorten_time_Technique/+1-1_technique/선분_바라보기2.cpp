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
	int s;	// 시작 지점
	int e;	// 종료 지점
	int y;	// y축

	Node() {}
	Node(int id_, int s_, int e_, int y_) {
		id = id_;
		s = s_;
		e = e_;
		y = y_;
	}

	// y 작은 순 우선
	bool operator<(Node o) const {
		return y < o.y;
	}

}nodes[MAX_N];

int N;
set<Node> Set;
vector<tuple<int, int, int> > lists;	// x지점, v시작점여부, id
unordered_set<int> colorSet;	// 정답 색 개수

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

	// x좌표 순 정렬
	sort(lists.begin(), lists.end());

	for (auto it : lists) {
		int x, v, id;
		tie(x, v, id) = it;

		// 시작지점인 경우 -> 해당 노드 추가
		if (v > 0) {
			Set.emplace(nodes[id]);
		}
		// 종료지점인 경우 -> 해당 노드 삭제
		else {
			Set.erase(nodes[id]);
		}

		// y가장 작은 놈이 보임
		if (Set.begin() != Set.end()) colorSet.emplace(Set.begin()->id);
	}

	cout << colorSet.size();
}