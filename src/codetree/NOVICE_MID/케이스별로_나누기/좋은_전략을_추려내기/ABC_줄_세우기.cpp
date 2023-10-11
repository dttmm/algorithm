#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

#define MAX_N 26

int N;
int arr[MAX_N];	// 각 위치에 알파벳 정보 저장

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		char c;
		cin >> c;

		arr[i] = c;
	}

	// 내 앞에 나보다 큰 알파벳 있으면 그만큼 자리 바꿔줘야됨
	int ans = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < i; j++) {
			if (arr[i] < arr[j]) ans++;
		}
	}

	cout << ans;
}