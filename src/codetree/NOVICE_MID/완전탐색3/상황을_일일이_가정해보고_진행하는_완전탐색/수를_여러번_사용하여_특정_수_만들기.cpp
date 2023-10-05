#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_A 1000

int A;
int B;
int C;
int ans;
bool visited[MAX_A + 1];

void solve(int sum) {
	// C�� �Ѵ� ���
	if (sum > C) return;

	// �̹� Ȯ���� ������ ���
	if (visited[sum]) return;
	visited[sum] = true;

	ans = max(ans, sum);

	// A ���
	solve(sum + A);
	// B ���
	solve(sum + B);
}

int main() {

	// �Է�
	cin >> A >> B >> C;

	solve(0);

	cout << ans;
}