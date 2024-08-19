#include<iostream>
#include<string>

using namespace std;

#define MAX_N 100000

struct Node {
	string data;
	Node* left;
	Node* right;

	Node() {}

	Node(string data_) {
		data = data_;
		left = nullptr;
		right = nullptr;
	}
}nodes[MAX_N * 2];

int index;
int N;
int Q;
Node* cur;

// 두 노드를 연결
void connect(Node* s, Node* e) {
	if (s != nullptr) s->right = e;
	if (e != nullptr) e->left = s;
}

// 1. 핀셋을 꽂은 도시를 현재의 오른쪽 인접 도시로 바꿔서 꽂습니다.
void changePinRight() {
	// 오른쪽 도시가 있는지 확인
	if (cur->right == nullptr) return;

	cur = cur->right;
}

// 2. 핀셋을 꽂은 도시를 현재의 왼쪽쪽 인접 도시로 바꿔서 꽂습니다.
void changePinLeft() {
	// 왼쪽 도시가 있는지 확인
	if (cur->left == nullptr) return;

	cur = cur->left;
}

// 3. 핀셋이 꽂혀 있는 도시의 오른쪽에 위치한 도시의 스티커를 제거합니다.
void removePinRight() {
	// 오른쪽 도시가 있는지 확인
	if (cur->right == nullptr) return;

	// 도시가 두개밖에 없는 경우
	if (cur->right == cur) {
		cur->right = nullptr;
		cur->left = nullptr;
		return;
	}

	// 핀과 오른오른쪽 도시 연결
	connect(cur, cur->right->right);
}

// 4. 핀셋이 꽂혀 있는 도시의 오른쪽에 새로운 도시를 추가하여 스티커를 붙입니다.
void addPinRight(Node* node) {
	// 핀과 핀오른도시에 새로운 도시 넣음
	connect(node, cur->right);
	connect(cur, node);

	// 새로운 도시 오른쪽이 없는 경우 <- 기존에 도시가 하나밖에 없던 경우
	// 두 도시를 서로 연결해줌
	if (node->right == nullptr) connect(node, cur);
}

void print() {
	if (cur->right == nullptr || cur->left == nullptr || cur->right == cur->left) {
		cout << "-1\n";
	}
	else {
		cout << cur->left->data << " " << cur->right->data << "\n";
	}
}


int main() {

	cin >> N >> Q;

	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		nodes[index++] = Node(s);
	}

	for (int i = 0; i < N - 1; i++) {
		connect(&nodes[i], &nodes[i + 1]);
	}
	if (N - 1 != 0) connect(&nodes[N - 1], &nodes[0]);

	cur = &nodes[0];

	for (int q = 0; q < Q; q++) {
		int x;
		cin >> x;

		if (x == 1) {
			changePinRight();
		}
		else if (x == 2) {
			changePinLeft();
		}
		else if (x == 3) {
			removePinRight();
		}
		else if (x == 4) {
			string s;
			cin >> s;

			nodes[index] = Node(s);

			addPinRight(&nodes[index++]);
		}

		print();
	}
}