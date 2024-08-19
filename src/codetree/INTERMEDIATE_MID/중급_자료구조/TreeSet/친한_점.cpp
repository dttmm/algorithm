#include <iostream>
#include <set>

using namespace std;

class Node {
public:
	int x;
	int y;

	Node(int x, int y) {
		this->x = x;
		this->y = y;
	}

	bool operator< (Node o) const {
		if (this->x != o.x) {
			return this->x < o.x;
		}
		return this->y < o.y;
	}
};

int N;
int M;
set<Node> Set;

int main() {

	// ют╥б
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;

		Set.insert(Node(x, y));
	}

	for (int i = 0; i < M; i++) {
		int x, y;
		cin >> x >> y;

		Node node = Node(x, y);
		auto it = Set.lower_bound(node);

		if (it != Set.end()) cout << (*it).x << " " << (*it).y << "\n";
		else cout << "-1 -1\n";
	}
}