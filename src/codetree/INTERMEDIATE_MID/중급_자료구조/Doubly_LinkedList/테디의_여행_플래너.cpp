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

// �� ��带 ����
void connect(Node* s, Node* e) {
	if (s != nullptr) s->right = e;
	if (e != nullptr) e->left = s;
}

// 1. �ɼ��� ���� ���ø� ������ ������ ���� ���÷� �ٲ㼭 �Ƚ��ϴ�.
void changePinRight() {
	// ������ ���ð� �ִ��� Ȯ��
	if (cur->right == nullptr) return;

	cur = cur->right;
}

// 2. �ɼ��� ���� ���ø� ������ ������ ���� ���÷� �ٲ㼭 �Ƚ��ϴ�.
void changePinLeft() {
	// ���� ���ð� �ִ��� Ȯ��
	if (cur->left == nullptr) return;

	cur = cur->left;
}

// 3. �ɼ��� ���� �ִ� ������ �����ʿ� ��ġ�� ������ ��ƼĿ�� �����մϴ�.
void removePinRight() {
	// ������ ���ð� �ִ��� Ȯ��
	if (cur->right == nullptr) return;

	// ���ð� �ΰ��ۿ� ���� ���
	if (cur->right == cur) {
		cur->right = nullptr;
		cur->left = nullptr;
		return;
	}

	// �ɰ� ���������� ���� ����
	connect(cur, cur->right->right);
}

// 4. �ɼ��� ���� �ִ� ������ �����ʿ� ���ο� ���ø� �߰��Ͽ� ��ƼĿ�� ���Դϴ�.
void addPinRight(Node* node) {
	// �ɰ� �ɿ������ÿ� ���ο� ���� ����
	connect(node, cur->right);
	connect(cur, node);

	// ���ο� ���� �������� ���� ��� <- ������ ���ð� �ϳ��ۿ� ���� ���
	// �� ���ø� ���� ��������
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