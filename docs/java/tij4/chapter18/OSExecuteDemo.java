package chapter18;

import util.OSExecute;

public class OSExecuteDemo {
    public static void main(String[] args) {
      //javap ��������
        OSExecute.command("javap OSExecuteDemo");
    }
} /* Output:
Compiled from "OSExecuteDemo.java"
public class OSExecuteDemo extends java.lang.Object{
    public OSExecuteDemo();
    public static void main(java.lang.String[]);
}
*/
