#include <iostream>
#include <unordered_set>

using namespace std;

int A;
int B;
unordered_set<int> setA;

int main() {

	// ют╥б
	cin >> A >> B;
	for (int i = 0; i < A; i++) {
		int n;
		cin >> n;

		setA.insert(n);
	}

	int total = 0;
	for (int i = 0; i < B; i++) {
		int n;
		cin >> n;

		if (setA.find(n) != setA.end()) total++;
	}

	cout << A + B - 2 * total;
}