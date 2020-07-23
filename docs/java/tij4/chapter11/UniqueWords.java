package chapter11;

import util.TextFile;

import java.util.*;


public class UniqueWords {
    public static void main(String[] args) {
        Set<String> words = new TreeSet<String>(
                new TextFile("src/tij4/chapter11/SetOperations.java", "\\W+"));
        System.out.println(words);
    }
}
