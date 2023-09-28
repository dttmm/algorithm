#include <iostream>
#include <string>

using namespace std;

int N;

int main() {


	string s;

	cin >> N;
	cin >> s;

	int cntC = 0;	// C의 개수
	int cntO = 0;	// C 뒤에 있는 O의 개수
	int cntW = 0;	// CO 뒤에 있는 W의 개수

	for (int i = 0; i < N; i++) {
		char c = s[i];

		if (c == 'C') cntC++;
		else if (c == 'O')cntO += cntC;
		else cntW += cntO;
	}

	cout << cntW;
}