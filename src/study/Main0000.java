package study;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main0000 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    }
}
