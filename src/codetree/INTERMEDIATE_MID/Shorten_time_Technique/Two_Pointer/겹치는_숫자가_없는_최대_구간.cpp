#include <iostream>

using namespace std;

#define MAX_N 100000
#define MAX_X 100000
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
int arr[MAX_N];
bool visited[MAX_X + 1];

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int R = 0;
	int ans = 0;
	for (int L = 0; L < N; L++) {
		while (R < N && !visited[arr[R]]) {
			visited[arr[R]] = true;
			R++;
		}

		ans = MAX(ans, R - L);

		visited[arr[L]] = false;
	}

	cout << ans;
}