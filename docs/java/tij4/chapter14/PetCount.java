package chapter14;


import pets.*;

import java.util.HashMap;

import static util.Print.print;
import static util.Print.printnb;

public class PetCount {
    static class PetCounter extends HashMap<String, Integer> {
        public void count(String type) {
            Integer quantity = get(type);
            if (quantity == null) {
                put(type, 1);
            } else {
                put(type, quantity + 1);
            }

        }
    }

    public static void
    countPets(PetCreator creator) {
        PetCounter counter = new PetCounter();
        for (Pet pet : creator.createArray(20)) {
            // List each individual pet:
            printnb(pet.getClass().getSimpleName() + " ");
            if (pet instanceof Pet) {
                counter.count("pets.Pet");
            }

            if (pet instanceof Dog) {
                counter.count("pets.Dog");
            }

            if (pet instanceof Mutt) {
                counter.count("pets.Mutt");
            }

            if (pet instanceof Pug) {
                counter.count("pets.Pug");
            }

            if (pet instanceof Cat) {
                counter.count("pets.Cat");
            }

            if (pet instanceof Manx) {
                counter.count("pets.EgyptianMau");
            }

            if (pet instanceof Manx) {
                counter.count("pets.Manx");
            }

            if (pet instanceof Manx) {
                counter.count("pets.Cymric");
            }

            if (pet instanceof Rodent) {
                counter.count("pets.Rodent");
            }

            if (pet instanceof Rat) {
                counter.count("pets.Rat");
            }

            if (pet instanceof Mouse) {
                counter.count("pets.Mouse");
            }

            if (pet instanceof Hamster) {
                counter.count("pets.Hamster");
            }
        }
        // Show the counts:
        print();
        print(counter);
    }

    public static void main(String[] args) {
        countPets(new ForNameCreator());
    }
}