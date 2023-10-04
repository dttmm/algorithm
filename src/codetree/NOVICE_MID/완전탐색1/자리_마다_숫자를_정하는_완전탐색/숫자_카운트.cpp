#include <iostream>
#include <string>

using namespace std;

#define MAX_N 10
#define R 3

class Node {
public:
	int n1;
	int n2;
	int n3;
	int cnt1;
	int cnt2;
	Node(string s, int cnt1, int cnt2) {
		this->n1 = stoi(s.substr(0, 1));
		this->n2 = stoi(s.substr(1, 1));
		this->n3 = stoi(s.substr(2, 1));
		this->cnt1 = cnt1;
		this->cnt2 = cnt2;
	}
	Node() {}
};

int N;
Node nodes[MAX_N];
int tr[R];
int ans;
bool visited[10];

//  B�� ��� ����� ��
bool compare(Node node) {
	int n1 = tr[0];
	int n2 = tr[1];
	int n3 = tr[2];
	int cnt1 = 0;
	int cnt2 = 0;

	if (n1 == node.n1) cnt1++;
	else if (n1 == node.n2 || n1 == node.n3) cnt2++;

	if (n2 == node.n2) cnt1++;
	else if (n2 == node.n3 || n2 == node.n1) cnt2++;

	if (n3 == node.n3) cnt1++;
	else if (n3 == node.n2 || n3 == node.n1) cnt2++;

	return (cnt1 == node.cnt1 && cnt2 == node.cnt2);
}

// �ߺ� ����
void solve(int k) {
	if (k == R) {
		// B�� ��� ����� ��
		for (int i = 0; i < N; i++) {
			Node node = nodes[i];
			bool ret = compare(node);

			if (!ret) return;
		}
		// B�� ����� ��ġ�ϴ� ���
		ans++;
	}
	else {
		// ���� �ٸ� ���� 3�� ����
		for (int i = 1; i <= 9; i++) {
			if (visited[i]) continue;

			visited[i] = true;
			tr[k] = i;
			solve(k + 1);
			visited[i] = false;
		}
	}
}

int main() {

	// �Է� �ޱ�
	cin >> N;
	for (int i = 0; i < N; i++) {
		string s;
		int cnt1;
		int cnt2;
		cin >> s >> cnt1 >> cnt2;

		nodes[i] = Node(s, cnt1, cnt2);
	}

	// ���� �ٸ� ���� 3�� ����
	solve(0);

	cout << ans;
}