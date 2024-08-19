#include <iostream>
#include <fstream>

/*
* in 2338 out 
* dp
* ���ڸ��� ���� �� r,g,b�� �ϳ��� ��󰡸�
* ������ �̿��ؼ� ��� ����� ���� ���Ѵٸ�
* 3^50������ ����� �� �߻�
* �߰��� ����ġ�⸦ �ϴ��� ���� ������ ���ɰ� ����
* 
* �ٷ� dp�� �����Ϸ� ������
* ���ڸ� �ϳ��� �����ذ��鼭
* ���ڿ��� r,g,b�� ������ ���� �ּҰ��� �����س���
* ������ ���ڱ��� ����� ���� ���ڵ� �߿��� ��� r,g,b�� �ϳ����� ������ �־��
* r,g,b�� ������ �ٸ� ���ڷ� �ű� �� �����Ƿ�
* r,g,b�� ��� �ϳ��� ���õǾ��ִ��� ���¸� �ϳ��� �߰��ؼ� 3���� dp�� Ǯ��
*/

using namespace std;

#define MAX_N 50
#define MIN(a, b) ((a) < (b) ? (a) : (b));
#define INF 2000000000

int N;
int arr[MAX_N + 1][3];	// i��° �ڽ��� j�� ���� j -> 0:r, 1: g, 2: b
int d[MAX_N + 1][3][8];	// i��° �ڽ����� j���� ���������� �ּҰ�, k�� ���ݱ��� � ���� ������� ���� ǥ����
// j -> 0:r, 1: g, 2: b
// k -> 1: r�� ����, 2: g�� ����, 3: r,g�� ����, 4: b�� ����... 7: r,g,b�� ����

// dp�迭 �ʱ�ȭ
void init() {
	for (int i = 0; i <= N; i++) {
		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 8; k++) {
				d[i][j][k] = INF;
			}
		}
	}
}

int solve() {
	d[0][0][0] = 0;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 8; k++) {
				// ������� �Ͼ �� ���� ��Ȳ�� �о�
				if (d[i][j][k] == INF) continue;

				// ���� ���ڿ��� r�� ������ ���
				d[i + 1][0][k | (1 << 0)] = MIN(d[i + 1][0][k | (1 << 0)], d[i][j][k] + arr[i + 1][1] + arr[i + 1][2]);

				// ���� ���ڿ��� g�� ������ ���
				d[i + 1][1][k | (1 << 1)] = MIN(d[i + 1][1][k | (1 << 1)], d[i][j][k] + arr[i + 1][2] + arr[i + 1][0]);

				// ���� ���ڿ��� b�� ������ ���
				d[i + 1][2][k | (1 << 2)] = MIN(d[i + 1][2][k | (1 << 2)], d[i][j][k] + arr[i + 1][0] + arr[i + 1][1]);
			}
		}
	}

	// ������ ����(i=N)���� r,g,b�� ��� ���� ���(k=7)�� �ּҰ� ��ȯ
	int ret = INF;
	for (int j = 0; j < 3; j++) {
		ret = MIN(ret, d[N][j][7]);
	}

	return ret;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("src/swea/input.txt", "r", stdin);

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> N;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				cin >> arr[i][j];
			}
		}

		// �ʱ�ȭ
		init();

		int ret = solve();

		if (ret == INF) cout << "#" << tc << " -1\n";
		else cout << "#" << tc << " " << ret << "\n";
	}
}