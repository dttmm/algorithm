#include <iostream>
#include <unordered_map>
#include <string>
using namespace std;

int N, M;
unordered_map<string, int> mStoI;
unordered_map<int, string> mItoS;

int main() {
	cin >> N >> M;

	int index = 1;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		mItoS[index] = s;
		mStoI[s] = index++;
	}

	for (int i = 0; i < M; i++) {
		string s;
		cin >> s;

		if (s[0] >= '0' && s[0] <= '9') {
			int n = stoi(s);
			cout << mItoS[n] << "\n";
		}
		else cout << mStoI[s] << "\n";
	}
}