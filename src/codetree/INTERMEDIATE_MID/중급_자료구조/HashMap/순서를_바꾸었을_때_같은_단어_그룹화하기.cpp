#include <iostream>
#include <unordered_map>
#include <algorithm>
#include <string>
using namespace std;

#define MAX_M 1000
int N;
unordered_map<string, int> map;

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		char crr[MAX_M];
		string s;
		cin >> s;

		// ����
		sort(s.begin(), s.end());
		map[s]++;
	}

	// ���� ū �� ã��
	int maxVal = 0;
	for (auto& item : map) {
		maxVal = max(maxVal, item.second);
	}
	cout << maxVal;
}