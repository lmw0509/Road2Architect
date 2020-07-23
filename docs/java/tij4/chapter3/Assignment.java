package chapter3;

import static util.Print.print;

class Tank {
    int level;
}

public class Assignment {
    public static void main(String[] args) {
        Tank t1 = new Tank();
        Tank t2 = new Tank();
        t1.level = 9;
        t2.level = 47;
        print("1: t1.level: " + t1.level +
                ", t2.level: " + t2.level);
        t1 = t2;
        print("2: t1.level: " + t1.level +
                ", t2.level: " + t2.level);
        //t1和t2包含相同的引用，指向相同的对象，所以修改t1的时候也修改了t2
        t1.level = 27;
        print("3: t1.level: " + t1.level +
                ", t2.level: " + t2.level);
    }
}
