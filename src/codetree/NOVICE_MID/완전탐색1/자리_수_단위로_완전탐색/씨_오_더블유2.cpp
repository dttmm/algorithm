#include <iostream>
#include <string>

using namespace std;

int N;

int main() {


	string s;

	cin >> N;
	cin >> s;

	int cntC = 0;	// C�� ����
	int cntO = 0;	// C �ڿ� �ִ� O�� ����
	int cntW = 0;	// CO �ڿ� �ִ� W�� ����

	for (int i = 0; i < N; i++) {
		char c = s[i];

		if (c == 'C') cntC++;
		else if (c == 'O')cntO += cntC;
		else cntW += cntO;
	}

	cout << cntW;
}