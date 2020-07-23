package chapter15;

import pets.Person;
import pets.Pet;

import java.util.List;
import java.util.Map;

public class LimitsOfInference {
    static void f(Map<Person, List<? extends Pet>> petPeople) {
    }

    public static void main(String[] args) {
        // Does not compile
        // f(New.map());
    }
}