//: typeinfo/pets/ForNameCreator.java
package pets;
import java.util.*;

public class ForNameCreator extends PetCreator {
  private static List<Class<? extends Pet>> types =
    new ArrayList<Class<? extends Pet>>();
  // Types that you want to be randomly created:
  private static String[] typeNames = {
    "pets.Mutt",
    "pets.Pug",
    "pets.EgyptianMau",
    "pets.Manx",
    "pets.Cymric",
    "pets.Rat",
    "pets.Mouse",
    "pets.Hamster"
  };	
  @SuppressWarnings("unchecked")
  private static void loader() {
    try {
      for(String name : typeNames){
          types.add(
                  (Class<? extends Pet>)Class.forName(name));
      }
    } catch(ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  static { loader(); }
  @Override
  public List<Class<? extends Pet>> types() {return types;}
} ///:~
