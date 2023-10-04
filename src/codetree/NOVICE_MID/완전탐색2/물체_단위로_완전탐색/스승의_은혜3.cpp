#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000

class Node {
public:
	int num;	// �л� ��ȣ
	int p;		// ���� ����
	int sum;	// ���� ���� + ��ۺ�

	Node() {}
	Node(int num, int p, int s) {
		this->num = num;
		this->p = p;
		this->sum = p + s;
	}
};

int N;
int B;
Node* nodes[MAX_N];	// node ���� ������� ���� �迭
Node* arr[MAX_N];	// ������ �� ����� �迭
int ans;

// ��
bool cmp(Node* a, Node* b) {
	return (*a).sum < (*b).sum;
}

// ��Ž
void solve() {
	// �л� i���� �ݰ����� ����
	for (int i = 0; i < N; i++) {
		Node* picked = nodes[i];

		// �ݰ����� ����
		(*picked).sum -= (*picked).p * 0.5;

		// �������� + ��ۺ� ���� ������ ����
		sort(arr, arr + N, cmp);

		int total = 0;
		int cnt = 0;
		// ���� �л��� ���� ���
		for (int j = 0; j < N; j++) {
			Node* node = arr[j];

			total += (*node).sum;

			// ���� �ʰ��ߴ��� Ȯ��
			if (total > B) break;

			cnt++;
			ans = max(ans, cnt);
		}

		// ���� �ߴ��� ����
		(*picked).sum += (*picked).p * 0.5;
	}
}

int main() {

	// �Է� �ޱ�
	cin >> N >> B;
	for (int i = 0; i < N; i++) {
		int p;
		int s;
		cin >> p >> s;

		Node* node = new Node(i, p, s);
		nodes[i] = node;
		arr[i] = node;
	}

	// ��Ž
	solve();

	cout << ans;
}