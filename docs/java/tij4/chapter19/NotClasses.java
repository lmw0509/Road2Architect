package chapter19;


import static util.Print.print;

enum LikeClasses {
    WINKEN {
        @Override
        void behavior() {
            print("Behavior1");
        }
    },
    BLINKEN {
        @Override
        void behavior() {
            print("Behavior2");
        }
    },
    NOD {
        @Override
        void behavior() {
            print("Behavior3");
        }
    };

    abstract void behavior();
}

public class NotClasses {
    // void f1(LikeClasses.WINKEN instance) {} // Nope
} /* Output:
Compiled from "NotClasses.java"
abstract class LikeClasses extends java.lang.Enum{
public static final LikeClasses WINKEN;

public static final LikeClasses BLINKEN;

public static final LikeClasses NOD;
...
*///:~
