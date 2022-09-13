package swea.swea11707;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class UserSolution {

	int mstrlen(char[] a) {
		int len = 0;

		while (a[len] != '\0')
			len++;

		return len;
	}

	int mstrcmp(char[] a, char[] b) {
		int i;
		for (i = 0; a[i] != '\0'; i++) {
			if (a[i] != b[i])
				return i;
		}
		return -1;
	}

	void mstrcpy(char[] dest, char[] src) {
		int i = 0;
		while (src[i] != '\0') {
			dest[i] = src[i];
			i++;
		}
		dest[i] = src[i];
	}

	static int N;
	static Map historyMap;
	static Map correctDB;
	static Map mistakes;

	static class History {
		int timeStamp;
		char[] word;

		public History(int timeStamp, char[] word) {
			this.timeStamp = timeStamp;
			this.word = word;
		}
	}

	static class DB {
		char[] data;
		Set idSet;
		boolean isInDB;

		public DB(char[] word) {
			data = word;
			idSet = new HashSet<>();
			isInDB = false;
		}
	}

	static class Checked {
		Map wordMap;

		public Checked() {
			wordMap = new HashMap<Character[], Boolean>();
		}
	}

	int getHash(char[] word) {
		int hash = 5381;

		int i = 0;
		while (word[i] != '\0') {
			int c = (int) word[i];
			hash = ((hash << 5) + hash) + c;
			i++;
		}

		if (hash < 0)
			hash *= -1;

		return hash;
	}

	boolean checkWord(char[] word1, char[] word2) {
		int i;
		boolean flag = true; // 찾았는지 체크

		int size1 = mstrlen(word1);
		int size2 = mstrlen(word2);

		if (Math.abs(size1 - size2) > 1)
			return false;

		int index = mstrcmp(word1, word2);
		// 다른 경우
		if (index >= 0) {

			// 1. 다음거 검사하기
			for (i = index; word1[i] != '\0'; i++) {
				if (word1[i] != word2[i + 1]) {
					flag = false;
					break;
				}
			}

			if (flag)
				return true;
			flag = true;

			// 2. 한번 맞다고 해주기
			for (i = index + 1; word1[i] != '\0'; i++) {
				if (word1[i] != word2[i]) {
					flag = false;
					break;
				}
			}

			if (flag)
				return true;
			flag = true;
			
			// 3. 고대로 한번 넘어가기
			for (i = index + 1; word1[i] != '\0'; i++) {
				if (word1[i] != word2[i - 1]) {
					flag = false;
					break;
				}
			}
		}

		return flag;
	}

	void init(int n) {
		N = n;
		historyMap = new HashMap<Integer, History>();
		correctDB = new HashMap<Integer, Map<Integer, DB>>();
		mistakes = new HashMap<Integer, Checked>();
	}

	int search(int mId, int searchTimestamp, char[] searchWord, char[][] correctWord) {

		int count = 0;

		Map<Integer, DB> dbMap = (Map<Integer, DB>) correctDB.get(getHash(searchWord));
		if (dbMap == null) {
			dbMap = new HashMap<>();
		} else {
			// db에 오타 정보가 있는 경우
			Set<Integer> keySet = dbMap.keySet();
			for (int i : keySet) {
				DB db = dbMap.get(i);
				if (db.isInDB) {
					mstrcpy(correctWord[count++], db.data);
				}
			}
		}

		// 이전 데이터와 word 확인
		History history = (History) historyMap.get(mId);
		History newHistory = new History(searchTimestamp, searchWord);
		if (history == null) {
			historyMap.put(mId, newHistory);
		} else {

			// 10초 안에 재검색 한 경우
			if (searchTimestamp - history.timeStamp <= 10) {

				// 검사하기
				boolean result = checkWord(searchWord, history.word);

				// 오타가 맞을 경우
				if (result) {
					// dbMap에 searchWord를 키 값으로 DB 저장
					dbMap = (Map<Integer, DB>) correctDB.get(getHash(history.word));
					if (dbMap == null) {
						dbMap = new HashMap<>();
					}
					
					DB db = dbMap.get(getHash(searchWord));
					if (db == null) {
						DB newDB = new DB(searchWord);
						newDB.idSet.add(mId);
						dbMap.put(getHash(searchWord), newDB);
					} else {
						db.idSet.add(mId);
						if (db.idSet.size() >= 3) {
							db.isInDB = true;
						}
						dbMap.put(getHash(searchWord), db);
					}

					correctDB.put(getHash(history.word), dbMap);
				}
			}

			historyMap.put(mId, newHistory);
		}

		return count;
	}
}
