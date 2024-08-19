#include <iostream>
#include <unordered_map>

using namespace std;

#define MAX_N 100000

struct Node {
	int data;
	Node* left;
	Node* right;

	Node() {}

	Node(int data_) {
		data = data_;
		left = nullptr;
		right = nullptr;
	}
}nodes[MAX_N];

int index;
int N;
int M;
unordered_map<int, Node*> Map; // n�� ��� ���� ����

// �� ��带 ����
void connect(Node* s, Node* e) {
	if (s != nullptr) s->left = e;
	if (e != nullptr) e->right = s;
}

// ��� ȣ��
void pop(Node* node) {
	cout << node->left->data << " " << node->right->data << "\n";

	// ���� ������ �򰥸� ����
	connect(node->right, node->left);
	node->left = nullptr;
	node->right = nullptr;
}

int main() {

	cin >> N >> M;

	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		nodes[index] = Node(n);
		Map[n] = &nodes[index++];
	}

	for (int i = 0; i < N - 1; i++) {
		connect(&nodes[i], &nodes[i + 1]);
	}
	connect(&nodes[N - 1], &nodes[0]);

	for (int i = 0; i < M; i++) {
		int n;
		cin >> n;

		pop(Map[n]);
	}
}