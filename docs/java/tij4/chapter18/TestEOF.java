package chapter18;

import java.io.*;

public class TestEOF {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream("src/tij4/chapter18/TestEOF.java")));
        while (in.available() != 0) {
            System.out.print((char) in.readByte());
        }
    }
}
