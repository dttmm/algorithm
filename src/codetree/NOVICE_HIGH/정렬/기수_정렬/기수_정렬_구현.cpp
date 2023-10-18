#include <iostream>
#include <vector>

using namespace std;

#define MAX_N 100

int N;
vector<int> v;

// pos��° �ڸ��� ��ȯ
int getDigit(int n, int pos) {
	for (int i = 0; i < pos; i++) {
		n /= 10;
	}

	return n % 10;
}

// ��� ���� ����
void solve(int k) {
	for (int pos = 0; pos < k; pos++) {
		vector<int> newV[10];	// 0���� 9���� ���� �����ϴ� �� �ڷ��� �������

		// pos��° ���ڸ� �� �ڸ����� �����ϴ� ���Ϳ� ����
		for (int i = 0; i < v.size(); i++) {
			int n = v[i];
			int digit = getDigit(n, pos);
			newV[digit].push_back(n);
		}

		// 0���� 9���� ��ȸ�ϸ�
		// �� �ڸ����� �������� ���ĵ� ���� ���ʴ�� ����
		vector<int> sortedV;	
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < newV[i].size(); j++) {
				sortedV.push_back(newV[i][j]);
			}
		}

		// ����
		v = sortedV;
	}
}

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		v.push_back(n);
	}

	solve(7);

	for (int i = 0; i < N; i++) {
		cout << v[i] << " ";
	}
}