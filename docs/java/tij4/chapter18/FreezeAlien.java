package chapter18;

import java.io.*;

public class FreezeAlien {
    public static void main(String[] args) throws Exception {
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("src/tij4/chapter18/X.file"));
        Alien quellek = new Alien();
        out.writeObject(quellek);
    }
}
