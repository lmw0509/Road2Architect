package chapter18;
// Creating a very large file using mapping.
// {RunByHand}

import java.nio.*;
import java.nio.channels.*;
import java.io.*;

import static util.Print.print;
import static util.Print.printnb;

public class LargeMappedFiles {
    // 128 MB
    static int length = 0x8FFFFFF;

    public static void main(String[] args) throws Exception {
        MappedByteBuffer out = new RandomAccessFile("src/tij4/chapter18/test.dat", "rw").getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++) {
            out.put((byte) 'x');
        }
        print("Finished writing");
        for (int i = length / 2; i < length / 2 + 6; i++) {
            printnb((char) out.get(i));
        }
    }
} ///:~
