#include<iostream>
#include<queue>
#include <cstdlib>

using namespace std;

class Node {
public:
	int x;
	int y;

	Node(int x, int y) {
		this->x = x;
		this->y = y;
	}

	bool operator<(Node o) const {
		int sum1 = abs(this->x) + abs(this->y);
		int sum2 = abs(o.x) + abs(o.y);
		if (sum1 != sum2) return !(sum1 < sum2);
		if (this->x != o.x) return !(this->x < o.x);
		return !(this->y < o.y);
	}
};

int N;
int M;
priority_queue<Node> pq;


int main() {

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;
		pq.emplace(x, y);
	}

	for (int i = 0; i < M; i++) {
		Node node = pq.top();
		pq.pop();

		pq.emplace(node.x + 2, node.y + 2);
	}

	Node node = pq.top();
	cout << node.x << " " << node.y;
}