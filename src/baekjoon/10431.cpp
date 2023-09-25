#include <fstream>
#include <iostream>

/*
* 설계 7분 구현+삽질 9분
* 정렬
* 문제에서는 버블정렬을 하고 있고
* 버블정렬에서 스왑이 일어난 횟수를 구하면 됨
* 
* 아오 C++왜이렇게 꾸졌어
* ide에서 되는데 채점환경에서는 안되면 우짜
* 배열 초기화 fill로 해야되는거 불편하네
*/
using namespace std;

int T;
int N = 20;
int arr[20];

// 스왑
void swap(int& a, int& b) {
	int temp = a;
	a = b;
	b = temp;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/10431.txt", "r", stdin);

	cin >> T;

	for (int t = 1; t <= T; t++) {
		int n;
		cin >> n;

		// 배열 초기화
		fill(arr, arr + N, 0);

		// 입력 받기
		for (int i = 0; i < N; i++) {
			cin >> arr[i];
		}

		// 버블 정렬
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N - i; j++) {
				if (arr[j] < arr[j - 1]) {
					swap(arr[j], arr[j - 1]);
					cnt++;
				}
			}
		}

		cout << t << " " << cnt << "\n";
	}
}