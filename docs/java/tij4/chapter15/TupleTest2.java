package chapter15;

import util.FiveTuple;
import util.FourTuple;
import util.ThreeTuple;
import util.TwoTuple;

import static util.Tuple.tuple;

public class TupleTest2 {
    static TwoTuple<String, Integer> f() {
        return tuple("hi", 47);
    }

    /**
     * ������û�й���f2()�ľ�����Ϣ����Ϊ���Ǳ�û�н��䷵��ֵ��Ϊ����������ʹ�ã���ĳ�������ϣ���������ת��Ϊһ���ǲ�������TwoTuple��
     * Ȼ�������ͼ��f2()�ķ���ֵת��Ϊ��������TwoTuple���������ͻᷢ�����档
     *
     * @return
     */
    static TwoTuple f2() {
        return tuple("hi", 47);
    }

    static ThreeTuple<Amphibian, String, Integer> g() {
        return tuple(new Amphibian(), "hi", 47);
    }

    static FourTuple<Vehicle, Amphibian, String, Integer> h() {
        return tuple(new Vehicle(), new Amphibian(), "hi", 47);
    }

    static FiveTuple<Vehicle, Amphibian, String, Integer, Double> k() {
        return tuple(new Vehicle(), new Amphibian(), "hi", 47, 11.1);
    }

    public static void main(String[] args) {
        TwoTuple<String, Integer> ttsi = f();
        System.out.println(ttsi);
        System.out.println(f2());
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
    }
}