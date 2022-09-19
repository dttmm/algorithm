package swea.swea13461;

import java.util.HashMap;
import java.util.Map;

class UserSolution {
	final int MAX_N = 10000;
	final int MAX_M = 10;
	static int N;
	static int M;
	static char[][][] images;
	static Map<String, Image> map;
	static int[] tr;
	static int answerId;

	private static class Image {
		int id;
		String key;

		public Image(int id, String key) {
			this.id = id;
			this.key = key;
		}
	}

	void init(int N, int M, char mImageList[][][]) {
		this.N = N;
		this.M = M;
		this.images = mImageList;
		map = new HashMap<String, Image>();

		for (int k = 0; k < N; k++) {
			String s = "";
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					char c = mImageList[k][i][j];

					s += c;
				}
			}

			boolean isAlready = map.containsKey(s);
			if (!isAlready) {
				Image newImage = new Image(k + 1, s);
				map.put(s, newImage);
			}
		}
	}

	int findImage(char mImage[][]) {
		answerId = MAX_N + 1;

		String s = "";
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				char c = mImage[i][j];

				s += c;
			}
		}

		boolean isAlready = map.containsKey(s);

		if (isAlready) {
			Image image = map.get(s);
			answerId = image.id;
		} else {
			tr = new int[1];
			per(0, 0, 1, s);

			// 1개 불일치 하는 것이 없는 경우
			if (answerId > MAX_N) {
				tr = new int[2];
				per(0, 0, 2, s);
			}
		}

		return answerId;
	}

	static void per(int k, int start, int R, String s) {
		if (k == R) {
			char[] cArray = s.toCharArray();
			for (int i = 0; i < R; i++) {
				int index = tr[i];

				char c = cArray[index];
				if (c == 1) {
					cArray[index] = 0;
				} else {
					cArray[index] = 1;
				}
			}
			s = String.valueOf(cArray);

			boolean isAlready = map.containsKey(s);

			if (isAlready) {
				Image image = map.get(s);
				int newId = image.id;
				if (newId < answerId) {
					answerId = newId;
				}
			}
		} else {
			for (int i = start; i < M * M; i++) {
				tr[k] = i;
				per(k + 1, start + 1, R, s);
			}
		}
	}
}
