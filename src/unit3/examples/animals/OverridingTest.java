package unit3.examples.animals;
/**
 * Illustrate how overriding works in Java.
 * @author Mark Jones
 *
 */
public class OverridingTest {

	public static void main(String[] args) {
		
		Dog dog = new Dog();
		System.out.println("dog says " + dog.bark());
		System.out.println("dog says (repeatedly) " + dog.bark(3));
		System.out.println();
		
		Dog snoopy = new Beagle();
		System.out.println("snoopy says " + snoopy.bark());
		System.out.println("snoopy (as Dog) says " + ((Dog)snoopy).bark());
		System.out.println("snoopy (as Beagle) says " + ((Beagle)snoopy).bark());
		System.out.println("snoopy says (repeatedly) " + snoopy.bark(3));
		System.out.println();
		
		Dog lassie = new Collie();
		System.out.println("lassie says " + lassie.bark());
		System.out.println("lassie (as Dog) says " + ((Dog)lassie).bark());		
		System.out.println("lassie (as Collie) says " + ((Collie)lassie).bark());
		System.out.println("lassie says (repeatedly) " + lassie.bark(3));
	}
}
