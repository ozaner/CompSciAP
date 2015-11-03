package unit2;

public class WrapperTester {

	public static void main(String[] args) {
		Byte wrapByte;
		byte primbyte;
		
		wrapByte = new Byte((byte)127);  // constructor
		System.out.println("wrapByte = new Byte((byte)127) = " + wrapByte);
		
		wrapByte = 127;            // auto-boxing and implicit cast
//		wrapByte = (byte)127;      // auto-boxing and explicit cast
		System.out.println("wrapByte (auto-boxing) = " + wrapByte);
		
		primbyte = 127;            // normal case
		System.out.println("primbyte (127) = " + primbyte);
		
		primbyte = wrapByte.byteValue();
		System.out.println("primbyte (wrapByte.byteValue()) = " + primbyte);
		
		primbyte = wrapByte;       // auto-unboxing
		System.out.println("primbyte (auto-unboxing) = " + primbyte);
		
		// beware of undetected overflow
		wrapByte = (byte)255;      // auto-boxing and explicit cast
		System.out.println("wrapByte (undetected overflow) = " + wrapByte);
		
		// beware of undetected overflow
		primbyte = (byte)255;
		System.out.println("primbyte (undetected overflow) = " + primbyte);

		// compiler can detect overflow if no cast, since 255 is an int
//		primbyte = 255;
	}
}