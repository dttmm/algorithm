package programmers.lv0;

import java.util.*;

class Solution {

    Map<String, String> map;

    public String solution(String[] id_pw, String[][] db) {
        String answer = "";
        map = new HashMap();

        for (int i = 0; i < db.length; i++) {
            String id = db[i][0];
            String pw = db[i][1];

            map.put(id, pw);
        }

        String id = id_pw[0];
        String pw = id_pw[1];
        if (map.containsKey(id)) {
            if (map.get(id).equals(pw)) answer = "login";
            else answer = "wrong pw";
        } else answer = "fail";

        return answer;
    }
}