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

//  B가 물어본 결과와 비교
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

// 중복 순열
void solve(int k) {
	if (k == R) {
		// B가 물어본 결과와 비교
		for (int i = 0; i < N; i++) {
			Node node = nodes[i];
			bool ret = compare(node);

			if (!ret) return;
		}
		// B의 결과와 일치하는 경우
		ans++;
	}
	else {
		// 서로 다른 숫자 3개 고르기
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

	// 입력 받기
	cin >> N;
	for (int i = 0; i < N; i++) {
		string s;
		int cnt1;
		int cnt2;
		cin >> s >> cnt1 >> cnt2;

		nodes[i] = Node(s, cnt1, cnt2);
	}

	// 서로 다른 숫자 3개 고르기
	solve(0);

	cout << ans;
}