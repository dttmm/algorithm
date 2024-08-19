#include <iostream>
#include<unordered_map>

using namespace std;

# define MAX_N 100000
# define MAX_M 10

struct Node {
	int data;
	Node* prev;
	Node* next;

	Node() {}
	Node(int data_) {
		data = data_;
		prev = nullptr;
		next = nullptr;
	}
}nodes[MAX_N + 1];

int N;
int M;
int Q;
int index = 1;
Node* heads[MAX_M + 1];
unordered_map<int, Node*> Map;

// �� ��带 ����
void connect(Node* a, Node* b) {
	if (a != nullptr) a->next = b;
	if (b != nullptr) b->prev = a;
}

// a��� ����
void popNode(Node* a) {
	connect(a->prev, a->next);

	for (int i = 1; i <= M; i++) {
		if (heads[i] != a) continue;

		heads[i] = a->next;
		break;
	}

	a->prev = nullptr;
	a->next = nullptr;
}

// a��� �տ� b��� �߰�
void pushNode(Node* a, Node* b) {
	connect(a->prev, b);
	connect(b, a);

	for (int i = 1; i <= M; i++) {
		if (heads[i] != a) continue;

		heads[i] = b;
		break;
	}
}

// a~b��� ����
void popNodeRange(Node* a, Node* b) {
	connect(a->prev, b->next);

	for (int i = 1; i <= M; i++) {
		if (heads[i] != a) continue;

		heads[i] = b->next;
		break;
	}

	a->prev = nullptr;
	b->next = nullptr;
}

// a��� �տ� b~c��� �߰�
void pushNodeRange(Node* a, Node* b, Node* c) {
	connect(a->prev, b);
	connect(c, a);

	for (int i = 1; i <= M; i++) {
		if (heads[i] != a) continue;

		heads[i] = b;
		break;
	}
}

int main() {

	cin >> N >> M >> Q;

	// �ʱ� �� ���� ����
	for (int i = 1; i <= M; i++) {
		int k;
		cin >> k;

		if (k == -1) continue;

		// ��� ����
		for (int j = 0; j < k; j++) {
			int n;
			cin >> n;

			nodes[index + j] = Node(n);
			Map[n] = &nodes[index + j];
		}

		// ��� ����
		for (int j = 0; j < k - 1; j++) {
			connect(&nodes[index + j], &nodes[index + j + 1]);
		}

		heads[i] = &nodes[index];
		index += k;
	}

	for (int q = 0; q < Q; q++) {
		int n;
		cin >> n;

		if (n == 1) {
			int a, b;
			cin >> a >> b;

			popNode(Map[a]);
			pushNode(Map[b], Map[a]);
		}
		else if (n == 2) {
			int a;
			cin >> a;

			popNode(Map[a]);
		}
		else if (n == 3) {
			int a, b, c;
			cin >> a >> b >> c;

			popNodeRange(Map[a], Map[b]);
			pushNodeRange(Map[c], Map[a], Map[b]);
		}
	}

	// ���
	for (int i = 1; i <= M; i++) {
		Node* node = heads[i];

		if (node == nullptr) {
			cout << "-1\n";
			continue;
		}

		while (node != nullptr) {
			cout << node->data << " ";
			node = node->next;
		}
		cout << "\n";
	}
}