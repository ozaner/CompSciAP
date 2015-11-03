package unit3.examples.animals;
/**
 * The two 'bark' methods illustrate method overloading.
 * @author Mark Jones
 *
 */
public class Dog {

	public Dog(){
		
	}
	
	public String bark() {
		return "woof";
	}
	
	public String bark(int n) {
		if (n <= 0) return "";
		String says = bark();
		for (int i=2; i<=n; i++)
			says += " " +  bark();
		return says;
	}
}
